package com.base.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import biz.entity.dict.DataDictionary;
import biz.entity.people.People;

import com.base.annotation.Auth;
import com.base.interceptor.PermissionException;
import com.base.util.ClassReflectUtil;
import com.base.util.Constant;
import com.base.util.FileUtil;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.util.edit.MyEditor;

public class BaseAction extends MemCachedUtil {

	public final static String SUCCESS = "success";

	public final static String MSG = "msg";

	public final static String NEXT_STEP = "nextStep";

	public final static String TO_URL = "toUrl";

	public final static String DATA = "data";

	public final static String LOGOUT_FLAG = "logoutFlag";

	public final static String MSG_SAVE_SUCCESS = "保存成功";

	public final static String MSG_DELETE_SUCCESS = "删除成功";

	public final static String MSG_PERMISSOIN_DENNIED = "对不起，您无权限进行此项操作";

	public final static String MSG_UPDATE_SUCCESS = "更新成功";
	
	public final static String MSG_PUBLISH_SUCCESS = "发布成功";

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(int.class, new MyEditor());
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/error")
	public ModelAndView error(String msgCode, String nextStepCode, String toUrl, HttpServletRequest request) {
		Map<String, Object> context = new HashMap<String, Object>();
		if ("OFFLINE".equals(msgCode)) {
			context.put(MSG, "对不起，请先登陆！错误代码：001");
		} else if ("NOPERMISSION".equals(msgCode)) {
			context.put(MSG, "对不起，您没有权限操作！");
		} else if ("NORESULT".equals(msgCode)) {
			context.put(MSG, "对不起，没有您要找的记录！");
		} else if ("EXCEPTION".equals(msgCode)) {
			context.put(MSG, "系统异常");
		} else {
			context.put(MSG, "未知错误");
		}

		context.put(TO_URL, toUrl);

		context.put(NEXT_STEP, nextStepCode);

		return forword("error", context);
	}

	/**
	 * 所有ActionMap 统一从这里获取
	 * 
	 * @return
	 */
	public Map<String, Object> getRootMap() {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		// 添加url到 Map中
		// rootMap.putAll(URLUtils.getUrlMap());

		// Cache中取得所有数据字典内容
		Map<String, List<DataDictionary>> dataDict = this.getDataDictionaryByTypeFromCache();
		Set<Entry<String, List<DataDictionary>>> entrySet = dataDict.entrySet();
		Iterator<Entry<String, List<DataDictionary>>> items = entrySet.iterator();
		while (items.hasNext()) {
			Entry<String, List<DataDictionary>> entry = items.next();
			rootMap.put(entry.getKey(), entry.getValue());
		}

		// Cache中取得所有数据字典内容
		Map<String, Map<String, String>> dataDictMap = this.getDataDictionaryMapByTypeFromCache();
		Set<Entry<String, Map<String, String>>> entrySetMap = dataDictMap.entrySet();
		Iterator<Entry<String, Map<String, String>>> itemsMap = entrySetMap.iterator();
		while (itemsMap.hasNext()) {
			Entry<String, Map<String, String>> entryMap = itemsMap.next();
			rootMap.put(entryMap.getKey() + "_MAP", entryMap.getValue());
		}
		
		rootMap.put("COMPANY_OBJ", this.getCompanyFromCache());

		return rootMap;
	}

	/**
	 * 根据当前Controller或Action类名，得到实体对象名称
	 * 
	 * @return
	 */
	protected String getEntityName() {
		String entityName = this.getClass().getSimpleName();
		if (entityName.endsWith("Action")) {
			entityName = entityName.substring(0, entityName.length() - 6);
		} else if (entityName.endsWith("Controller")) {
			entityName = entityName.substring(0, entityName.length() - 10);
		}

		return ClassReflectUtil.convertToTableName(entityName).toUpperCase();
	}

	public ModelAndView forword(String viewName, Map context) {
		return new ModelAndView(viewName, context);
	}

	/**
	 * 将指定对象内属性的值从off/on值转换为Y/N值
	 * 
	 * @param o
	 * @param fields
	 */
	protected void convertOnOff2YN(Object o, List<String> fields) {
		try {
			for (int i = 0; i < fields.size(); i++) {
				Object val = ClassReflectUtil.getFieldValue(o, fields.get(i));
				if (val != null && val.toString().equalsIgnoreCase("on")) {
					ClassReflectUtil.setFieldValue(o, fields.get(i), "Y");
				} else {
					ClassReflectUtil.setFieldValue(o, fields.get(i), "N");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 将指定对象内属性的值从Y/N值转换为off/on值
	 * 
	 * @param o
	 * @param fields
	 */
	protected void convertYN2OnOff(Object o, List<String> fields) {
		try {
			for (int i = 0; i < fields.size(); i++) {
				Object val = ClassReflectUtil.getFieldValue(o, fields.get(i));
				if (val != null && val.toString().equalsIgnoreCase("Y")) {
					ClassReflectUtil.setFieldValue(o, fields.get(i), "on");
				} else {
					ClassReflectUtil.setFieldValue(o, fields.get(i), "off");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response, Object data, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(DATA, data);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	@RequestMapping("/clearHTMLCache")
	public void clearHTMLCache(HttpServletResponse response, HttpServletRequest request) throws PermissionException {
		if (SessionUtils.isAdmin()) {
			SessionUtils.clearHTMLCache(Constant.HTML_ROOT, null, null);
			this.sendSuccessMessage(response, MSG_DELETE_SUCCESS);
		} else {
			this.sendFailureMessage(response, MSG_PERMISSOIN_DENNIED);
			// throw new PermissionException();
		}

	}

	@RequestMapping("/publishToClient")
	public void publishToClient(HttpServletResponse response, HttpServletRequest request)
			throws PermissionException, IOException {
		if (SessionUtils.isAdmin()) {
			// 清除前段静态化和图片文件
			FileUtils.deleteDirectory(new File(Constant.PUBLISH_CONTEXT_PATH +"/" +Constant.HTML_ROOT));
			FileUtils.deleteDirectory(new File(Constant.PUBLISH_CONTEXT_PATH +"/" +Constant.UPLOAD_FOLDER));
			FileUtils.deleteDirectory(new File(Constant.PUBLISH_CONTEXT_PATH +"/" +Constant.UPLOAD_FOLDER_TMP));
			
			// 将后端文件COPY到前端显示
			FileUtils.copyDirectory(new File(SessionUtils.getServletContextPath() + "/" + Constant.UPLOAD_FOLDER),
					new File(Constant.PUBLISH_CONTEXT_PATH + "/" + Constant.UPLOAD_FOLDER));
			this.sendSuccessMessage(response, MSG_PUBLISH_SUCCESS);
		} else {
			this.sendFailureMessage(response, MSG_PERMISSOIN_DENNIED);
			// throw new PermissionException();
		}
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response, Object data, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(DATA, data);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}
}

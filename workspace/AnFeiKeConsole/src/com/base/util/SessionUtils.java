package com.base.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import biz.entity.people.People;

import com.base.util.Constant.SuperAdmin;

/**
 * 
 * Cookie 工具类
 *
 */
public final class SessionUtils {

	protected static final Logger log = Logger.getLogger(SessionUtils.class);

	private static final String SESSION_USER = "SESSION_USER";

	private static final String SESSION_VALIDATECODE = "SESSION_VALIDATECODE";// 验证码

	private static final String SESSION_ACCESS_URLS = "SESSION_ACCESS_URLS"; // 系统能够访问的URL

	private static final String SESSION_MENUBTN_MAP = "SESSION_MENUBTN_MAP"; // 系统菜单按钮

	private static final String SESSION_LANGUAGE_CD = "SESSION_LANGUAGE_CD"; // 语言环境

	private static final String SESSION_CURRENT_URI = "SESSION_CURRENT_URI"; // 语言环境

	/**
	 * 设置当前访问的URI
	 * 
	 * @param uri
	 */
	public static void setCurrentUri(String uri) {
		setAttr(SESSION_CURRENT_URI, uri);
	}

	/**
	 * 得到当前访问的URI
	 * 
	 * @param uri
	 */
	public static String getCurrentUri() {
		return String.valueOf(getAttr(SESSION_CURRENT_URI));
	}

	public static void setCurrentlanguageCd(HttpServletRequest request, Object value) {
		setAttr(request, SESSION_LANGUAGE_CD, value);
	}

	/**
	 * 获取当前SESSION id
	 * 
	 * @return
	 */
	public static String getSessionId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getSession().getId();
	}
	
	public static void clearHTMLCache(String rootPath, String htmlFld, String fileType, Set<String> ids) {
		log.debug("HTML FOLDER:" + htmlFld);
		log.debug("HTML FILE TYPE:" + fileType);
		log.debug("HTML IDS:" + ids);
		String htmlSubFld = htmlFld.toLowerCase();
		String baseFld = rootPath + "/" + Constant.HTML_ROOT;
		if (Constant.HTML_ROOT.equalsIgnoreCase(htmlSubFld)) {
			boolean result = FileUtils.deleteQuietly(new File(baseFld));
			log.debug("Delete Folder:" + baseFld + ">>" + result);
		} else {
			File fileFld = new File(baseFld + "/" + htmlSubFld);
			if (StringUtils.isNotBlank(fileType)) {
				// 若指定了文件类型，则针对性删除，否则全部删除
				File[] subFiles = fileFld.listFiles();

				if (subFiles != null) {
					for (File f : subFiles) {
						String fileName = f.getName();

						// 列表数据（只要数据存在更新，则即应删除）
						if (fileName.startsWith("list")) {
							boolean result = f.delete();
							log.debug("Delete File:" + f.getName() + ">>" + result);
						}
						// 编辑数据，包括修改，查看,列表也将清除
						else if (Constant.HTML_FILE_TYPE_EDIT.equals(fileType)
								&& (fileName.startsWith("edit") || fileName.startsWith("view"))) {

							if (ids != null && !ids.isEmpty()) {// 指定了ID，则只删除ID对应的数据
								String[] nameStr = fileName.split("_");
								if (nameStr.length >= 2) {
									String idInside = nameStr[1];
									if (ids.contains(idInside)) {
										boolean result = f.delete();
										log.debug("Delete File:" + f.getName() + ">>" + result);
									}
								}
							} else {
								boolean result = f.delete();
								log.debug("Delete File:" + f.getName() + ">>" + result);
							}
						}
					}
				}
			} else {
				// 全部删除
				boolean result = FileUtils.deleteQuietly(fileFld);
				log.debug("Delete Folder:" + fileFld.getAbsolutePath() + ">>" + result);
			}
		}
	}

	/**
	 * 根据类型及ID确认清除哪些HTML静态化页面
	 * 
	 * @param type
	 * @param id
	 */
	public static void clearHTMLCache(String htmlFld, String fileType, Set<String> ids) {
		clearHTMLCache(SessionUtils.getServletContextPath(), htmlFld, fileType, ids);
	}

	/**
	 * 得到请求中所有参数并ENCODE
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncodeParameters(HttpServletRequest req, boolean needEncode)
			throws UnsupportedEncodingException {
		Enumeration<String> parameterNames = req.getParameterNames();
		List<String> list = Collections.list(parameterNames); // create list
																// from
																// enumeration
		Collections.sort(list);
		parameterNames = Collections.enumeration(list);

		StringBuffer buf = new StringBuffer();
		int k = 0;
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				if (i == 0) {
					if (k == 0) {
						buf.append("?");
					} else {
						buf.append("&");
					}

					buf.append(paramName + "=" + paramValue);
				} else {
					buf.append("&" + paramName + "=" + paramValue);
				}

				// log.debug("request parameter:" + paramName + "=" +
				// paramValue);
			}
			k++;
		}

		if (needEncode) {
			return URLEncoder.encode(buf.toString(), "UTF-8");
		} else {
			return buf.toString();
		}

	}

	/**
	 * 获取当前上下文绝对路径
	 * 
	 * @return
	 */
	public static String getServletContextPath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 设置session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttr(String key, Object value) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		request.getSession(true).setAttribute(key, value);
	}

	/**
	 * 设置session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttr(HttpServletRequest request, String key, Object value) {
		request.getSession(true).setAttribute(key, value);
	}

	/**
	 * 获取session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttr(HttpServletRequest request, String key) {
		return request.getSession(true).getAttribute(key);
	}

	/**
	 * 获取session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttr(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		return request.getSession(true).getAttribute(key);
	}

	/**
	 * 删除Session值
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAttr(HttpServletRequest request, String key) {
		request.getSession(true).removeAttribute(key);
	}

	/**
	 * 设置用户信息 到session
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, People user) {
		request.getSession(true).setAttribute(SESSION_USER, user);
	}

	/**
	 * 设置用户信息 到session
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(People user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		request.getSession(true).setAttribute(SESSION_USER, user);
	}

	/**
	 * 从session中获取用户信息
	 * 
	 * @param request
	 * @return People
	 */
	public static People getUser(HttpServletRequest request) {
		return (People) request.getSession(true).getAttribute(SESSION_USER);
	}

	/**
	 * 从session中获取用户信息
	 * 
	 * @param request
	 * @return People
	 */
	public static People getUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return (People) request.getSession().getAttribute(SESSION_USER);
	}

	/**
	 * 从session中获取用户信息
	 * 
	 * @param request
	 * @return People
	 */
	public static String getUserId(HttpServletRequest request) {
		People user = getUser(request);
		if (user != null) {
			return user.getId().toString();
		}
		return null;
	}

	/**
	 * 从session中获取用户信息
	 * 
	 * @param request
	 * @return People
	 */
	public static void removeUser(HttpServletRequest request) {
		removeAttr(request, SESSION_USER);
	}

	/**
	 * 设置验证码 到session
	 * 
	 * @param request
	 * @param user
	 */
	public static void setValidateCode(HttpServletRequest request, String validateCode) {
		request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
	}

	/**
	 * 从session中获取验证码
	 * 
	 * @param request
	 * @return People
	 */
	public static String getValidateCode(HttpServletRequest request) {
		return (String) request.getSession(true).getAttribute(SESSION_VALIDATECODE);
	}

	/**
	 * 从session中获删除验证码
	 * 
	 * @param request
	 * @return People
	 */
	public static void removeValidateCode(HttpServletRequest request) {
		removeAttr(request, SESSION_VALIDATECODE);
	}

	/**
	 * 判断当前登录用户是否超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAdmin(HttpServletRequest request) { // 判断登录用户是否超级管理员
		People user = getUser(request);
		if (user == null || !user.getIsAdmin().equals(SuperAdmin.YES.key)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断当前登录用户是否超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAdmin() { // 判断登录用户是否超级管理员
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (sra == null) {
			return false;
		}

		HttpServletRequest request = sra.getRequest();

		People user = getUser(request);
		if (user == null || !user.getIsAdmin().equals(SuperAdmin.YES.key)) {
			return false;
		}

		return true;
	}

	/**
	 * 判断当前登录用户是否超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static void setAccessUrl(HttpServletRequest request, List<String> accessUrls) { // 判断登录用户是否超级管理员
		setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	}

	/**
	 * 判断URL是否可访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAccessUrl(HttpServletRequest request, String url) {
		List<String> accessUrls = (List) getAttr(request, SESSION_ACCESS_URLS);
		if (accessUrls == null || accessUrls.isEmpty() || !accessUrls.contains(url)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断URL是否可访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAccessUrl(String url) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		List<String> accessUrls = (List) getAttr(request, SESSION_ACCESS_URLS);
		if (accessUrls == null || accessUrls.isEmpty() || !accessUrls.contains(url)) {
			return false;
		}
		return true;
	}

	/**
	 * 设置菜单按钮
	 * 
	 * @param request
	 * @param btnMap
	 */
	public static void setMemuBtnMap(HttpServletRequest request, Map<String, List> btnMap) { // 判断登录用户是否超级管理员
		setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	}

	/**
	 * 获取菜单按钮
	 * 
	 * @param request
	 * @param btnMap
	 */
	public static List<String> getMemuBtnListVal(HttpServletRequest request, String menuUri) { // 判断登录用户是否超级管理员
		Map btnMap = (Map) getAttr(request, SESSION_MENUBTN_MAP);
		if (btnMap == null || btnMap.isEmpty()) {
			return null;
		}
		return (List<String>) btnMap.get(menuUri);
	}

	// private static final String SESSION_ACCESS_URLS = "session_access_urls";
	// //系统能够访问的URL
	//
	//
	// private static final String SESSION_MENUBTN_MAP = "session_menubtn_map";
	// //系统菜单按钮

}
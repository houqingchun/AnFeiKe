package biz.controller.evaluations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import biz.entity.evaluations.Evaluations;
import biz.page.evaluations.EvaluationsPage;
import biz.page.members.MembersPage;
import biz.service.evaluations.EvaluationsService;

import com.base.annotation.Auth;
import com.base.util.HtmlUtil;
import com.base.web.BaseAction;
 
/**
 * 
 * <br>
 * <b>功能：</b>EvaluationsController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */ 
@Controller
@RequestMapping("/evaluations") 
public class EvaluationsController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(EvaluationsController.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private EvaluationsService<Evaluations> evaluationsService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(EvaluationsPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/evaluations/evaluations",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(EvaluationsPage page,HttpServletResponse response) throws Exception{
		List<Evaluations> dataList = evaluationsService.queryByList(page);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 获取数据 
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/loadData")
	public void loadData(Integer offset, Integer limit, String search, String mainId, String mainType,
			HttpServletResponse response) throws Exception {
		EvaluationsPage page = new EvaluationsPage();
		page.setMainType(mainType);
		page.setMainId(mainId);
		page.setRows(limit);
		page.setSort("createTime");
		page.setOrder("desc");
		page.setPage((int) offset / limit + 1);
		List<Evaluations> dataList = evaluationsService.queryByList(page);
						
		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		

		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 获取登录状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/queryAvgAssessmentRs")
	public void queryAvgAssessmentRs(MembersPage page, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(SUCCESS, true);
		
		context.put("data", Math.round(this.evaluationsService.queryAvgAssessmentRs(page)));
		HtmlUtil.writerJson(response, context);
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/save")
	public void save(Evaluations entity,String[] typeIds,HttpServletRequest request, HttpServletResponse response) throws Exception{
		entity.setIp(request.getRemoteAddr());
		if(entity.getId()==null||StringUtils.isBlank(entity.getId().toString())){
			evaluationsService.add(entity);
		}else{
			evaluationsService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	/**
	 * 根据ID获取对象信息
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/edit")
	public ModelAndView edit(EvaluationsPage page, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();

		//若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Evaluations entity = evaluationsService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}
			context.put("data", entity);
		}else{
			context.put("data", page);
		}

		return forword("biz/evaluations/evaluations-edit",context);
	}
	
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		Evaluations entity  = evaluationsService.queryById(id);
		if(entity  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		evaluationsService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}

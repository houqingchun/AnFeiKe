package biz.controller.club;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.base.web.BaseAction;
import com.base.annotation.Auth;
import com.base.util.HtmlUtil;
import biz.entity.club.Club;
import biz.page.club.ClubPage;
import biz.service.club.ClubService;
 
/**
 * 
 * <br>
 * <b>功能：</b>ClubController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */ 
@Controller
@RequestMapping("/club") 
public class ClubController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(ClubController.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ClubService<Club> clubService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(ClubPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/club/club",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(ClubPage page,HttpServletResponse response) throws Exception{
		List<Club> dataList = clubService.queryByList(page);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(Club entity, String[] imageFilePaths,
			HttpServletResponse response) throws Exception {
		// 根据ID查询是否已经存在此数据，不存在，则按新数据处理是
		clubService.update(entity);

		this.sendSuccessMessage(response, entity, MSG_SAVE_SUCCESS);
	}

	@RequestMapping("/getId")
	public ModelAndView getId(String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = this.getRootMap();
		Club entity = clubService.queryById(id);
		if (entity == null) {
			return this.error("NORESULT", null, null, request);
		}

		context.put("data", entity);
		return forword("biz/club/club-edit", context);
	}
	

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/service")
	public ModelAndView service(String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		Club entity = clubService.queryById("A1000");
		if (entity == null) {
			return this.error("NORESULT", null, null, request);
		}

		context.put("data", entity);
		return forword("biz/club/club-service", context);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(ClubPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();
		page = new ClubPage();
		List<Club> cpList = this.clubService.queryByList(page);
		if (cpList.size() == 1){
			context.put("data", cpList.get(0));
		}else{
			return this.error("NORESULT", null, null, request);
		}

		return forword("biz/club/club-edit", context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		clubService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}

package biz.controller.recruit;

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

import biz.entity.recruit.Recruit;
import biz.page.recruit.RecruitPage;
import biz.service.recruit.RecruitService;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;
import com.base.web.MemCachedUtil;
 
/**
 * 
 * <br>
 * <b>功能：</b>RecruitController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */ 
@Controller
@RequestMapping("/recruit") 
public class RecruitController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(RecruitController.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private RecruitService<Recruit> recruitService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(RecruitPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/recruit/recruit",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(RecruitPage page,HttpServletResponse response) throws Exception{
		List<Recruit> dataList = recruitService.queryByList(page);
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
	public void save(Recruit entity, String[] imageFilePaths,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		// 根据ID查询是否已经存在此数据，不存在，则按新数据处理是

		RecruitPage page = new RecruitPage();
		page.setId(entity.getId());

		if (this.recruitService.queryUniqueObjCount(page) > 0) {
			// 更新数据
			recruitService.update(entity);
		} else {
			// 新建数据
			recruitService.add(entity);
		}

		this.sendSuccessMessage(response, entity, MSG_SAVE_SUCCESS);
	}

	@RequestMapping("/getId")
	public ModelAndView getId(String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		Recruit entity = recruitService.queryById(id);
		if (entity == null) {
			return this.error("NORESULT", null, null, request);
		}

		context.put("data", entity);
		return forword("biz/recruit/recruit-edit", context);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(RecruitPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Recruit entity = recruitService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}

			context.put("data", entity);
		} else {
			// 为新增数据设置ID，以便子表引用
			page.setId(MemCachedUtil.getInstance().generateTblKey(
					this.getEntityName()));
			context.put("data", page);
		}

		return forword("biz/recruit/recruit-edit", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/view")
	public ModelAndView view(RecruitPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Recruit entity = recruitService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}
			context.put("data", entity);
		} else {
			// 为新增数据设置ID，以便子表引用
			page.setId(MemCachedUtil.getInstance().generateTblKey(
					this.getEntityName()));
			context.put("data", page);
		}

		return forword("biz/recruit/recruit-view", context);
	}

	@RequestMapping("/delete")
	public void delete(String[] id, HttpServletResponse response)
			throws Exception {
		recruitService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}



	/**
	 * 获取当前登陆者所拥有的数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myList")
	public void myList(Integer offset, Integer limit, String search,
			HttpServletResponse response) throws Exception {
		RecruitPage page = new RecruitPage();
		page.setRows(limit);
		page.setPage((int) offset / limit + 1);
		// 若为管理员，则可查询全部信息，若为普通用户，仅能查询自己的数据
		if (!SessionUtils.isAdmin()) {
			page.setCreateBy(SessionUtils.getUser().getId());
		}
		List<Recruit> dataList = recruitService.queryByList(page);

		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
		jsonMap.put("rows", dataList);

		HtmlUtil.writerJson(response, jsonMap);
	}
}

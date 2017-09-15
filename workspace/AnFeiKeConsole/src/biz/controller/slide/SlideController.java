package biz.controller.slide;

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

import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;
import com.base.web.MemCachedUtil;

import biz.entity.slide.Slide;
import biz.page.slide.SlidePage;
import biz.service.slide.SlideService;
 
/**
 * 
 * <br>
 * <b>功能：</b>SlideController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */ 
@Controller
@RequestMapping("/slide") 
public class SlideController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SlideController.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SlideService<Slide> slideService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(SlidePage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/slide/slide",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(SlidePage page,HttpServletResponse response) throws Exception{
		List<Slide> dataList = slideService.queryByList(page);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
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
		SlidePage page = new SlidePage();
		page.setRows(limit);
		page.setPage((int) offset / limit + 1);
		// 若为管理员，则可查询全部信息，若为普通用户，仅能查询自己的数据
		if (!SessionUtils.isAdmin()) {
			page.setCreateBy(SessionUtils.getUser().getId());
		}
		List<Slide> dataList = slideService.queryByList(page);

		// 将CODE转换为文字
		Map<String, String> typesMap = super
				.getDataDictionaryMapByTypeFromCache().get(
						Constant.DICT_TYPE_SLIDETYPE);
		for (Slide obj : dataList) {
			obj.setSlideType(typesMap.get(obj.getSlideType()).toString());
		}

		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
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
	public void save(Slide entity, String[] imageFilePaths,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		// 根据ID查询是否已经存在此数据，不存在，则按新数据处理是

		SlidePage page = new SlidePage();
		page.setId(entity.getId());

		if (this.slideService.queryUniqueObjCount(page) > 0) {
			// 更新数据
			slideService.update(entity);
		} else {
			// 新建数据
			slideService.add(entity);
		}

		this.sendSuccessMessage(response, entity, MSG_SAVE_SUCCESS);
	}

	@RequestMapping("/getId")
	public ModelAndView getId(String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		Slide entity = slideService.queryById(id);
		if (entity == null) {
			return this.error("NORESULT", null, null, request);
		}

		context.put("data", entity);
		return forword("biz/slide/slide-edit", context);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(SlidePage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Slide entity = slideService.queryById(page.getId());
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

		return forword("biz/slide/slide-edit", context);
	}
	
	
	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		slideService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}

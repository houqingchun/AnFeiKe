package biz.controller.news;

import java.util.ArrayList;
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

import biz.entity.news.News;
import biz.entity.photos.Photos;
import biz.page.news.NewsPage;
import biz.page.photos.PhotosPage;
import biz.service.news.NewsService;
import biz.service.photos.PhotosService;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;
import com.base.web.MemCachedUtil;

/**
 * 
 * <br>
 * <b>功能：</b>NewsController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseAction {

	private final static Logger log = Logger.getLogger(NewsController.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private NewsService<News> newsService;

	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private PhotosService<Photos> photosService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/list")
	public ModelAndView list(NewsPage page, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		// 默认按照完整度和姓名倒序
		if (StringUtils.isBlank(page.getSort())) {
			page.setSort("createTime");
			page.setOrder("desc");
		}

		// this.initialForm(page, false);
		List<News> dataList = newsService.queryByList(page);

		// this.initialForm(page, true);
		// 设置页面数据
		context.put("total", page.getPager().getRowCount());
		context.put("rows", dataList);

		context.put("data", page);
		return forword("biz/news/news-list", context);
	}

	private void initialForm(Object p, boolean toForm) {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("onTop");
		if (toForm) {
			// 将属性内Y/N值转换为on/off值，以正确转换为checkbox的状态
			this.convertYN2OnOff(p, fields);
		} else {
			// 将属性内on/off值转换为Y/N值
			this.convertOnOff2YN(p, fields);
		}
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
		NewsPage page = new NewsPage();
		page.setRows(limit);
		page.setPage((int) offset / limit + 1);
		// 若为管理员，则可查询全部信息，若为普通用户，仅能查询自己的数据
		if (!SessionUtils.isAdmin()) {
			page.setCreateBy(SessionUtils.getUser().getId());
		}
		List<News> dataList = newsService.queryByList(page);

		// 将CODE转换为文字
		Map<String, String> typesMap = super
				.getDataDictionaryMapByTypeFromCache().get(
						Constant.DICT_TYPE_NEWSTYPE);
		for (News obj : dataList) {
			obj.setTypes(typesMap.get(obj.getTypes()).toString());
		}

		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
		jsonMap.put("rows", dataList);

		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void datalist(NewsPage page, HttpServletResponse response)
			throws Exception {
		List<News> dataList = newsService.queryByList(page);
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
	public void save(News entity, String[] imageFilePaths,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		// 根据ID查询是否已经存在此数据，不存在，则按新数据处理是

		initialForm(entity, false);

		NewsPage page = new NewsPage();
		page.setId(entity.getId());

		if (this.newsService.queryUniqueObjCount(page) > 0) {
			// 更新数据
			newsService.update(entity);
		} else {
			// 新建数据
			newsService.add(entity);
		}

		// 更新相关图片
		if (imageFilePaths != null && imageFilePaths.length >= 1) {
			// 先删除相关图片
			PhotosPage param = new PhotosPage();
			param.setMainId(entity.getId());
			param.setMainType("PRODUCTS");
			List<Photos> photoList = photosService.queryByList(param);
			for (int i = 0; i < photoList.size(); i++) {
				photosService.deleteBatch(photoList.get(i).getId());
			}

			// 增加新图片
			List<Photos> list = new ArrayList<Photos>();
			for (String path : imageFilePaths) {
				Photos p = new Photos();
				p.setId(MemCachedUtil.getInstance().generateTblKey(
						p.getClass().getSimpleName().toUpperCase()));
				p.setMainId(entity.getId());
				p.setMainType("PRODUCTS");
				p.setPhotoPath(path);
				list.add(p);
			}
			photosService.addBatch(list);
		}

		this.sendSuccessMessage(response, entity, MSG_SAVE_SUCCESS);
	}

	@RequestMapping("/getId")
	public ModelAndView getId(String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		News entity = newsService.queryById(id);
		initialForm(entity, true);
		if (entity == null) {
			return this.error("NORESULT", null, null, request);
		}

		context.put("data", entity);
		return forword("biz/news/news-edit", context);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(NewsPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			News entity = newsService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}

			// 查询相关图片
			PhotosPage param = new PhotosPage();
			param.setMainId(entity.getId());
			param.setMainType("PRODUCTS");
			entity.setPhotos(photosService.queryByList(param));

			initialForm(entity, true);
			context.put("data", entity);
		} else {
			// 为新增数据设置ID，以便子表引用
			page.setId(MemCachedUtil.getInstance().generateTblKey(
					this.getEntityName()));
			context.put("data", page);
		}

		return forword("biz/news/news-edit", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/view")
	public ModelAndView view(NewsPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			News entity = newsService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}
			// 查询相关图片
			PhotosPage param = new PhotosPage();
			param.setMainId(entity.getId());
			param.setMainType("PRODUCTS");
			entity.setPhotos(photosService.queryByList(param));
			context.put("data", entity);
		} else {
			// 为新增数据设置ID，以便子表引用
			page.setId(MemCachedUtil.getInstance().generateTblKey(
					this.getEntityName()));
			context.put("data", page);
		}

		return forword("biz/news/news-view", context);
	}

	@RequestMapping("/delete")
	public void delete(String[] id, HttpServletResponse response)
			throws Exception {
		newsService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 将code转换为文本，供前台显示
	 * 
	 * @param dataList
	 */
	private void formatDataListForView(List<News> dataList) {
		// Map<String, String> yesOrNoMap =
		// this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_YES_OR_NO);
		// Map<String, String> formationMap =
		// this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BAND_FORMATION);
		// Map<String, String> styleMap =
		// this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BAND_STYLE);
		// Map<String, String> compositionMap =
		// this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_STAFF_COMPOSITION);
		// Map<String, String> genderMap =
		// this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_GENDER);
		// Map<String, String> provinceMap =
		// this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_PROVINCE);
		// //转换数据
		// for (News c : dataList){
		// StringBuffer buf = new StringBuffer();
		// c.setFormation(formationMap.get(c.getFormation()));
		// c.setStyle(styleMap.get(c.getStyle()));
		// c.setLeaderGender(genderMap.get(c.getLeaderGender()));
		// c.setStaffComposition(compositionMap.get(c.getStaffComposition()));
		// c.setProvince(provinceMap.get(c.getProvince()));
		// c.setIsAcademic(yesOrNoMap.get(c.getIsAcademic()));
		// c.setIsOriginal(yesOrNoMap.get(c.getIsOriginal()));
		// c.setIsStationed(yesOrNoMap.get(c.getIsStationed()));
		// }
	}

}

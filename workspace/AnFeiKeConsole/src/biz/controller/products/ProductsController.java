package biz.controller.products;

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

import biz.entity.products.Products;
import biz.entity.photos.Photos;
import biz.entity.products.Products;
import biz.page.photos.PhotosPage;
import biz.page.products.ProductsPage;
import biz.service.photos.PhotosService;
import biz.service.products.ProductsService;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;
import com.base.web.MemCachedUtil;

/**
 * 
 * <br>
 * <b>功能：</b>ProductsController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Controller
@RequestMapping("/products")
public class ProductsController extends BaseAction {

	private final static Logger log = Logger
			.getLogger(ProductsController.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ProductsService<Products> productsService;

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
	public ModelAndView list(ProductsPage page, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		// 默认按照完整度和姓名倒序
		if (StringUtils.isBlank(page.getSort())) {
			page.setSort("name");
			page.setOrder("desc");
		}

		// this.initialForm(page, false);
		List<Products> dataList = productsService.queryByList(page);

		// this.initialForm(page, true);
		// 设置页面数据
		context.put("total", page.getPager().getRowCount());
		context.put("rows", dataList);

		context.put("data", page);
		return forword("biz/products/products-list", context);
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
		ProductsPage page = new ProductsPage();
		page.setRows(limit);
		page.setPage((int) offset / limit + 1);
		// 若为管理员，则可查询全部信息，若为普通用户，仅能查询自己的数据
		if (!SessionUtils.isAdmin()) {
			page.setCreateBy(SessionUtils.getUser().getId());
		}
		List<Products> dataList = productsService.queryByList(page);

		// 将CODE转换为文字
		Map<String, String> typesMap = super
				.getDataDictionaryMapByTypeFromCache().get(
						Constant.DICT_TYPE_PRODUCTTYPE);
		Map<String, String> brandMap = super
				.getDataDictionaryMapByTypeFromCache().get(
						Constant.DICT_TYPE_BRANDS);
		for (Products obj : dataList) {
			obj.setTypes(typesMap.get(obj.getTypes()).toString());
			obj.setBrand(brandMap.get(obj.getBrand()).toString());
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
	public void datalist(ProductsPage page, HttpServletResponse response)
			throws Exception {
		List<Products> dataList = productsService.queryByList(page);
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
	public void save(Products entity, String[] imageFilePaths,
			HttpServletResponse response) throws Exception {
		// 根据ID查询是否已经存在此数据，不存在，则按新数据处理是
		ProductsPage page = new ProductsPage();
		page.setId(entity.getId());

		if (this.productsService.queryUniqueObjCount(page) > 0) {
			// 更新数据
			productsService.update(entity);
		} else {
			// 新建数据
			productsService.add(entity);
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
		Products entity = productsService.queryById(id);
		if (entity == null) {
			return this.error("NORESULT", null, null, request);
		}

		context.put("data", entity);
		return forword("biz/products/products-edit", context);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(ProductsPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Products entity = productsService.queryById(page.getId());
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

		return forword("biz/products/products-edit", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/view")
	public ModelAndView view(ProductsPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Products entity = productsService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}
			// 查询相关图片
			PhotosPage param = new PhotosPage();
			param.setMainId(entity.getId());
			param.setMainType("PRODUCTS");
			entity.setPhotos(photosService.queryByList(param));
			entity.setTypes(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_PRODUCTTYPE).get(entity.getTypes()));
			entity.setBrand(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BRANDS).get(entity.getBrand()));
			context.put("data", entity);
		} else {
			// 为新增数据设置ID，以便子表引用
			page.setId(MemCachedUtil.getInstance().generateTblKey(
					this.getEntityName()));
			context.put("data", page);
		}

		return forword("biz/products/products-view", context);
	}

	@RequestMapping("/delete")
	public void delete(String[] id, HttpServletResponse response)
			throws Exception {
		productsService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 将code转换为文本，供前台显示
	 * 
	 * @param dataList
	 */
	private void formatDataListForView(List<Products> dataList) {
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
		// for (Products c : dataList){
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

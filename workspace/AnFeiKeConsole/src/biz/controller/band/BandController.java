package biz.controller.band;

import java.util.ArrayList;
import java.util.Arrays;
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

import biz.entity.band.Band;
import biz.page.band.BandPage;
import biz.service.band.BandService;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;
import com.base.web.MemCachedUtil;
 
/**
 * 
 * <br>
 * <b>功能：</b>BandController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */ 
@Controller
@RequestMapping("/band") 
public class BandController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(BandController.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BandService<Band> bandService; 

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/list") 
	public ModelAndView  list(BandPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		
		//只显示已发布的信息
		page.setStatus(Constant.PUBLISH_STATUS_PUBLISHED);
				
		//默认按照完整度和姓名倒序
		if (StringUtils.isBlank(page.getSort())){
			page.setSort("integrity");
			page.setOrder("desc");
		}
		
		//this.initialForm(page, false);
		List<Band> dataList = bandService.queryByList(page);
		
		//this.initialForm(page, true);
		//设置页面数据
		context.put("total",page.getPager().getRowCount());
		context.put("rows", dataList);
		
		context.put("data", page);
		return forword("biz/band/band-list",context); 
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
	public void myList(Integer offset, Integer limit, String search, HttpServletResponse response) throws Exception {
		BandPage page = new BandPage();
		page.setRows(limit);
		page.setPage((int) offset / limit + 1);
		//若为管理员，则可查询全部信息，若为普通用户，仅能查询自己的数据
		if (!SessionUtils.isAdmin()){
			page.setCreateBy(SessionUtils.getUser().getId());
		}
		List<Band> dataList = bandService.queryByList(page);
		
		//将CODE转换为文字
//		Map<String, String> statusMap = super.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_PUBLISH_STATUS);
//		for (Band obj : dataList){
//			obj.setStatus(statusMap.get(obj.getStatus()).toString());
//		}
		
		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		

		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/dataList") 
	public void  datalist(BandPage page,HttpServletResponse response) throws Exception{
		List<Band> dataList = bandService.queryByList(page);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	

	/**
	 * 将code转换为文本，供前台显示
	 * @param dataList
	 */
//	private void formatDataListForView(List<Band> dataList){
//		Map<String, String> yesOrNoMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_YES_OR_NO);
//		Map<String, String> formationMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BAND_FORMATION);
//		Map<String, String> styleMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BAND_STYLE);
//		Map<String, String> compositionMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_STAFF_COMPOSITION);
//		Map<String, String> genderMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_GENDER);
//		Map<String, String> provinceMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_PROVINCE);
//		//转换数据
//		for (Band c : dataList){
//			StringBuffer buf = new StringBuffer();
//			c.setFormation(formationMap.get(c.getFormation()));
//			c.setStyle(styleMap.get(c.getStyle()));
//			c.setLeaderGender(genderMap.get(c.getLeaderGender()));
//			c.setStaffComposition(compositionMap.get(c.getStaffComposition()));
//			c.setProvince(provinceMap.get(c.getProvince()));
//			c.setIsAcademic(yesOrNoMap.get(c.getIsAcademic()));
//			c.setIsOriginal(yesOrNoMap.get(c.getIsOriginal()));
//			c.setIsStationed(yesOrNoMap.get(c.getIsStationed()));
//		}
//	}
	
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(Band entity,String[] typeIds,HttpServletRequest request, HttpServletResponse response) throws Exception{
		initialForm(entity, false);
		//根据ID查询是否已经存在此数据，不存在，则按新数据处理是
		BandPage page = new BandPage();
		page.setId(entity.getId());

		//重置完整并
		this.resetIntegrity(entity);
		
		if (this.bandService.queryUniqueObjCount(page) > 0){
			//更新数据
			bandService.update(entity);
		}else{
			//初始点击量为1
			entity.setHit(1);
			//新建数据
			bandService.add(entity);
		}

		this.sendSuccessMessage(response, entity, MSG_SAVE_SUCCESS);
	}
	
	private void resetIntegrity(Band entity) throws Exception{
		int total = 0; //23 in total
		/**
		 * 检查自身属性完整性
		 */
		if (StringUtils.isNotBlank(entity.getName())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getCity())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getContact())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getDescription())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getFormation())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getIsAcademic())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getIsStationed())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getIsOriginal())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getLeaderGender())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getMobile())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getPhotoPath())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getPriceUnit())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getProvince())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getStaffComposition())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getStatus())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getStyle())){
			total++;
		}
		if (StringUtils.isNotBlank(entity.getStyleComment())){
			total++;
		}
		if (entity.getHighPrice() > 0){
			total++;
		}
		if (entity.getLowerPrice() > 0){
			total++;
		}
		
		entity.setIntegrity(total);
	}
	
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/getId")
	public ModelAndView getId(String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object>  context = this.getRootMap();
		Band entity  = bandService.queryById(id);
		if(entity  == null){
			return this.error("NORESULT", null, null, request);
		}
		initialForm(entity, true);

		context.put("data", entity);
		
		return forword("view/band/band-edit", context);
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(BandPage page,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> context = getRootMap();

		//若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Band entity  = bandService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}

			initialForm(entity, true);
			context.put("data", entity);
		}else{
			//为新增数据设置ID，以便子表引用 
			page.setId(MemCachedUtil.getInstance().generateTblKey(this.getEntityName()));
			context.put("data", page);
		}

		
		return forword("biz/band/band-edit", context);
	}
	
	@RequestMapping("/test")
	public ModelAndView test(BandPage page,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> context = getRootMap();
		return forword("biz/band/test", context);
	}
	
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/view")
	public ModelAndView view(BandPage page,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> context = getRootMap();

		//若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			Band entity  = bandService.queryById(page.getId());
			if (entity == null) {
				return this.error("NORESULT", null, null, request);
			}
//			entity.setProvince(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_PROVINCE).get(entity.getProvince()));
//			entity.setLeaderGender(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_GENDER).get(entity.getLeaderGender()));
//			entity.setFormation(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BAND_FORMATION).get(entity.getFormation()));
//			entity.setStaffComposition(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_STAFF_COMPOSITION).get(entity.getStaffComposition()));
//			entity.setStyle(this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_BAND_STYLE).get(entity.getStyle()));
			
//			Map<String, String> yesOrNoMap = this.getDataDictionaryMapByTypeFromCache().get(Constant.DICT_TYPE_YES_OR_NO);
//			entity.setIsAcademic(yesOrNoMap.get(entity.getIsAcademic()));
//			entity.setIsOriginal(yesOrNoMap.get(entity.getIsOriginal()));
//			entity.setIsStationed(yesOrNoMap.get(entity.getIsStationed()));

			context.put("data", entity);
		}else{
			//为新增数据设置ID，以便子表引用 
			page.setId(MemCachedUtil.getInstance().generateTblKey(this.getEntityName()));
			context.put("data", page);
		}

		
		return forword("biz/band/band-view", context);
	}
	
	
	private void initialForm(Object p, boolean toForm) {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("isOriginal");
		fields.add("isBiz");
		fields.add("isStationed");
		fields.add("isAcademic");
		if (toForm) {
			// 将属性内Y/N值转换为on/off值，以正确转换为checkbox的状态
			this.convertYN2OnOff(p, fields);
		} else {
			// 将属性内on/off值转换为Y/N值
			this.convertOnOff2YN(p, fields);
		}
	}
	
	
	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		bandService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	/**
	 * 获取点击率
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/queryHitsByIds")
	public void queryHitsByIds(String[] id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(SUCCESS, true);
		if (id != null){
			context.put("data", this.bandService.queryHitsByIds(Arrays.asList(id)));
		}
		
		HtmlUtil.writerJson(response, context);
	}
	
	/**
	 * 增加点击率
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/increaseHitsById")
	public void increaseHitsById(String id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(SUCCESS, true);
		this.bandService.increaseHitsById(id);
		HtmlUtil.writerJson(response, context);
	}	
}

package biz.controller.dict;

import java.util.ArrayList;
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
import com.base.web.MemCachedUtil;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;

import biz.entity.dict.DataDictionary;
import biz.entity.news.News;
import biz.entity.photos.Photos;
import biz.entity.products.Products;
import biz.page.dict.DataDictionaryPage;
import biz.page.news.NewsPage;
import biz.page.photos.PhotosPage;
import biz.page.products.ProductsPage;
import biz.service.dict.DataDictionaryService;
 
/**
 * 
 * <br>
 * <b>功能：</b>DataDictionaryController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */ 
@Controller
@RequestMapping("/dataDictionary") 
public class DataDictionaryController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(DataDictionaryController.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private DataDictionaryService<DataDictionary> dataDictionaryService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(DataDictionaryPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/dict/dataDictionary",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(DataDictionaryPage page,HttpServletResponse response) throws Exception{
		List<DataDictionary> dataList = dataDictionaryService.queryByList(page);
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
	public void save(DataDictionary entity, HttpServletResponse response) {
		Map<String, Object> context = new HashMap<String, Object>();
		// 根据ID查询是否已经存在此数据，不存在，则按新数据处理是

		DataDictionaryPage page;
		
		
		int codeCnt;
		int idCnt;
		try {
			page = new DataDictionaryPage();
			page.setType(entity.getType());
			page.setCode(entity.getCode());
			List<DataDictionary> codeList = this.dataDictionaryService.queryByList(page);
			codeCnt = codeList.size();
			
			page = new DataDictionaryPage();
			page.setId(entity.getId());
			idCnt = this.dataDictionaryService.queryUniqueObjCount(page);
			
			if (idCnt > 0 && codeCnt > 1 || idCnt > 0 && codeCnt == 1 && !codeList.get(0).getId().equals(entity.getId()) || idCnt == 0 && codeCnt > 0){
				this.sendFailureMessage(response, entity, "编码重复，请修改后再试！");
			}else{
				if (idCnt > 0){
					// 更新数据
					dataDictionaryService.update(entity);
				} else {
					// 新建数据
					dataDictionaryService.add(entity);
				}

				this.sendSuccessMessage(response, entity, MSG_SAVE_SUCCESS);
				
				//Refresh cache
				MemCachedUtil.getInstance().refreshDataDictCache();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.sendFailureMessage(response, entity, "保存失败！");
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(DataDictionaryPage page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();

		// 若ID为不空，则表示为编辑动作，否则为添加动作
		if (StringUtils.isNotBlank(page.getId())) {
			DataDictionary entity = dataDictionaryService.queryById(page.getId());
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

		return forword("biz/dataDictionary/dataDictionary-edit", context);
	}
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		DataDictionary entity  = dataDictionaryService.queryById(id);
		if(entity  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
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
		DataDictionaryPage page = new DataDictionaryPage();
		page.setType(Constant.DICT_TYPE_PRODUCTTYPE);
		//page.setRows(limit);
		page.setPagination(false);
		//page.setPage((int) offset / limit + 1);
		
		// 若为管理员，则可查询全部信息，若为普通用户，仅能查询自己的数据
		if (!SessionUtils.isAdmin()) {
			page.setCreateBy(SessionUtils.getUser().getId());
		}
		List<DataDictionary> dataList = dataDictionaryService.queryByList(page);
		
		int rowCount = page.getPager().getRowCount();
		
		page.setType(Constant.DICT_TYPE_BRANDS);
		dataList.addAll(dataDictionaryService.queryByList(page));
		
		rowCount += page.getPager().getRowCount();
		
		for (DataDictionary dt : dataList){
			if (dt.getType().equals(Constant.DICT_TYPE_PRODUCTTYPE)){
				dt.setType("机型类别");
			}else if (dt.getType().equals(Constant.DICT_TYPE_BRANDS)){
				dt.setType("机型品牌");
			}
		}
		
		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rowCount);
		jsonMap.put("rows", dataList);

		HtmlUtil.writerJson(response, jsonMap);
	}
	
	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		dataDictionaryService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}

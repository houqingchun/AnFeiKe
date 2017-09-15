package com.base.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import biz.entity.people.People;

import com.base.dao.BaseDao;
import com.base.interceptor.PermissionException;
import com.base.page.BasePage;
import com.base.util.ClassReflectUtil;
import com.base.util.Constant;
import com.base.util.SessionUtils;
import com.base.web.MemCachedUtil;

public abstract class BaseService<T> {
	private static final Log log = LogFactory.getLog(BaseService.class);

	public abstract BaseDao<T> getDao();

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * 批量修改
	 * 
	 * @param list
	 */
	public void updateBatch(List<T> list) {
		if (list == null || list.size() == 0) {
			return;
		}

		log.debug("Start bulk update...");
		SqlSession sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH);
		BaseDao<T> mapperBatch = (BaseDao<T>) sqlSession.getMapper(this.getMapperClass());
		int idx = 0;
		Set<String> ids = new HashSet<String>();
		try {
			for (T t1 : list) {
				this.doAuthorized(t1);
				
				this.populateNewObject(t1);

				mapperBatch.updateBySelective(t1);
				idx++;
				if (idx % Constant.BATCH_PROCESS_MAX_NBR_UPDATE == 0 || idx == list.size()) {
					sqlSession.commit();
					sqlSession.clearCache();
				}
				
				//将ID放入集合，删除缓存时使用
				ids.add((String)ClassReflectUtil.getFieldValue(t1, "id"));
			}
			sqlSession.commit();
			sqlSession.clearCache();
			log.debug("commit...");

		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		
		log.debug("End bulk update...");
		
		//根据文件类型清除HTML缓存
		this.clearHTMLCache(Constant.HTML_FILE_TYPE_EDIT, ids);
	}

	/**
	 * 批量增加
	 * 
	 * @param list
	 */
	public void addBatch(List<T> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		log.debug("Start bulk update...");
		SqlSession sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH);
		BaseDao<T> mapperBatch = (BaseDao<T>) sqlSession.getMapper(this.getMapperClass());
		int idx = 0;
		try {
			for (T t1 : list) {
				this.populateNewObject(t1);

				mapperBatch.add(t1);
				idx++;
				if (idx % Constant.BATCH_PROCESS_MAX_NBR_INSERT == 0 || idx == list.size()) {
					sqlSession.commit();
					sqlSession.clearCache();
				}
			}
			sqlSession.commit();
			sqlSession.clearCache();
			log.debug("commit...");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}

		//根据文件类型清除HTML缓存
		this.clearHTMLCache(Constant.HTML_FILE_TYPE_LIST, null);
		log.debug("End bulk update...");
	}

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	public void deleteBatch(Object... ids) {
		if (ids == null || ids.length < 1) {
			return;
		}
		log.debug("Start bulk delete...");
		SqlSession sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH);
		BaseDao<T> mapperBatch = (BaseDao<T>) sqlSession.getMapper(this.getMapperClass());
		Set<String> idSet = new HashSet<String>();
		
		int idx = 0;
		for (int i = 0; i < ids.length; i++) {
			Object id = ids[i];
			try {
				mapperBatch.delete(id);
				idx++;
				if (idx % Constant.BATCH_PROCESS_MAX_NBR_INSERT == 0 || idx == ids.length) {
					sqlSession.commit();
					sqlSession.clearCache();
				}
				sqlSession.commit();
				sqlSession.clearCache();
				log.debug("commit...");
				

				//将ID放入集合，删除缓存时使用
				idSet.add((String)id);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sqlSession.rollback();
			} finally {
				sqlSession.close();
			}
		}

		log.debug("End bulk delete...");
		

		//根据文件类型清除HTML缓存
		this.clearHTMLCache(Constant.HTML_FILE_TYPE_EDIT, idSet);
	}

	/**
	 * 获取当前操作对象对应的MAPPER类
	 * 
	 * @return
	 */
	private Class getMapperClass() {
		String className = this.getClass().getName();
		className = className.replace(".service.", ".dao.");
		if (className.endsWith("Service")) {
			className = className.substring(0, className.length() - 7);
		}
		className += "Dao";
		log.debug("Mapper Class Name:" + className);
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 获取当前操作对象实体名称
	 * 
	 * @return
	 */
	private String getEntityName() {
		String entityName = this.getClass().getSimpleName();
		if (entityName.endsWith("Service")) {
			entityName = entityName.substring(0, entityName.length() - 7);
		}
		
		return ClassReflectUtil.convertToTableName(entityName).toUpperCase();
	}
	
	/**
	 * 针对特定数据，根据文件类型清除对应的HTML文件数据
	 * @param fileType
	 * @param id
	 */
	private void clearHTMLCache(String fileType, Set<String> ids){
		String className = this.getClass().getSimpleName();
		String objName = className.substring(0, className.lastIndexOf("Service"));
		log.debug("Try to clear html cache for objec " + className + " TYPE: " + fileType);
		if (objName.equalsIgnoreCase("BAND") 
				|| objName.equalsIgnoreCase("GROUPS") 
				|| objName.equalsIgnoreCase("SINGER") 
				|| objName.equalsIgnoreCase("BANDSMAN")){
			SessionUtils.clearHTMLCache(objName, fileType, ids);
		}
		
		//若数据字典存在更新，则全部清除
		if (objName.equalsIgnoreCase("DataDictionary")){
			SessionUtils.clearHTMLCache(Constant.HTML_ROOT, null, null);
		}
	}

	/**
	 * 设置主键.字符类型采用UUID,数字类型采用自增
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected String populateNewObject(Object t) {
		String id = null;
		try {
			//若不存在ID，则设置ID
			
			String oldId = (String) ClassReflectUtil.getFieldValue(t, "id");
			if (StringUtils.isBlank(oldId)){
				id = MemCachedUtil.getInstance().generateTblKey(this.getEntityName());
				ClassReflectUtil.setIdKeyValue(t, "id", id);
			}
			ClassReflectUtil.setFieldValue(t, "createBy", SessionUtils.getUser().getId().toString());
			ClassReflectUtil.setFieldValue(t, "createTime", new Timestamp(new Date().getTime()));
			ClassReflectUtil.setFieldValue(t, "updateBy", SessionUtils.getUser().getId().toString());
			ClassReflectUtil.setFieldValue(t, "updateTime", new Timestamp(new Date().getTime()));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return id;
	}

	/**
	 * 设置主键.字符类型采用UUID,数字类型采用自增
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected void populateUpdateObject(Object t) {
		try {
			ClassReflectUtil.setFieldValue(t, "updateBy", SessionUtils.getUser().getId().toString());
			ClassReflectUtil.setFieldValue(t, "updateTime", new Timestamp(new Date().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(T t) throws Exception {
		this.populateNewObject(t);
		getDao().add(t);
		
		//根据文件类型清除HTML缓存
		this.clearHTMLCache(Constant.HTML_FILE_TYPE_LIST, null);
	}

	public void update(T t) throws Exception {
		this.doAuthorized(t);
		
		this.populateUpdateObject(t);
		this.updateBySelective(t);
	}
	
	/**
	 * 权限检查，或不通过，则抛出异常
	 * @param t
	 * @throws Exception 
	 */
	private void doAuthorized(T t) throws Exception{
		String id = ClassReflectUtil.getFieldValue(t, "id").toString();
		this.doAuthorizedById(id);	
	}
	
	/**
	 * 权限检查，或不通过，则抛出异常
	 * @param t
	 * @throws Exception 
	 */
	private void doAuthorizedForEditAction(String id) throws Exception{
		if (StringUtils.isNotBlank(id)){
			//若当前为编辑修改操作，则进行权限验证
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			
			String uri = request.getRequestURI();
			uri = uri.substring(uri.lastIndexOf("/") + 1);
			if (uri.equalsIgnoreCase("edit.do") || uri.equalsIgnoreCase("edit.shtml") || uri.equalsIgnoreCase("edit.shtm")){
				this.doAuthorizedById(id);
			}
		}
	}
	
	/**
	 * 权限检查，或不通过，则抛出异常
	 * @param t
	 * @throws PermissionException 
	 * @throws Exception 
	 */
	private void doAuthorizedById(String id) throws PermissionException{
		T oldT = getDao().queryById(id);
		String createById = null;
		try {
			createById = ClassReflectUtil.getFieldValue(oldT, "createBy").toString();
			log.debug("Authorized Result CreateBy:" + createById);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!SessionUtils.isAdmin() && createById != null){
			People user = SessionUtils.getUser(); 
			if (user == null || !user.getId().equals(createById)){
				throw new PermissionException();
			}
		}		
	}

	public void updateBySelective(T t) throws Exception {
		this.doAuthorized(t);
		
		this.populateUpdateObject(t);
		getDao().updateBySelective(t);
		
		//根据文件类型清除HTML缓存
		Set<String> idSet = new HashSet<String>();
		idSet.add((String) ClassReflectUtil.getFieldValue(t, "id"));
		this.clearHTMLCache(Constant.HTML_FILE_TYPE_EDIT, idSet);
	}

	public void delete(Object... ids) throws Exception {
		if (ids == null || ids.length < 1) {
			return;
		}

		Set<String> idSet = new HashSet<String>();
		for (Object id : ids) {
			this.doAuthorizedById(id.toString());
			getDao().delete(id);
			
			idSet.add((String) id);
		}
		
		//根据文件类型清除HTML缓存
		this.clearHTMLCache(Constant.HTML_FILE_TYPE_EDIT, idSet);
	}

	public int queryByCount(BasePage page) throws Exception {
		return getDao().queryByCount(page);
	}
	
	public int queryUniqueObjCount(BasePage page) throws Exception {
		return getDao().queryUniqueObjCount(page);
	}

	public List<T> queryByList(BasePage page) throws Exception {
		Integer rowCount;
		//若需要分页，则读取DB总行数，否则设置为默认最大可显示数量
		if (page.isPagination()){
			rowCount = queryByCount(page);
		}else{
			rowCount = Constant.MAX_PAGESIZE;
			page.setRows(rowCount);
		}
		page.getPager().setRowCount(rowCount);
		
		return getDao().queryByList(page);
	}

	public T queryById(Object id) throws Exception {
		this.doAuthorizedForEditAction(String.valueOf(id));
		
		return getDao().queryById(id);
	}
	
	public List<T>  queryHitsByIds(List<String> idList){
		return getDao().queryHitsById(idList);
	}
	
	public void increaseHitsById(Object id){
		getDao().increaseHitsById(id);
	}
}

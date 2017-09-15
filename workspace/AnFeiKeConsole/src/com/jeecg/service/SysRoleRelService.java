package com.jeecg.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.jeecg.dao.SysRoleRelDao;
import com.jeecg.entity.SysRoleRel;

/**
 * 
 * <br>
 * <b>功能：</b>SysRoleRelService<br>
 * 
 * <b>日期：</b> July, 2015 <br>
 * 
 */
@Service("sysRoleRelService")
public class SysRoleRelService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SysRoleRelService.class);
	
	
	public List<SysRoleRel> queryByRoleId(String roleId,String relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		return getDao().queryByRoleId(param);
	}
	
	
	public List<SysRoleRel> queryByObjId(String objId,String relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		return getDao().queryByObjId(param);
	}
	
	/**
	 * 根据关联对象id,关联类型删除 
	 * @param objId
	 * @param relType
	 */
	public void deleteByObjId(String objId,String relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		getDao().deleteByObjId(param);
	}
	
	/**
	 * 根据角色id删除 
	 * @param roleId
	 */
	public void deleteByRoleId(String roleId){
		deleteByRoleId(roleId,null);
	}
	
	/**
	 *  根据角色id,关联类型删除 
	 * @param roleId
	 * @param relType
	 */
	public void deleteByRoleId(String roleId,String relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		getDao().deleteByRoleId(param);
	}
	
	
	

	@Autowired
    private SysRoleRelDao<T> mapper;

		
	public SysRoleRelDao<T> getDao() {
		return mapper;
	}

}

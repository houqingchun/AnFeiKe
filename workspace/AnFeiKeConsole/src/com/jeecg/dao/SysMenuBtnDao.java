package com.jeecg.dao;

import java.util.List;

import com.base.dao.BaseDao;

/**
 * SysMenuBtn Mapper
 * @author Administrator
 *
 */
public interface SysMenuBtnDao<T> extends BaseDao<T> {
	
	public List<T> queryByMenuid(String menuid);
	
	public List<T> queryByMenuUrl(String url); 
	
	public void deleteByMenuid(String menuid);
	
	public List<T> getMenuBtnByUser(String userid); 
	
	
	
	public List<T> queryByAll();
}

package com.base.dao;

import java.math.BigDecimal;
import java.util.List;

import com.base.page.BasePage;

public interface BaseDao<T> {

	
	public void add(T t);
	
	
	public void update(T t);
	
	
	public void updateBySelective(T t); 	
	
	public void delete(Object id);
	

	public int queryByCount(BasePage page);
	
	public List<T> queryByList(BasePage page);
	
	public T queryById(Object id);
	
	public int queryUniqueObjCount(BasePage page);
	
	public List<T> queryHitsById(List<String> ids);
	
	public void increaseHitsById(Object id);
}

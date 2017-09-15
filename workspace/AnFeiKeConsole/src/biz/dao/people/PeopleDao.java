package biz.dao.people;


import biz.page.people.PeoplePage;

import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>PeopleDao<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public interface PeopleDao<T> extends BaseDao<T> {
	
	/**
	 * 检查登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	public T queryLogin(PeoplePage model);	
}

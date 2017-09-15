package biz.service.people;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import biz.dao.people.PeopleDao;
import biz.page.people.PeoplePage;

/**
 * 
 * <br>
 * <b>功能：</b>PeopleService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("peopleService")
public class PeopleService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(PeopleService.class);
	

	

	@Autowired
    private PeopleDao<T> dao;

		
	public PeopleDao<T> getDao() {
		return dao;
	}

	public T queryLogin(PeoplePage model){
		return dao.queryLogin(model);
	}
}

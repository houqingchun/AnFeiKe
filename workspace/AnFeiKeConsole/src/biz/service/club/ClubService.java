package biz.service.club;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.club.ClubDao;

/**
 * 
 * <br>
 * <b>功能：</b>ClubService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("clubService")
public class ClubService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ClubService.class);
	

	

	@Autowired
    private ClubDao<T> dao;

		
	public ClubDao<T> getDao() {
		return dao;
	}

}

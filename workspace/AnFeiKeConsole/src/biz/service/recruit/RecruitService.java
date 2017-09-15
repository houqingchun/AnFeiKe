package biz.service.recruit;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.recruit.RecruitDao;

/**
 * 
 * <br>
 * <b>功能：</b>RecruitService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("recruitService")
public class RecruitService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(RecruitService.class);
	

	

	@Autowired
    private RecruitDao<T> dao;

		
	public RecruitDao<T> getDao() {
		return dao;
	}

}

package biz.service.company;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.company.CompanyDao;

/**
 * 
 * <br>
 * <b>功能：</b>CompanyService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("companyService")
public class CompanyService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CompanyService.class);
	

	

	@Autowired
    private CompanyDao<T> dao;

		
	public CompanyDao<T> getDao() {
		return dao;
	}

}

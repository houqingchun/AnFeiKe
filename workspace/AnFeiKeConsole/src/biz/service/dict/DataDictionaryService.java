package biz.service.dict;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.dict.DataDictionaryDao;

/**
 * 
 * <br>
 * <b>功能：</b>DataDictionaryService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("dataDictionaryService")
public class DataDictionaryService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(DataDictionaryService.class);
	

	

	@Autowired
    private DataDictionaryDao<T> dao;

		
	public DataDictionaryDao<T> getDao() {
		return dao;
	}

}

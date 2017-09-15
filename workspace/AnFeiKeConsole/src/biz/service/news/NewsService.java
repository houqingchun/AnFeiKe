package biz.service.news;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.news.NewsDao;

/**
 * 
 * <br>
 * <b>功能：</b>NewsService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("newsService")
public class NewsService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(NewsService.class);
	

	

	@Autowired
    private NewsDao<T> dao;

		
	public NewsDao<T> getDao() {
		return dao;
	}

}

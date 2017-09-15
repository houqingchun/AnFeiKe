package biz.service.slide;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.slide.SlideDao;

/**
 * 
 * <br>
 * <b>功能：</b>SlideService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("slideService")
public class SlideService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SlideService.class);
	

	

	@Autowired
    private SlideDao<T> dao;

		
	public SlideDao<T> getDao() {
		return dao;
	}

}

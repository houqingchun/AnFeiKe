package biz.service.photos;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.photos.PhotosDao;

/**
 * 
 * <br>
 * <b>功能：</b>PhotosService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("photosService")
public class PhotosService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(PhotosService.class);
	

	

	@Autowired
    private PhotosDao<T> dao;

		
	public PhotosDao<T> getDao() {
		return dao;
	}

}

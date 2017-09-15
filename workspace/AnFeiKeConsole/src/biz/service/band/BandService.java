package biz.service.band;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import biz.dao.band.BandDao;

/**
 * 
 * <br>
 * <b>功能：</b>BandService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("bandService")
public class BandService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(BandService.class);
	

	

	@Autowired
    private BandDao<T> dao;

		
	public BandDao<T> getDao() {
		return dao;
	}
}

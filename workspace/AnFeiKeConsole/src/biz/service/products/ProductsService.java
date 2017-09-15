package biz.service.products;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import biz.dao.products.ProductsDao;

/**
 * 
 * <br>
 * <b>功能：</b>ProductsService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("productsService")
public class ProductsService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProductsService.class);
	

	

	@Autowired
    private ProductsDao<T> dao;

		
	public ProductsDao<T> getDao() {
		return dao;
	}

}

package biz.service.evaluations;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.dao.evaluations.EvaluationsDao;

import com.base.page.BasePage;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>EvaluationsService<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Service("evaluationsService")
public class EvaluationsService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(EvaluationsService.class);
	

	

	@Autowired
    private EvaluationsDao<T> dao;

		
	public EvaluationsDao<T> getDao() {
		return dao;
	}

	public double queryAvgAssessmentRs(BasePage page){
		BigDecimal o = dao.queryAvgAssessmentRs(page);
		return o != null ? o.doubleValue() : 1;
	}
}

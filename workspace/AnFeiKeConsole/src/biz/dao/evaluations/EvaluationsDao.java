package biz.dao.evaluations;


import java.math.BigDecimal;

import com.base.dao.BaseDao;
import com.base.page.BasePage;
/**
 * 
 * <br>
 * <b>功能：</b>EvaluationsDao<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public interface EvaluationsDao<T> extends BaseDao<T> {
	
	public BigDecimal queryAvgAssessmentRs(BasePage page);	
}

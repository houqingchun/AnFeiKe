package com.base.page;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.base.util.Constant;

public class BasePage {
	private final static Logger log= Logger.getLogger(BasePage.class);
	
	private Integer page = 1;
	
	private Integer rows = Constant.PAGINATION_ROWS_PER_PAGE;
	
	private String sort;
	
	private String order;
	
	private Integer previousPage = 0;//上一页
	private Integer nextPage = 0;//下一页
	private Integer paginationStart = 0;//起始显示页码
	private Integer paginationEnd = 0;//终止显示页码

	private boolean pagination = true;//是分页显示吗，默认为true
	/**
	 * 分页导航
	 */
	private Pager pager = new Pager();
	
	public Pager getPager() {
		pager.setPageId(getPage());
		pager.setPageSize(getRows());
		String orderField="";
		if(StringUtils.isNotBlank(sort)){
			orderField = sort;
		}
		if(StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)){
			orderField +=" "+ order;
		}
		pager.setOrderField(orderField);
		log.debug("Order Field: " + orderField);
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public Integer getPreviousPage() {
		this.previousPage = this.page - 1;
		if (this.previousPage <= 0){
			this.previousPage = 1;
		}
		
		return this.previousPage;
	}

	public Integer getNextPage() {
		this.nextPage = this.page + 1;
		if (this.nextPage>= this.pager.getPageCount()){
			this.nextPage = this.pager.getPageCount();
		}
		
		return this.nextPage;
	}

	public Integer getPaginationStart() {
		this.paginationStart = 1;
		
		//若小于10页，则直接从第1页开始显示页码
		Pager p = this.getPager();
		if (p.getPageCount() <= Constant.PAGINATION_NBR_SHOWN){
			return this.paginationStart;
		}
		
		//当大于5时，开始自动滚动页码
		if (this.page > Constant.PAGINATION_NBR_SHOWN/2){
			this.paginationStart = this.page - Constant.PAGINATION_NBR_SHOWN/2;
			//若大于10页，则最小值为1，最大值为总页数-10
			if (this.paginationStart <= 0){
				this.paginationStart = 1;
			}else if (this.paginationStart + Constant.PAGINATION_NBR_SHOWN >= p.getPageCount()){
				this.paginationStart = p.getPageCount()-Constant.PAGINATION_NBR_SHOWN + 1;
			}
		}
		
		return this.paginationStart;
	}

	public Integer getPaginationEnd() {
		this.paginationEnd = this.paginationStart + Constant.PAGINATION_NBR_SHOWN - 1;
		
		if (this.paginationEnd > this.pager.getPageCount()){
			this.paginationEnd = this.pager.getPageCount(); 
		}
		
		return this.paginationEnd;
	}
}

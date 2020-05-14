package com.tkrs.controller.vo;

import com.tkrs.common.Page;

public class CbondPageVo {

	private String orderBy;
	private String order;
	private String rateFormer;
	private String company;
	private Integer pageNum;
	private Integer pageSize;
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getRateFormer() {
		return rateFormer;
	}
	public void setRateFormer(String rateFormer) {
		this.rateFormer = rateFormer;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}

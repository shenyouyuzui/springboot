package com.tvcp.controller.vo;

import java.util.List;

public class PropPageVo {
	private Integer pageNum;
	private Integer pageSize;
	private List<String> labelList;
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
	public List<String> getLabelList() {
		return labelList;
	}
	public void setLabelList(List<String> labelList) {
		this.labelList = labelList;
	}
	
	
	
}

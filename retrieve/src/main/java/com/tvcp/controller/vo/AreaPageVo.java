package com.tvcp.controller.vo;

import java.util.List;

public class AreaPageVo {
	
	private Integer pageNum;
	private Integer pageSize;
	private List<String> natureList;
	private List<String> yearsList;
	private List<String> typeList;
	private List<String> stageList;
	private List<String> featureList;
	public List<String> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(List<String> featureList) {
		this.featureList = featureList;
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
	public List<String> getNatureList() {
		return natureList;
	}
	public void setNatureList(List<String> natureList) {
		this.natureList = natureList;
	}
	public List<String> getYearsList() {
		return yearsList;
	}
	public void setYearsList(List<String> yearsList) {
		this.yearsList = yearsList;
	}
	public List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public List<String> getStageList() {
		return stageList;
	}
	public void setStageList(List<String> stageList) {
		this.stageList = stageList;
	}
	
	

}

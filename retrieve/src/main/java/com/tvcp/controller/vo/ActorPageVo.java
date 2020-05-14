package com.tvcp.controller.vo;

import java.util.List;

public class ActorPageVo {
	
	private Integer sex;
	private List<AgeRangeVo> ageList;
	private List<String> countryList;
	private List<String> hobbyList;
	private Integer pageNum;
	private Integer pageSize;
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public List<AgeRangeVo> getAgeList() {
		return ageList;
	}
	public void setAgeList(List<AgeRangeVo> ageList) {
		this.ageList = ageList;
	}
	public List<String> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}
	public List<String> getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List<String> hobbyList) {
		this.hobbyList = hobbyList;
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

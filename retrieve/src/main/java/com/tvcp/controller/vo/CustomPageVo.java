package com.tvcp.controller.vo;

import java.util.List;

public class CustomPageVo {
	
	private List<String> nameList;
	private Integer sex;
	private List<String> ageList;
	private List<String> typeList;
	private List<String> labelList;
	private List<String> channelList; 
	private Integer pageNum;
	private Integer pageSize;
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public List<String> getAgeList() {
		return ageList;
	}
	public void setAgeList(List<String> ageList) {
		this.ageList = ageList;
	}
	public List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public List<String> getLabelList() {
		return labelList;
	}
	public void setLabelList(List<String> labelList) {
		this.labelList = labelList;
	}
	public List<String> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<String> channelList) {
		this.channelList = channelList;
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

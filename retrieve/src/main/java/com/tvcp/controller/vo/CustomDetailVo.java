package com.tvcp.controller.vo;

import java.util.List;

public class CustomDetailVo {
	
	private Integer customId;
	private String name;
	private String sex;
	private String age;
	private String type;
	private String label;
	private String channel;
	private String info;
	private List<String> imgList;
	public Integer getCustomId() {
		return customId;
	}
	public void setCustomId(Integer customId) {
		this.customId = customId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

}

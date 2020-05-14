package com.tvcp.domain;

public class CustomDomain {

	private Integer customId;
	private String name;
	private String sex;
	private String age;
	private String type;
	private String label;
	private String channel;
	private String info;

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
	
	public CustomDomain(Integer customId, String name, String sex, String age, String type, String label,
			String channel, String info) {
		super();
		this.customId = customId;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.type = type;
		this.label = label;
		this.channel = channel;
		this.info = info;
	}

	public CustomDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

}

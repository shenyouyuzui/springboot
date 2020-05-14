package com.tkrs.domain;

import java.util.Date;

public class CbondYtmDomain {
	
	private String code;
	
	private String name;
	
	private Double ytm;
	
	private Double pqClose;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getYtm() {
		return ytm;
	}

	public void setYtm(Double ytm) {
		this.ytm = ytm;
	}

	public Double getPqClose() {
		return pqClose;
	}

	public void setPqClose(Double pqClose) {
		this.pqClose = pqClose;
	}

	public CbondYtmDomain(String code, String name, Double ytm, Double pqClose) {
		super();
		this.code = code;
		this.name = name;
		this.ytm = ytm;
		this.pqClose = pqClose;
	}

	public CbondYtmDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.tkrs.domain;

public class CbondRateDomain {

	private String company;

	private String date;

	private String code;

	private String rateFormer;
	
	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getRateFormer() {
		return rateFormer;
	}



	public void setRateFormer(String rateFormer) {
		this.rateFormer = rateFormer;
	}



	public CbondRateDomain(String company, String date, String code, String rateFormer) {
		super();
		this.company = company;
		this.date = date;
		this.code = code;
		this.rateFormer = rateFormer;
	}



	public CbondRateDomain() {
		// TODO Auto-generated constructor stub
	}
	
}

package com.tkrs.domain;

import java.util.Date;

public class ModeHistoryDomain {

	private String date;

	private Double riskValue;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(Double riskValue) {
		this.riskValue = riskValue;
	}

	public ModeHistoryDomain(String date, Double riskValue) {
		super();
		this.date = date;
		this.riskValue = riskValue;
	}

	public ModeHistoryDomain() {
		// TODO Auto-generated constructor stub
	}

}

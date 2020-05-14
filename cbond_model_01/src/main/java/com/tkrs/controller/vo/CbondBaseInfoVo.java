package com.tkrs.controller.vo;

public class CbondBaseInfoVo {
	
	private String secName;
	
	private String kmvModelRisk;
	
	private String mfModeRisk;
	
	private String rateFormer;

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public String getKmvModelRisk() {
		return kmvModelRisk;
	}

	public void setKmvModelRisk(String kmvModelRisk) {
		this.kmvModelRisk = kmvModelRisk;
	}

	public String getMfModeRisk() {
		return mfModeRisk;
	}

	public CbondBaseInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CbondBaseInfoVo(String secName, String kmvModelRisk, String mfModeRisk, String rateFormer) {
		super();
		this.secName = secName;
		this.kmvModelRisk = kmvModelRisk;
		this.mfModeRisk = mfModeRisk;
		this.rateFormer = rateFormer;
	}

	public void setMfModeRisk(String mfModeRisk) {
		this.mfModeRisk = mfModeRisk;
	}
	
	public String getRateFormer() {
		return rateFormer;
	}

	public void setRateFormer(String rateFormer) {
		this.rateFormer = rateFormer;
	}


}

package com.tkrs.domain;

public class CbondListDomain {

	public CbondListDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String bclc;
	private String code;
	private Double couponRate;
	private Double interestFrequency;
	private String interestType;
	private String ipoDate;
	private Double issueAmount;
	private String maturityDate;
	private Double par;
	private String paymentType;
	private String rateFormer;
	private String rateIssueFormer;
	private String secName;
	private String cnName;
	private String cnBclc;

	public String getBclc() {
		return bclc;
	}

	public void setBclc(String bclc) {
		this.bclc = bclc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(Double couponRate) {
		this.couponRate = couponRate;
	}

	public Double getInterestFrequency() {
		return interestFrequency;
	}

	public void setInterestFrequency(Double interestFrequency) {
		this.interestFrequency = interestFrequency;
	}

	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}

	public String getIpoDate() {
		return ipoDate;
	}

	public void setIpoDate(String ipoDate) {
		this.ipoDate = ipoDate;
	}

	public Double getIssueAmount() {
		return issueAmount;
	}

	public void setIssueAmount(Double issueAmount) {
		this.issueAmount = issueAmount;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Double getPar() {
		return par;
	}

	public void setPar(Double par) {
		this.par = par;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRateFormer() {
		return rateFormer;
	}

	public void setRateFormer(String rateFormer) {
		this.rateFormer = rateFormer;
	}

	public String getRateIssueFormer() {
		return rateIssueFormer;
	}

	public void setRateIssueFormer(String rateIssueFormer) {
		this.rateIssueFormer = rateIssueFormer;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getCnBclc() {
		return cnBclc;
	}

	public void setCnBclc(String cnBclc) {
		this.cnBclc = cnBclc;
	}

	public CbondListDomain(String bclc, String code, Double couponRate, Double interestFrequency, String interestType,
			String ipoDate, Double issueAmount, String maturityDate, Double par, String paymentType, String rateFormer,
			String rateIssueFormer, String secName, String cnName, String cnBclc) {
		super();
		this.bclc = bclc;
		this.code = code;
		this.couponRate = couponRate;
		this.interestFrequency = interestFrequency;
		this.interestType = interestType;
		this.ipoDate = ipoDate;
		this.issueAmount = issueAmount;
		this.maturityDate = maturityDate;
		this.par = par;
		this.paymentType = paymentType;
		this.rateFormer = rateFormer;
		this.rateIssueFormer = rateIssueFormer;
		this.secName = secName;
		this.cnName = cnName;
		this.cnBclc = cnBclc;
	}

}

package com.tkrs.controller.vo;

import java.util.List;

import com.tkrs.domain.ModeHistoryDomain;

public class CbondInfoVo {
	private String quotedCompany;
	private String code;
	private Double couponRate;
	private Double interestFrequency;
	private String interestType;
	private String ipoDate;
	private Double issueAmout;
	private String maturituDate;
	private Double par;
	private String paymentType;
	private String rateFormer;
	private String rateIssueFormer;
	private String secName;
	private Double pcClose;
	private Double kmvModelRisk;
	private Double mfModeRisk;
	private List<ModeHistoryDomain> kmvModelHistoryList;
	private List<ModeHistoryDomain> mfModelHistoryList;

	public String getQuotedCompany() {
		return quotedCompany;
	}

	public void setQuotedCompany(String quotedCompany) {
		this.quotedCompany = quotedCompany;
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

	public Double getIssueAmout() {
		return issueAmout;
	}

	public void setIssueAmout(Double issueAmout) {
		this.issueAmout = issueAmout;
	}

	public String getMaturituDate() {
		return maturituDate;
	}

	public void setMaturituDate(String maturituDate) {
		this.maturituDate = maturituDate;
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

	public Double getPcClose() {
		return pcClose;
	}

	public void setPcClose(Double pcClose) {
		this.pcClose = pcClose;
	}

	public Double getKmvModelRisk() {
		return kmvModelRisk;
	}

	public void setKmvModelRisk(Double kmvModelRisk) {
		this.kmvModelRisk = kmvModelRisk;
	}

	public Double getMfModeRisk() {
		return mfModeRisk;
	}

	public void setMfModeRisk(Double mfModeRisk) {
		this.mfModeRisk = mfModeRisk;
	}

	public List<ModeHistoryDomain> getKmvModelHistoryList() {
		return kmvModelHistoryList;
	}

	public void setKmvModelHistoryList(List<ModeHistoryDomain> kmvModelHistoryList) {
		this.kmvModelHistoryList = kmvModelHistoryList;
	}

	public List<ModeHistoryDomain> getMfModelHistoryList() {
		return mfModelHistoryList;
	}

	public void setMfModelHistoryList(List<ModeHistoryDomain> mfModelHistoryList) {
		this.mfModelHistoryList = mfModelHistoryList;
	}

	public CbondInfoVo(String quotedCompany, String code, Double couponRate, Double interestFrequency,
			String interestType, String ipoDate, Double issueAmout, String maturituDate, Double par, String paymentType,
			String rateFormer, String rateIssueFormer, String secName, Double pcClose, Double kmvModelRisk,
			Double mfModeRisk, List<ModeHistoryDomain> kmvModelHistoryList,
			List<ModeHistoryDomain> mfModelHistoryList) {
		super();
		this.quotedCompany = quotedCompany;
		this.code = code;
		this.couponRate = couponRate;
		this.interestFrequency = interestFrequency;
		this.interestType = interestType;
		this.ipoDate = ipoDate;
		this.issueAmout = issueAmout;
		this.maturituDate = maturituDate;
		this.par = par;
		this.paymentType = paymentType;
		this.rateFormer = rateFormer;
		this.rateIssueFormer = rateIssueFormer;
		this.secName = secName;
		this.pcClose = pcClose;
		this.kmvModelRisk = kmvModelRisk;
		this.mfModeRisk = mfModeRisk;
		this.kmvModelHistoryList = kmvModelHistoryList;
		this.mfModelHistoryList = mfModelHistoryList;
	}

	public CbondInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}

}

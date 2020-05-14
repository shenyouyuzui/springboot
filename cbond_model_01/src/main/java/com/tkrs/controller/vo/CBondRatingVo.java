package com.tkrs.controller.vo;

import java.util.Date;

public class CBondRatingVo {
	private String code;
	private Date date;
	private String previousRating;
	private Double newRating;
	private long previousDefaultrate;
	private long defaultrate;
	private String secName;
  /**
   * @return code
   */
  public String getCode() {
    return code;
  }
  /**
   * @param code set code
   */
  public void setCode(String code) {
    this.code = code;
  }
  /**
   * @return date
   */
  public Date getDate() {
    return date;
  }
  /**
   * @param date set date
   */
  public void setDate(Date date) {
    this.date = date;
  }
  /**
   * @return previousRating
   */
  public String getPreviousRating() {
    return previousRating;
  }
  /**
   * @param previousRating set previousRating
   */
  public void setPreviousRating(String previousRating) {
    this.previousRating = previousRating;
  }
  /**
   * @return newRating
   */
  public Double getNewRating() {
    return newRating;
  }
  /**
   * @param newRating set newRating
   */
  public void setNewRating(Double newRating) {
    this.newRating = newRating;
  }
  /**
   * @return previousDefaultrate
   */
  public long getPreviousDefaultrate() {
    return previousDefaultrate;
  }
  /**
   * @param previousDefaultrate set previousDefaultrate
   */
  public void setPreviousDefaultrate(long previousDefaultrate) {
    this.previousDefaultrate = previousDefaultrate;
  }
  /**
   * @return defaultrate
   */
  public long getDefaultrate() {
    return defaultrate;
  }
  /**
   * @param defaultrate set defaultrate
   */
  public void setDefaultrate(long defaultrate) {
    this.defaultrate = defaultrate;
  }
  /**
   * @return secName
   */
  public String getSecName() {
    return secName;
  }
  /**
   * @param secName set secName
   */
  public void setSecName(String secName) {
    this.secName = secName;
  }
  /**
   * @param code
   * @param date
   * @param previousRating
   * @param newRating
   * @param previousDefaultrate
   * @param defaultrate
   * @param secName
   */
  public CBondRatingVo(String code, Date date, String previousRating, Double newRating, long previousDefaultrate,
      long defaultrate, String secName) {
    super();
    this.code = code;
    this.date = date;
    this.previousRating = previousRating;
    this.newRating = newRating;
    this.previousDefaultrate = previousDefaultrate;
    this.defaultrate = defaultrate;
    this.secName = secName;
  }
  @Override
  public String toString() {
    return "CBondRatingVo [code=" + code + ", date=" + date + ", previousRating=" + previousRating + ", newRating="
        + newRating + ", previousDefaultrate=" + previousDefaultrate + ", defaultrate=" + defaultrate + ", secName="
        + secName + "]";
  }
	
}

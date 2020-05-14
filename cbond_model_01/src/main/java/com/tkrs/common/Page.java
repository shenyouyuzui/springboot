package com.tkrs.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private int showPageCount = 10;
	@JsonIgnore
	private int showCount = 20;
	private int totalPage;
	private int totalResult;
	private int currentPage;
	private String orderBy;
	@JsonIgnore
	private int currentResult;
	@JsonIgnore
	private boolean entityOrField;

	public int getShowPageCount() {
		return this.showPageCount;
	}

	public void setShowPageCount(int showPageCount) {
		this.showPageCount = showPageCount;
	}

	public int getTotalPage() {
		if (this.totalResult % this.showCount == 0) {
			this.totalPage = this.totalResult / this.showCount;
		} else {
			this.totalPage = this.totalResult / this.showCount + 1;
		}

		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return this.totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (this.currentPage <= 0) {
			this.currentPage = 1;
		}

		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getShowCount() {
		return this.showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		this.currentResult = (this.getCurrentPage() - 1) * this.getShowCount();
		if (this.currentResult < 0) {
			this.currentResult = 0;
		}

		return this.currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return this.entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setOrderByOnly(String orderByOnly) {
		this.showCount = Integer.MAX_VALUE;
		this.currentPage = 1;
		this.orderBy = orderByOnly;
	}
}

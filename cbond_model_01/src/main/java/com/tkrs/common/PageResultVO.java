package com.tkrs.common;

import java.io.Serializable;

public class PageResultVO<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private T list;
	private Page page;

	public PageResultVO() {
	}

	public PageResultVO(T list) {
		this.list = list;
	}

	public PageResultVO(T list, Page page) {
		this.list = list;
		this.page = page;
	}

	public T getList() {
		return this.list;
	}

	public void setList(T list) {
		this.list = list;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}

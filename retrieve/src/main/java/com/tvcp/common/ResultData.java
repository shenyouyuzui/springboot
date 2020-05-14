package com.tvcp.common;

import java.io.Serializable;

public class ResultData<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String status = "0";
	private String msg = "操作成功";
	private T data;
	
	public ResultData() {
    }

	public ResultData(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public ResultData(String status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public ResultData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String toString() {
		return String.format("[status=%s, msg=%s, data=%s]", new Object[] { this.status, this.msg, this.data });
	}

}

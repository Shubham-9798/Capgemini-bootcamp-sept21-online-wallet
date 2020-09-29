package com.onlinewallet.utill;

public class ResponseMsg {
	private String msg;
	private String statusCode;
	public Object HttpStatus;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

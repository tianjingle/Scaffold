package com.inclination.scaffold.constant.config;

public class Error {

	private String code;
	private String msg;
	public Error(String code2, String message) {
		// TODO Auto-generated constructor stub
		this.code=code2;
		this.msg=message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

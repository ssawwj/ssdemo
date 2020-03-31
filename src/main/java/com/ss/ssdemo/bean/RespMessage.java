package com.ss.ssdemo.bean;

/**
 * 响应消息
 * 
 * @author pol.wang
 * 
 */
public class RespMessage {

	private int code;
	private String msg;
	private String retrunMsg;

	public RespMessage() {
	}

	public RespMessage(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public RespMessage(int code, String msg, String retrunMsg) {
		this.code = code;
		this.msg = msg;
		this.retrunMsg = retrunMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRetrunMsg() {
		return retrunMsg;
	}

	public void setRetrunMsg(String retrunMsg) {
		this.retrunMsg = retrunMsg;
	}
}

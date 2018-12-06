package com.crm.crm.common.vo;

import java.io.Serializable;

public class Head implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2828781460169781441L;
	
	public String message;
    public int code;
    public String method;
    public String sessionId;
    public Object obj;
    public long ts=System.currentTimeMillis();
    
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
}

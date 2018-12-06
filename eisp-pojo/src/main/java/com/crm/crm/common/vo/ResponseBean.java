package com.crm.crm.common.vo;

import java.io.Serializable;

public class ResponseBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5349697339350690414L;
	public Head head;
	public Object businessObject;
	
	public Head getHead() {
		return head;
	}
	
	public void setHead(Head head) {
		this.head = head;
	}
	public Object getBusinessObject() {
		return businessObject;
	}
	public void setBusinessObject(Object businessObject) {
		this.businessObject = businessObject;
	}
	
}

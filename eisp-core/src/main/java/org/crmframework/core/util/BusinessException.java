package org.crmframework.core.util;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMsg;

	public BusinessException(String message) {
		super(message);
		this.errorMsg = message;
	}

	public BusinessException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.errorMsg = message;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}

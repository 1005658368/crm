package org.crmframework.core.util;

import org.apache.log4j.Logger;
import org.crmframework.core.vo.AjaxJson;
import java.lang.reflect.InvocationTargetException;

/**
 * 异常处理
 * 
 */
public class ExceptionHandlerUtil {
	public static void handlerException(AjaxJson result, Exception e) {
		result.setSuccess(false);
		// 反射异常首先取出目标异常
		e = isInvocationTargetException(e);
		if (e instanceof BusinessException) {
			BusinessException be = (BusinessException) e;
			result.setMsg(be.getMessage());
			// 错误码-1000,特殊处理
			if ("-1000".equals(be.getErrorCode())) {
				result.setSuccess(true);
			}
		} else {
			Logger logger = Logger.getLogger(ExceptionHandlerUtil.class);
			logger.error(e.toString(), e);
			result.setMsg(e.getMessage());
		}
	}

	public static Exception isInvocationTargetException(Exception e) {
		if (e instanceof InvocationTargetException) {
			e = (Exception) ((InvocationTargetException) e).getTargetException();
			e = isInvocationTargetException(e);
		}
		return e;
	}
}

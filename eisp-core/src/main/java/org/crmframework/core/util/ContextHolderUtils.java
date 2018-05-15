package org.crmframework.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: TODO(上下文工具类)
 */
public class ContextHolderUtils {
	
	//用户自己设计的Session，这里导入功能，自己启动的线程在使用这个属性，自己设置的Session
	private static HttpSession customerSession;

    public static void setCustomerSession(HttpSession customerSession) {
    	ContextHolderUtils.customerSession = customerSession;
	}
    
    /**
     * SpringMvc下获取request
     * 
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
         try {
             request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         }catch (Exception e){
            throw new RuntimeException("HttpServletRequest",e.getCause());
         }

        return request;

    }

    /**
     * SpringMvc下获取session
     * 
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        try{
            session = getRequest().getSession();
        }catch (Exception e){
            throw new RuntimeException("HttpSession",e.getCause());
        }
        return session;
    }

}

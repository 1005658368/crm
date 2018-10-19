package org.crmframework.base.listener;

import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.crmframework.core.service.MutiLangServiceI;
import org.crmframework.core.service.SystemService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 系统初始化监听器,在系统启动时运行,进行一些初始化工作
 * 
 * @author laien
 *
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        SystemService systemService = (SystemService) webApplicationContext.getBean(SystemService.class);
        MutiLangServiceI mutiLangService = (MutiLangServiceI) webApplicationContext.getBean(MutiLangServiceI.class);
//        CommonService commonService = (CommonService) webApplicationContext.getBean("commonServiceImpl");
//        List list=commonService.test();
        /**
         * 第三部分：加载多语言内容
         */
        mutiLangService.initAllMutiLang();
        
    }

}

package org.crmframework.core.listener;

import org.crmframework.core.service.MutiLangServiceI;
import org.crmframework.core.service.SystemService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        SystemService systemService = (SystemService) webApplicationContext.getBean(SystemService.class);
        MutiLangServiceI mutiLangService = (MutiLangServiceI) webApplicationContext.getBean(MutiLangServiceI.class);
        /**
         * 第一部分：对数据字典进行缓存
         */
        systemService.initAllTypeGroups();

        /**
         * 第三部分：加载多语言内容
         */
        mutiLangService.initAllMutiLang();

    }

}


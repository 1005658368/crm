package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.busi.test.service.TestService;
import org.crmframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController {
    @Autowired
    CommonService commonServiceImpl;
    @Reference
    @Autowired(required = false)
    UserService userService;
    @Reference
    @Autowired(required = false)
    TestService testService;

    @RequestMapping(params = "login")
    public String login(){
        System.out.println("cx");
        System.out.println(commonServiceImpl.test().size());
        String test1=userService.test("cx1");
//        String test2=userService2.test("cx2");
        String test3=testService.test("cx3");
        return "login/login2";
    }


}

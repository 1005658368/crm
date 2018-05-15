package com.crm.crm.base.mdm.controller;

import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController {
    @Resource
    CommonServiceImpl commonService;

    @RequestMapping(params = "login")
    public String login(){
        System.out.println(commonService.test().size());
        return "login/login2";
    }


}

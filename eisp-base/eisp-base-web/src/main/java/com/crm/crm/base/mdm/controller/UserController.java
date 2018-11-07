package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.entity.TSRole;
import com.crm.crm.base.mdm.entity.TSUser;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.common.vo.MiniDaoPage;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.tag.easyui.TagUtil;
import org.crmframework.core.vo.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController {
    @Resource
    SystemService systemService;
    @Reference
    @Autowired(required = false)
    UserService userService;
    @Resource
    MiniDaoFastQueryService miniDaoFastQueryService;
    /**
     * 用户列表页面跳转
     * @return
     */
    @RequestMapping(params = "user")
    public ModelAndView user(HttpServletRequest request) {
        return new ModelAndView("user/userList");
    }

    /**
     * 用户列表
     */
    @RequestMapping(params = "userGrid")
    public void userGrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        MiniDaoPage<TSUser> miniDaoPage=userService.findUserList(user,dataGrid.getPage(),dataGrid.getRows());
        dataGrid.setResults(miniDaoPage.getResults());
        dataGrid.setTotal(miniDaoPage.getTotal());
        TagUtil.datagrid(response, dataGrid);
    }


}

package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crm.crm.base.mdm.entity.TSFunction;
import com.crm.crm.base.mdm.entity.TSIcon;
import com.crm.crm.base.mdm.entity.TSRole;
import com.crm.crm.base.mdm.entity.TSUser;
import com.crm.crm.base.mdm.service.RoleService;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.common.vo.MiniDaoPage;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.tag.easyui.TagUtil;
import org.crmframework.core.util.BeanUtil;
import org.crmframework.core.util.ExceptionHandlerUtil;
import org.crmframework.core.util.StringUtil;
import org.crmframework.core.util.oConvertUtils;
import org.crmframework.core.vo.AjaxJson;
import org.crmframework.core.vo.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController {
    @Resource
    SystemService systemService;
    @Reference
    @Autowired(required = false)
    UserService userService;
    @Reference
    @Autowired(required = false)
    RoleService roleService;
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

    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(TSUser user, HttpServletRequest request) {
        if (StringUtil.isNotEmpty(user.getId())) {
            user = systemService.getEntity(TSUser.class, user.getId());
            request.setAttribute("user", user);
        }
        return new ModelAndView("user/user");
    }

    @RequestMapping(params = "saveUser")
    @ResponseBody
    public AjaxJson saveUser(TSUser user, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            if(StringUtil.isNotEmpty(user.getId())){
                TSUser oldUser=systemService.getEntity(TSUser.class,user.getId());
                BeanUtil.apply(user,oldUser);
                user=oldUser;
            }else{
                user.setId(null);
            }
            systemService.saveOrUpdate(user);
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }

    @RequestMapping(params = "userRole")
    public String userRole(TSUser user,String topOrgCode ,HttpServletRequest request) {
        String userId=user.getId();
        request.setAttribute("userId", userId);
        request.setAttribute("addUri", "userController.do?addRoles&userId="+userId);
        request.setAttribute("delUri", "userController.do?delRoles&userId="+userId);
        return "user/userRole";
    }

    @RequestMapping(params = "userRoleGrid")
    public void userRoleGrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        MiniDaoPage<TSRole> miniDaoPage=roleService.findRoleListByUserid(user.getId(),dataGrid.getPage(),dataGrid.getRows());
        dataGrid.setResults(miniDaoPage.getResults());
        dataGrid.setTotal(miniDaoPage.getTotal());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "addRoles")
    @ResponseBody
    public AjaxJson addRoles(String userId,String liststr,HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<TSRole> listrole=JSONObject.parseArray(liststr,TSRole.class);
            String insertSql=" " +
                    " insert into t_s_role_user " +
                    " (id,userid,roleid) " +
                    " values " +
                    " (lower(sys_guid()),'"+userId+"',:id) "
                    ;
            systemService.nameJdbcBatchUpdate(insertSql,listrole);
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }

    @RequestMapping(params = "delRoles")
    @ResponseBody
    public AjaxJson delRoles(String userId,String liststr,HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<TSRole> listrole=JSONObject.parseArray(liststr,TSRole.class);
            String delsql=" " +
                    " delete from t_s_role_user ru " +
                    " where 1=1 and ru.userid='" + userId +"' "+
                    " and ru.roleid=:id "
                    ;
            systemService.nameJdbcBatchUpdate(delsql,listrole);
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }


}

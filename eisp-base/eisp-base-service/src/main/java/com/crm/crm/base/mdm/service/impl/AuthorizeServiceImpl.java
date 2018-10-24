package com.crm.crm.base.mdm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.service.AuthorizeServiceI;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.pojo.entity.*;
import org.crmframework.core.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@com.alibaba.dubbo.config.annotation.Service
@Transactional
public class AuthorizeServiceImpl implements AuthorizeServiceI {
    @Autowired
    private SystemService systemService;
    @Reference
    @Autowired(required = false)
    UserService userService;

    @Override
    public boolean checkFuncAccessByUserId(String funcUrl, String userid) {
        String sql = "SELECT DISTINCT f.id FROM t_s_function f,t_s_role_function  rf,t_s_role_user ru "
                        + " WHERE f.id=rf.functionid AND rf.roleid=ru.roleid AND " + "ru.userid='" + userid
                        + "' AND f.functionurl like '" + funcUrl + "%'";
        List list = systemService.findSql(sql);
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<TSFunction> loadFunctionByUserId(String userId) {
        Set<TSFunction> functions = new HashSet<TSFunction>();
        List<TSRoleUser> rUsers = systemService.findHql("from TSRoleUser ru where ru.TSUser.id=? ",userId);
        for (TSRoleUser ru : rUsers) {
            TSRole role = ru.getTSRole();
            List<TSRoleFunction> rRoleFunction=systemService.findHql("from TSRoleFunction rf where rf.TSRole.id=?",role.getId());
            for (TSRoleFunction rf : rRoleFunction) {
                String functionId=rf.getTSFunction().getId();
                String functionName=rf.getTSFunction().getFunctionName();
                functions.add(rf.getTSFunction());
            }
        }
        List<TSFunction> retFuncs = new ArrayList<TSFunction>();
        retFuncs.addAll(functions);
        return retFuncs;
    }

}

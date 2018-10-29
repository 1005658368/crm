package com.crm.crm.base.mdm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.busi.test.service.TestService;
import com.crm.crm.base.mdm.entity.TSUser;
import org.crmframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    CommonService commonServiceImpl;
    @Reference
    @Autowired(required = false)
    TestService testServiceImpl;
    @Override
    public String test(String test){
        RpcContext rc=RpcContext.getContext();
        System.out.println(commonServiceImpl.test().size());
        testServiceImpl.test("cx6");
        return test;
    }

    @Override
    public TSUser checkUserExits(TSUser user) {
        String hql = "from TSUser u where u.userName = :userName and u.passWord=:passWord";
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("userName", user.getUserName());
        paramMap.put("passWord", user.getPassWord());
        List<TSUser> userList=commonServiceImpl.findHql(hql,paramMap);
        if (userList != null && userList.size() > 0) {
            user=userList.get(0);
            return user;
        }
        return null;
    }

}

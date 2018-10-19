package com.crm.crm.base.mdm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.busi.test.service.TestService;
import org.crmframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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

}

package com.crm.crm.base.mdm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.crm.crm.base.mdm.dao.FunctionDao;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.pojo.vo.TSFunctionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
@Transactional
public class FunctionServiceImpl implements FunctionService{
    @Autowired
    FunctionDao functionDao;

    @Override
    public List<TSFunctionVo> findFunctionByUserId(String userId) {
        List<TSFunctionVo> result=functionDao.findFunctionByUserIdSQL(userId);
        return result;
    }
}

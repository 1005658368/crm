package com.crm.crm.base.mdm.service;

import com.crm.crm.pojo.vo.TSFunctionVo;

import java.util.List;

public interface FunctionService {

    List<TSFunctionVo> findFunctionByUserId(String userId);

}

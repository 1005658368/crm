package com.crm.crm.base.mdm.service;

import com.crm.crm.base.mdm.vo.TSFunctionVo;

import java.util.List;
import java.util.Map;

public interface FunctionService {

    List<TSFunctionVo> findFunctionByUserId(String userId);

    Map<String,Map<String,TSFunctionVo>> setChildren(List<TSFunctionVo> functionList);
}

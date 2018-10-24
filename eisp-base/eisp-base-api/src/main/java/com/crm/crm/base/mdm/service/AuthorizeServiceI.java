package com.crm.crm.base.mdm.service;


import com.crm.crm.pojo.entity.TSFunction;

import java.util.List;

/**
 *　提供综合的角色，　权限，　菜单访问权等方面的功能访问接口的内部实现。
 *
 */
public interface AuthorizeServiceI {

    /**
     * 检查指定的功能请求路径是否有权被指定用户访问。
     * 
     * TODO: 考虑模块化的扩展。
     * 
     * @param funcUrl
     * @param userId
     * @return
     */
    boolean checkFuncAccessByUserId(String funcUrl, String userId);

    /**
     * 加载由指定的用户可以访问的功能项列表。
     * 
     * @param userId
     * @return
     */
    List<TSFunction> loadFunctionByUserId(String userId);

}

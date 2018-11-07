package com.crm.crm.base.mdm.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.crm.crm.base.mdm.dao.RoleDao;
import com.crm.crm.base.mdm.entity.TSRole;
import com.crm.crm.base.mdm.service.RoleService;
import com.crm.crm.common.vo.MiniDaoPage;
import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.crmframework.core.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Autowired
    SystemService systemService;

    @Override
    public MiniDaoPage<TSRole> findRoleList(TSRole role, int page, int rows) {
        MiniDaoPage<TSRole> miniDaoPage=roleDao.findRoleList(role,page,rows);
        return miniDaoPage;
    }

    @Override
    public void updateRoleFunction(String roleId, List<String> functionIdList){
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("roleId",roleId);
        paramMap.put("functionIdList",functionIdList);
        String delsql=" " +
                " delete from t_s_role_function rf " +
                " where 1=1 and rf.roleid=:roleId " +
                " and rf.functionid not in (:functionIdList) "
                ;
        systemService.nameJdbcUpdate(delsql,paramMap);
        String findNeedInsertSql=" " +
                " select :roleId as roleId,f.id as functionId from t_s_function f " +
                " left join t_s_role_function rf  on rf.functionid=f.id and rf.roleid=:roleId " +
                " where 1=1 " +
                " and f.id in (:functionIdList) " +
                " and rf.id is null "
                ;
        List<Map<String, Object>> needInsertMapList=systemService.findForNameJdbc(findNeedInsertSql,paramMap);
        String insertSql=" " +
                " insert into t_s_role_function " +
                " (id,functionid,roleid,datarule,create_date,operation) " +
                " values " +
                " (lower(sys_guid()),:FUNCTIONID,:ROLEID,null,systimestamp,null) "
                ;
        HashMap[] paramMapArray=needInsertMapList.toArray(new HashMap[needInsertMapList.size()]);
        systemService.nameJdbcBatchUpdate(insertSql,paramMapArray);
    }

}

package com.crm.crm.base.mdm.service;

import com.crm.crm.base.mdm.entity.TSRole;
import com.crm.crm.common.vo.MiniDaoPage;

import java.util.List;

public interface RoleService {

    MiniDaoPage<TSRole> findRoleList(TSRole role, int page, int rows);

    void updateRoleFunction(String roleId, List<String> functionIdList);

    MiniDaoPage<TSRole> findRoleListByUserid(String userId, int page, int rows);
}

package com.crm.crm.base.mdm.dao;

import com.crm.crm.base.mdm.entity.TSRole;
import com.crm.crm.common.vo.MiniDaoPage;
import org.crmframework.core.minidao.annotation.Arguments;
import org.crmframework.core.minidao.annotation.MiniDao;
import org.crmframework.core.minidao.annotation.ResultType;
import org.crmframework.core.minidao.annotation.Sql;

import java.util.List;

@MiniDao
public interface RoleDao {

    public static final String findRoleListSql =
        " select r.* from t_s_role r where 1=1 " +
        " <#if role.roleName ?exists && role.roleName ?length gt 0>" +
        " and r.rolename like '%'||:role.roleName||'%' " +
        " </#if>"+
        " <#if role.roleCode ?exists && role.roleCode ?length gt 0>" +
        " and r.rolecode like '%'||:role.roleCode||'%' " +
        " </#if>"+
        " <#if role.roleType ?exists && role.roleType ?length gt 0>" +
        " and r.role_type like '%'||:role.roleType||'%' " +
        " </#if>"+
        " ";
    @Sql(findRoleListSql)
    @Arguments({"role","page","rows"})
    @ResultType("com.crm.crm.base.mdm.entity.TSRole")
    public MiniDaoPage<TSRole> findRoleList(TSRole role, int page, int rows);

    public static final String findRoleListByUserid =
        " select r.* from t_s_role r join t_s_role_user ru on r.id=ru.roleid " +
        " where 1=1 " +
        " and ru.userid=:userId " +
        " ";
    @Sql(findRoleListByUserid)
    @Arguments({"userId","page","rows"})
    @ResultType("com.crm.crm.base.mdm.entity.TSRole")
    public MiniDaoPage<TSRole> findRoleListByUserid(String userId, int page, int rows);

}

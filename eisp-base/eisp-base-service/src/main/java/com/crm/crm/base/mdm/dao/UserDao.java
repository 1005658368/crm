package com.crm.crm.base.mdm.dao;

import com.crm.crm.base.mdm.entity.TSUser;
import com.crm.crm.common.vo.MiniDaoPage;
import org.crmframework.core.minidao.annotation.Arguments;
import org.crmframework.core.minidao.annotation.MiniDao;
import org.crmframework.core.minidao.annotation.ResultType;
import org.crmframework.core.minidao.annotation.Sql;

@MiniDao
public interface UserDao {

    public static final String findUserListSql =
        " select * from t_s_user u join t_s_base_user bu on bu.id=u.id " +
        " where 1=1 " +
        " <#if user.userName ? exists && user.userName ? length gt 0>" +
        " and bu.username like '%'||:user.userName||'%' " +
        " </#if>"+
        " <#if user.realName ? exists && user.realName ? length gt 0>" +
        " and bu.realname like '%'||:user.realName||'%' " +
        " </#if>"+
        " <#if user.mobilePhone ? exists && user.mobilePhone ? length gt 0>" +
        " and u.mobilePhone like '%'||:user.mobilePhone||'%' " +
        " </#if>"+
        " <#if user.email ? exists && user.email ? length gt 0>" +
        " and u.email like '%'||:user.email||'%' " +
        " </#if>"+
        " <#if user.userCode ? exists && user.userCode ? length gt 0>" +
        " and u.userCode like '%'||:user.userCode||'%' " +
        " </#if>"+
            " ";
    @Sql(findUserListSql)
    @Arguments({"user","page","rows"})
    @ResultType("com.crm.crm.base.mdm.entity.TSUser")
    public MiniDaoPage<TSUser> findUserList(TSUser user, int page, int rows);

}

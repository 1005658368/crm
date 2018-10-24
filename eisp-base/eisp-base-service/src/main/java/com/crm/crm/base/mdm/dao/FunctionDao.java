package com.crm.crm.base.mdm.dao;

import com.crm.crm.pojo.vo.TSFunctionVo;
import org.crmframework.core.minidao.annotation.Arguments;
import org.crmframework.core.minidao.annotation.MiniDao;
import org.crmframework.core.minidao.annotation.ResultType;
import org.crmframework.core.minidao.annotation.Sql;

import java.util.List;

@MiniDao
public interface FunctionDao {
    public static final String findFunctionByUserIdSql =
            " select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,f.functionorder as functionorder,f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,f.iconid as iconid,f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas from t_s_role_user ru join t_s_role_function rf on ru.roleid=rf.roleid join t_s_function f on f.id=rf.functionid join t_s_icon i on i.id=f.iconid where 1=1 " +
                    " <#if userId ?exists && userId ?length gt 0>" +
                    " and ru.userid=:userId " +
                    " </#if>"+
                    " ";
    @Sql(findFunctionByUserIdSql)
    @Arguments({ "userId" })
    @ResultType("com.crm.crm.pojo.vo.TSFunctionVo")
    List<TSFunctionVo> findFunctionByUserId(String userId);

    /**
     * 查询符合活动执行的政策类数据
     *
     * @return
     */
    @Arguments({ "userId" })
    @ResultType("com.crm.crm.pojo.vo.TSFunctionVo")
    List<TSFunctionVo> findFunctionByUserIdSQL(String userId);
}
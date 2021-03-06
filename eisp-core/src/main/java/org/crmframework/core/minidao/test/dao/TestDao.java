package org.crmframework.core.minidao.test.dao;

import org.crmframework.core.entity.BaseEntity;
import org.crmframework.core.minidao.annotation.Arguments;
import org.crmframework.core.minidao.annotation.MiniDao;
import org.crmframework.core.minidao.annotation.ResultType;
import org.crmframework.core.minidao.annotation.Sql;
import org.crmframework.core.minidao.pojo.MiniDaoPage;

@MiniDao
public interface TestDao {

    /**
     * minidao写法1
     * 参数page和rows,类型顺序均不能修改，否则会出现不能分页情况
     */
    public static final String findTest1List="select id as id from t_s_base_user";
    @Sql(findTest1List)
    @Arguments({"page","rows"})
    @ResultType("org.crmframework.core.entity.BaseEntity")
    public MiniDaoPage<BaseEntity> findTest1List(int page, int rows);

    /**
     * minidao写法2
     * 参数page和rows,类型顺序均不能修改，否则会出现不能分页情况
     */
    @Arguments({"vo","page","rows"})
    @ResultType("org.crmframework.core.entity.BaseEntity")
    public MiniDaoPage<BaseEntity> findTest2List(BaseEntity vo,int page, int rows);

}

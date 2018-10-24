package org.crmframework.core.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CommonService {
    public List test();

    <T>List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

    List findHql(String hql);

    List findHql(String hql, Object... param);

    List findHql(String hql, Map<String, Object> paramMap);

    List findSql(String sql);

    List findSql(String sql, Object... param);

    List findSql(String sql, Map<String, Object> paramMap);

    List<Map<String, Object>> findForJdbc(String sql, Object... objs);

    <T> T getEntity(Class entityName, Serializable id);
}

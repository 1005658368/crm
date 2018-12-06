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

    List<Map<String, Object>> findForNameJdbc(String sql, Map<String, Object> paramMap);

    <T> Serializable save(T entity);

    <T> void delete(T entity);

    <T> void update(T pojo);

    <T> void saveOrUpdate(T entity);

    void jdbcUpdate(String sql, Object... obj);

    void jdbcBatchUpdate(String sql, List<Object[]> batchArgs);

    void nameJdbcUpdate(String sql, Map<String, Object> paramMap);

    void nameJdbcUpdate(String sql, Object obj);

    void nameJdbcBatchUpdate(String sql, Map<String, Object>[] paramMapArray);

    void nameJdbcBatchUpdate(String sql, List objList);

}

package org.crmframework.core.common.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericBaseCommonDao {
    public Session getSession();

    List findHql(String hql, Object... param);

    List findHql(String hql, Map<String, Object> paramMap);

    List findSql(String sql);

    List findSql(String sql, Object... param);

    List findSql(String sql, Map<String, Object> paramMap);

    List<Map<String, Object>> findForJdbc(String sql, Object... objs);

    <T> T getEntity(Class entityName, Serializable id);

    <T>List<T> loadAll(Class<T> entityClass);

    <T>List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

    List findHql(String query);


    List<Map<String, Object>> findForNameJdbc(String sql, Map<String, Object> paramMap);

    <T> Serializable save(T entity);

    <T> void update(T pojo);

    <T> void saveOrUpdate(T entity);

    <T> void delete(T entity);

    void jdbcUpdate(String sql, Object... obj);

    void jdbcBatchUpdate(String sql, List<Object[]> batchArgs);

    void nameJdbcUpdate(String sql, Map<String, Object> paramMap);

    void nameJdbcUpdate(String sql, Object obj);

    void nameJdbcBatchUpdate(String sql, Map<String, Object>[] paramMapArray);

    void nameJdbcBatchUpdate(String sql, List<Object> objList);
}

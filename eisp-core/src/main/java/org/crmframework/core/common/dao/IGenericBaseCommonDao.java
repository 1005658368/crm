package org.crmframework.core.common.dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Map;

public interface IGenericBaseCommonDao {
    public Session getSession();

    <T> List<T> loadAll(Class<T> entityClass);

    <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

    List<Map<String, Object>> findForJdbc(String sql, Object... objs);
}

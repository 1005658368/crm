package org.crmframework.core.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {
    public List test();

    <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

    List<Map<String, Object>> findForJdbc(String sql, Object... objs);
}

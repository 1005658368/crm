package org.crmframework.core.common.dao.impl;

import org.crmframework.core.common.dao.IGenericBaseCommonDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class GenericBaseCommonDao<T, PK extends Serializable> implements IGenericBaseCommonDao {
    private static final Logger logger = LoggerFactory.getLogger(GenericBaseCommonDao.class);

    @Resource
    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> T getEntity(Class entityName, Serializable id) {
        T t = (T) getSession().get(entityName, id);
        if (t != null) {
            getSession().flush();
        }
        return t;
    }

    @Override
    public <T>List<T> loadAll(final Class<T> entityClass) {
        Criteria criteria = createCriteria(entityClass);
        return criteria.list();
    }

    private <T>Criteria createCriteria(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        return criteria;
    }


    @Override
    public <T>List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
        return createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
    }

    private <T>Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    @Override
    public List<T> findHql(String hql) {
        Query query = getSession().createQuery(hql);
        List<T> list = query.list();
        return list;

    }

    @Override
    public List<T> findHql(String hql, Object... param) {
        Query query = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                query.setParameter(i, param[i]);
            }
        }
        return query.list();
    }

    @Override
    public List<T> findHql(String hql, Map<String,Object> paramMap) {
        Query query = getSession().createQuery(hql);
        if (paramMap != null && paramMap.size() > 0 ) {
            for(Map.Entry entry: paramMap.entrySet()){
                query.setParameter(entry.getKey().toString(),entry.getValue());
            }
        }
        return query.list();
    }

    @Override
    public List<T> findSql(String sql) {
        Query q = getSession().createSQLQuery(sql);
        return q.list();
    }

    @Override
    public List<T> findSql(String sql,Object... param) {
        Query query = getSession().createSQLQuery(sql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                query.setParameter(i, param[i]);
            }
        }
        return query.list();
    }

    @Override
    public List<T> findSql(String sql, Map<String,Object> paramMap) {
        Query query = getSession().createSQLQuery(sql);
        if (paramMap != null && paramMap.size() > 0 ) {
            for(Map.Entry entry: paramMap.entrySet()){
                query.setParameter(entry.getKey().toString(),entry.getValue());
            }
        }
        return query.list();
    }


    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return this.jdbcTemplate.queryForList(sql, objs);
    }


}

package org.crmframework.core.common.service.impl;

import com.crm.crm.base.mdm.entity.MutiLangEntity;
import org.crmframework.core.common.dao.ICommonDao;
import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.minidao.test.dao.TestDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Transactional
public class CommonServiceImpl<T, PK extends Serializable> implements CommonService{
    @Resource
    public ICommonDao commonDao;

    @Override
    public List test() {
//        List tlist=commonDao.getSession().createQuery("from TSFunction").list();
//        System.out.println(tlist.size());
        Map<String, Object> paramMap=new HashMap<>();
//        return jdbcTemplate.queryForList("select id as id from t_s_base_user");
//        return namedParameterJdbcTemplate.queryForList("select id as id from t_s_base_user",paramMap);
//        List list=commonDao.getSession().createSQLQuery("select id as id from t_s_base_user").list();
        List list = this.commonDao.loadAll(MutiLangEntity.class);

        return list;
//        List list=miniDaoFastQueryService.queryReturnMinidaoList("select id as id from t_s_base_user",paramMap,BaseEntity.class);

//        BaseEntity vo=new BaseEntity();
//        vo.setId("356F9B326FA43C97E0534BC9020A2587");
//        MiniDaoPage<BaseEntity> miniDaoPage= testDao.findTest1List(1,10);
//        MiniDaoPage<BaseEntity> miniDaoPage2= testDao.findTest2List(vo,1,10);
//        return miniDaoPage2.getResults();
    }

    @Override
    public <T> T getEntity(Class entityName, Serializable id) {
        return commonDao.getEntity(entityName, id);
    }

    @Override
    public <T>List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
        return commonDao.findByProperty(entityClass,propertyName,value);
    }

    @Override
    public List<T> findHql(String hql) {
        return commonDao.findHql(hql);

    }

    @Override
    public List<T> findHql(String hql, Object... param) {
        return commonDao.findHql(hql,param);
    }

    @Override
    public List<T> findHql(String hql, Map<String, Object> paramMap) {
        return commonDao.findHql(hql,paramMap);
    }

    @Override
    public List<T> findSql(String sql) {
        return commonDao.findSql(sql);

    }

    @Override
    public List<T> findSql(String sql, Object... param) {
        return commonDao.findSql(sql,param);
    }

    @Override
    public List<T> findSql(String sql, Map<String, Object> paramMap) {
        return commonDao.findSql(sql,paramMap);
    }

    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return commonDao.findForJdbc(sql, objs);
    }

    @Override
    public List<Map<String, Object>> findForNameJdbc(String sql, Map<String,Object> paramMap) {
        return commonDao.findForNameJdbc(sql, paramMap);
    }

    @Override
    public <T> Serializable save(T entity) {
        try {
            return commonDao.save(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public <T> void delete(T entity) {
        try {
            commonDao.delete(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public <T> void update(T pojo) {
        commonDao.update(pojo);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        try {
            commonDao.saveOrUpdate(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void jdbcUpdate(String sql, Object... obj){
        commonDao.jdbcUpdate(sql,obj);
    }

    @Override
    public void jdbcBatchUpdate(String sql, List<Object[]> batchArgs){
        commonDao.jdbcBatchUpdate(sql,batchArgs);
    }

    @Override
    public void nameJdbcUpdate(String sql, Map<String, Object> paramMap){
        commonDao.nameJdbcUpdate(sql,paramMap);
    }

    @Override
    public void nameJdbcUpdate(String sql, Object obj){
        commonDao.nameJdbcUpdate(sql,obj);
    }

    @Override
    public void nameJdbcBatchUpdate(String sql, Map<String, Object>[] paramMapArray){
        commonDao.nameJdbcBatchUpdate(sql,paramMapArray);
    }

    @Override
    public void nameJdbcBatchUpdate(String sql, List objList){
        commonDao.nameJdbcBatchUpdate(sql,objList);
    }

}

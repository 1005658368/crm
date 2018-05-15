package org.crmframework.core.common.service.impl;

import org.crmframework.core.common.dao.ICommonDao;
import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.minidao.test.dao.TestDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService{
    @Resource
    public ICommonDao commonDao;
    @Resource
    public JdbcTemplate jdbcTemplate;
    @Resource
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Resource
    public MiniDaoFastQueryService miniDaoFastQueryService;
    @Resource
    public TestDao testDao;

    public ICommonDao getCommonDao() {
        return commonDao;
    }

    public void setCommonDao(ICommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List test() {
        Map<String, Object> paramMap=new HashMap<>();
        //return jdbcTemplate.queryForList("select id as id from t_s_base_user");
//        return namedParameterJdbcTemplate.queryForList("select id as id from t_s_base_user",paramMap);
        List list=commonDao.getSession().createSQLQuery("select id as id from t_s_base_user").list();
        return list;
//        List list=miniDaoFastQueryService.queryReturnMinidaoList("select id as id from t_s_base_user",paramMap,BaseEntity.class);

//        BaseEntity vo=new BaseEntity();
//        vo.setId("356F9B326FA43C97E0534BC9020A2587");
//        MiniDaoPage<BaseEntity> miniDaoPage= testDao.findTest1List(1,10);
//        MiniDaoPage<BaseEntity> miniDaoPage2= testDao.findTest2List(vo,1,10);
//        return miniDaoPage.getResults();
    }

    @Override
    public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
        return commonDao.findByProperty(entityClass, propertyName, value);
    }

    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return commonDao.findForJdbc(sql, objs);
    }

}

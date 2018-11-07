package org.crmframework.core.minidao.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.crm.crm.common.vo.MiniDaoPage;
import org.apache.commons.lang3.StringUtils;
import org.crmframework.core.minidao.util.FreemarkerParseFactory;
import org.crmframework.core.minidao.util.MiniDaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;


/**
 * miniDao快速开发Service
 */
public class MiniDaoFastQueryService {
	private static final Logger logger = LoggerFactory.getLogger(MiniDaoFastQueryService.class);

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private String dbType = "oracle";
	
	/**
	 * 查询当个实体对象
	 * @param sql 需要查询的sql语句
	 * @param paramMap 参数类
	 * @param returnType 返回值class
	 * @return 返回对象
	 */
	public <T> T queryReturnMinidaoObject(String sql, Map<String, Object> paramMap, Class<T> returnType){
		if(StringUtils.isNotEmpty(sql) && returnType != null){
			//格式化sql语句
			String executeSql = FreemarkerParseFactory.parseTemplateContent(sql,paramMap);
			logger.debug("sql--->" + executeSql);
			if(returnType.isPrimitive()){
				//如果为基本类型
				Number number = null;
				if(paramMap != null){
					number = namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, BigDecimal.class);
				}else{
					number = jdbcTemplate.queryForObject(executeSql, BigDecimal.class);
				}
				if ("int".equals(returnType.getCanonicalName())) {
					return (T)Integer.valueOf(number.intValue());
				} else if ("long".equals(returnType.getCanonicalName())) {
					return (T)Long.valueOf(number.longValue());
				} else if ("double".equals(returnType.getCanonicalName())) {
					return (T)Double.valueOf(number.doubleValue());
				}
			}else if(returnType.isAssignableFrom(String.class)){
				if (paramMap != null) {
					return (T)namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, String.class);
				} else {
					return (T)jdbcTemplate.queryForObject(executeSql, String.class);
				}
			}else{
				// 对象类型
				RowMapper<?> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(returnType);
				if (paramMap != null) {
					return (T)namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, rowMapper);
				} else {
					return (T)jdbcTemplate.queryForObject(executeSql, rowMapper);
				}
			}
		}
		return null;
	}
	
	/**
	 * 查询当个实体对象
	 * @param sql 需要查询的sql语句
	 * @param paramMap 参数类
	 * @param returnType 返回值class
	 * @return 返回对象
	 */
	public <T> List<T> queryReturnMinidaoList(String sql, Map<String, Object> paramMap, Class<T> returnType){
		if(StringUtils.isNotEmpty(sql) && returnType != null){
			//格式化sql语句
			String executeSql = FreemarkerParseFactory.parseTemplateContent(sql,paramMap);
			logger.debug("sql--->" + executeSql);
			if(returnType.isAssignableFrom(String.class)){
				if (paramMap != null) {
					return (List<T>)namedParameterJdbcTemplate.queryForList(executeSql, paramMap, String.class);
				} else {
					return (List<T>)jdbcTemplate.queryForList(executeSql, String.class);
				}
			}else{
				RowMapper<?> rowMapper = getColumnMapRowMapper(returnType);
				if(paramMap != null){
					return (List<T>)namedParameterJdbcTemplate.query(executeSql, paramMap, rowMapper);
				}else{
					return (List<T>)jdbcTemplate.query(executeSql, rowMapper);
				}
			}
		}
		return null;
	}
	
	/**
	 * 查询当个实体对象
	 * @param sql 需要查询的sql语句
	 * @param paramMap 参数类
	 * @param page 当前页数
	 * @param rows 一页多少行
	 * @param returnType 返回值class
	 * 
	 * @return 返回对象
	 */
	public MiniDaoPage queryReturnMinidaoMiniDaoPage(String sql, Map<String, Object> paramMap, int page, int rows, Class<?> returnType){
		if(StringUtils.isNotEmpty(sql) && returnType != null && page != 0 && rows != 0){
			//格式化sql语句
			String executeSql = FreemarkerParseFactory.parseTemplateContent(sql,paramMap);
			logger.debug("sql--->" + executeSql);
			MiniDaoPage pageSetting = new MiniDaoPage();
			pageSetting.setPage(page);
			pageSetting.setRows(rows);
			
			String countSql = getCountSql(executeSql);
			logger.debug("sql(count)--->" + executeSql);
			if(paramMap != null){
				Integer count = namedParameterJdbcTemplate.queryForInt(countSql, paramMap);
				pageSetting.setTotal(count);
			}else{
				Integer count = jdbcTemplate.queryForInt(countSql);
				pageSetting.setTotal(count);
			}
			executeSql = MiniDaoUtil.createPageSql(dbType, executeSql, page, rows);
			RowMapper<?> rowMapper = getColumnMapRowMapper(returnType);
			List list;
			if(paramMap != null){
				list = namedParameterJdbcTemplate.query(executeSql, paramMap, rowMapper);
			}else{
				list = jdbcTemplate.query(executeSql, rowMapper);
			}
			pageSetting.setResults(list);
			return pageSetting;
		}
		return null;
	}
	
	
	/**
	 * 获取总数sql - 如果要支持其他数据库，修改这里就可以
	 * 
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql) {
		return "select count(0) from (" + sql + ") ";
	}
	
	private RowMapper<?> getColumnMapRowMapper(Class<?> returnClass){
		if(returnClass != null){
			return ParameterizedBeanPropertyRowMapper.newInstance(returnClass);
		}
		return new ColumnMapRowMapper();
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
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

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
}

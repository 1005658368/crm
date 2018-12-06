package com.crm.crm.base.system.controller;

import com.crm.crm.common.vo.DuplicateCheckPage;
import org.apache.commons.lang.StringUtils;
import org.crmframework.core.vo.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/duplicateCheckAction")
public class DuplicateCheckAction {

    @Autowired
    // SQL 使用JdbcDao
    private JdbcTemplate jdbcTemplate;

    /**
     * 校验数据是否在系统中是否存在
     *
     * @return
     */
    @RequestMapping(params = "doDuplicateCheck")
    @ResponseBody
    public AjaxJson doDuplicateCheck(DuplicateCheckPage duplicateCheckPage, HttpServletRequest request) {

        AjaxJson j = new AjaxJson();
        Long num = null;

        if (StringUtils.isNotBlank(duplicateCheckPage.getRowObid())) {
            // [2].编辑页面校验
            String sql = "SELECT count(*) FROM " + duplicateCheckPage.getTableName() + " WHERE "
                    + duplicateCheckPage.getFieldName() + " =? and id != ?";
            num = jdbcTemplate.queryForObject(sql, new Object[] { duplicateCheckPage.getFieldVlaue(),
                    duplicateCheckPage.getRowObid() },Long.class);
        } else {
            // [1].添加页面校验
            String sql = "SELECT count(*) FROM " + duplicateCheckPage.getTableName() + " WHERE "
                    + duplicateCheckPage.getFieldName() + " =?";
            num = jdbcTemplate.queryForObject(sql, new Object[] { duplicateCheckPage.getFieldVlaue() },Long.class);
        }

        if (num == null || num == 0) {
            // 该值可用
            j.setSuccess(true);
            j.setMsg("该值可用！");
        } else {
            // 该值不可用
            j.setSuccess(false);
            j.setMsg("该值不可用，系统中已存在！");
        }
        return j;
    }
}

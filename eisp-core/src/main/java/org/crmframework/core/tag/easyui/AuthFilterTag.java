package org.crmframework.core.tag.easyui;

import com.crm.crm.pojo.entity.TSOperation;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.ApplicationContextUtil;
import org.crmframework.core.util.Globals;
import org.crmframework.core.util.ResourceUtil;
import org.crmframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Set;

/**
 * 
 * @Title:AuthFilterTag
 * @description:列表按钮权限过滤
 * @author: biz
 * @date Aug 24, 2013 7:46:57 PM
 * @version V1.0
 */
public class AuthFilterTag extends TagSupport {
    /** 列表容器的ID */
    protected String name;
    @Autowired
    private static SystemService systemService;

    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;

    }

    protected Object end() {
        StringBuilder out = new StringBuilder();
        getAuthFilter(out);
        return out;
    }

    /**
     * 获取隐藏按钮的JS代码
     * 
     * @param out
     */
    @SuppressWarnings("unchecked")
    protected void getAuthFilter(StringBuilder out) {
        out.append("<script type=\"text/javascript\">");
        out.append("$(document).ready(function(){");
        // update-begin--Author:anchao Date:20140822 for：[bugfree号]字段级权限（表单，列表）--------------------
        if (ResourceUtil.getSessionUser().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
        } else {
            Set<String> operationCodes = (Set<String>) super.pageContext.getRequest().getAttribute(
                            Globals.OPERATIONCODES);
            if (null != operationCodes) {
                for (String MyoperationCode : operationCodes) {
                    if (oConvertUtils.isEmpty(MyoperationCode))
                        break;
                    systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                    TSOperation operation = systemService.getEntity(TSOperation.class, MyoperationCode);
                    if (operation.getOperationType().intValue() == Globals.OPERATION_TYPE_HIDE) {
                        out.append("$(\"#" + name + "\").find(\"#" + operation.getOperationcode().replaceAll(" ", "")
                                        + "\").hide();");
                    } else {
                        out.append("$(\"#" + name + "\").find(\"#" + operation.getOperationcode().replaceAll(" ", "")
                                        + "\").find(\":input\").attr(\"disabled\",\"disabled\");");
                    }
                }
            }

        }
        // update-end--Author:anchao Date:20140822 for：[bugfree号]字段级权限（表单，列表）--------------------
        out.append("});");
        out.append("</script>");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

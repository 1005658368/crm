package org.crmframework.core.tag.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 
 * 类描述：改变HTML控件颜色
 * 
 * @author: Biz
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class ColorChangeTag extends TagSupport {
    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        StringBuffer sb = new StringBuffer();
        return sb;
    }

}
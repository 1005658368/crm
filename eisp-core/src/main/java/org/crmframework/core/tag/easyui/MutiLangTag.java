package org.crmframework.core.tag.easyui;

import org.crmframework.core.service.MutiLangServiceI;
import org.crmframework.core.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 类描述：MutiLang标签处理类
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked", "static-access" })
public class MutiLangTag extends TagSupport {
    protected String langKey;
    protected String langArg;

    @Autowired
    private static MutiLangServiceI mutiLangService;

    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        if (mutiLangService == null) {
            mutiLangService = ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
        }

        String lang_context = mutiLangService.getLang(langKey, langArg);

        return new StringBuffer(lang_context);
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public void setLangArg(String langArg) {
        this.langArg = langArg;
    }
}

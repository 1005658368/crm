package org.crmframework.core.tag.easyui;

import org.crmframework.core.minidao.map.MiniDaoLinkedMap;
import org.crmframework.core.util.MutiLangUtil;
import org.crmframework.core.util.ReflectHelper;
import org.crmframework.core.util.StringUtil;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SelectTag extends TagSupport {
    private static final long serialVersionUID = 1;
    private String name;// select名称
    private String id;// select id
    private String defaultVal;// 默认值
    private String divClass;// 默认样式
    private String labelClass;
    private String list;
    private String selectValue;
    private String selectText;
    private String datatype;
    private String nullmsg;
    private String onchange;
    private String title;

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
        if (StringUtil.isBlank(divClass)) {
            divClass = "form"; // 默认form样式
        }
        if (StringUtil.isBlank(labelClass)) {
            labelClass = "Validform_label"; // 默认label样式
        }

        sb.append("<select name=\"" + name + "\" ");
        if (!StringUtil.isBlank(title)) {
    		sb.append(" title=\""+title+"\"");
		}
        if (!StringUtil.isBlank(this.id)) {
            sb.append(" id=\"" + id + "\"");
        }
        if (StringUtil.isNotEmpty(datatype)) {
            sb.append(" datatype=\"" + datatype + "\"");
        }
        if (StringUtil.isNotEmpty(nullmsg)) {
            sb.append(" nullmsg=\"" + nullmsg + "\"");
        }
        if(StringUtil.isNotEmpty(onchange)){
        	sb.append(" onchange=\""+onchange+"\"");
        }
        sb.append(">");
        // update-begin--Author:Biz Date:20140724 for：[bugfree号]默认选择项目--------------------
        select("common.please.select", "", sb);
        // update-end--Author:Biz Date:20140724 for：[bugfree号]默认选择项目----------------------
        List resultList = (List) pageContext.getRequest().getAttribute(list);

        if (resultList != null && resultList.size() > 0) {
            for (Object obj : resultList) {
                ReflectHelper reflectHelper = new ReflectHelper(obj);
                
                if(obj instanceof MiniDaoLinkedMap){
                	MiniDaoLinkedMap map = (MiniDaoLinkedMap)obj;
                	String value = (String)map.get(selectValue);
                	String text = (String)map.get(selectText);
                	select(text, value, sb);
                }else if(obj instanceof Map){
                	Map<String, String> map = (Map<String, String>)obj;
                	select(map.get("value"), map.get("key"), sb);
                }else{
                	String value = objectChangeStringObject(reflectHelper.getMethodValue(selectValue));
                    String text = objectChangeStringObject(reflectHelper.getMethodValue(selectText));
                    select(text, value, sb);
                }
            }
        }

        sb.append("</select>");

        return sb;
    }

    private String objectChangeStringObject(Object obj) {
        String strResult = null;
        if (obj instanceof Integer) {
            strResult = ((Integer) obj).toString();
        } else if (obj instanceof Double) {
            strResult = ((Double) obj).toString();
        } else {
            strResult = (String) obj;
        }
        return strResult;
    }

    /**
     * 选择框方法
     * 
     * @作者：Alexander
     * 
     * @param text
     * @param value
     * @param sb
     */
    private void select(String text, String value, StringBuffer sb) {
        if (StringUtil.isNotEmpty(this.defaultVal) && value.equals(this.defaultVal)) {
            sb.append(" <option value=\"" + value + "\" selected=\"selected\">");
        } else {
            sb.append(" <option value=\"" + value + "\">");
        }
        sb.append(MutiLangUtil.getMutiLangInstance().getLang(text));
        sb.append("</option>");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getDivClass() {
        return divClass;
    }

    public void setDivClass(String divClass) {
        this.divClass = divClass;
    }

    public String getLabelClass() {
        return labelClass;
    }

    public void setLabelClass(String labelClass) {
        this.labelClass = labelClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getNullmsg() {
        return nullmsg;
    }

    public void setNullmsg(String nullmsg) {
        this.nullmsg = nullmsg;
    }

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}

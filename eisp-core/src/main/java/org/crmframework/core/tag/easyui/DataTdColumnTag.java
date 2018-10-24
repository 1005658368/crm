package org.crmframework.core.tag.easyui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DataTdColumnTag extends TagSupport {
	private static final long serialVersionUID = 2977051237796989492L;
	private String id;
	private String name;
	private String fieldName;
	private String field; 
	private String list;
	private String selectValue;
	private String selectText;
	private String datatype;
	private String nullmsg;
	private String errormsg;
	private String recheck;
	private String fun;
	private String target;
	private String url;
	private String onchange;
	private String defaultValue;
    private String type;
    private String style;
    private String dictionary;
    private String onclick;
    private String callbak;
    private String param;
    private String isenabled;
    private String tdAttr;
    private String showText;
    private String width;
    private String replace;
    
    
	@Override
	public int doEndTag() throws JspException {
		Tag t = findAncestorWithClass(this, DataTrTag.class);
		DataTrTag parent = (DataTrTag) t;
		parent.setColumn(id, name, fieldName, field, defaultValue, list, selectValue, selectText, datatype, nullmsg, errormsg, recheck,
				onchange, fun, target, url, type, dictionary, style, onclick, callbak, param, isenabled, tdAttr, showText, width, replace);
		return EVAL_PAGE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDictionary() {
		return dictionary;
	}
	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getFun() {
		return fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getNullmsg() {
		return nullmsg;
	}

	public void setNullmsg(String nullmsg) {
		this.nullmsg = nullmsg;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getRecheck() {
		return recheck;
	}

	public void setRecheck(String recheck) {
		this.recheck = recheck;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getCallbak() {
		return callbak;
	}

	public void setCallbak(String callbak) {
		this.callbak = callbak;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(String isenabled) {
		this.isenabled = isenabled;
	}

	public String getTdAttr() {
		return tdAttr;
	}

	public void setTdAttr(String tdAttr) {
		this.tdAttr = tdAttr;
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}
}

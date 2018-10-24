package org.crmframework.core.tag.easyui;

import org.crmframework.core.minidao.map.MiniDaoLinkedMap;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.*;
import org.crmframework.core.vo.DataTdColumn;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTrTag extends TagSupport {
	private static final long serialVersionUID = -8917777909431849600L;
	@Autowired
	private static SystemService systemService;
	private String id;
	private String trAttr;
	private String namestr;
	private String modelId;
	private List<DataTdColumn> lstDataTd = new ArrayList<DataTdColumn>();
	
	public void setColumn(String columnId, String columnName, String fieldName, String field, String defaultValue, String list, String selectValue, String selectText, 
			String datatype, String nullmsg, String errormsg, String recheck, String onchange, String fun, String target, String url, String type, String dictionary, 
			String style, String onclick, String callbak, String param, String isenabled, String tdAttr, String showText, String width, String replace){
		DataTdColumn dataEdit = new DataTdColumn();
		dataEdit.setColumnId(columnId);
		if(StringUtil.isEmpty(columnName)){
			columnName = columnId;
		}
		dataEdit.setColumnName(columnName);
		dataEdit.setFieldName(fieldName);
		dataEdit.setField(field);
		dataEdit.setDefaultValue(defaultValue);
		dataEdit.setSelectValue(selectValue);
		dataEdit.setSelectText(selectText);
		dataEdit.setList(list);
		dataEdit.setDatatype(datatype);
		dataEdit.setNullmsg(nullmsg);
		dataEdit.setErrormsg(errormsg);
		dataEdit.setRecheck(recheck);
		dataEdit.setFun(fun);
		dataEdit.setTarget(target);
		dataEdit.setUrl(url);
		dataEdit.setType(type);
		dataEdit.setDictionary(dictionary);
		dataEdit.setStyle(style);
		dataEdit.setOnchange(onchange);
		dataEdit.setOnclick(onclick);
		dataEdit.setCallbak(callbak);
		dataEdit.setParam(param);
		dataEdit.setIsenabled(isenabled);
		dataEdit.setTdAttr(tdAttr);
		dataEdit.setShowText(showText);
		dataEdit.setWidth(width);
		dataEdit.setReplace(replace);
		
		lstDataTd.add(dataEdit);
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
    private void select(String text, String value, String defaultValue, StringBuffer sb) {
        if (StringUtil.isNotEmpty(defaultValue) && value.equals(defaultValue)) {
            sb.append(" <option value=\"" + value + "\" selected=\"selected\">");
        } else {
            sb.append(" <option value=\"" + value + "\">");
        }
        sb.append(MutiLangUtil.getMutiLangInstance().getLang(text));
        sb.append(" </option>");
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
	
	@Override
    public int doStartTag() throws JspTagException {
		this.lstDataTd.clear();
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		return EVAL_PAGE;
	}
	
	public StringBuffer end(){
		StringBuffer buf = new StringBuffer();
		if(CollectionUtil.listNotEmptyNotSizeZero(lstDataTd)){
			StringBuffer bodyTr = new StringBuffer();
			if(getId()==null){//这是第一版老版

				bodyTr.append("<tr class='datagrid-row droppable'>");
				for (int i = 0; i < lstDataTd.size(); i++) {
					DataTdColumn dataTd = lstDataTd.get(i);
					
					String type = dataTd.getType();
					String datatype = dataTd.getDatatype();
					String nullmsg = dataTd.getNullmsg();
					String errormsg = dataTd.getErrormsg();
					String recheck = dataTd.getRecheck();
					String defaultValue = dataTd.getDefaultValue();
					String style = dataTd.getStyle();
					String isenabled = dataTd.getIsenabled();
					String tdAttr = dataTd.getTdAttr();
					
					String onclick = dataTd.getOnclick();
					String callbak = dataTd.getCallbak();
					String param = dataTd.getParam();
					StringBuffer sbuf = new StringBuffer();
					if(StringUtil.isNotEmpty(param)){
						String[] arrParam = param.split(",");
						for (int j = 0; j < arrParam.length; j++) {
							sbuf.append("\\'");
							sbuf.append(arrParam[j]);
							sbuf.append("\\'");
						}
					}
					if(StringUtil.isNotEmpty(callbak)){
						callbak = callbak + "("+sbuf.toString()+")";
						onclick  = onclick.replace("#callbak#", callbak);
					}
					
					if(StringUtil.isNotEmpty(tdAttr)){
						bodyTr.append("<td "+tdAttr+" >");
					}else{
						bodyTr.append("<td>");
					}
					
					if(type.equals("checkbox")) {
						bodyTr.append("<div class='datagrid-cell-check'>");
					}else{
						bodyTr.append("<div class='datagrid-cell'>");
					}
					
					StringBuffer htmlBuf = new StringBuffer();
					if(StringUtil.isNotEmpty(datatype)){
						htmlBuf.append(" datatype='"+datatype+"' ");
					}
					if(StringUtil.isNotEmpty(nullmsg)){
						htmlBuf.append(" nullmsg='"+nullmsg+"' ");
					}
					if(StringUtil.isNotEmpty(errormsg)){
						htmlBuf.append(" errormsg='"+errormsg+"' ");
					}
					if(StringUtil.isNotEmpty(recheck)){
						htmlBuf.append(" recheck='"+recheck+"' ");
					}
					if(StringUtil.isNotEmpty(style)){
						htmlBuf.append(" style='"+style+"' ");
					}
					if(StringUtil.isNotEmpty(isenabled)){
						htmlBuf.append(" "+isenabled+" ");
					}
					if(StringUtil.isNotEmpty(onclick)){
						htmlBuf.append(" onclick=\""+onclick+"\"");
					}
					
					if(type.equals("select")){
						String list = dataTd.getList();
						String fun = dataTd.getFun();
						String target = dataTd.getTarget();
						String url = dataTd.getUrl();
						String change = "";
						if(StringUtil.isNotEmpty(fun) && StringUtil.isNotEmpty(target) && StringUtil.isNotEmpty(url)){
							change = " onchange=\""+fun+"('"+dataTd.getColumnId()+"','"+target+"','"+url+"');\" ";
							htmlBuf.append(change);
						}
						
						bodyTr.append("<select id='"+dataTd.getColumnId()+"' name='"+dataTd.getColumnName()+"' "+htmlBuf.toString()+">");
						
						bodyTr.append("<option value=''>---请选择---</option>");
						
						if(StringUtil.isNotEmpty(list)){
							String selectValue = dataTd.getSelectValue();
							String selectText = dataTd.getSelectText();
							List resultList = (List) pageContext.getRequest().getAttribute(list);
							if (resultList != null && resultList.size() > 0) {
					            for (Object obj : resultList) {
					                ReflectHelper reflectHelper = new ReflectHelper(obj);
					                if(obj instanceof MiniDaoLinkedMap){
					                	MiniDaoLinkedMap map = (MiniDaoLinkedMap)obj;
					                	String value = (String)map.get(selectValue);
					                	String text = (String)map.get(selectText);
					                	select(text, value, defaultValue, bodyTr);
					                }else if(obj instanceof Map){
					                	Map<String, String> map = (Map<String, String>)obj;
					                	select(map.get("value"), map.get("key"), defaultValue, bodyTr);
					                }else{
					                	String value = objectChangeStringObject(reflectHelper.getMethodValue(selectValue));
					                    String text = objectChangeStringObject(reflectHelper.getMethodValue(selectText));
					                    select(text, value, defaultValue, bodyTr);
					                }
					            }
					        }
						}else{
							String dictionary = dataTd.getDictionary();
							if(StringUtil.isNotEmpty(dictionary)){
								String sql = "select t.typecode,t.typename from t_s_typegroup tp "
										   + " join t_s_type t on tp.id=t.typegroupid "
										   + " where tp.typegroupcode=? ";
								List<Map<String, Object>> listMap = systemService.findForJdbc(sql, dictionary);
								if(CollectionUtil.listNotEmptyNotSizeZero(listMap)){
									for (int j = 0; j < listMap.size(); j++) {
										Map<String, Object> map = listMap.get(j);
										String typeCode = (String)map.get("typecode");
										if(typeCode.equals(defaultValue)){
											bodyTr.append("<option value='"+map.get("typecode")+"' selected='selected' >"+MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(map.get("typename")))+"</option>");
										}else{
											bodyTr.append("<option value='"+map.get("typecode")+"'>"+MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(map.get("typename")))+"</option>");
										}
									}
								}
							}
						}
						bodyTr.append("</select>");
					}else if(type.equals("text")){
						//String onclick = dataTd.getOnclick();
						if(StringUtil.isNotEmpty(defaultValue)){
							htmlBuf.append(" value='"+defaultValue+"' ");
						}
						bodyTr.append("<input type='text' id='"+dataTd.getColumnId()+"' name='"+dataTd.getColumnName()+"' "+htmlBuf.toString()+" >");
					}else if(type.equals("date")){
						if(StringUtil.isNotEmpty(defaultValue)){
							htmlBuf.append(" value='"+defaultValue+"' ");
						}

						if(StringUtil.isNotEmpty(onclick)){
							bodyTr.append("<input type='text' class='Wdate' id='"+dataTd.getColumnId()+"' name='"+dataTd.getColumnName()+"' "+htmlBuf.toString()+" >");
						}else{
							bodyTr.append("<input type='text' onclick=\"WdatePicker({dateFmt:\'yyyy-MM-dd'})\" class='Wdate' id='"+dataTd.getColumnId()+"' name='"+dataTd.getColumnName()+"' "+htmlBuf.toString()+" >");
						}
					}else if(type.equals("checkbox")){
						if(StringUtil.isNotEmpty(defaultValue)){
							htmlBuf.append(" value='"+defaultValue+"' ");
						}
						bodyTr.append("<input type='checkbox' id='"+dataTd.getColumnId()+"' name='"+dataTd.getColumnName()+"' "+htmlBuf.toString()+">");
					}else if(type.equals("hidden")){
						if(StringUtil.isNotEmpty(defaultValue)){
							htmlBuf.append(" value='"+defaultValue+"' ");
						}
						bodyTr.append("<input type='hidden' id='"+dataTd.getColumnId()+"' name='"+dataTd.getColumnName()+"' "+htmlBuf.toString()+">");
					}else if(type.equals("label")){
						bodyTr.append(defaultValue);
					}else if(type.equals("a")){
						//<a id="index_dialogText" onclick="reDialog('添加规格类', 'xpsMarketGuideController.do?findCostGuideList', '600', '300', '#callbak#" ><input type="hidden" value="" id="index_dialog" />添加</a>
						bodyTr.append("<input type=\"hidden\" name=\""+dataTd.getColumnName()+"\" id=\""+dataTd.getTarget()+"\"><div id=\""+dataTd.getColumnId()+"\"  "+htmlBuf.toString()+" >"+dataTd.getFieldName()+"</div>");
					}
					bodyTr.append("</div>");
					bodyTr.append("</td>");
				}
				bodyTr.append("</tr>");
				buf.append(bodyTr);
			}else{//新版

				//自定义列宽
				String modelId = getModelId()==null?"":"#"+getModelId();
				bodyTr.append("<style>");
				for (int i = 0; i < lstDataTd.size(); i++) {
					String tdWidth = lstDataTd.get(i).getWidth();
					if(StringUtil.isNotEmpty(tdWidth)){
						bodyTr.append(modelId + " [field='"+lstDataTd.get(i).getField()+"']," + modelId + " [field='"+lstDataTd.get(i).getField()+"']>div{min-width:"+tdWidth+" !important;width:"+tdWidth+" !important;}");
					}
				}
				bodyTr.append("</style>");
				
				//自定义tr属性
				String id = getId()==null?"index":getId();
				String trAttr = getTrAttr();
				String namestr = getNamestr();
				bodyTr.append("<tr  ");
				if(StringUtil.isNotEmpty(id)){
					bodyTr.append(" id='"+id+"' ");
				}
				if(StringUtil.isNotEmpty(trAttr)){
					bodyTr.append(" "+trAttr+" ");
				}
				bodyTr.append(">");
				
				for (int i = 0; i < lstDataTd.size(); i++) {
					DataTdColumn dataTd = lstDataTd.get(i);
					
					String type = dataTd.getType();
					String datatype = dataTd.getDatatype();
					String nullmsg = dataTd.getNullmsg();
					String errormsg = dataTd.getErrormsg();
					String recheck = dataTd.getRecheck();
					String field = dataTd.getField();
					String defaultValue = dataTd.getDefaultValue();
					String style = dataTd.getStyle();
					String isenabled = dataTd.getIsenabled();
					String tdAttr = dataTd.getTdAttr();
					String showText = dataTd.getShowText();
					String onchange = dataTd.getOnchange();
					String onclick = dataTd.getOnclick();
					String callbak = dataTd.getCallbak();
					String param = dataTd.getParam();
					//String columnid = dataTd.getColumnId()==null?"":dataTd.getColumnId();
					//String columnname = dataTd.getColumnName()==null?"":dataTd.getColumnName();
					String columnid = dataTd.getColumnId()!=null?dataTd.getColumnId():(id==null?"":id+"_"+field);
					String columnname = dataTd.getColumnName()!=null?dataTd.getColumnName():(namestr==null?field:namestr+field);
					if(StringUtil.isEmpty(showText) && StringUtil.isNotEmpty(defaultValue)){
						showText = defaultValue;
					}if(StringUtil.isEmpty(defaultValue) && StringUtil.isNotEmpty(showText)){
						defaultValue = showText;
					}
					
					StringBuffer sbuf = new StringBuffer();
					if(StringUtil.isNotEmpty(param)){
						String[] arrParam = param.split(",");
						for (int j = 0; j < arrParam.length; j++) {
							sbuf.append("\\'");
							sbuf.append(arrParam[j]);
							sbuf.append("\\'");
						}
					}
					if(StringUtil.isNotEmpty(callbak)){
						callbak = callbak + "("+sbuf.toString()+")";
						onchange = onchange.replace("#callbak#", callbak);
						onclick  = onclick.replace("#callbak#", callbak);
					}
					
					bodyTr.append("<td  ");
					if(StringUtil.isNotEmpty(field)){
						bodyTr.append(" field='"+field+"' ");
					}
					if(StringUtil.isNotEmpty(tdAttr)){
						bodyTr.append(" "+tdAttr+" ");
					}
					if(StringUtil.isNotEmpty(isenabled)){
						bodyTr.append(" editdisabled ");
					}else{
						//bodyTr.append(" editabled ");
					}
					bodyTr.append(" >");

					if(type.equals("checkbox")) {
						bodyTr.append("<div class='datagrid-cell-check'>");
					}else{
						if(StringUtil.isNotEmpty(showText)){
							bodyTr.append("<div class='editStatus' id='"+columnid+"_text' value='"+showText+"' >"+showText+"</div>");
						}else {
							bodyTr.append("<div class='editStatus' id='"+columnid+"_text' value='' ></div>");
						}
						bodyTr.append("<div class='datagrid-cell' >");
					}
					
					StringBuffer htmlBuf = new StringBuffer();
					if(StringUtil.isNotEmpty(datatype)){
						htmlBuf.append(" datatype='"+datatype+"' ");
					}
					if(StringUtil.isNotEmpty(nullmsg)){
						htmlBuf.append(" nullmsg='"+nullmsg+"' ");
					}
					if(StringUtil.isNotEmpty(errormsg)){
						htmlBuf.append(" errormsg='"+errormsg+"' ");
					}
					if(StringUtil.isNotEmpty(recheck)){
						htmlBuf.append(" recheck='"+recheck+"' ");
					}
					if(StringUtil.isNotEmpty(style)){
						htmlBuf.append(" style='"+style+"' ");
					}
					if(StringUtil.isNotEmpty(isenabled)){
						htmlBuf.append(" "+isenabled+" ");
					}
					if(StringUtil.isNotEmpty(onclick)){
						htmlBuf.append(" onclick=\""+onclick+"\" ");
					}
					if(StringUtil.isNotEmpty(onchange)){
						htmlBuf.append(" onchange=\""+onchange+"\" ");
					}
					if(StringUtil.isNotEmpty(field)){
						htmlBuf.append(" fieldname='"+field+"' ");
					}
					if(StringUtil.isNotEmpty(defaultValue)){
						htmlBuf.append(" value='"+defaultValue+"' ");
					}else{
						htmlBuf.append(" value='' ");
					}
					
					if(type.equals("select")){

						String replace = dataTd.getReplace();
						String dictionary = dataTd.getDictionary();
						String list = dataTd.getList();
						String fun = dataTd.getFun();
						String target = dataTd.getTarget();
						String url = dataTd.getUrl();
						String change = "";
						if(StringUtil.isNotEmpty(fun) && StringUtil.isNotEmpty(target) && StringUtil.isNotEmpty(url)){
							change = " onchange=\""+fun+"({a:'"+columnid+"',b:'"+target+"',url:'"+url+"'});\" ";
							htmlBuf.append(change);
						}
						
						bodyTr.append("<select id='"+columnid+"' name='"+columnname+"' "+htmlBuf.toString()+">");
						
						bodyTr.append("<option value=''>---请选择---</option>");
						
						if(StringUtil.isNotEmpty(list)){
							String selectValue = dataTd.getSelectValue();
							String selectText = dataTd.getSelectText();
							List resultList = (List) pageContext.getRequest().getAttribute(list);
							if (resultList != null && resultList.size() > 0) {
					            for (Object obj : resultList) {
					                ReflectHelper reflectHelper = new ReflectHelper(obj);
					                if(obj instanceof MiniDaoLinkedMap){
					                	MiniDaoLinkedMap map = (MiniDaoLinkedMap)obj;
					                	String value = (String)map.get(selectValue);
					                	String text = (String)map.get(selectText);
					                	select(text, value, defaultValue, bodyTr);
					                }else if(obj instanceof Map){
					                	Map<String, String> map = (Map<String, String>)obj;
					                	select(map.get("value"), map.get("key"), defaultValue, bodyTr);
					                }else{
					                	String value = objectChangeStringObject(reflectHelper.getMethodValue(selectValue));
					                    String text = objectChangeStringObject(reflectHelper.getMethodValue(selectText));
					                    select(text, value, defaultValue, bodyTr);
					                }
					            }
					        }
						}else if(StringUtil.isNotEmpty(dictionary)){
							String sql = "select t.typecode,t.typename from t_s_typegroup tp "
									   + " join t_s_type t on tp.id=t.typegroupid "
									   + " where tp.typegroupcode=? ";
							List<Map<String, Object>> listMap = systemService.findForJdbc(sql, dictionary);
							if(CollectionUtil.listNotEmptyNotSizeZero(listMap)){
								for (int j = 0; j < listMap.size(); j++) {
									Map<String, Object> map = listMap.get(j);
									String typeCode = (String)map.get("typecode");
									if(typeCode.equals(defaultValue)){
										bodyTr.append("<option value='"+map.get("typecode")+"' selected='selected' >"+MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(map.get("typename")))+"</option>");
									}else{
										bodyTr.append("<option value='"+map.get("typecode")+"'>"+MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(map.get("typename")))+"</option>");
									}
								}
							}
						}else if(StringUtil.isNotEmpty(replace)){
							String[] arrVal = replace.split(",");
							for (int j = 0; j < arrVal.length; j++) {
								String val = arrVal[j];
								String value = val.split("_")[1];
								String text = val.split("_")[0];
								if(value.equals(defaultValue)){
									bodyTr.append("<option value='"+value+"' selected='selected' >"+text+"</option>");
								}else{
									bodyTr.append("<option value='"+value+"' >"+text+"</option>");
								}
							}
							
						}
						bodyTr.append("</select>");
					}else if(type.equals("text")){
						bodyTr.append("<input type='text' id='"+columnid+"' name='"+columnname+"' "+htmlBuf.toString()+" >");
					}else if(type.equals("date")){
						if(StringUtil.isNotEmpty(onclick)){
							bodyTr.append("<input type='text' class='Wdate' id='"+columnid+"' name='"+columnname+"' "+htmlBuf.toString()+" >");
						}else{
							bodyTr.append("<input type='text' onclick=\"WdatePicker({dateFmt:\'yyyy-MM-dd'})\" class='Wdate' id='"+columnid+"' name='"+columnname+"' "+htmlBuf.toString()+" >");
						}
					}else if(type.equals("checkbox")){
						bodyTr.append("<input type='checkbox' id='"+columnid+"' name='"+columnname+"' "+htmlBuf.toString()+">");
					}else if(type.equals("hidden")){
						bodyTr.append("<input type='hidden' id='"+columnid+"' name='"+columnname+"' "+htmlBuf.toString()+">");
					}else if(type.equals("label")){
						bodyTr.append(defaultValue);
					}else if(type.equals("a")){
						//<a id="index_dialogText" onclick="reDialog('添加规格类', 'xpsMarketGuideController.do?findCostGuideList', '600', '300', '#callbak#" ><input type="hidden" value="" id="index_dialog" />添加</a>
						bodyTr.append("<input type=\"hidden\" name=\""+columnname+"\" id=\""+dataTd.getTarget()+"\"><div id=\""+columnid+"\"  "+htmlBuf.toString()+" >"+dataTd.getField()+"</div>");
					}
					bodyTr.append("</div>");
					bodyTr.append("</td>");
				}
				bodyTr.append("</tr>");
				buf.append(bodyTr);
				
				String str = buf.toString();
				if(StringUtil.isNotEmpty(id)){
					str = str.replaceAll("index", id);
				}
				buf = new StringBuffer(str);
			}
			
		}
		return buf;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(end().toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamestr() {
		return namestr;
	}

	public void setNamestr(String namestr) {
		this.namestr = namestr;
	}

	public String getTrAttr() {
		return trAttr;
	}

	public void setTrAttr(String trAttr) {
		this.trAttr = trAttr;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	
}

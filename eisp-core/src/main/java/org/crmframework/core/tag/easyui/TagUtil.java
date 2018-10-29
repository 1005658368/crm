package org.crmframework.core.tag.easyui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crm.crm.base.mdm.entity.TSRole;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.crmframework.core.common.qbc.PageList;
import org.crmframework.core.util.DateUtils;
import org.crmframework.core.util.ReflectHelper;
import org.crmframework.core.util.StringUtil;
import org.crmframework.core.util.oConvertUtils;
import org.crmframework.core.vo.Autocomplete;
import org.crmframework.core.vo.ComboBox;
import org.crmframework.core.vo.DataGrid;
import org.crmframework.core.vo.datatable.DataTableReturn;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 
 * 类描述：标签工具类
 * 
 * @author: Biz
 * @date： 日期：2012-12-28 时间：上午09:58:00
 * @version 1.1
 * @author liuht 修改不能输入双引号问题解决
 */
public class TagUtil implements java.io.Serializable{
    /**
     * <b>Summary: </b> getFiled(获得实体Bean中所有属性)
     * 
     * @param objClass
     * @return
     * @throws ClassNotFoundException
     */
    public static Field[] getFiled(Class<?> objClass) throws ClassNotFoundException {
        Field[] field = null;
        if (objClass != null) {
            Class<?> class1 = Class.forName(objClass.getName());
            field = class1.getDeclaredFields();// 这里便是获得实体Bean中所有属性的方法
            return field;
        } else {
            return field;
        }
    }

    /**
     * 
     * 获取对象内对应字段的值
     */
    public static Object fieldNametoValues(String FiledName, Object o) {
        Object value = "";
        String fieldName = "";
        String childFieldName = null;
        ReflectHelper reflectHelper = new ReflectHelper(o);
        if (FiledName.indexOf("_") == -1) {
            if (FiledName.indexOf(".") == -1) {
                fieldName = FiledName;
            } else {
                fieldName = FiledName.substring(0, FiledName.indexOf("."));// 外键字段引用名
                childFieldName = FiledName.substring(FiledName.indexOf(".") + 1);// 外键字段名
            }
        } else {
            fieldName = FiledName.substring(0, FiledName.indexOf("_"));// 外键字段引用名
            childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);// 外键字段名
        }
        try {
            Field[] fields = TagUtil.getFiled(o.getClass());
            String type = getColumnType(FiledName,fields);
            if("date".equals(type)){
                if(reflectHelper.getMethodValue(fieldName) == null){
                    value="";
                }else{
                    value = reflectHelper.getMethodValue(fieldName);
                    Date datevalue = (Date) value;
                    String strValue = DateUtils.date2Str(datevalue,DateUtils.datetimeFormat);
                    Timestamp ts = Timestamp.valueOf(strValue);
                    value = ts;
                }
            }else{
                value = reflectHelper.getMethodValue(fieldName) == null ? "" : reflectHelper.getMethodValue(fieldName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            value = reflectHelper.getMethodValue(fieldName) == null ? "" : reflectHelper.getMethodValue(fieldName);
        }

        if (value != "" && value != null && (FiledName.indexOf("_") != -1 || FiledName.indexOf(".") != -1)) {
            // update-start--Author:Biz Date:20140827 for：功能增强，添加处理对象中List<Object>属性字段的解析
            if (value instanceof List) {
                Object tempValue = "";
                for (Object listValue : (List) value) {
                    tempValue = tempValue.toString() + fieldNametoValues(childFieldName, listValue) + ",";
                }
                value = tempValue;
            } else {
                value = fieldNametoValues(childFieldName, value);
            }
            // update-end--Author:Biz Date:20140827 for：功能增强，添加处理对象中List<Object>属性字段的解析
        }
        if (value != "" && value != null) {
            value = value.toString().replaceAll("\r\n", "");
            value = value.toString().replace("\\\\", "\\\\\\\\");//对斜线的转义  
        }
        return value;
    }

    /**
     * 对象转数组
     * 
     * @param fields
     * @param o
     * @return
     * @throws Exception
     */
    protected static Object[] field2Values(String[] fields, Object o) throws Exception {
        Object[] values = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].toString();
            values[i] = fieldNametoValues(fieldName, o);
        }
        return values;
    }

    /**
     * 循环LIST对象拼接EASYUI格式的JSON数据
     * 
     * @param fields
     * @param total
     * @param list
     */
    public static String listtojson(String[] fields, int total, List<?> list, String[] footers) throws Exception {
        Object[] values = new Object[fields.length];
        StringBuffer jsonTemp = new StringBuffer();
//        jsonTemp.append("{\"total\":" + total + ",\"rows\":[");
        jsonTemp.append("{\"total\":" + total + ",\"rows\":");
        int i;
        String fieldName;
/*
        for (int j = 0; j < list.size(); ++j) {
            jsonTemp.append("{\"state\":\"closed\",");
            for (i = 0; i < fields.length; ++i) {
                fieldName = fields[i].toString();
                if (list.get(j) instanceof Map)
                    values[i] = ((Map<?, ?>) list.get(j)).get(fieldName);
                else {
                    values[i] = fieldNametoValues(fieldName, list.get(j));
                }
                jsonTemp.append("\"" + fieldName + "\"" + ":\"" + String.valueOf(values[i])
                                + "\"");
                if (i != fields.length - 1) {
                    jsonTemp.append(",");
                }
            }
            if (j != list.size() - 1)
                jsonTemp.append("},");
            else {
                jsonTemp.append("}");
            }
        }
*/
        List<Map<String,Object>> jlist=new ArrayList<Map<String,Object>>();
        for (int j = 0; j < list.size(); ++j) {
            Map<String,Object> jmap=new HashMap<String,Object>();
            jmap.put("state","closed");
            for (i = 0; i < fields.length; ++i) {
                fieldName = fields[i].toString();
                if (list.get(j) instanceof Map)
                    values[i] = ((Map<?, ?>) list.get(j)).get(fieldName);
                else {
                    values[i] = fieldNametoValues(fieldName, list.get(j));
                }
                jmap.put(fieldName,values[i]);
            }
            jlist.add(jmap);
        }
//        jsonTemp.append("]");
        jsonTemp.append(JSON.toJSONString(jlist));
        if (footers != null) {
            jsonTemp.append(",");
            jsonTemp.append("\"footer\":[");
            jsonTemp.append("{");
//            jsonTemp.append("\"name\":\"合计\",");
            for (String footer : footers) {
                String footerFiled = footer.split(":")[0];
                Object value = null;
                if (footer.split(":").length == 2)
                    value = footer.split(":")[1];
                else {
                    value = getTotalValue(footerFiled, list);
                }
                jsonTemp.append("\"" + footerFiled + "\":\"" + value + "\",");
            }
            if (jsonTemp.lastIndexOf(",") == jsonTemp.length()) {
                jsonTemp = jsonTemp.deleteCharAt(jsonTemp.length());
            }
            jsonTemp.append("}");
            jsonTemp.append("]");
        }
        jsonTemp.append("}");
        return jsonTemp.toString();
    }

    /**
     * 计算指定列的合计
     * 
     *            字段名
     * @param list
     *            列表数据
     * @return
     */
    private static Object getTotalValue(String fieldName, List list) {
        Double sum = 0D;
        try {
            for (int j = 0; j < list.size(); j++) {
                Double v = 0d;
                String vstr = String.valueOf(fieldNametoValues(fieldName, list.get(j)));
                if (!StringUtil.isEmpty(vstr)) {
                    v = Double.valueOf(vstr);
                } else {

                }
                sum += v;
            }
        } catch (Exception e) {
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");//格式化设置
        return decimalFormat.format(sum);
    }

    /**
     * 循环LIST对象拼接自动完成控件数据
     * @param list
     * @throws Exception
     */
    public static String getAutoList(Autocomplete autocomplete, List list) throws Exception {
        String field = autocomplete.getLabelField() + "," + autocomplete.getValueField();
        String[] fields = field.split(",");
        Object[] values = new Object[fields.length];
        StringBuffer jsonTemp = new StringBuffer();
        jsonTemp.append("{\"totalResultsCount\":\"1\",\"geonames\":[");
        if (list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                jsonTemp.append("{'nodate':'yes',");
                for (int i = 0; i < fields.length; i++) {
                    String fieldName = fields[i].toString();
                    values[i] = fieldNametoValues(fieldName, list.get(j));
                    jsonTemp.append("\"").append(fieldName).append("\"").append(":\"").append(values[i]).append("\"");
                    if (i != fields.length - 1) {
                        jsonTemp.append(",");
                    }
                }
                if (j != list.size() - 1) {
                    jsonTemp.append("},");
                } else {
                    jsonTemp.append("}");
                }
            }
        } else {
            jsonTemp.append("{'nodate':'数据不存在'}");
        }
        jsonTemp.append("]}");
        return JSONObject.toJSONString(jsonTemp).toString();
    }

    /**
     * 循环LIST对象拼接DATATABLE格式的JSON数据
     * @param total
     * @param list
     */
    private static String datatable(String field, int total, List list) throws Exception {
        String[] fields = field.split(",");
        Object[] values = new Object[fields.length];
        StringBuffer jsonTemp = new StringBuffer();
        jsonTemp.append("{\"iTotalDisplayRecords\":" + total + ",\"iTotalRecords\":" + total + ",\"aaData\":[");
        for (int j = 0; j < list.size(); j++) {
            jsonTemp.append("{");
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].toString();
                values[i] = fieldNametoValues(fieldName, list.get(j));
                jsonTemp.append("\"" + fieldName + "\"" + ":\"" + values[i] + "\"");
                if (i != fields.length - 1) {
                    jsonTemp.append(",");
                }
            }
            if (j != list.size() - 1) {
                jsonTemp.append("},");
            } else {
                jsonTemp.append("}");
            }
        }
        jsonTemp.append("]}");
        return jsonTemp.toString();
    }

    /**
     * 返回列表JSONObject对象
     * @return
     */
    private static JSONObject getJson(DataGrid dg) {
        JSONObject jObject = null;
        try {
            if (!StringUtil.isEmpty(dg.getFooter())) {
                jObject = JSONObject.parseObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getResults(),
                                dg.getFooter().split(",")));
            } else {
            	String string = listtojson(dg.getField().split(","), dg.getTotal(), dg.getResults(),
                        null);
            	//将反斜杠转换
//            	string = string.replaceAll("\\\\", "/");
            	// 例如 替换掉'/"SQPRD/"./"MV_BIC_AZQD_O3000/"'里面的  /"   后为 'SQPRD/"./"MV_BIC_AZQD_O3000'
            	string = string.replaceAll("/\"","");
                jObject = JSONObject.parseObject(string);
            }
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        return jObject;

    }

    /**
     * 返回列表JSONObject对象
     * @param field
     * @return
     */
    private static JSONObject getJson(DataTableReturn dataTable, String field) {
        JSONObject jObject = null;
        try {
            jObject = JSONObject.parseObject(datatable(field, dataTable.getiTotalDisplayRecords(),
                            dataTable.getAaData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObject;

    }

    /**
     * 获取指定字段类型 <b>Summary: </b> getColumnType(请用一句话描述这个方法的作用)
     * 
     * @param fileName
     * @param fields
     * @return
     */
    public static String getColumnType(String fileName, Field[] fields) {
        String type = "";
        if (fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName(); // 获取属性的名字
                String filedType = fields[i].getGenericType().toString(); // 获取属性的类型
                if (fileName.equals(name)) {
                    if (filedType.equals("class java.lang.Integer")) {
                        filedType = "int";
                        type = filedType;
                    } else if (filedType.equals("class java.lang.Short")) {
                        filedType = "short";
                        type = filedType;
                    } else if (filedType.equals("class java.lang.Double")) {
                        filedType = "double";
                        type = filedType;
                    } else if (filedType.equals("class java.util.Date")) {
                        filedType = "date";
                        type = filedType;
                    } else if (filedType.equals("class java.lang.String")) {
                        filedType = "string";
                        type = filedType;
                    } else if (filedType.equals("class java.sql.Timestamp")) {
                        filedType = "Timestamp";
                        type = filedType;
                    } else if (filedType.equals("class java.lang.Character")) {
                        filedType = "character";
                        type = filedType;
                    } else if (filedType.equals("class java.lang.Boolean")) {
                        filedType = "boolean";
                        type = filedType;
                    } else if (filedType.equals("class java.lang.Long")) {
                        filedType = "long";
                        type = filedType;
                    }

                }
            }
        }
        return type;
    }

    /**
     * 
     * <b>Summary: </b> getSortColumnIndex(获取指定字段索引)
     * 
     * @param fileName
     * @param fieldString
     * @return
     */
    protected static String getSortColumnIndex(String fileName, String[] fieldString) {
        String index = "";
        if (fieldString.length > 0) {
            for (int i = 0; i < fieldString.length; i++) {
                if (fileName.equals(fieldString[i])) {
                    int j = i + 1;
                    index = oConvertUtils.getString(j);
                }
            }
        }
        return index;

    }

    // JSON返回页面MAP方式
    public static void ListtoView(HttpServletResponse response, PageList pageList) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageList.getCount());
        map.put("rows", pageList.getResultList());
        ObjectMapper mapper = new ObjectMapper();
        // JSONObject jsonObject = JSONObject.fromObject(map);
        try {
            mapper.writeValue(response.getWriter(), map);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 控件类型：easyui 返回datagrid JSON数据
     * 
     * @param response
     */
    public static void datagrid(HttpServletResponse response, DataGrid dg) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        JSONObject object = TagUtil.getJson(dg);
        try {
            PrintWriter pw = response.getWriter();
            String result=object.toString();
            pw.write(result);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 控件类型：easyui 返回datagrid StringJson数据
     * 
     * @param response
     */
    public static void Stringdatagrid(HttpServletResponse response, String json) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.write(json.toString());
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void StringResponse(HttpServletResponse response, String json) {
        response.setContentType("text");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.write(json.toString());
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 控件类型：datatable 返回datatable JSON数据
     * 
     * @param response
     */
    public static void datatable(HttpServletResponse response, DataTableReturn dataTableReturn, String field) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        JSONObject object = TagUtil.getJson(dataTableReturn, field);
        try {
            response.getWriter().write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手工拼接JSON
     */
    public static String getComboBoxJson(List<TSRole> list, List<TSRole> roles) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (TSRole node : list) {
            if (roles.size() > 0) {
                buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"");
                for (TSRole node1 : roles) {
                    if (node.getId() == node1.getId()) {
                        buffer.append(",\"selected\":true");
                    }
                }
                buffer.append("},");
            } else {
                buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"},");
            }

        }
        buffer.append("]");

        // 将,\n]替换成\n]

        String tmp = buffer.toString();
        tmp = tmp.replaceAll(",]", "]");
        return tmp;

    }

    /**
     * 根据模型生成JSON
     * 
     * @param all
     *            全部对象
     * @param in
     *            已拥有的对象
     * @param comboBoxModel
     *            模型
     * @return
     */
    public static List<ComboBox> getComboBox(List all, List in, ComboBox comboBoxModel) {
        List<ComboBox> comboxBoxs = new ArrayList<ComboBox>();
        String[] fields = new String[] { comboBoxModel.getId(), comboBoxModel.getText() };
        Object[] values = new Object[fields.length];
        for (Object node : all) {
            ComboBox box = new ComboBox();
            ReflectHelper reflectHelper = new ReflectHelper(node);
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].toString();
                values[i] = reflectHelper.getMethodValue(fieldName);
            }
            box.setId(values[0].toString());
            box.setText(values[1].toString());
            if (in != null) {
                for (Object node1 : in) {
                    ReflectHelper reflectHelper2 = new ReflectHelper(node);
                    if (node1 != null) {
                        String fieldName = fields[0].toString();
                        String test = reflectHelper2.getMethodValue(fieldName).toString();
                        if (values[0].toString().equals(test)) {
                            box.setSelected(true);
                        }
                    }
                }
            }
            comboxBoxs.add(box);
        }
        return comboxBoxs;

    }

    /**
     * 获取自定义函数名
     * 
     * @param functionname
     * @return
     */
    public static String getFunction(String functionname) {
        int index = functionname.indexOf("(");
        if (index == -1) {
            return functionname;
        } else {
            return functionname.substring(0, functionname.indexOf("("));
        }
    }

    /**
     * 获取自定义函数的参数
     * 
     * @param functionname
     * @return
     */
    public static String getFunParams(String functionname) {
        int index = functionname.indexOf("(");
        String param = "";
        if (index != -1) {
            String testparam = functionname.substring(functionname.indexOf("(") + 1, functionname.length() - 1);
            if (StringUtil.isNotEmpty(testparam)) {
                String[] params = testparam.split(",");
                for (String string : params) {
                    param += (string.indexOf("{") != -1) ? ("'\"+" + string.substring(1, string.length() - 1) + "+\"',")
                                    : ("'\"+rec." + string + "+\"',");
                }
            }
        }
        param += "'\"+index+\"'";// 传出行索引号参数
        return param;
    }
}

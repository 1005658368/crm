<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<%--update-start--Author:Biz  Date:20140825 for：格式化页面代码 并 添加组织机构combobox多选框--%>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="userController.do?saveUser" beforeSubmit="checkUser" >
<%--   	<input id="id" name="id" type="text" value="${user.id }">  --%>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" >
		<tr>
			<td align="right" width="15%" nowrap>
                <label class="Validform_label"> 登陆账号:<span style="color: red">*</span> </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }"> ${user.userName } </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" name="userName" class="inputxt" validType="t_s_base_user,userName,id" value="${user.userName }" datatype="/^[0-9A-Za-z]|[0-9]|[A-Z]|[a-z]$/" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to10"/></span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 用户名:<span style="color: red">*</span> </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="s2-10">
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label">性别:<span style="color: red">*</span></label></td>
			<td class="value" width="10%">
               <t:dictSelect field="sex" id="sex" typeGroupCode="sex" selectPrompt="---请选择---" defaultVal="${user.sex }" dataType="*"  hasLabel="false"  title="性别"></t:dictSelect>
               <span class="Validform_checktip">
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 员工编码:</label></td>
			<td class="value" width="10%">
                <input id="userCode" class="inputxt" name="userCode" validType="t_s_user,userCode,id" value="${user.userCode }" dataType="/^[0-9A-Za-z]{0,100}$/" onblur="checkUserCode(this)">
                <span class="Validform_checktip">
            </td>
		</tr>
		<%--<tr>--%>
			<%--<td align="right" width="10%" nowrap><label class="Validform_label"> 人员级别:</label></td>--%>
			<%--<td class="value" width="10%">--%>
                <%--<input id="employeepos" class="inputxt" name="employeepos"  value="${user.employeepos }" value="${user.employeepos }">--%>
            <%--</td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<td align="right"><label class="Validform_label">  职位: <span style="color: red">*</span></label></td>--%>
            <%--<td class="value">--%>
		     	 <%--<input id="posId" name="posId" hidden="false" value=" " type="text" style="width: 150px">  --%>
		     	 <%--<input id="posName" name="posName" type="text" value=" " readonly="readonly" style="width: 150px" class="inputxt" datatype="*">--%>
		     	 <%----%>
		     	 <%--<t:choose hiddenName="id" hiddenid="id" url="tPositionController.do?toSelectPosition" name="tPositionList" isclear="true"--%>
                       <%--icon="icon-search" title="选择职位" textname="posName,id" inputTextname="posName,posId" width="825px" height="400px"></t:choose>--%>
				<%--<span class="Validform_checktip"></span>--%>
				<%----%>
				<%----%>
<%--<!-- 				 <input class="inputxt" name="userPosition.position.posName" id="posName"  onclick="selectPosition('tPositionController.do?toSelectPosition', 720, 420);" ignore="ignore"> -->--%>
               <%----%>
<%--<!--                 <input id="posId" name="userPosition.posId" type="hidden" dataType="*"> -->--%>
<%--&lt;%&ndash;                 <span class="Validform_checktip"><t:mutiLang langKey="请选择职位"/></span> &ndash;%&gt;--%>
                <%----%>
                <%----%>
			<%--</td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.role"/>: <span style="color: red">*</span></label></td>--%>
			<%--<td class="value" nowrap>--%>
                <%--<input name="roleid" name="roleid" type="hidden" value="${id}" id="roleid">--%>
                <%--<input name="roleName" class="inputxt" value="${roleName }" id="roleName" readonly="readonly" datatype="*" />--%>
                <%--<t:choose hiddenName="roleid" width="1000px" hiddenid="id" url="userController.do?userRole" name="roleList"--%>
                          <%--icon="icon-search" title="common.role.list" textname="roleName" isclear="true"></t:choose>--%>
                <%--<span class="Validform_checktip"><t:mutiLang langKey="role.muti.select"/></span>--%>
            <%--</td>--%>
		<%--</tr>--%>
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="common.phone"/>: <span style="color: red">*</span></label></td>
			<td class="value">
                <input class="inputxt" name="mobilePhone" value="${user.mobilePhone}" datatype="n6-15" >
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<%-- <tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.tel"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="officePhone" value="${user.officePhone}" datatype="n" errormsg="办公室电话不正确" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr> --%>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.common.mail"/>: </label></td>
			<td class="value">
                <input  class="inputxt" name="email" id="email" value="${user.email}"  datatype="/^$|(^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+)/" autocomplete="off" >
                <span class="Validform_checktip"></span>
            </td>
		</tr>
			<tr>
			<td align="right" width="10%" ><label class="Validform_label"> 密码:<span style="color: red">*</span> </label></td>
			<td class="value" width="10%">
				<input type="password" name="clear" style="position:fixed;bottom:-9999px;"><!-- 防止自动填充 -->
                <input id="password" class="inputxt" type="password"  name="password" value="${user.password }" datatype="s6-10" autocomplete="off">
                <span class="Validform_checktip"> </span>
            </td>
		</tr>
	</table>
</t:formvalid>
<script type="text/javascript">
var callBack = function (selectedRows) {
	$("#posName").val(selectedRows[0].posName);
	$("#posId").val(selectedRows[0].id);
}
//运用lhgDialog的init属性，设置回调函数，解除父页面和子页面的耦合，子页面返回父页面需要的数据
var selectPosition = function (url, width, height) {
	createwindowExt("选择职位", url, width, height, {
		init: function () {
			alert();
			var currentIframe = this.iframe.contentWindow;
			currentIframe.setAddCallBack(callBack);
		}
	});
}

//重写添加客户关系弹出窗口
function createAddCustRelWindow(title, addurl, width, height) {
    width = width ? width : 700;
    height = height ? height : 320;
    if (width == "100%" || height == "100%") {
        width = window.top.document.body.offsetWidth;
        height = window.top.document.body.offsetHeight - 100;
    }
    if (typeof (windowapi) == 'undefined') {
        $.dialog({
            content : 'url:' + addurl,
            lock : true,
            zIndex:1990,
            width : width,
            height : height,
            title : title,
            opacity : 0.3,
            cache : false,
            button : [ {
                name : "确定",
                callback : function() {
                    iframe = this.iframe.contentWindow;
                    iframe.addCustRel();
                    return false;
                },
                focus : true 
            }], 
            cancelVal : '关闭',
            cancel:function(){
            	iframe = this.iframe.contentWindow;
            	return true;
            }
        }).zindex();
    } else {
        W.$.dialog({
            content : 'url:' + addurl,
            lock : true,
            width : width,
            // zIndex:1990,
            height : height,
            parent : windowapi,
            title : title,
            opacity : 0.3,
            cache : false,
            ok : '确定',
			button : [{
                    name : "确定",
                    callback : function() {
                        iframe = this.iframe.contentWindow;
                        iframe.addCustRel();
                        return false;
                    },
                    focus : true 
                }], 
            cancelVal : '关闭',
            cancel:function(){
            	iframe = this.iframe.contentWindow;
            	return true;
            }
        }).zindex();
    }
}
function checkUserCode(_this){
	var userCode=_this.value;
	var reg=/^[0-9A-Za-z]|[0-9]|[A-Z]|[a-z]$/;
	if(userCode!=""){
		if(!reg.test(userCode)){
			tip("只能输入数字和大小写字母");
			return false;
		}
	}
}

function checkUser(){
	var userName = $('#userName').val();
	if(userName.length<2 || userName.length>50){
		tip("登陆账号范围在2~50位字符");
		return false;
	}
	return true;
}
</script>
</body>
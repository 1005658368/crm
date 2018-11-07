<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><t:mutiLang langKey="common.role.info"/></title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" action="roleController.do?saveRole">
	<input name="id" type="hidden" value="${role.id}">
	<fieldset class="step">
	<div class="form">
		<label class="Validform_label">
		<t:mutiLang langKey="common.role.name"/>: <span style="color: red">*</span></label>
		<c:if test="${opid==0 }">
			<input name="roleName" ajaxurl="roleController.do?checkRoleName&code=${role.roleCode }"  class="inputxt" value="${role.roleName }" datatype="s2-20">
		</c:if>
		<c:if test="${opid==1 }">
			<input name="roleName" class="inputxt" value="${role.roleName }" datatype="s2-20">
		</c:if>
		<span class="Validform_checktip"></span>
	</div>
	<div class="form">
		<label class="Validform_label"> <t:mutiLang langKey="common.role.code"/>: <span style="color: red">*</span></label> 
		<input name="roleCode" id="roleCode" ajaxurl="roleController.do?checkRole&code=${role.roleCode }" class="inputxt"
			value="${role.roleCode }" ${opid==1 ?"readonly='readonly' ":""} datatype="/^[0-9a-zA-Z_]{2,30}$/" errormsg="必须输入英文字母或者数字且长度在2-30个字符之间"> 
			<span class="Validform_checktip"></span>
	</div>
	<div class="form">
		<label class="Validform_label"> <t:mutiLang langKey="角色类型"/>: <span style="color: red">*</span></label>
		<%--<c:if test="${opid==0 }">--%>
			<%--<t:dictSelect field="roleType" typeGroupCode="roleType" hasLabel="false" ></t:dictSelect>--%>
		<%--</c:if>--%>
		<%--<c:if test="${opid==1 }">--%>
			<%--<t:dictSelect field="roleType" typeGroupCode="roleType" hasLabel="false" defaultVal="${role.roleType }"></t:dictSelect>--%>
		<%--</c:if>--%>
		<select name="roleType">
			<option value="">---请选择---</option>
			<option value="0" selected="selected">菜单角色</option>
			<option value="1">权限角色</option>
			<option value="2">流程角色</option>
		</select>
	</div>
	</fieldset>
</t:formvalid>
</body>
</html>

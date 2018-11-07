<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="userList" title="user.manage" actionUrl="roleController.do?roleUserGrid&roleId=${roleId}" fit="true" fitColumns="true" idField="id">
	<t:dgCol title="用户id" field="userId" hidden="true" ></t:dgCol>
	<t:dgCol title="登陆账号" sortable="false" field="userName" width="5"></t:dgCol>
	<t:dgCol title="用户名" field="realName" width="5"></t:dgCol>
	<t:dgCol title="岗位名称" field="posName" width="5"></t:dgCol>
	<t:dgCol title="组织" field="departName" width="5"></t:dgCol>
	
</t:datagrid>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach items="${childFunctionList}" var="function">
	<c:if test="${fn:length(function.functionVoList)>0}">
		<li state="closed" iconcls="${function.iconClas}">
			<a>${function.functionName}</a>
			<ul style=""><c:set var="childFunctionList" value="${function.functionVoList}" scope="request"/><c:import url="menu.jsp" /></ul>
		</li>
	</c:if>
	<c:if test="${fn:length(function.functionVoList)<=0}">
		<li iconcls="default"><a onclick="addTab('${function.functionName}','${function.functionUrl}','default')" title="${function.functionName}" url="${function.functionUrl}" href="#" style="color: rgb(0, 0, 0); font-weight: normal;">${function.functionName}</a></li>
	</c:if>
</c:forEach>

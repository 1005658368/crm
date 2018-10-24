<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/accordion/css/icons.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/accordion/css/accordion.css" type="text/css"></link>
<script type="text/javascript" src="plug-in/accordion/js/leftmenu.js"></script>
<script type="text/javascript">
	function showChildMenu(){
	}

</script>

	<div class="fenster_content">
			<div class="fenster_content_left">
				<ul class="topnav">
					<c:forEach items="${functionList}" var="function"><li><a><img src='${function.iconPath}'/>&nbsp;${function.functionName}</a><ul><c:set var="childFunctionList" value="${function.functionVoList}" scope="request"/><c:import url="menu.jsp" /></ul>
					</c:forEach>
			  	</ul>
			 </div>
	</div>
	<script type="text/javascript" src="plug-in/newLeftStyle/js/fenster.js"></script>
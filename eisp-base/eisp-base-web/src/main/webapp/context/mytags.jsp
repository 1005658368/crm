<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%
    String path = request.getContextPath();
    String strHttp = (String) request.getSession().getAttribute("ishttps");
    int strport = request.getServerPort();
    String basePath="";
    if(("https").equals(strHttp)){
        basePath = strHttp+"://"+request.getServerName()+path+"/";
    }else{
        basePath = strHttp+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }
%>
<c:set var="webRoot" value="<%=basePath%>" />

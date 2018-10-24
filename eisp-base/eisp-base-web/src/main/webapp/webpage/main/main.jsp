<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>TPM系统</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<!-- <link rel="shortcut icon" href="images/favicon.ico"> -->

<style type="text/css">
*{
	padding: 0px;
	margin: 0px;
}
a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
.tree-node-selected{
    background: #eaf2ff;
}

.j-homePage .content{
	width:100%;
	height:auto;
	text-align:center;
	font-family:"Microsoft YaHei";
}
.j-homePage h1{
	font-size:40px;
	letter-spacing:10px;
}
.j-homePage h2{
	color:#1E4677;
	letter-spacing:2px;
	font-size:20px;
}
.j-homePage p{
	line-height:40px;
	font-weight:bold;
	color:#888;
	font-size:12px;
	letter-spacing:0;
}
.j-homePage .content div{
	width:500px;
	margin:0 auto;
	line-height:18px;
	color:#000;
	letter-spacing:0;
	font-size:12px;
	font-weight:normal;
	text-align: center;
}
.j-homePage .content div table{
	width:99.9999%;
	border-collapse: collapse;border-top: 1px solid #e7e7e2;
}
.j-homePage .content div div{
	height: 100%;
	overflow: auto;
}
.j-homePage img{
	margin-top:10px;
	height:260px;
	width:100%;
	opacity:0.6;
}
.j-homePage{
	height: 100%;
	height: 600px;
	width: 100%;
	background: url(plug-in/Validform/images/home-back.png)no-repeat 100% 80%;
	background-size:100% 200px;
	opacity:0.8;
}
.j-homePage table th{
	width: 50%;
	padding:8px 10px;
	background:transparent linear-gradient(to bottom, #F9F9F9 0px, #EFEFEF 100%) repeat-x scroll 0% 0%;
	color: #000;
}
.j-homePage table th:first-child{
	border-right: 1px solid #E6E6E6;
}
.j-homePage table .g-tr:hover{
	cursor: pointer;
	background-color:#eff5ff ;
	color: #000;
}

.j-homePage table td{
	padding: 7px 10px;
	border:1px dotted #E6E6E6;	
}
.j-homePage>.content>div{
	border: 1px double #95b8e7;
	border-width: 3px;
}
.j-homePage .myTable div{
	overflow:hidden !important;	
}
.j-homePage>.content>.myTable{
	border:none;
}
.j-homePage .myTable table th{
	width:auto;
}

.top-space {
	z-index: -1;
	position: absolute;
	width: 100%;
	height: 50px;
	background: #6c94c2;
	border-bottom: 1px solid #95B8E7;
}
.top-logo {
	overflow: hidden;
	/* width: 200px; */
	height: 50px;
	text-align: center;
	/* background:#75b9e6;  */
	/* background:#4aa3de;  */
	background:#1c8fbd;
	border-right:3px solid #1c8fbd;
}
 
 
.top-info {
    z-index: 51;
	position: absolute;
	right: 0;
    top: 0;
    width:260px;
    height: 50px;
     background: #1c8fbd;
}
.layout-split-west {
    border-right: 2px solid #1c8fbd!important;
}
.top-info a.l-btn span span.l-btn-text{margin-top:15px;}
#mainPanle {
    border: none;
}
.main-tips {
  /*   background: transparent linear-gradient(to bottom, #F9F9F9 0px, #EFEFEF 100%) repeat-x scroll 0% 0%;
    border-color: #F9F9F9!important; */
    border-bottom:none!important;
    background:rgb(141, 207, 245);
}
.main-menu {
	width: 200px;
	padding: 0px;
	border-right: none;
	background:#f3f5f6;
}
.top-nav {
    padding: 0;
	margin-right: 260px;
    width: auto !important;
    background: #1c8fbd!important;
}
.top-nav .tabs-wrap {
	right: 260px;
}
.top-nav .tabs {
    height: 50px;
/* 	background: #6c94c2; */
/* 	background:#75b9e6; */
    background: #1c8fbd;
	border: none;
}
.top-nav .tabs li {
	top: 9px;left: -5px;
}
.top-nav .tabs .tabs-inner {
	height: 39px;
    line-height: 39px;
}
.top-op {
	position: absolute;
	right: 0px;
	bottom: 0px;
}
.top-op a {
	color: #fff;
}
.top-op .m-btn-plain-active, .top-op .l-btn-plain:hover {
	color: #fff;
	background: #97b3d3;
	border-color: #97b3d3;
	border-radius: 0;
}
.base-info {
	position: absolute;
	right: 0px;
}
.base-info span {
	color: #fff;
}
.base-info .base-info-tit {
	color: #000;
}
#mm {
	width: 150px;
	display: none;
}
.fenster_content .topnav a:hover {
	background:rgba(231,240,255,0.5);  /* #E7F0FF; */
}
.tabs-panels.tabs-panels-noborder {
	margin: 0;
}
/* #maintabs {
   margin-top:-6px; 
} */
#myTable .tabs-header{background-color:#1c8fbd!important;}
.tabs-scroller-left, .tabs-scroller-right{
	bottom:-1px;height:50px !important;
}

.main-menu{height:100%;}
.layout-split-west {height: 100%;background:#f3f5f6}
.layout-panel-west.layout-split-west .panel-header{padding:10px 7px;}
.panel-body{border-style:none;}
div#processListtb span{
    margin-bottom: 3px!important;
}
#layout_north_kzmbMenu2 .menu-line{border-left:none;border-right:none;}
#layout_north_kzmbMenu2 .menu-text {
    
    line-height: 24px;
    float:none;
    padding-left: 15px;
    height: 24px;
}


.m-btn-downarrow {
    display: none;
}

a.l-btn span span.l-btn-text {
    /* margin-top: 15px; */
    height: 18px;
    line-height: 18px;
}
a.l-btn-plain {
    height: 50px;
    width: 56px;
    padding: 0;    overflow: hidden;
}
.l-btn-plain:hover{background:rgba(73,165,202,0.5)!important; }
.menu-active {
    -moz-border-radius: 0;
    -webkit-border-radius:0;
    border-radius:0; 
    border-color:transparent;
    height:24px;
}
.l-btn.l-btn-plain>.l-btn-left .l-btn-left{display:none;}
.tabs li a.tabs-inner {
    padding: 0px 20px 0px 26px;
}
.layout-panel-west.layout-split-west .panel-header .panel-title{font-size:14px;}
.top-logo+.panel-header{background-color:#f3f3f3!important;border-right: none;
    border-bottom: 1px solid #ccc!important;
}
.top-logo+.panel-header .panel-title{color:#333!important}
</style>
<SCRIPT type="text/javascript">
var starttip = "starttip";
$(document).ready(function(){
    initMainView();
});

function initMainView(){
    var $mainmenu = $(".main-menu"),
        $maintips = $mainmenu.siblings(".panel-header");
    $maintips.addClass("main-tips").before('<div class="top-logo"><img src="plug-in/login/images/main-logo.png" /></div>');
    $("#maintabs").find(".tabs-header").addClass("top-nav");
}
	$('.g-tr').live('click',function(){
		$('.g-tr').css('background','');
		$(this).css('background','#FDF3AD');
		var url = $(this).children().html();
		 var taskName = $(this).children().next().html();
	     addTab(taskName, url, 'default');
	});

	var tipSystemMessageTimer;
	
	var onlineInterval;
	
	function changePassWordNoClose(title,url){
		createwindowExt(title,url, "", "", {
	    	okVal: '确定',
            ok : function() {
                iframe = this.iframe.contentWindow;
                saveObj();
                return false;
            },
			cancelVal : '',
       		cancel : false
	    });
	}

	function changePassWordHaveClose(title,url){
		createwindowExt(title,url, "", "", {
	    	okVal: '确定',
            ok : function() {
                iframe = this.iframe.contentWindow;
                saveObj();
                return false;
            },
			cancelVal : '暂不修改',
       		cancel : true
	    });
	}
	
	function showChangePassWordDiv(title,url){
		var isResetPwd = '${isResetPwd}';
		var isChange = '${isChange}';		
		if("true" == isResetPwd){
			changePassWordNoClose(title,url)
		}else if("true" == isChange&&starttip =="starttip"){
			 starttip = "endtip";
			changePassWordHaveClose("你的密码为纯数字密码，过于简单，建议修改",url)
			
		}else{
			createwindowExt("切换职位",url, "", "", {
	    	okVal: '确定',
            ok : function() {
                iframe = this.iframe.contentWindow;
                saveObj();
                //location.reload();
                return false;
            },
			cancelVal : '关闭',
       		cancel : true
	        });
			
		}
		
	}
	
	function reload1(){
		window.location.reload(true);
	}
	
</SCRIPT>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<!-- 顶部-->
	<div class="top-space"></div>
	<table class="top-info" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="left" style="vertical-align: text-bottom;"></td>
			<td align="right" nowrap>
				<table>
					<%-- <tr>
						 <td valign="top" height="40">
		                  <div class="base-info">
		                        <span class="base-info-tit"><t:mutiLang langKey="common.user"/>:</span>
		                        <span>${userName }</span>
		                        <span class="base-info-tit"><t:mutiLang langKey="current.org"/>:</span>
		                        <span>${currentOrgName }</span>
		                        <span class="base-info-tit"><t:mutiLang langKey="common.role"/>:</span>
		                        <span>${roleName }</span>
		                        <span class="base-info-tit">主职位:</span>
		                        <span>${posName }</span>
		                    </div>   
		                    
		                </td>				
					</tr> --%>
					<td>
					
					
					<div style="position: absolute; right: 0px; bottom: 0px;height:50px;">
						<span style="color:#fff; position: absolute;  margin-right: 124px;  left: 0;   top: 20px;  display: inline-block;   width: 55px;  margin-left: -36px;">${userName }</span>
						<a href="javascript:void(0);" class="easyui-menubutton m-btn l-btn l-btn-plain" menu="#layout_north_kzmbMenu2" icon="icon-user" plain="true"><span class="l-btn-left"><span class="l-btn-text l-btn-icon-left"><span class="m-btn-downarrow">&nbsp;</span></span></span>
							
						 </a>
						
						<a href="javascript:void(0);" class="easyui-menubutton m-btn l-btn l-btn-plain" menu="#layout_north_kzmbMenu" icon="icon-set" plain="true" id=""><span class="l-btn-left"><span class="l-btn-text  l-btn-icon-left"><span class="m-btn-downarrow">&nbsp;</span></span></span></a>
						<a href="javascript:void(0);" class="easyui-menubutton m-btn l-btn l-btn-plain" menu="#layout_north_zxMenu" icon="icon-exit" plain="true" onclick="exit('loginController.do?logout','确认退出系统吗？',1);" id=""><span class="l-btn-left"><span class="l-btn-text  l-btn-icon-left"><span class="m-btn-downarrow">&nbsp;</span></span></span></a>	
					</div>
					
					
					<%-- <div class="top-op" style="right:180px">
						<a href="javascript:void(0);" class="easyui-menubutton m-btn l-btn l-btn-plain" menu="#layout_north_kzmbMenu2" icon="icon-user" plain="true"><t:mutiLang langKey="common.control.panel"/></a>
				 	</div>
					<div class="top-op">
						<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help"><t:mutiLang langKey="common.control.panel"/></a>
						<a class="easyui-linkbutton l-btn l-btn-plain" onclick="exit('loginController.do?logout','<t:mutiLang langKey="common.exit.confirm"/>',1);"icon="icon-back" plain="true" href="#"><t:mutiLang langKey="common.logout"/>&nbsp;</a>	
					</div>
					 --%>
				 
					
					<div id="layout_north_kzmbMenu2" style="width: 215px; left: 1144px; top: 50px; z-index: 110003; display: block;" class="menu-top menu">
						<div class="menu-text"> <span class="base-info-tit" style="color:#333"><t:mutiLang langKey="common.user"/>:</span>${userName }</div>
						 <div class="menu-sep"></div>
						<div class="menu-text"> <span class="base-info-tit" style="color:#333"><t:mutiLang langKey="current.org"/>:</span>${currentOrgName } </div>
						 <div class="menu-sep"></div>
					<%-- 	<div class="menu-text"> <span class="base-info-tit" ><t:mutiLang langKey="common.role"/>:</span>${roleName }</div>
						 --%> 
						<div class="menu-text"><span>主职位:</span><span>${posName }</span></div>
					</div>
					
					
					<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
						<div onclick="openwindow('<t:mutiLang langKey="common.profile"/>','userController.do?userinfo')"><t:mutiLang langKey="common.profile"/></div>
						<div class="menu-sep"></div>
						<input type="hidden" name="loginNum" id='loginNum' value="${loginNum}">
						<div id="changePassWordDiv" onclick="showChangePassWordDiv('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword&isResetPwd=${isResetPwd}&isChange=${isChange}')"><t:mutiLang langKey="common.change.password"/></div>
						<div class="menu-sep"></div>
						<div id="changePassWordDiv" onclick="showChangePassWordDiv('切换职位','userController.do?toChangePosition')">切换职位</div>
			
						<%-- <div class="menu-sep"></div>
							<div onclick="add('<t:mutiLang langKey="common.change.style"/>','userController.do?changestyle')"><t:mutiLang langKey="common.my.style"/></div> --%>
					</div>
					
					
					<div id="layout_north_zxMenu" style="width: 100px; display: none;">
						 
						<div onclick="exit('loginController.do?logout','<t:mutiLang langKey="common.exit.confirm"/>',1);"><t:mutiLang langKey="common.exit"/></div>
					</div>
					
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	
 
	
	
	
	<!-- 左侧-->
	<div class="main-menu" region="west" split="true" href="loginController.do?left" title=<t:mutiLang langKey="common.navegation"/> ></div>
	
	<!-- 中间-->
	<div id="mainPanle" region="center" style="overflow: hidden;">
		<div id="maintabs" class="easyui-tabs" fit="true" border="false">
			<div class="easyui-tab" title=<t:mutiLang langKey="common.dash_board"/> href="loginController.do?home" style="padding: 2px; overflow: hidden;"></div>
			<c:if test="${map=='1'}">
				<div class="easyui-tab" title=<t:mutiLang langKey="common.map"/> style="padding: 1px; overflow: hidden;">
					<iframe name="myMap" id="myMap" scrolling="no" frameborder="0" src="mapController.do?map" style="width: 100%; height: 99.5%;"></iframe>
				</div>
			</c:if>
		</div>
	</div>
	
	<!-- 右侧 -->
	<%-- <div collapsed="true" region="east" iconCls="icon-reload" title=<t:mutiLang langKey="common.assist.tools"/> split="true" style="width: 190px;"
		data-options="onCollapse:function(){easyPanelCollapase()},onExpand:function(){easyPanelExpand()}">
	<div id="tabs" class="easyui-tabs" border="false" style="height: 240px">
	<div title='<t:mutiLang langKey="common.calendar"/>' style="padding: 0px; overflow: hidden; color: red;">
	<div id="layout_east_calendar"></div>
	</div>
	</div>
	<div id="layout_eisp_onlinePanel" data-options="fit:true,border:false" title=<t:mutiLang langKey="common.online.user"/>>
	<table id="layout_eisp_onlineDatagrid"></table>
	</div> --%>
	<!-- 底部 -->
	<!-- <div region="south" border="false" style="height: 25px; overflow: hidden;"> -->
	<!-- <div align="center" style="color: #CC99FF; padding-top: 2px">
		<a href="http://www.eisp.org" title=" 成都博智维讯信息技术有限公司"> 成都博智维讯信息技术有限公司(推荐谷歌浏览器，获得更快响应速度)</a> 
		<t:mutiLang langKey="common.copyright"/>:<a href="#" title=" 成都博智维讯信息技术有限公司">成都博智维讯信息技术有限公司</a> </span></div>
	</div> -->

	<div id="mm" class="easyui-menu">
		<div id="mm-tabupdate"><t:mutiLang langKey="common.refresh"/></div>
		<div id="mm-tabclose"><t:mutiLang langKey="common.close"/></div>
		<div id="mm-tabcloseall"><t:mutiLang langKey="common.close.all"/></div>
		<div id="mm-tabcloseother"><t:mutiLang langKey="common.close.all.but.this"/></div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright"><t:mutiLang langKey="common.close.all.right"/></div>
		<div id="mm-tabcloseleft"><t:mutiLang langKey="common.close.all.left"/></div>
	</div>
	<script type="text/javascript" src="plug-in/newLeftStyle/js/fenster.js"></script>
</body>
</html>

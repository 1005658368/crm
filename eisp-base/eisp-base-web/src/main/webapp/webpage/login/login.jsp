<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<%
	String lang = org.crmframework.core.util.BrowserUtils.getBrowserLanguage(request);
	String langurl = "plug-in/mutiLang/" + lang +".js";
%>
<html>
<head>
	<title></title>
	<script src=<%=langurl%> type="text/javascript"></script>
	<script src="plug-in/jquery/jquery.tools.js" type="text/javascript"></script>
	<script src="plug-in/md5/aes.js" type="text/javascript"></script>
	<script src="plug-in/md5/mode-ecb-min.js" type="text/javascript"></script>
	<link href="plug-in/login/slide/base.css" type="text/css" rel="stylesheet" />
	<link href="plug-in/login/slide/public.css" type="text/css" rel="stylesheet" />
	<link href="plug-in/login/slide/layout.css" type="text/css" rel="stylesheet" />
	<link href="plug-in/login/slide/main.css" type="text/css" rel="stylesheet" />
	<link href="plug-in/login/css/zice.style.css" rel="stylesheet" type="text/css" />
	<link href="plug-in/login/css/buttons.css" rel="stylesheet" type="text/css" />
	<link href="plug-in/login/css/icon.css" rel="stylesheet" type="text/css?v=20171226" />
	<link rel="stylesheet" type="text/css" href="plug-in/login/css/tipsy.css" media="all" />
	<style type="text/css">
		html{overflow:hidden;}
		.form-label{color:#fff}
		#loginButton { float: right; padding: 1px 0; margin-right: -5px; margin-top: 25px;}
		@media screen and (-webkit-min-device-pixel-ratio:0) { #loginButton { float: right; padding: 1px 0; margin-right: -8px; } }
		@media screen and(-ms-high-contrast:active),(-ms-high-contrast:none) { #loginButton { float: right; padding: 1px 0; margin-right: -15px; } }
		#sfaQr { float: right; margin: 0; padding: 0; }
		#sfaQr img {width: 64px; margin-right: 10px;margin-top: 25px;}
		#sfaQr img:hover { cursor: crosshair; opacity: 1; }
		.qrTip { top: 2px; left: 0; color: #fff; background-color: rgba(0,0,0,0.7); position: relative; width: 96px; padding: 8px 25px; }
		select#langCode {
			width: 98px;
		}
	</style>
	<script type="text/javascript">
        var keyval = "<%=request.getSession().getId().substring(0, 16)%>";
        $(function() {
            $(".slidetabs").tabs(".slideimages > div",{
                effect: 'fade',
                fadeOutSpeed: "slow",
                rotate: true
            }).slideshow({
                interval:5000,
                autoplay: true
            });

            // sfaQr Tips
            $("#sfaQr img").hover(function(event) {
                qrTitle = this.title;
                this.title = "";
                var qrTip = '<div class="qrTip">' + qrTitle + '</div>';

                $("#sfaQr").append(qrTip);
                /* $(".qrTip").css({
                    "top" : "2px",
                    "left" : "0px",
                    "color" : "#fff",
                    "position" : "relative",
                    "width" : "96px",
                    "padding" : "8px 25px",
                    "background-color" : "rgba(0,0,0,0.7)"
                }); */
            }, function() {
                this.title = qrTitle;
                $(".qrTip").remove();
            });
        });
	</script>
</head>
<body>
<!-- header begin -->
<div class="topbar">
	<div class="section">
		<div class="mininavLeft">
			<!-- <img  src="plug-in/login/images/logo.png"> -->
		</div>
		<div class="mininavRight">
		</div>
	</div>
</div>
<div class="header">
	<div class="wf-wrap">
		<div class="branding">
			<a href="">
			</a>
		</div>
		<div class="navigation">
			<ul class="main-nav">
				<li class="acts">
				</li>
			</ul>
		</div>
	</div>
</div>
<!-- slider begin -->
<div class="slide" id="loginim">
	<div class="login-box">
		<div class="login-aside" id="loginIframe" style="margin-top:-39px">
			<form action="loginController.do?login" check="loginController.do?checkuser" name="formLogin" id="formLogin" method="post">
				<h1>欢迎登陆TPM系统</h1>
				<div class="login-form-con" >
					<fieldset>
						<div class="account">
							<label class="form-label" for="logonId">账户名：</label>
							<input type="text" name="userName" type="text" id="userName" autocomplete="on" class="login-input" maxlength="100" iscookie="true" nullmsg=""/>
						</div>
						<div class="passwords">
							<label class="form-label" desc="登录密码">登录密码：</label>
							<input type="password" name="passWord" id="passWord" autocomplete="off" class="login-pass" title="" nullmsg=""/>
						</div>
						<br>
						<div >
							<input class="randCode" name="randCode" type="text" id="randCode"  autocomplete="on" style="width: 130px" class="login-input" maxlength="100" />
							<img id="randCodeImage" src="randCodeImage" style="float:right;margin-top: 7px;" width="80px" height="22px" />
						</div>
						<div id="loginButton">
							<div>
								<ul class="uibutton-group">
									<li><a class="uibutton normal" href="#" id="but_login"><t:mutiLang langKey="common.login"/></a></li>
									<li><a class="uibutton normal" href="#" id="forgetpass"><t:mutiLang langKey="common.reset"/></a></li>
								</ul>
							</div>
							<t:dictSelect id="langCode" field="langCode" typeGroupCode="lang" hasLabel="false" defaultVal="zh-cn"></t:dictSelect>
						</div>

						<div id="sfaQr">
							<img alt="三全SFA安卓端二维码" title="SFA安卓端二维码" src="data:image/png;base64,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">
							<img alt="三全SFA苹果端二维码" title="SFA苹果端二维码" src="data:image/png;base64,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">
						</div>

						<div class="clear"></div>
					</fieldset>
				</div>
			</form>
		</div>
	</div>
	<div class="slideimages">
		<div style="display: none; background-image:url(plug-in/login/images/bg20180106.png); opacity: 1;"></div>
		<div style="display: none; background-image:url(plug-in/login/images/bg20180106.png); opacity: 1;"></div>
		<div style="display: none; background-image:url(plug-in/login/images/bg20180106.png); opacity: 1;"></div>
	</div>
	<div class="slide-number-box">
		<div class="slidetabs">
			<a href="#"></a>
			<a href="#"></a>
			<a href="#"></a>
		</div>
	</div>
	<div class="shadow"></div>
	<div id="alertMessage"></div>
</div>
<!-- slider end -->
<!-- Link JScript-->
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery-jrumble.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript" src="plug-in/login/js/login.js?v=1_1"></script>
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
</body>
</html>

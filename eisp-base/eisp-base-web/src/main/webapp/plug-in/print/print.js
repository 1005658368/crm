$.ajaxSetup({
   async:false
  });
/**费用系统打印公用JS接口*/
function printPageInfo(param){
	/*
	* Eg: param = "&servicePrefix=cityManager&reqId=123456";
	*/
	
	var url = "costAuditPrintController.do?printPageInfo" + param;
	var temp = {};
	//弹框
	temp.dialog = $.dialog({content: 'url:' + url,zIndex: 2100,title: '客户列表',lock : true,
		parent:windowapi,width :1200,height :600,left :'65%',top :'55%',opacity : 0.4
	});
}

function printUrl(url, title) {
	$.dialog({
		content:"url:printController.do?print&printUrl=" + url,
		width: 500,
	    height: 500,
	    modal:true,
	    title: '打印信息',
	    init:function(){
	    	this.max();
	    },
	    button:[{name: '预览',callback:function(){this.iframe.contentWindow.previewData(title);return false;}},
	            {name: '打印',callback:function(){this.iframe.contentWindow.printData(title);return false;}},
	            {name: '关闭',callback:function(){}}]
	});
}

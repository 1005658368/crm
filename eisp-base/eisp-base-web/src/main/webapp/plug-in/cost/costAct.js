/***************更新费用细类******************/
function _changeSmallType(){
	var productCode = $("#productCode").val();
	var channelCode = $("#channelCode").val();
	var bigTypeCode = $("#bigTypeCode").val();
	
	if(productCode == "undefined" || productCode == "" || channelCode == "undefined" || channelCode == ""
		|| bigTypeCode == "undefined" || bigTypeCode == ""){
		return;
	}
	
	$.getJSON("costController.do?queryCostSmallTypeInfo",{
		"productCode":productCode,"channelCode":channelCode,"bigTypeCode":bigTypeCode},function(json){
		//先清空数据
		$("#smallTypeCode").html("<option value=''>---------请选择---------</option>");
		$("#useWayCode").html("<option value=''>---------请选择---------</option>");
		$("#costCodeVersion").text("");
		$("#costCode").val("");
		
		//清空客户数据
		_clearSelectedClientInfo();
		
		//循环插入数据
		$.each(json.attributes.costActList,function(key,val){
			$("#smallTypeCode").append("<option value=" + val.actcode+">" + val.actname + "</option>");
		});
    	
	});
}

/***************更新费用使用方式******************/
function _changeUseWay(){
	var smallTypeCode = $("#smallTypeCode").val();
	
	if(smallTypeCode == "undefined" || smallTypeCode == ""){
		return;
	}
	
	$.getJSON("costController.do?queryCostUseWayInfo",{
		"smallTypeCode":smallTypeCode},function(json){
			
		$("#useWayCode").html("<option value=''>---------请选择---------</option>");
		$("#costCodeVersion").text("");
		$("#costCode").val("");	
		
		//清空客户数据
		_clearSelectedClientInfo();
		
		//循环插入数据
		$.each(json.attributes.costActList,function(key,val){
			$("#useWayCode").append("<option value=" + val.actcode+">" + val.actname + "</option>");
		});
    	
	});
	
	_chanageCostVersionInfo();
}

/***************更新费用归属******************/
function _chanageCostVersionInfo(){
	var productCode = $("#productCode").val();
	var channelCode = $("#channelCode").val();
	var bigTypeCode = $("#bigTypeCode").val();
	var smallTypeCode = $("#smallTypeCode").val();
	var bgDate = $("#bgDate").val();
	var enDate = $("#enDate").val();
	
	if(productCode == "undefined" || productCode == "" || channelCode == "undefined" || channelCode == ""
		|| bigTypeCode == "undefined" || bigTypeCode == "" || smallTypeCode == "undefined" || smallTypeCode == ""
		|| bgDate == "undefined" || bgDate == "" || enDate == "undefined" || enDate == ""){
		return;
	}
	
	$.getJSON("costController.do?queryCostVersionInfo",{
		"productCode":productCode,"channelCode":channelCode,"bigTypeCode":bigTypeCode,
		"smallTypeCode":smallTypeCode,"bgDate":bgDate,"enDate":enDate},function(json){
		$("#costCodeVersion").html(json.obj.costName);
		$("#costCode").val(json.obj.costcode);
		
		_updateCostVs(json);
	});
}

/************更新费用信息*********/
function _updateCostVs(json){
	if(json == null || typeof(json) == "undefined" || json.length == 0
			|| json == ""){
		$("#payTime").html("");
    	$("#syr").html("");
    	$("#actualPay").html("");
    	$("#quarterBack").html("");
    	$("#complementGood").html("");
    	$("#siteHook").html("");
    	$("#cityManagerHook").html("");
    	$("#dealerHook").html("");
    	$("#wlmHook").html("");
    	$("#moneyDiscount").html("");
    	$("#goodDiscount").html("");
    	$("#cjz").html("");
    	$("#cwPay").html("");
    	$("#wlRush").html("");
    	$("#stdp").html('无');
    	$("#cost_type").html("");
    	$("#auditBy").val("");
    	$("#hx_type").html("");
    	$("#dpcost").val("");
    	$("#moneyLimit").val("");
	}else {
		$("#payTime").html(json.obj.paymentTime);
    	$("#syr").html(json.obj.benefit);
    	$("#actualPay").html(json.obj.actualPay=="Y"?"是":"否");
    	$("#quarterBack").html(json.obj.quarterBack=="Y"?"是":"否");
    	$("#complementGood").html(json.obj.complementGood=="Y"?"是":"否");
    	$("#siteHook").html(json.obj.siteHook=="Y"?"是":"否");
    	$("#cityManagerHook").html(json.obj.cityManagerHook=="Y"?"是":"否");
    	$("#dealerHook").html(json.obj.dealerHook=="Y"?"是":"否");
    	$("#wlmHook").html(json.obj.wlmHook=="Y"?"是":"否");
    	$("#moneyDiscount").html(json.obj.moneyDiscount!=""?json.obj.moneyDiscount+"%":json.obj.moneyDiscount);
    	$("#goodDiscount").html(json.obj.goodDiscount!=""?json.obj.goodDiscount+"%":json.obj.goodDiscount);
    	$("#cjz").html(json.obj.cjz!=""?json.obj.cjz+"%":json.obj.cjz);
    	$("#cwPay").html(json.obj.cwPay!=""?json.obj.cwPay+"%":json.obj.cwPay);
    	$("#wlRush").html(json.obj.wlRush!=""?json.obj.wlRush+"%":json.obj.wlRush);
    	$("#stdp").html(json.obj.standardCalculate == 0?"无":json.obj.standardCalculate);
    	$("#dpcost").val(json.standardCalculate);
    	$("#moneyLimit").val(json.moneyLimit);
    	$("#auditBy").val(json.obj.auditby);
    	if(json.obj.costType == "cost_complex")
	    	$("#cost_type").html("综合");
    	if(json.obj.costType == "cost_count")
	    	$("#cost_type").html("计算");
    	if(json.obj.costType == "cost_provision")
	    	$("#cost_type").html("计提");
    	if(json.obj.auditby == "M")
    		$("#hx_type").html("按金额核算");
    	if(json.obj.auditby == "S")
    		$("#hx_type").html("按销量核算");
	}
}

/***********更新费用结案资料***********/
function _costVerifyData(){
	var productCode = $("#productCode").val();
	var channelCode = $("#channelCode").val();
	var bigTypeCode = $("#bigTypeCode").val();
	var smallTypeCode = $("#smallTypeCode").val();
	var useWayCode = $("#useWayCode").val();
	
	if(productCode == "undefined" || productCode == "" || channelCode == "undefined" || channelCode == ""
		|| bigTypeCode == "undefined" || bigTypeCode == "" || smallTypeCode == "undefined" || smallTypeCode == ""
		|| useWayCode == "undefined" || useWayCode == ""){
		return;
	}
	
	$.getJSON("costController.do?queryCostVerifyData",{
		"product":productCode,"channel":channelCode,"bigType":bigTypeCode,
		"smallType":smallTypeCode,"useWay":useWayCode},function(json){
			//清空客户数据
			_clearSelectedClientInfo();
		$("#verifyData").html(json.obj.info);
	});
}


function _clearExistsCostInfo(){
	//先清空数据
	$("#smallTypeCode").html("<option value=''>---------请选择---------</option>");
	$("#useWayCode").html("<option value=''>---------请选择---------</option>");
	$("#costCodeVersion").html("");
	$("#costCode").val("");
	$("#payTime").html("");
	$("#syr").html("");
	$("#actualPay").html("");
	$("#quarterBack").html("");
	$("#complementGood").html("");
	$("#siteHook").html("");
	$("#cityManagerHook").html("");
	$("#dealerHook").html("");
	$("#wlmHook").html("");
	$("#moneyDiscount").html("");
	$("#goodDiscount").html("");
	$("#cjz").html("");
	$("#cwPay").html("");
	$("#wlRush").html("");
	$("#stdp").html('无');
	$("#cost_type").html("");
	$("#auditBy").val("");
	$("#hx_type").html("");
	$("#dpcost").val("");
	$("#moneyLimit").val("");
	_clearSelectedClientInfo();
}

/*******清除客户数据********/
function _clearSelectedClientInfo(){
	//清除客户数据
	var rows =  $('#clientGrid').datagrid('getRows');
	var len = rows.length;
	if(rows != 0){
		for(var i = 0; i < len; i++){
			var row = rows[i];
			var rowIndex = $('#clientGrid').datagrid('getRowIndex', row);
			$('#clientGrid').datagrid('deleteRow', rowIndex);
		}
	}
}

/**判断金额是否受到限制，也就是说我司费用是否可以大于余额的情况也可以允许其申报,true表示不受到限制*/
function _isMoneyLimit(costSmallTypeCode){
	var flag = false;
	if(costSmallTypeCode != null && costSmallTypeCode != "undefined"){
		if(costSmallTypeCode == "XL023" || costSmallTypeCode == "XL024" || costSmallTypeCode == "XL025"
			|| costSmallTypeCode == "XL028" || costSmallTypeCode == "XL036" || costSmallTypeCode == "XL037"
				|| costSmallTypeCode == "XL038"){
			flag = true;
		}
	}
	
	return flag;
}

/***********子页面使用的消息提示**********/
function msgTips(msg,fn){
	W.$.dialog.tips(msg,2,'tips.gif',fn);
}

/***********本页面使用的消息提示**********/
function msgTipsSelf(msg,fn){
	$.dialog.tips(msg,2,'tips.gif',fn);
}

/***********客户资料编辑验证(单个|多个,type == 1 表示的是单个费用)**********/
function autoCreateBotCost(type){
	var preCost = $("#preCost").val();
	var myCost = $("#myCost").val();
	var stdp = $("#stdp").val();
	var gpMoney = 0.0;
	if(type == 1){
		gpMoney = $("#gpMoney").val();
	}
	
	var costSmallType = $("#costSmallType").val();
	
	//判断是否是浮点数
	var regExp22 = new RegExp("^[0-9.-]*$");
	var auditBy = W.$("#auditBy").val();
	
	if(auditBy == "S"){
		
		//预估销量必须填写
		if(preCost == "undefined" || preCost == ""){
			$("#preCostErrorInfo").html("<span style='color:red;'>预估销量必须填写!</span>");
		}else {
			if(parseInt(preCost) <= 0){
				$("#preCostErrorInfo").html("<span style='color:red;'>预估销量必须大于0!</span>");
			}else {
				$("#preCostErrorInfo").html("");
			}
		}
		
		//我司费用必须填写
		if(myCost == "undefined" || myCost == ""){
			$("#myCostErrorInfo").html("<span style='color:red;'>我司费用必须填写!</span>");
		}else {
			$("#myCostErrorInfo").html("");
		}
		
		//我司费用不能大于经销商余额
		if(myCost != "undefined" && myCost != ""){
			if(!regExp22.test(myCost)){
				$("#myCostErrorInfo").html("<span style='color:red;'>我司费用不正确!</span>");
			}else {
				if(type == 1){
					var flag = _isMoneyLimit(costSmallType);
					//金额是否受到限制
					if(!flag){
						if(parseFloat(myCost) > parseFloat(gpMoney)){
							$("#myCostErrorInfo").html("<span style='color:red;'>我司费用大于了余额!</span>");
						}else {
							$("#myCostErrorInfo").html("");
						}
					}else {
						$("#myCostErrorInfo").html("");
					}
				}else {
					$("#myCostErrorInfo").html("");
				}
			}
		}
		
		//验证单瓶费用
		if(preCost != "undefined" && preCost != "" && myCost != "undefined" && myCost != ""){
			var botCost = myCost/preCost;
			$("#botCost").val(botCost);
			
			if(isNaN(botCost) || botCost == "Infinity" || (parseFloat(botCost) > parseFloat(stdp))){
				$("#botCostErrorInfo").html("<span style='color:red;'>单瓶费用不正确!</span>");
			}else {
				$("#botCostErrorInfo").html("");
			}
		}
	}else {
		
		//我司费用必须填写
		if(myCost == "undefined" || myCost == ""){
			$("#myCostErrorInfo").html("<span style='color:red;'>我司费用必须填写!</span>");
		}else {
			$("#myCostErrorInfo").html("");
		}
		
		//我司费用不能大于经销商余额
		if(myCost != "undefined" && myCost != ""){
			if(!regExp22.test(myCost)){
				$("#myCostErrorInfo").html("<span style='color:red;'>我司费用不正确!</span>");
			}else {
				if(type == 1){
					if(parseFloat(myCost) > parseFloat(gpMoney)){
						$("#myCostErrorInfo").html("<span style='color:red;'>我司费用大于了余额!</span>");
					}else {
						$("#myCostErrorInfo").html("");
					}
				}else {
					$("#myCostErrorInfo").html("");
				}
			}
		}
	}
}


/***********按数量验证编辑的客户信息,true表示通过,false表示失败**********/
function validCostClitInfoAuditByS(iframe){
	  var preCostErrorInfo = $(iframe.document.getElementById("preCostErrorInfo")).text();
	  var myCostErrorInfo = $(iframe.document.getElementById("myCostErrorInfo")).text();
	  var clientCostErrorInfo = $(iframe.document.getElementById("clientCostErrorInfo")).text();
	  var botCostErrorInfo = $(iframe.document.getElementById("botCostErrorInfo")).text();
	  
	  var tips1 = "预估销量必须填写!";
	  var tips2 = "请填写数值！";
	  var tips3 = "我司费用必须填写!";
	  var tips4 = "我司费用不正确!";
	  var tips5 = "我司费用大于了余额!";
	  var tips6 = "单瓶费用不正确!";
	  var tips7 = "请填写正确信息！";
	  var tips8 = "预估销量必须大于0!";
	  
	  if(tips1 == preCostErrorInfo || tips2 == preCostErrorInfo || tips3 == myCostErrorInfo || tips4 == myCostErrorInfo
			  || tips5 == myCostErrorInfo || tips6 == botCostErrorInfo || tips7 == clientCostErrorInfo || tips8 == preCostErrorInfo){
		  
		  validateErrTipsInfo(tips1,preCostErrorInfo,iframe);
		  validateErrTipsInfo(tips2,preCostErrorInfo,iframe);
		  
		  validateErrTipsInfo(tips3,myCostErrorInfo,iframe);
		  validateErrTipsInfo(tips4,myCostErrorInfo,iframe);
		  validateErrTipsInfo(tips5,myCostErrorInfo,iframe);
		  
		  validateErrTipsInfo(tips6,botCostErrorInfo,iframe);
		  
		  validateErrTipsInfo(tips7,clientCostErrorInfo,iframe);
		  
		  validateErrTipsInfo(tips8,preCostErrorInfo,iframe);
		  
		  return false;
	  }else {
		  return true;
	  }
}

/***********按金额验证编辑的客户信息,true表示通过,false表示失败**********/
function validCostClitInfoAuditByM(iframe){
	  var preCostErrorInfo = $(iframe.document.getElementById("preCostErrorInfo")).text();
	  var myCostErrorInfo = $(iframe.document.getElementById("myCostErrorInfo")).text();
	  var clientCostErrorInfo = $(iframe.document.getElementById("clientCostErrorInfo")).text();
	  var botCostErrorInfo = $(iframe.document.getElementById("botCostErrorInfo")).text();
	  
	  var tips1 = "预估销量必须填写!";
	  var tips2 = "请填写数值！";
	  var tips3 = "我司费用必须填写!";
	  var tips4 = "我司费用不正确!";
	  var tips5 = "我司费用大于了余额!";
	  var tips6 = "单瓶费用不正确!";
	  var tips7 = "请填写正确信息！";
	  var tips8 = "预估销量必须大于0!";
	  
	  if(tips3 == myCostErrorInfo || tips4 == myCostErrorInfo
			  || tips5 == myCostErrorInfo){
		 
		  validateErrTipsInfo(tips3,myCostErrorInfo,iframe);
		  validateErrTipsInfo(tips4,myCostErrorInfo,iframe);
		  validateErrTipsInfo(tips5,myCostErrorInfo,iframe);
		  
		  return false;
	  }else {
		  return true;
	  }
}
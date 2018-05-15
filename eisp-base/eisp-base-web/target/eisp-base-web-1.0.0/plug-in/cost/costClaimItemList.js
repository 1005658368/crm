function claimSubmit(title,url) {
	var reqIds = getcostClaimItemListSelections("reqId");
	window.top.$.messager.progress({
		text : '操作执行中....',
		interval : 300
	});
	$.ajax({
		type : 'POST',
		data : {reqId:reqIds + ""},
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var v = $.parseJSON(data);
			if(v.success == false) {
				$.messager.alert('错误', v.msg);	
			} else {
				$.messager.alert('正确', v.msg);
				reloadcostClaimItemList();
			}
			window.top.$.messager.progress('close');
		}
	});
}

function editCostClaimItem(index) {
	$("#costClaimItemList").datagrid("selectRow", index);
	var r = $("#costClaimItemList").datagrid("getSelected");
	if(r.itemStatus != 0 && r.itemStatus != 1) {
		$.messager.alert('错误', "活动已提交，不能修改");
		return ;
	}
	var prefix = $("#prefix").val();
	var url = prefix + "Controller.do?edit&reqId=" + r.reqId;
	var dir = getURLPrefixDir();
	openwindow('修改', url, dir + "/costClaimItemList", 820, 500);
}

function attachmentDetail() {
	var reqId = getcostClaimItemListSelected("reqId");
	var itemStatus = getcostClaimItemListSelected("itemStatus");
	var siteHook = getcostClaimItemListSelected("siteHook");
	if(itemStatus != 0 && itemStatus != 1) {
		$.messager.alert('错误', "活动已提交，不能添加附件");
		return ;
	}
	if(siteHook == "N") {
		$.messager.alert('错误', "活动不能添加附件");
		return ;
	}
	var url = "costClaimSiteController.do?costClaimSite&reqId=" + reqId;
	openwindow('附件', url, "costClaimItemList", 820, 500);
}

$(function() {
	$("input[name='actMonth']").attr("class","Wdate").attr("style","height:20px;width:105px;").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
	
	$("select[name$='costBigType']").change(function(){
		var bigTypeCode = $("select[name$='costBigType']").val();
		var productCode = $("select[name$='product']").val();
		var channelCode = $("select[name$='channel']").val();
		
		if(productCode == "undefined" || productCode == "" || channelCode == "undefined" || channelCode == ""
			|| bigTypeCode == "undefined" || bigTypeCode == ""){
			return;
		}
		
		$.getJSON("costController.do?queryCostSmallTypeInfo",{
			"productCode":productCode,"channelCode":channelCode,"bigTypeCode":bigTypeCode},function(json){
			//先清空数据
			$("select[name$='costSmallType']").html("<option value=''>---------请选择---------</option>");
			//循环插入数据
			$.each(json.attributes.costActList, function(key,val){
				$("select[name$='costSmallType']").append("<option value=" + val.actcode+">" + val.actname + "</option>");
			});
		});
	});
});
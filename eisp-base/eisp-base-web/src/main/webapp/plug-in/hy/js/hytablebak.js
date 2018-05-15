
	//
	function reloadTable(){
		return true;
	}

	//活动table 自适应窗口

	//$(window).resize(function(){resize()});
	$(function(){setInterval("resize()",1000)});

	function resize(){
		$(".layout-body").each(function(){
			$(this).find(".cssModel").css({"height":100})
			var max = $(this).height();
			var min = $(this).find(".datagrid").eq("0").height();
			$(this).find(".cssModel").css({"height":(max-min+98)});
		})
	}
	
	function resizeTest() {
		$(".layout-panel-center .cssModel").css({"height":0});
		$(".layout-panel-center .cssModel").css({"height":($(".layout-panel-center").height()-$(".layout-panel-center .datagrid").height()-5)});
		$(".layout-panel-east .cssModel").css({"height":0});
		$(".layout-panel-east .cssModel").css({"height":($(".layout-panel-east").height()-$(".layout-panel-east .datagrid").height()-5)});
		$(".layout-panel-west .cssModel").css({"height":0});
		$(".layout-panel-west .cssModel").css({"height":($(".layout-panel-west").height()-$(".layout-panel-west .datagrid").height()-5)});
		$(".layout-panel-north .cssModel").css({"height":0});
		$(".layout-panel-north .cssModel").css({"height":($(".layout-panel-north").height()-$(".layout-panel-north .datagrid").height()-5)});
		$(".layout-panel-south .cssModel").css({"height":0});
		$(".layout-panel-south .cssModel").css({"height":($(".layout-panel-south").height()-$(".layout-panel-south .datagrid").height()-5)});
	}
	function resizeModel() {
		//$(".cssModel").animate({"height": (($(window).height() - 115) > 200 ? ($(window).height() - 115) : 200)}, 100);
		
		for (var i=0;i<getElementsbyClassName("cssModel").length;i++){
			var currentModel = getElementsbyClassName("cssModel")[i];
			//$(".cssModel").css({"height":0});
			currentModel.style.height = 0;
			var max = parentLoop(currentModel,"layout-body");
			var min = parentLoop(currentModel,"datagrid");
			if (max !== false && min !== false){
				//$(".cssModel").css({"height":(max-min-10)});
				currentModel.style.height = (max-min-10);
			}
		}
	}
	function parentLoop(obj,name){
		if (obj == null) return false;
		var exist = false;
		for (var key in obj.className.split(" ")){
			if (obj.className.split(" ")[key] == name)
				exist = true;
		}
		if (exist){
			return obj.clientHeight;
		}else{
			return parentLoop(obj.parentElement,name);
		}
	}

	//宏定义当前页多个表格框架
	var tableModelArr=new Array();
	function tableModel(pub_arr){
		tableModelArr['num']=pub_arr['tagNum']=pub_arr['tagNum']?pub_arr['tagNum']:(tableModelArr['num']?tableModelArr['num']-(-1):'1');
		tableModelArr[tableModelArr['num']]=new Array();
		tableModelArr[tableModelArr['num']]['tableId']=pub_arr['tableId'];
		tableModelArr[tableModelArr['num']]['formId']=pub_arr['formId'];
		
		//添加tr行点击选中事件
		$("#"+pub_arr['tableId']+" tbody").on("click","td",function(){
			if ($(this).find("*[name='id']").length == 0 && $(this).closest("tr").find("*[name='id']").length > 0){
				var checked = ($(this).find(".editStatus").length == 0 || $(this).find(".editStatus").css("display") == "none")?true:!$(this).closest("tr").find("*[name='id']")[0].checked;
				$(this).closest("tr").find("*[name='id']")[0].checked = checked;
				$(this).closest("tr").find("*[name='id']").eq(0).trigger("change");
			}
			//$(this).find("*[name='id']")[0].checked = !$(this).find("*[name='id']")[0].checked;
		});
		//添加tr行选中样式事件
		$("#"+pub_arr['tableId']+" tbody").on("change","*[name='id']",function(){
			$(this).closest("table").find("tbody tr").each(function(){
				if ($(this).find("*[name='id']")[0].checked == true)
					$(this).addClass("activeModel");
				else 
					$(this).removeClass("activeModel");
			});
		});
		//添加input框宽度样式事件
		$("#"+pub_arr['tableId']+" ").on("change keyup","input,select",function(){
			if ($(this).attr("type")=="checkbox") return false;
			if (this.tagName == "INPUT") 
				$("body").append('<pre id="'+$(this).attr("id")+'_pre" style="display:none" >'+$(this).val()+'</pre>');
			else if (this.tagName == "SELECT")
				$("body").append('<pre id="'+$(this).attr("id")+'_pre" style="display:none" >'+$(this).find("option:checked").html()+'</pre>');
			//$(this).css("width","100%");
			//var allWidth = $(this).css("width").replace("px","");
			var textWidth = $('#'+$(this).attr("id")+'_pre').css("width").replace("px","");
			//$(this).css("width",Math.max((textWidth-(-20)),allWidth*0.8));
			$(this).css("min-width",(textWidth-(-20)));
			$('#'+$(this).attr("id")+'_pre').remove();
		});
		//固定序号列的宽度
		$("#"+pub_arr['tableId']+" thead tr:eq(1) .datagrid-cell-check").closest("td").css("width","27px");
		//触发下拉列的宽度
		$("#"+pub_arr['tableId']+" thead tr:eq(0) select").trigger("change");
		/*
		//鼠标选中复制
		$("#"+pub_arr['tableId']+"").parent().append('<input type="text" class="" style="position:absolute;z-index:-1;top:0;left:0;" id="'+pub_arr['tableId']+'_copy" />');
		$("#"+pub_arr['tableId']+" tbody").on("hover","td",function(){
			if ($(this).find(".editStatus").length==0 || $(this).find(".editStatus").css("display")=="none") {
				$(this).find("input,select").eq(0).focus();
				$(this).find("input,select").eq(0).select();
			}else {
				$("#"+$(this).closest("table").attr("id")+"_copy").val($(this).find("input").val());
				$("#"+$(this).closest("table").attr("id")+"_copy").select();
			}
		})
		*/
		//动态变化输入框的值
		$("#"+pub_arr['tableId']+" tbody").on("keyup change","input,textarea,select",function(){
			//var trid = $(this).attr("id").substr(0,($(this).attr("id").length-$(this).attr("name").length)-1);
			var editOldName = "";
			var editOldValue = "";
			var editOldHtml = "";
			//先获取当前输入域的值
			if (this.tagName == "INPUT") {
				editOldName = $(this).attr("name");
				editOldValue = $(this).val();
				editOldHtml = $(this).val();
			}
			if (this.tagName == "TEXTAREA") {
				editOldName = $(this).attr("name");
				editOldValue = $(this).val();
				editOldHtml = $(this).val();
			}
			if (this.tagName == "SELECT") {
				editOldName = $(this).attr("name");
				editOldValue = $(this).val();
				$(this).find("option").each(function(){
					if (editOldValue == $(this).attr("value") && editOldValue !="") editOldHtml = $(this).html();
				});
			}
			if ($(this).parent().prev().attr("class") == "editStatus") {
				$(this).parent().prev().html(editOldHtml);
				
			}
		});
		
		return true;
	}
	//修改全局变量editBeforeData
	function editBeforeDataMod(num,trid,name,value){
		if (typeof(editBeforeData[num])!="undefined"&&typeof(editBeforeData[num][trid])!="undefined"&&typeof(editBeforeData[num][trid][name])!="undefined") editBeforeData[num][trid][name] = value;
		return true;
	}
	//获取第一个表格框架的序号
	function getOneTagNum(){
		for (var key in tableModelArr){
			if (key != "num"){
				return key;
			}
		}
	}
	var addnum = 0;
	//新增
	function addModel(para) {
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var unfill = para["unfill"] = typeof(para["unfill"])=="undefined"?"":(","+para["unfill"]+",");
		var type = para["type"] = typeof(para["type"])=="undefined"?"":para["type"];
		if (type==""){
			var key = "0";
			var obj_copy = $("#"+tableModelArr[num]['tableId']+" thead").find("tr");
			var addid = "add_"+addnum++;
		}else if (type=="copy"){
			var key = para["key"];
			var obj_copy = $("#"+tableModelArr[num]['tableId']+" tbody").find("tr");
			var addid = "add_"+addnum++;
		}else if (type=="search"){
			var key = "0";
			var obj_copy = $("#"+tableModelArr[num]['tableId']+" thead").find("tr");
			var addid = para["searchDate"]["id"];
		}else {
			
		}
		
		//新增行
		var obj_id = obj_copy.eq(key).find("*[name='id']").eq('0').attr("value");
		var replaceStrReg=new RegExp(obj_id,"g");
		var addstr = (obj_copy[key].outerHTML).replace(replaceStrReg,addid);

		var obj_body = $("#"+tableModelArr[num]['tableId']+" tbody");
		obj_body.prepend(addstr);
		obj_body.find("tr").eq('0').find("*[data-status='no']").remove();
		obj_body.find("tr").eq('0').find(".editStatus+.datagrid-cell").show();
		obj_body.find("tr").eq('0').find(".editStatus").remove();

		if (type==""){
			//新增填充值
			obj_body.find("tr").eq('0').find("*[name]").each(function(){
				if ($(this).attr("name") != "id" && unfill.indexOf(","+$(this).attr("name")+",") == "-1") {
					$("#"+tableModelArr[num]['tableId']+" #"+addid+"_"+$(this).attr("name")).val($("#"+tableModelArr[num]['tableId']+" #"+obj_id+"_"+$(this).attr("name")).val());
				}
			});
			obj_body.find("tr").eq('0').find("*[type='checkbox']")['0'].checked=typeof(para["checked"])=="undefined"?true:para["checked"];
		}else if (type=="copy"){
			//复制填充值
			obj_body.find("tr").eq('0').find("*[name]").each(function(){
				if ($(this).attr("name") != "id" && unfill.indexOf(","+$(this).attr("name")+",") == "-1") {
					$("#"+tableModelArr[num]['tableId']+" #"+addid+"_"+$(this).attr("name")).val($("#"+tableModelArr[num]['tableId']+" #"+obj_id+"_"+$(this).attr("name")).val());
				}
			});
			obj_body.find("tr").eq('0').find("*[type='checkbox']")['0'].checked=typeof(para["checked"])=="undefined"?true:para["checked"];
		}else if (type=="search"){//var aa=new Date();console.log("add++++++++++"+aa.getSeconds()+":"+aa.getMilliseconds());
			//查询填充值
			for (var key in para["searchDate"]){
				if (unfill.indexOf(","+key+",") == "-1"){
					$("#"+tableModelArr[num]['tableId']+" #"+addid+"_"+key).val(para["searchDate"][key]);
					$("#"+tableModelArr[num]['tableId']+" #"+addid+"_"+key).change();
					typeof(para["searchDate"][key+"Text"])!="undefined"?$("#"+tableModelArr[num]['tableId']+" #"+para["searchDate"]["id"]+"_"+key+"Text").html(para["searchDate"][key+"Text"]?para["searchDate"][key+"Text"]:"添加"):"";
				}
			}
			//安全起见，重复一次，有可能级联的数据先后颠倒，此次不用触发变化事件
			for (var key in para["searchDate"]){
				if (unfill.indexOf(","+key+",") == "-1"){
					$("#"+tableModelArr[num]['tableId']+" #"+addid+"_"+key).val(para["searchDate"][key]);
					//$("#"+tableModelArr[num]['tableId']+" #"+addid+"_"+key).change();
					typeof(para["searchDate"][key+"Text"])!="undefined"?$("#"+tableModelArr[num]['tableId']+" #"+para["searchDate"]["id"]+"_"+key+"Text").html(para["searchDate"][key+"Text"]?para["searchDate"][key+"Text"]:"添加"):"";
				}
			}
			obj_body.find("tr").eq('0').find("*[type='checkbox']")['0'].checked=typeof(para["checked"])=="undefined"?false:para["checked"];
		}else {
			
		}
		//var aa=new Date();console.log("add----------"+aa.getSeconds()+":"+aa.getMilliseconds());
		obj_body.find("tr").eq('0').find("*[type='checkbox']").eq(0).trigger("change");
		//判断哪些不可编辑
		editDisabled(addid);
		return true;
	}

	//灰化不可编辑字段
	function editDisabled(id){
		var editDisabledArr = (typeof($("#"+id+"_editDisabled").val())=="undefined")?"":$("#"+id+"_editDisabled").val().split(",");
		for (var i=0;i<editDisabledArr.length;i++){
			if (editDisabledArr[i]!=""){
				if ($("#"+id+"_"+editDisabledArr[i])[0].tagName == "INPUT")
					$("#"+id+"_"+editDisabledArr[i]).attr("readonly","readonly");
				if ($("#"+id+"_"+editDisabledArr[i])[0].tagName == "SELECT")
					$("#"+id+"_"+editDisabledArr[i]).attr("disabled","disabled");
				if ($("#"+id+"_"+editDisabledArr[i]).attr("onclick") != "")
					$("#"+id+"_"+editDisabledArr[i]).attr("disabled","disabled");
				
			}
		}
		return true;
	}
	
	//复制
	function copyModel(para) {
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var checked = para["checked"] = typeof(para["checked"])=="undefined"?true:para["checked"];
		var unfill = para["unfill"] = typeof(para["unfill"])=="undefined"?"":(","+para["unfill"]+",");
		var idarr = getcheckboxvalue("id","","#"+tableModelArr[num]['tableId']+" tbody").split("||");
		if (idarr.length > 100) {
			tip("已选择"+idarr.length+"项，最多选择100项，请重新选择");
			return false;
		}
		for (var i=0;i<idarr.length;i++){
			if (idarr[i]!="")
			$("#"+tableModelArr[num]['tableId']+" tbody tr").each(function(e){
				if ($(this).find("*[name='id']").val() == idarr[i]){
					addModel({
						tagNum:num,
						type:'copy',
						key:e,
						checked:checked,
						unfill:unfill
					});
				}
			});
		}
		return true;
	}
	
	//查询
	function searchModel(para){
		if (typeof(para["url"])=="undefined" || para["url"]==""){tip("未传入url");return false;} else var url = para["url"];
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var checked = para["checked"] = typeof(para["checked"])=="undefined"?false:para["checked"];
		var unfill = para["unfill"] = typeof(para["unfill"])=="undefined"?"":(","+para["unfill"]+",");
		var datastr = para["datastr"] = typeof(para["datastr"])=="undefined"?"":para["datastr"];
		var catalog = para["catalog"] = typeof(para["catalog"])=="undefined"?"obj":para["catalog"];
		var callback = para["callback"] = typeof(para["callback"])=="undefined"?"":para["callback"];
		var failback = para["failback"] = typeof(para["failback"])=="undefined"?"":para["failback"];
		var tipSuccess = para["tipSuccess"] = typeof(para["tipSuccess"])=="undefined"?"undefined":para["tipSuccess"];
		var tipFail = para["tipFail"] = typeof(para["tipFail"])=="undefined"?"undefined":para["tipFail"];
		
		//tip('begin');
		var bk_json=Aj_post(datastr,url);
		var bk_arr = JSON.parse(bk_json);
		if (bk_arr["success"]){
			if(tipSuccess=="undefined") /*tip("查询成功"); */{} else if(tipSuccess!="") tip(tipSuccess);

			var catalog_arr = bk_arr;
			for (var i=0;i<catalog.split(",").length;i++){
				if (catalog.split(",")[i]!="")
				catalog_arr = catalog_arr[catalog.split(",")[i]];
			}
			//填充行数据
			fillDataModel({
				tagNum:num,
				dataList:catalog_arr,
				checked:checked,
				unfill:unfill
			})

	        if (callback!="")
	        	eval(callback.replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
			return true;
			
		}else {
			if(tipFail=="undefined") tip("查询失败:"+bk_arr["msg"]); else if(tipFail!="") tip(tipFail);
	        if (failback!="")
	        	eval(failback.replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
			return false;
		}
	}
	var fillDataList = [];
	//填充行数据
	function fillDataModel(para){
		$("body").append('<div class="pb_cover"><div class="pb_cover_cont">数据加载中……</div></div>');
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var checked = para["checked"] = typeof(para["checked"])=="undefined"?true:para["checked"];
		var editType = para["editType"] = typeof(para["editType"])=="undefined"?false:para["editType"];
		var unfill = para["unfill"] = typeof(para["unfill"])=="undefined"?"":(","+para["unfill"]+",");
		var dataList = para["dataList"] = (typeof(para["dataList"])=="undefined"||para["dataList"]==null)?"":para["dataList"];
		var start = para["start"] = (typeof(para["start"])=="undefined"||para["start"]==null)?0:para["start"];
		var size = para["size"] = (typeof(para["size"])=="undefined"||para["size"]==null)?10:para["size"];
		
		//清空加载中点击
		$("#"+tableModelArr[num]['tableId']+"_page").remove();
		if (start == 0){
			$("#"+tableModelArr[num]['formId']).html("");
			$("#"+tableModelArr[num]['tableId']+" tbody").html("");
			$("#"+tableModelArr[num]['tableId']+" *[name='checkall']")[0].checked=false;
			editBeforeData[num] = [];//填充数据时，清空全局变量
			fillDataList[num] = dataList;
		}else {
			dataList = fillDataList[num];
		}
		if (dataList.length != 0){
			
			var count = 0;
			for (var i=dataList.length-1; i>=0; i--){
				if (count < start){
					count++;
					continue;
				}else if (count >= start && count < (start-(-size))){
					addModel({
						tagNum:num,
						type:'search',
						searchDate:dataList[i],
						checked:checked,
						unfill:unfill
					});
					
					if (!editType){
						editModelExec({
							tagNum:num,
							id:dataList[i]["id"],
							type:false
						});
						
					}
					count++;
					continue;
				}else if (count >= (start-(-size))){
					break;
				}
			}
			//动态添加加载中点击
			if (count < dataList.length) {
				var paraNew1 = para;
				paraNew1["dataList"] = "";
				paraNew1["start"] = count;
				paraNew1["size"] = 10;
//				var paraNew2 = para;
//				paraNew2["dataList"] = "";
//				paraNew2["start"] = count;
//				paraNew2["size"] = 20;
//				var paraNew3 = para;
//				paraNew3["dataList"] = "";
//				paraNew3["start"] = count;
//				paraNew3["size"] = 50;
				$("#"+tableModelArr[num]['tableId']+" ").closest(".cssModel").after("<div id='"+tableModelArr[num]['tableId']+"_page' class='cssModelPage' ><span onclick=\"fillDataModel("+JSON.stringify(paraNew1).replace(/\"/g,"\'")+")\" >加载下一组数据</span></div>");
			}
		}
		$(".pb_cover").remove();
        return true;
	}

	//修改与取消修改
	function editModel(para){
		if (typeof(para["type"])=="undefined") {tip("请传入type值");return false;} else var type=para["type"];
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var idarr = getcheckboxvalue("id","","#"+tableModelArr[num]['tableId']+" tbody").split("||");
		for (var i=0;i<idarr.length;i++){
			if (idarr[i]!="")
			if (idarr[i].split("_")[0] != "add"){
				editModelExec({
					tagNum:num,
					id:idarr[i],
					type:type
				});
			}
		}
		return true;
	}
	var editBeforeData = [];//保存执行修改之前的数据
	function editModelExec(para){
		if (typeof(para["id"])=="undefined") {tip("请传入id值");return false;} else var trid=para["id"];
		if (typeof(para["type"])=="undefined") {tip("请传入type值");return false;} else var type=para["type"];
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		if(typeof(editBeforeData[num]) == "undefined") editBeforeData[num] = [];
		$("#"+tableModelArr[num]['tableId']+" tbody tr").each(function(){
			if ($(this).find("*[name='id']").eq(0).val() == trid){
				if(typeof(editBeforeData[num][trid]) == "undefined") editBeforeData[num][trid] = [];

				if (type){
					$(this).find("td").each(function(){
						//设置修改之前的数据
						var editOldName = "";
						var editOldValue = "";
						//先获取当前输入域的值
						$(this).find("input,textarea,select").each(function(){
								editOldName = $(this).attr("name");
								editOldValue = $(this).val();
						});
						if (typeof(editBeforeData[num][trid][editOldName])=="undefined") editBeforeData[num][trid][editOldName] = editOldValue;
						
						
						//if ($(this).find("*[readonly]").length == 0 && $(this).find("*[disabled]").length == 0){
							$(this).find(".editStatus+.datagrid-cell").eq('0').show();
							$(this).find(".editStatus").eq('0').remove();
						//}
					});
				}else {

					//填充修改之前的数据
					for(var name in editBeforeData[num][trid]) {
						var newValue = editBeforeData[num][trid][name];
						$("#"+tableModelArr[num]['tableId']+" #"+trid+"_"+name).val(newValue);
						$("#"+tableModelArr[num]['tableId']+" #"+trid+"_"+name).change();
					}
					//安全起见，重复一次，有可能级联的数据先后颠倒，此次不用触发变化事件
					for(var name in editBeforeData[num][trid]) {
						var newValue = editBeforeData[num][trid][name];
						$("#"+tableModelArr[num]['tableId']+" #"+trid+"_"+name).val(newValue);
						//$("#"+trid+"_"+name).change();
					}
					
					if ($(this).find(".editStatus").length){
						//$(this).find("td").each(function(){
						//	$(this).find(".editStatus+.datagrid-cell").eq('0').hide();
						//	$(this).find(".editStatus").eq('0').show();
						//});
					}else {
						$(this).find("td").each(function(){
							var editOldName = "";
							var editOldValue = "";
							var editOldHtml = "";
							//先获取当前输入域的值
							$(this).find("input").each(function(){
								editOldName = $(this).attr("name");
								editOldValue = $(this).val();
								editOldHtml = $(this).val();
							});
							$(this).find("textarea").each(function(){
								editOldName = $(this).attr("name");
								editOldValue = $(this).val();
								editOldHtml = $(this).val();
							});
							$(this).find("select").each(function(){
								editOldName = $(this).attr("name");
								editOldValue = $(this).val();
								$(this).find("option").each(function(){
									if (editOldValue == $(this).attr("value") && editOldValue !="") editOldHtml = $(this).html();
								});
							});
							//追加td
							if (editOldValue !== trid){
								//if (typeof(editBeforeData[num][trid][editOldName])=="undefined") editBeforeData[num][trid][editOldName] = editOldValue;
								$(this).prepend('<div class="editStatus">'+editOldHtml+'</div>');
								$(this).find(".editStatus+.datagrid-cell").eq('0').hide();
								$(this).find(".editStatus").eq('0').show();
							}
						});
					}
				}
			}
		});
		return true;
	}
	
	//保存
	function saveModel(para){
		if (typeof(para["url"])=="undefined" || para["url"]==""){tip("未传入url");return false;} else var url = para["url"];
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var namestr = para["namestr"] = typeof(para["namestr"])=="undefined"?"":para["namestr"];
		var datastr = para["datastr"] = typeof(para["datastr"])=="undefined"?"":para["datastr"];
		var callback = para["callback"] = typeof(para["callback"])=="undefined"?"":para["callback"];
		var failback = para["failback"] = typeof(para["failback"])=="undefined"?"":para["failback"];
		var tipSuccess = para["tipSuccess"] = typeof(para["tipSuccess"])=="undefined"?"undefined":para["tipSuccess"];
		var tipFail = para["tipFail"] = typeof(para["tipFail"])=="undefined"?"undefined":para["tipFail"];
		
		var formstr = "";//拼接form字符串
		var idarr = [];//获取所选行的id数组
		var allidarr = getcheckboxvalue("id","","#"+tableModelArr[num]['tableId']+" tbody").split("||");
		for (var i=0;i<allidarr.length;i++){
			if (allidarr[i]!="")
			$("#"+tableModelArr[num]['tableId']+" tbody tr").each(function(){
				if ($(this).find("*[name='id']").val() == allidarr[i]  && ($(this).find(".editStatus").length==0 || $(this).find(".editStatus")[0].style.display == "none")){
					idarr.push(allidarr[i]);
				}
			});
		}
		if (idarr.length>0){
			for (var i=0;i<idarr.length;i++){
				var obj = $("#"+tableModelArr[num]['tableId']+" tbody");
				obj.find("tr").each(function(e) {
					var is_check = '';//判断是否选中，is_check代表第几行从0开始
					obj.find("tr").eq(e).find("input").each(function(q) {
						if (this.type=="checkbox" && this.value==idarr[i]){
							is_check = e;
						}
					});
					if (is_check !== ''){
						obj.find("tr").eq(is_check).find("select").each(function(q) {
							formstr += '<input type="text" name="'+namestr+'['+i+'].'+this.name+'"  value="'+(this.value==idarr[i]&&idarr[i].indexOf("_")!="-1"?"":this.value)+'" '+(this.getAttribute('datatype')?'datatype="'+this.getAttribute('datatype')+'"':'')+' '+(this.getAttribute('errormsg')?'errormsg="'+this.getAttribute('errormsg')+'"':'')+' '+(this.getAttribute('nullmsg')?'nullmsg="'+this.getAttribute('nullmsg')+'"':'')+' >';
						});
						obj.find("tr").eq(is_check).find("input").each(function(q) {
							formstr += '<input type="text" name="'+namestr+'['+i+'].'+this.name+'"  value="'+(this.value==idarr[i]&&idarr[i].indexOf("_")!="-1"?"":this.value)+'" '+(this.getAttribute('datatype')?'datatype="'+this.getAttribute('datatype')+'"':'')+' '+(this.getAttribute('errormsg')?'errormsg="'+this.getAttribute('errormsg')+'"':'')+' '+(this.getAttribute('nullmsg')?'nullmsg="'+this.getAttribute('nullmsg')+'"':'')+' >';
						});
					}
				});
			}
		}
		if (formstr == ""){
			tip("请选择新增或已修改项");return false;
		}
		//填充隐藏form
		$("#"+tableModelArr[num]['formId']+"").html(formstr);
		//此处是将请求的参数变量datastr放置于动态的formId里，然后提交的时候根据id获取，原因是datastr在validform里面不会每次变化而直接等于第一次的值
		//$("#"+tableModelArr[num]['formId']+"").prepend("<input type='hidden' id='"+tableModelArr[num]["formId"]+"_datastr' value='"+(typeof(para["datastr"])=="undefined"?"":para["datastr"])+"' />");
		//$("#"+tableModelArr[num]['formId']+"").prepend("<input type='hidden' id='"+tableModelArr[num]["formId"]+"_callback' value='"+(typeof(para["callback"])=="undefined"?"":para["callback"]).replace(/\'/g,"\"")+"' />");
		//验证form
		pubValidForm({
			formId:tableModelArr[num]['formId'],
			callback:"saveModelValidform("+JSON.stringify(para)+")"
		})
		/*
		ValidformCallbackFun = "saveModelValidform("+JSON.stringify(para)+")";
		//$.Tipmsg.r=null;
		$("#"+tableModelArr[num]['formId']+"").Validform({
			tiptype:function(msg){
				tip(msg);
			},
			tipSweep:true,
			ajaxPost:true,
			callback:function(){
				if(ValidformCallbackFun!=""){
					eval(ValidformCallbackFun);
				}
				return false;
			},
			datatype:{
				"float":function(gets,obj,curform,regxp){
					var num = typeof(obj.attr("datatype-float-num"))=="undefined"?2:Number(obj.attr("datatype-float-num"));
					var negative = (typeof(obj.attr("datatype-float-negative"))=="undefined"||obj.attr("datatype-float-negative")!="y"?"":"(|-)");
					var patt1=new RegExp("^"+negative+"([1-9][\\d]{0,9}|0)(\\.[\\d]{1,"+num+"})?$");
					if (patt1.exec(gets) != null && patt1.exec(gets)[0] == gets)
						return true;
					else 
						return false;
				}
				
			}

		});
		*/
		//提交ajax保存
		$("#"+tableModelArr[num]['formId']+"").submit();
		return true;
	}
	function saveModelValidform(para){
		var bk_json=Aj_post(para["datastr"]+"&"+$("#"+tableModelArr[para["tagNum"]]['formId']+"").serialize(),para["url"]);
		var bk_arr = JSON.parse(bk_json);
		if (bk_arr["success"]){
			if(para["tipSuccess"]=="undefined") tip("保存成功"); else if(para["tipSuccess"]!="") tip(para["tipSuccess"]);
            if (para["callback"]!="")
            	eval(para["callback"].replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
			return true;
		}else {
			if(para["tipFail"]=="undefined") tip("保存失败:"+bk_arr["msg"]); else if(para["tipFail"]!="") tip(para["tipFail"]);
            if (para["failback"]!="")
            	eval(para["failback"].replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
			return false;
		}
		
	}

	//删除
	function delModel(para){
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var url = para["url"] = typeof(para["url"])=="undefined"?"":para["url"];
		var datastr = para["datastr"] = typeof(para["datastr"])=="undefined"?"":para["datastr"];
		var callback = para["callback"] = typeof(para["callback"])=="undefined"?"":para["callback"];
		var failback = para["failback"] = typeof(para["failback"])=="undefined"?"":para["failback"];
		var tipSuccess = para["tipSuccess"] = typeof(para["tipSuccess"])=="undefined"?"undefined":para["tipSuccess"];
		var tipFail = para["tipFail"] = typeof(para["tipFail"])=="undefined"?"undefined":para["tipFail"];

		if (getcheckboxvalue("id","","#"+tableModelArr[num]["tableId"]+" tbody") == ""){
			tip("请选择删除项");return false;
		}
		
		//先干掉选中的新增的行
		$("#"+tableModelArr[num]["tableId"]+" tbody tr").each(function(){
			if ($(this).find("*[name='id']")[0].checked && $(this).find("*[name='id']").eq(0).val().indexOf("_")!="-1") $(this).remove();
		})
		
		if (url!="" && getcheckboxvalue("id","","#"+tableModelArr[num]["tableId"]+" tbody")!=""){

			var bk_json=Aj_post(datastr+"&id="+getcheckboxvalue("id","","#"+tableModelArr[num]["tableId"]+" tbody").replace(/\|\|/g,","),url);
			var bk_arr = JSON.parse(bk_json);
			if (!bk_arr["success"]){
				if(tipFail=="undefined") tip("删除失败:"+bk_arr["msg"]); else if(tipFail!="") tip(tipFail);
				if (failback!="")
					eval(failback.replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
				return false;
			}
		}
		
		//干掉选中的所有行
		$("#"+tableModelArr[num]["tableId"]+" tbody tr").each(function(){
			if ($(this).find("*[name='id']")[0].checked) $(this).remove();
		})
		if(tipSuccess=="undefined") tip("删除成功"); else if(tipSuccess!="") tip(tipSuccess);
		if (callback!="")
			eval(callback.replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
		return true;
		
	}
	
	//集联查询
	function loadSelect(a,b,url,field,catalog,key,value){
		var field = typeof(field)=="undefined"?"parentId":field;
		var catalog = typeof(catalog)=="undefined"?"obj":catalog;
		var key = typeof(key)=="undefined"?"key":key;
		var value = typeof(value)=="undefined"?"value":value;
		
		var val = $("#"+a).val();
		if (val!=""){
			var bk_json=Aj_post(field+"="+val,url);
			var bk_arr = JSON.parse(bk_json);
		}else {
			var bk_arr = "";
		}
		var catalog_arr = bk_arr;
		for (var i=0;i<catalog.split(",").length;i++){
			if (catalog.split(",")[i]!="")
			catalog_arr = catalog_arr[catalog.split(",")[i]];
		}
        $("#"+b).empty();
        var option = "<option value=''>---请选择---</option>";
    	$("#"+b).append(option);
        if (catalog_arr) {
            for(var i=0; i<catalog_arr.length; i++){
            	var option = "<option value='"+catalog_arr[i][key]+"'>"+catalog_arr[i][value]+"</option>";
            	$("#"+b).append(option);
            }
        }
        $("#"+b).change();
        return true;
	}
	
	function reDialog(title, addurl, width, height, callback) {
	    //typeof(param)=="undefined"||!param?"":addurl += param;
	    /* createwindow(title, addurl, width, height); */
    	var title = '';
		createwindowExt(title, addurl, width, height, {
	    	 okVal: '确定',
	         ok : function() {
	             iframe = this.iframe.contentWindow;
	             //iframe.validateForm();
	             //alert(iframe.$("#priceDictId").val());
	             //console.log(iframe.$("#priceDictId").val());
	             //console.log(this);
	             //this.close();
	             if (typeof(callback)!="undefined") eval(callback);
	             //this.close();
	 			 //this.iframe.api.close();
	             return true;
	         },
			cancelVal : '关闭',
        	cancel : function() {
	             //iframe = this.iframe.contentWindow;
	             return true;
	         }
	    });
	}
	
	//弹出选择行数据
	//selectSingleData('选择物料','cgReportController.do?list&id=matnr_code',650,400,0,'matnr,maktx,xl,guigelei','matnr_codeList');
	function selectSingleData(para){
		if (typeof(para["datagrid"])=="undefined" || para["datagrid"]==""){tip("未传入datagrid");return false;} else var datagrid = para["datagrid"];
		if (typeof(para["addurl"])=="undefined" || para["addurl"]==""){tip("未传入addurl");return false;} else var addurl = para["addurl"];
		var num = para["tagNum"] = (typeof(para["tagNum"])=="undefined"||para["tagNum"]=="")?getOneTagNum():para["tagNum"];
		var isMulti = para["isMulti"] = (typeof(para["isMulti"])=="undefined" || !para["isMulti"])?"n":para["isMulti"];
		var dataSearch = para["dataSearch"] = typeof(para["dataSearch"])=="undefined"?"":para["dataSearch"].split(',');
		var dataBack = para["dataBack"] = typeof(para["dataBack"])=="undefined"?dataSearch:para["dataBack"].split(',');
		var width = para["width"] = typeof(para["width"])=="undefined"?"":para["width"];
		var height = para["height"] = typeof(para["height"])=="undefined"?"":para["height"];
		var title = para["title"] = typeof(para["title"])=="undefined"?"":para["title"];
		var rowId = para["rowId"] = typeof(para["rowId"])=="undefined"?"":para["rowId"];

		createwindowExt(title, addurl, width, height, {
	    	okVal: '确定',
	         ok : function() {
	             iframe = this.iframe.contentWindow;
	             var rowsData = iframe.$('#'+datagrid).datagrid('getSelections');
	             if (!rowsData || rowsData.length == 0) {
	            	 iframe.tip('请选择记录');
	                 return false;
	             }
	             if (isMulti == 'n' && rowsData.length > 1) {
	            	 iframe.tip('只能选择一条记录');
	                 return false;
	             }
	             //填充所需字段值
	             for(var i=0; i<rowsData.length; i++){
	            	 if (rowId.length!=0){
	            		 var trid = rowId.split(",")[i];
	            	 }else {
	            		 addModel({tagNum:num});
	            		 var trid = $("#"+tableModelArr[num]["tableId"]+" tbody tr td *[name='id']").eq('0').val();
	            	 }
	            	 if (trid!="") {
			             for(var j=0; j<dataSearch.length; j++){
			            	 var field_dataSearch = dataSearch[j];
			            	 var field_dataBack = dataBack[j];
		            		 var field_val = rowsData[i][field_dataSearch];
		            		 $("#"+tableModelArr[num]['tableId']+" #"+trid+"_"+field_dataBack).val(field_val);
		            		 $("#"+tableModelArr[num]['tableId']+" #"+trid+"_"+field_dataBack).attr("title",field_val);
		            		 $("#"+tableModelArr[num]['tableId']+" #"+trid+"_"+field_dataBack).trigger("change");
			             }
			             //返回方法单条
			             if (typeof(para["callbackList"])!="undefined")
			             	eval(para["callbackList"].replace(/DATA/g,JSON.stringify(rowsData[i])).replace(/PARA/g,JSON.stringify(para)));
	            	 }
	             }
	             //返回方法
	             if (typeof(para["callback"])!="undefined")
	             	eval(para["callback"].replace(/DATA/g,JSON.stringify(rowsData)).replace(/PARA/g,JSON.stringify(para)));
	             return true;
	         },
			cancelVal : '关闭',
        	cancel : function() {
	             return true;
	         }
	    });
	}

	//任意弹出框操作后返回callback带iframe
	function defineDialog(para){
		if (typeof(para["addurl"])=="undefined" || para["addurl"]==""){tip("未传入addurl");return false;} else var addurl = para["addurl"];
		if (typeof(para["callback"])=="undefined" || para["callback"]==""){tip("未传入callback");return false;} else var callback = para["callback"];
		var title = para["title"] = typeof(para["title"])=="undefined"?"":para["title"];
		var width = para["width"] = typeof(para["width"])=="undefined"?"":para["width"];
		var height = para["height"] = typeof(para["height"])=="undefined"?"":para["height"];
		createwindowExt(title, addurl, width, height, {
	    	okVal: '确定',
	         ok : function() {
	             iframe = this.iframe.contentWindow;
	             if (eval(callback) == false) return false;
	             else return true;
	         },
			cancelVal : '关闭',
        	cancel : function() {
	             return true;
	         }
	    });
	}

	
	
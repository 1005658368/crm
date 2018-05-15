
	//活动table 自适应窗口

	//$(window).resize(function(){resize()});
	$(function(){setInterval("resize()",2000)});

	function resize(){
		$(".layout-body").each(function(){
			$(this).find(".datagrid").eq("0").css("height","auto");
			$(this).find(".cssModel").css({"height":100})
			var max = $(this).height();
			var min = $(this).find(".datagrid").eq("0").height();
			$(this).find(".cssModel").css({"height":(max-min+98)});
			$(this).find(".cssModel form").each(function(k,v){
				var thead_h = $(this).siblings("table").height();
				if(thead_h!=0) $(this).css({"height":(max-min+98-thead_h-2),"margin-top":thead_h});
			})
		})
	}
	
	//宏定义当前页多个表格框架
	var tableModelArr=new Array();
	function tableModel(pub_arr){
		tableModelArr['num']=pub_arr['tagNum']=pub_arr['tagNum']?pub_arr['tagNum']:(tableModelArr['num']?tableModelArr['num']-(-1):'1');
		tableModelArr[tableModelArr['num']]=new Array();
		tableModelArr[tableModelArr['num']]['tableId']=pub_arr['tableId'];
		tableModelArr[tableModelArr['num']]['sign']=$("#"+pub_arr['tableId']+" tbody tr").length+1;
		tableModelArr[tableModelArr['num']]['singleSelect']=pub_arr['singleSelect']=pub_arr['singleSelect']||false;
		tableModelArr[tableModelArr['num']]['theme']=pub_arr['theme']=pub_arr['theme']||"multirow";
		tableModelArr[tableModelArr['num']]['themeEllipsis']=pub_arr['themeEllipsis']=pub_arr['themeEllipsis']||"";
		$("#"+pub_arr['tableId']+" thead").attr("id",""+pub_arr['tableId']+"_thead");
		$("#"+pub_arr['tableId']+" form").attr("id",""+pub_arr['tableId']+"_form");
		$("#"+pub_arr['tableId']+" tbody").attr("id",""+pub_arr['tableId']+"_tbody");
		
		//样式风格选择
		if (pub_arr['theme'] == "ellipsis"){
			$("#"+pub_arr['tableId']+"_thead style").append(" .cssModel #"+pub_arr['tableId']+"_tbody .editStatus{white-space: nowrap;text-overflow: ellipsis;overflow: hidden;}");
			onHover(pub_arr['tableId']+"_tbody",pub_arr['themeEllipsis']);
		}
		
		//绑定滚动事件
		$("#"+pub_arr['tableId']+"_form").scroll(function(e){
		    //console.log($(this).scrollLeft());
		    $(this).siblings("table").css({"left":"-"+$(this).scrollLeft()+"px"});
		})
		
		//是否单选
		if( pub_arr['singleSelect'] ){
			//添加tr行点击选中事件
			$("#"+pub_arr['tableId']+"_tbody").on("click","tr",function(){
				var checked = $(this).find("[fieldname='id']")[0].checked;
				var newChecked = ($(this).attr("data-edit") == "y")?true:!checked;
				$(this).find("[fieldname='id']")[0].checked = newChecked;
				changeBox($(this));
				if (!checked == newChecked) $(this).find("[fieldname='id']").eq(0).trigger("change");
			});
			//添加tr行选中样式事件
			$("#"+pub_arr['tableId']+"_tbody").on("change","[fieldname='id']",function(){
				changeBox($(this).closest("tr"));
				$(this).closest("tr").siblings().each(function(k,v){
					$(this).find("[fieldname='id']")[0].checked = false;
					changeBox($(this));
				})
			});
			//取消兄弟元素选中
			$("#"+pub_arr['tableId']+"_tbody").on("click","tr",function(){
				$(this).siblings().each(function(k,v){
					$(this).find("[fieldname='id']")[0].checked = false;
					changeBox($(this));
				})
			});
		}else {
			//添加tr行点击选中事件
			$("#"+pub_arr['tableId']+"_tbody").on("click","tr",function(){
				var checked = $(this).find("[fieldname='id']")[0].checked;
				var newChecked = ($(this).attr("data-edit") == "y")?true:!checked;
				$(this).find("[fieldname='id']")[0].checked = newChecked;
				changeBox($(this));
				if (!checked == newChecked) $(this).find("[fieldname='id']").eq(0).trigger("change");
			});
			//添加tr行选中样式事件
			$("#"+pub_arr['tableId']+"_tbody").on("change","[fieldname='id']",function(){
				changeBox($(this).closest("tr"));
			});
			//添加全选按钮
			$("#"+pub_arr['tableId']+"_thead tr:last .datagrid-cell-check").html('<input type="checkbox" value="" id="'+pub_arr['tableId']+'_checkall" name="checkall" onclick="this.checked?selall(\'id\',\'#'+pub_arr['tableId']+'_tbody\',true,\'2\'):selall(\'id\',\'#'+pub_arr['tableId']+'_tbody\',false,\'2\');changeBox(\'\',\''+pub_arr['tagNum']+'\');">');
		}
		
		//添加tr行点击选中禁止冒泡事件
		$("#"+pub_arr['tableId']+"_tbody").on("click","[fieldname='id']",function(e){
			e.stopPropagation();
		});
		//固定序号列的宽度
		//$("#"+pub_arr['tableId']+"_thead tr:eq(1) .datagrid-cell-check").closest("td").css("width","27px");
		//添加tr编辑状态属性
		//$("#"+pub_arr['tableId']+"_thead tr:first").attr("data-edit","y");
		
		return true;
	}
	function changeBox(_this,tagNum){
		if(_this||""){
			if (_this.find("[fieldname='id']")[0].checked == true)
				_this.addClass("activeModel");
			else 
				_this.removeClass("activeModel");
		}else{
			$("#"+tableModelArr[tagNum]['tableId']+"_tbody tr").each(function(){
				if ($(this).find("[fieldname='id']")[0].checked == true)
					$(this).addClass("activeModel");
				else 
					$(this).removeClass("activeModel");
			});
		}
	}
	
	//获取第一个表格框架的序号
	function getOneTagNum(){
		for (var key in tableModelArr){
			if (key != "num"){
				return key;
			}
		}
	}
	var addnum = 1;
	//新增
	function addModel(para) {
		//console.log("----"+dd()+"--新增--start----");
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var unfill = para["unfill"] = (","+(para["unfill"]||"")+",");
		var changestr = para["changestr"] = para["changestr"]||"";
		//var dataSearch = para["dataSearch"] = (typeof(para["dataSearch"])=="undefined"?"":para["dataSearch"]).split(',');
		//var dataBack = para["dataBack"] = typeof(para["dataBack"])=="undefined"?dataSearch:para["dataBack"].split(',');
		var type = para["type"] = para["type"]||"";
		var total = para["total"] = para["total"]||1;
		var idarr = para["idarr"] = para["idarr"]||"";
		var searchData = para["searchData"] = para["searchData"]||[];
		var checked = para["checked"] = typeof(para["checked"])=="undefined"?true:para["checked"];
		var editType = para["editType"] = typeof(para["editType"])=="undefined"?true:para["editType"];
		var editYes = para["editYes"] = para["editYes"]||"";
		var editNo = para["editNo"] = para["editNo"]||"";
		
		var newTr = $("#"+tableModelArr[num]['tableId']+"_thead tr:first");
		if (checked){
			newTr.find("[type='checkbox']:first").attr("checked","checked");
			newTr.addClass("activeModel");
		}else{
			newTr.find("[type='checkbox']:first").removeAttr("checked");
			newTr.removeClass("activeModel");
		}
		if (editType){
			newTr.attr("data-edit","y");
		}else{
			newTr.attr("data-edit","n");
		}
		
		var obj_addstr = "";
		var totalNum = 0;
		if (type == ""){
			$("#"+tableModelArr[num]['tableId']+"_thead tr:first [field='pbNum'] .editStatus").attr("value","index").html("index");
			$("#"+tableModelArr[num]['tableId']+"_thead tr:first [fieldname='pbNum']").attr("value","index");
			var obj_copy = $("#"+tableModelArr[num]['tableId']+"_thead tr")[0].outerHTML;
			var obj_id = "index";
			totalNum = total;
			for(var i=0;i<totalNum;i++){
				//替换id 替换行号
				var addid = "add_"+addnum++;
				var replaceStrReg=new RegExp(obj_id,"g");
				var addstr = (obj_copy).replace(replaceStrReg,addid);
				//替换下标
				obj_addstr = (addstr).replace(/\[0\]/g,"["+(tableModelArr[num]['sign']++)+"]") + obj_addstr;
			}
		}else if(type == "copy"){
			//var obj_copy = $("#"+tableModelArr[num]['tableId']+"_tbody tr");
			totalNum = idarr.length;
			for(var i=totalNum-1;i>=0;i--){
				//替换id
				var obj_id = idarr[i];
				var addid = "add_"+addnum++;
				var replaceStrReg=new RegExp(obj_id,"g");
				var addstr = ($("#"+tableModelArr[num]['tableId']+" #"+idarr[i]+"")[0].outerHTML).replace(replaceStrReg,addid);
				//替换下标
				obj_addstr = (addstr).replace(/\[[0-9]{1,}\]/g,"["+(tableModelArr[num]['sign']++)+"]") + obj_addstr;
			}
		}else if (type == "search"){
			$("#"+tableModelArr[num]['tableId']+"_thead tr:first [field='pbNum'] .editStatus").attr("value","#i#").html("#i#");
			$("#"+tableModelArr[num]['tableId']+"_thead tr:first [fieldname='pbNum']").attr("value","#i#");
			var obj_copy = $("#"+tableModelArr[num]['tableId']+"_thead tr")[0].outerHTML;
			var obj_id = 'index';
			totalNum = searchData.length;
			//totalNum>1000?totalNum=1000:"";
			for(var i=totalNum-1;i>=0;i--){
				//替换id
				var addid = searchData[i]["id"]||("add_"+addnum++);
				var replaceStrReg=new RegExp(obj_id,"g");
				var addstr = (obj_copy).replace(replaceStrReg,addid);
				//替换下标 替换行号
				obj_addstr = (addstr).replace(/\[0\]/g,"["+(tableModelArr[num]['sign']++)+"]").replace(/\#i\#/g,i+1) + obj_addstr;
			}
		}
		
		var obj_body = $("#"+tableModelArr[num]['tableId']+"_tbody");
		obj_body.prepend(obj_addstr);
		
		if (type == ""){
			 $("#"+tableModelArr[num]['tableId']+"_tbody tr").each(function(k,v){
		    	//var trid = this.id;
		    	if(k<totalNum){
					//判断哪些不可编辑
		    		editFunc({num:num,trid:this.id,editYes:editYes,editNo:editNo});
		    	}
		    	else{
		    		return true;
		    	}
		    })
		}
		else if (type == "copy"){
			var unfillArr = unfill.split(",");
		    $("#"+tableModelArr[num]['tableId']+"_tbody tr").each(function(k,v){
		    	var trid = this.id;
		    	if(k<totalNum){
		    		//取消复制值
		    		$.each(unfillArr,function(m,n){
		    			if (n!="") {
		    				var showText = $("#"+tableModelArr[num]['tableId']+"_thead tr:first [field='"+n+"'] .editStatus").attr("value");
		    				var defaultValue = $("#"+tableModelArr[num]['tableId']+"_thead tr:first [fieldname='"+n+"']").attr("value");
		    				$("#"+trid+"_"+n).attr("value",defaultValue);
		    				//$("#"+trid+"_"+n).val(oldValue);
		    				if ($("#"+trid+"_"+n)[0].tagName == "SELECT"){
		    					var select_obj = $("#"+trid+"_"+n+" option[value='"+defaultValue+"']");
		    					select_obj.attr("selected","selected");
		    					$("#"+trid+"_"+n+"_text").attr("value",showText).html(showText);
		    				}
		    				else{
			    				$("#"+trid+"_"+n+"_text").attr("value",showText).html(showText);
		    				}
		    			}
		    		})
		    		//复制替换序号
		    		$("#"+trid+"_pbNum").attr("value",trid);
		    		$("#"+trid+"_pbNum_text").attr("value",trid).html(trid);
		    		/*
		    		//复制默认选中
		    		$("#"+trid+"_id").attr("checked","checked");
		    		$(this).addClass("activeModel");
		    		//复制默认编辑
					$(this).attr("data-edit","y");
					*/
		    		//复制默认选中
					if (checked){
			    		$("#"+trid+"_id").attr("checked","checked");
			    		$(this).addClass("activeModel");
					}else{
						$("#"+trid+"_id").removeAttr("checked");
						$(this).removeClass("activeModel");
					}
		    		//复制默认编辑
					if (editType){
						$(this).attr("data-edit","y");
					}else{
						$(this).attr("data-edit","n");
					}
					
					//判断哪些不可编辑
					editFunc({num:num,trid:trid,editYes:editYes,editNo:editNo});
		    	}
		    	else{
		    		return true;
		    	}
		    })
		}
		else if (type == "search"){
			//填充值
			//var unfillArr = unfill.split(",");
			$("#"+tableModelArr[num]['tableId']+"_thead tr:first [fieldname]").each(function(i,j){
				var this_field = $(this).attr("fieldname");
				if(this_field != "id" && this_field != "pbNum" && unfill.indexOf(","+this_field+",") == "-1"){
				    $("#"+tableModelArr[num]['tableId']+"_tbody [fieldname='"+this_field+"']").each(function(k,v){
				    	if(k<totalNum){
			    			$(this).attr("value",searchData[k][this_field]);
				    		if(this.tagName == "SELECT"){
				    			var select_obj = $("#"+this.id+" option[value='"+searchData[k][this_field]+"']");
				    			select_obj.attr("selected","selected");
				    			$("#"+this.id+"_text").attr("value",select_obj.html()).html(select_obj.html());
				    		}else{
				    			$("#"+this.id+"_text").attr("value",searchData[k][this_field]).html(searchData[k][this_field]);
				    		}
				    	}
				    	else{
				    		return true;
				    	}
				    })
				}
			})
			$("#"+tableModelArr[num]['tableId']+"_tbody tr").each(function(k,v){
		    	//var trid = this.id;
		    	if(k<totalNum){
					//判断哪些不可编辑
		    		editFunc({num:num,trid:this.id,editYes:editYes,editNo:editNo});
		    	}
		    	else{
		    		return true;
		    	}
		    })
			
		}
		//自动触发一级联动,多级联动请页面上自行处理
		loadSelect({tagNum:num,changestr:changestr});
		return true;
		
	}

	//不可编辑字段
	function editFunc(para){
	    para = $.extend({
	    	num:"",
	    	trid:"",
	    	editYes:"",
	    	editNo:""
	    }, para);
		var fieldArr = (typeof($("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_editDisabled").val())=="undefined")?"":$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_editDisabled").val().split(",");
		for (var i=0;i<fieldArr.length;i++){
			if (fieldArr[i]!=""){
				$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+" [field='"+fieldArr[i]+"']").attr("editdisabled","");
				/*
				//可以不用写
				if ($("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_"+fieldArr[i])[0].tagName == "INPUT")
					$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_"+fieldArr[i]).attr("readonly","readonly");
				if ($("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_"+fieldArr[i])[0].tagName == "SELECT")
					$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_"+fieldArr[i]).attr("disabled","disabled");
				if ($("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_"+fieldArr[i]).attr("onclick") != "")
					$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+"_"+fieldArr[i]).attr("disabled","disabled");
				*/
			}
		}
		var editYesArr = para.editYes.split(",");
		for (var i=0;i<editYesArr.length;i++){
			if (editYesArr[i]!=""){
				$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+" [field='"+editYesArr[i]+"']").removeAttr("editdisabled");
			}
		}
		var editNoArr = para.editNo.split(",");
		for (var i=0;i<editNoArr.length;i++){
			if (editNoArr[i]!=""){
				$("#"+tableModelArr[para.num]['tableId']+"_tbody #"+para.trid+" [field='"+editNoArr[i]+"']").attr("editdisabled","");
			}
		}
		return true;
	}
	
	//复制
	function copyModel(para) {
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var checked = para["checked"] = typeof(para["checked"])=="undefined"?(tableModelArr[num]['singleSelect']?false:true):para["checked"];
		var editType = para["editType"] = typeof(para["editType"])=="undefined"?true:para["editType"];
		var unfill = para["unfill"] = para["unfill"]||"";
		var changestr = para["changestr"] = para["changestr"]||"";
		var editYes = para["editYes"] = para["editYes"]||"";
		var editNo = para["editNo"] = para["editNo"]||"";
		var idarr = getcheckboxvalue("id","","#"+tableModelArr[num]['tableId']+"_tbody",2).split("||");
		if (idarr.length == 0) {
			tip("请选择复制项");
			return false;
		}
		if (idarr.length > 10000) {
			tip("已选择"+idarr.length+"项，最多选择10000项，请重新选择");
			return false;
		}

		addModel({
			tagNum:num,
			type:'copy',
			checked:checked,
			editType:editType,
			unfill:unfill,
			changestr:changestr,
			editYes:editYes,
			editNo:editNo,
			idarr:idarr
		});
		return true;
		
	}

	//查询
	function searchModel(para){
		if (para["url"]||"") var url = para["url"]; else{tip("未传入url");return false;}
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var checked = para["checked"] = para["checked"]||false;
		var editType = para["editType"] = para["editType"]||false;
		var isClear = para["isClear"] = typeof(para["isClear"])=="undefined"?true:para["isClear"];
		var unfill = para["unfill"] = para["unfill"]||"";
		var changestr = para["changestr"] = para["changestr"]||"";
		var datastr = para["datastr"] = para["datastr"]||"";
		var catalog = para["catalog"] = para["catalog"]||"obj";
		//var dataSearch = para["dataSearch"] = para["dataSearch"]||"";
		//var dataBack = para["dataBack"] = typeof(para["dataBack"])=="undefined"?dataSearch:para["dataBack"];
		var editYes = para["editYes"] = para["editYes"]||"";
		var editNo = para["editNo"] = para["editNo"]||"";
		var callback = para["callback"] = para["callback"]||"";
		var failback = para["failback"] = para["failback"]||"";
		var tipSuccess = para["tipSuccess"] = typeof(para["tipSuccess"])=="undefined"?"undefined":para["tipSuccess"];
		var tipFail = para["tipFail"] = typeof(para["tipFail"])=="undefined"?"undefined":para["tipFail"];
		
		$.ajax({
			url:url,
			data:datastr,
			type:"post",
			success:function(bk_json){
				var bk_arr = typeof(bk_json)=="string"?JSON.parse(bk_json):bk_json;
				if (bk_arr["success"] || bk_arr["total"]>=0){
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
						unfill:unfill,
						changestr:changestr,
						//dataSearch:dataSearch,
						//dataBack:dataBack,
						checked:checked,
						editType:editType,
						isClear:isClear,
						editYes:editYes,
						editNo:editNo
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
		});
	}
	
	//填充行数据
	function fillDataModel(para){
		//console.log("----"+dd()+"--fill--start----");
		//$("body").append('<div class="pb_cover"><div class="pb_cover_cont">数据加载中……</div></div>');
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var checked = para["checked"] = para["checked"]||false;
		var editType = para["editType"] = para["editType"]||false;
		var isClear = para["isClear"] = typeof(para["isClear"])=="undefined"?true:para["isClear"];
		var unfill = para["unfill"] = para["unfill"]||"";
		var changestr = para["changestr"] = para["changestr"]||"";
		var dataList = para["dataList"] = para["dataList"]||"";
		var editYes = para["editYes"] = para["editYes"]||"";
		var editNo = para["editNo"] = para["editNo"]||"";
		//var dataSearch = para["dataSearch"] = para["dataSearch"]||"";
		//var dataBack = para["dataBack"] = para["dataBack"]||dataSearch;
		
		//清空加载中点击
		//$("#"+tableModelArr[num]['tableId']+"_form").html("");
		if(isClear) {
			$("#"+tableModelArr[num]['tableId']+"_tbody").html("");
		}
		//$("#"+tableModelArr[num]['tableId']+" [name='checkall']")[0].checked=false;

		addModel({
			tagNum:num,
			type:'search',
			searchData:dataList,
			checked:checked,
			editType:editType,
			unfill:unfill,
			changestr:changestr,
			//dataSearch:dataSearch,
			//dataBack:dataBack,
			editYes:editYes,
			editNo:editNo
		});
		return true;
		
	}

	//修改与取消修改
	function editModel(para){
		if (typeof(para["type"]) == "undefined") {tip("请传入type值");return false;} else var type=para["type"];
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var idarr = getcheckboxvalue("id","","#"+tableModelArr[num]['tableId']+"_tbody",2).split("||");
		for (var i=0;i<idarr.length;i++){
			if (idarr[i]!="")
				if (idarr[i].split("_")[0] != "add"){
					/*editModelExec({
						tagNum:num,
						id:idarr[i],
						type:type
					});*/
					//console.log("----"+dd()+"--start----");
					if (type){
						$("#"+tableModelArr[num]['tableId']+"_tbody #"+idarr[i]+"").attr("data-edit","y");
					}else {
						$("#"+tableModelArr[num]['tableId']+"_tbody #"+idarr[i]+"").attr("data-edit","n");
						//恢复修改之前的值
						$("#"+tableModelArr[num]['tableId']+"_tbody #"+idarr[i]+" [fieldname]").each(function(){
							$(this).val(this.getAttribute("value"));
							if ( $("#"+tableModelArr[num]['tableId']+"_tbody #"+this.id+"_text").length ) $("#"+tableModelArr[num]['tableId']+"_tbody #"+this.id+"_text").html($("#"+tableModelArr[num]['tableId']+"_tbody #"+this.id+"_text")[0].getAttribute("value"));
						});
					}
				}
		}
		return true;
	}
	//已放弃
	function editModelExec(para){
		if (para["id"]||"") var trid=para["id"]; else {tip("请传入id值");return false;}
		if (typeof(para["type"]) == "undefined") {tip("请传入type值");return false;} else var type=para["type"];
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		
		var _this = $("#"+tableModelArr[num]['tableId']+"_tbody #"+trid+"");

		if (type){
			_this.attr("data-edit","y");
		}else {
			_this.attr("data-edit","n");
		}
		return true;
	}
	
	//保存
	function saveModel(para){
		//console.log("----"+dd()+"--保存方法开始----");
		var url = para["url"] = para["url"]||para["url"];
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
//		var namestr = para["namestr"] = para["namestr"]||"";
		var datastr = para["datastr"] = para["datastr"]||"";
		var isToStr = para["isToStr"] = para["isToStr"]||false;
		var isSaveAll = para["isSaveAll"] = para["isSaveAll"]||false;
		var callback = para["callback"] = para["callback"]||"";
		var failback = para["failback"] = para["failback"]||"";
		var validback = para["validback"] = para["validback"]||"";
		var validfailback = para["validfailback"] = para["validfailback"]||"";
		var tipSuccess = para["tipSuccess"] = typeof(para["tipSuccess"])=="undefined"?"undefined":para["tipSuccess"];
		var tipFail = para["tipFail"] = typeof(para["tipFail"])=="undefined"?"undefined":para["tipFail"];

		pubValidForm({
			formId:tableModelArr[num]['tableId']+"_form",
			callback:"saveModelValidform("+JSON.stringify(para)+")",
			validfailback:validfailback
		})
		$("#"+tableModelArr[num]['tableId']+"_form").submit();
		return true;
	}
	function saveModelValidform(para){
		if (para["url"]==""){//仅仅使用验证功能，验证成功执行
            if (para["validback"]!="")
            	eval(para["validback"].replace(/PARA/g,JSON.stringify(para)));
			return true;
		}else {//保存功能
			//如果是全选保存，先全选，后取消多余的选择项
			if (para["isSaveAll"]){
				var checkedIds = ","+getcheckboxvalue("id","","#"+tableModelArr[para["tagNum"]]['tableId']+"_form",2).replace(/\|\|/g,",")+",";
				selall('id',"#"+tableModelArr[para["tagNum"]]['tableId']+"_tbody",true,'2');
			}
			//先去掉disabled，获取提交字符串，再填充disabled
			$("#"+tableModelArr[para["tagNum"]]['tableId']+"_form [disabled]").attr("data-disabled","disabled").removeAttr("disabled");
			var isToStr = para["isToStr"]?JSON.stringify($("#"+tableModelArr[para["tagNum"]]['tableId']+"_form").serializeObject()):$("#"+tableModelArr[para["tagNum"]]['tableId']+"_form").serialize();
			$("#"+tableModelArr[para["tagNum"]]['tableId']+"_form [data-disabled]").attr("disabled","disabled").removeAttr("data-disabled");
			if (para["isSaveAll"]){
				$("#"+tableModelArr[para["tagNum"]]['tableId']+"_tbody [fieldname='id']").each(function(k,v){
					if(checkedIds.indexOf(","+$(this).val()+",")=="-1"){
						this.checked = false;
					}
				})
			}
			$.ajax({
				url:para["url"],
				data:para["datastr"]+"&"+isToStr,
				//data:para["datastr"]+"&fds="+JSON.stringify($("#"+tableModelArr[para["tagNum"]]['tableId']+"_from").serializeObject()),
				type:"post",
				success:function(bk_json){
					var bk_arr = typeof(bk_json)=="string"?JSON.parse(bk_json):bk_json;
					//console.log("----"+dd()+"--post成功----");
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
			})
			return true;
		}
		
	}

	//删除
	function delModel(para){
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var url = para["url"] = para["url"]||"";
		var datastr = para["datastr"] = para["datastr"]||"";
		var callback = para["callback"] = para["callback"]||"";
		var failback = para["failback"] = para["failback"]||"";
		var tipSuccess = para["tipSuccess"] = para["tipSuccess"]||"undefined";
		var tipFail = para["tipFail"] = para["tipFail"]||"undefined";
		
		var checkStr = getcheckboxvalue("id","","#"+tableModelArr[num]["tableId"]+"_tbody",2).replace(/\|\|/g,",");
		if (checkStr == ""){
			tip("请选择删除项");return false;
		}
		
		//先干掉选中的新增的行
		$("#"+tableModelArr[num]["tableId"]+"_tbody tr").each(function(){
			if ($(this).find("[fieldname='id']")[0].checked && $(this).find("[fieldname='id']").eq(0).val().indexOf("_")!="-1") $(this).remove();
		})
		
		if (url!="" && checkStr!=""){
			
			$.ajax({
				url:url,
				data:datastr+"&id="+checkStr,
				type:"post",
				success:function(bk_json){
					var bk_arr = typeof(bk_json)=="string"?JSON.parse(bk_json):bk_json;
					if (!bk_arr["success"]){
						if(tipFail=="undefined") tip("删除失败:"+bk_arr["msg"]); else if(tipFail!="") tip(tipFail);
						if (failback!="")
							eval(failback.replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
						return false;
					}
					//干掉选中的所有行
					$("#"+tableModelArr[num]["tableId"]+"_tbody tr").each(function(){
						if ($(this).find("[fieldname='id']")[0].checked) $(this).remove();
					})
					if(tipSuccess=="undefined") tip("删除成功"); else if(tipSuccess!="") tip(tipSuccess);
					if (callback!="")
						eval(callback.replace(/DATA/g,bk_json).replace(/PARA/g,JSON.stringify(para)));
					return true;
					
				}
			})
		}
		
	}
	
	function loadSelect(p){
		//a,b,url,fieldname,catalog,key,value,val,callback
		var num = p["tagNum"]||"";
		var changestr = p["changestr"]||"";
		var a = p['a']||"";
		var b = p['b']||"";
		var url = p['url']||"";
		var fieldname = p['fieldname']||"parentId";
		var catalog = p['catalog']||"obj";
		var key = p['key']||"key";
		var value = p['value']||"value";
		var val = p['val']||($('#'+b).length>0?$('#'+b)[0].getAttribute("value"):"");
		var callback = p['callback']||"";
		if(p['url']||""){
			loadSelectExec({a:a,b:b,url:url,fieldname:fieldname,catalog:catalog,key:key,value:value,val:val,callback:callback});
		}else{
			$("#"+tableModelArr[num]['tableId']+"_tbody tr").each(function(k,v){
				var trid = this.id;
				$.each(changestr.split(","),function(k,v){
					if (v!="")
						eval($('#'+trid+'_'+v+'').attr('onchange'));
						//loadSelectExec({a:trid+'_costoneId',b:trid+'_costId',url:'costTypeController.do?selectCostType',val:$('#'+trid+'_costId')[0].getAttribute("value")});
				})
			})
		}
	}

	//集联查询
	function loadSelectExec(p){
		//a,b,url,fieldname,catalog,key,value,val,callback
		var a = p['a'];
		var b = p['b'];
		var url = p['url'];
		var fieldname = p['fieldname']||"parentId";
		var catalog = p['catalog']||"obj";
		var key = p['key']||"key";
		var value = p['value']||"value";
		var val = p['val']||"";
		var callback = p['callback']||"";
		
		var idval = $("#"+a).val();
		if (idval!=""){
			$.ajax({
				url:url,
				data:fieldname+"="+idval,
				type:"post",
				success:function(bk_json){
					var bk_arr = typeof(bk_json)=="string"?JSON.parse(bk_json):bk_json;

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
			            	if(val==catalog_arr[i][key]){
			            		$("#"+b+"_text").attr("value",catalog_arr[i][value]).html(catalog_arr[i][value]);
			            	}
			            	var option = "<option value='"+catalog_arr[i][key]+"' "+(val==catalog_arr[i][key]?"selected":"")+" >"+catalog_arr[i][value]+"</option>";
			            	$("#"+b).append(option);
			            }
			        }
		             if (typeof(callback)!="undefined") eval(callback);
			        //$("#"+b).change();
				}
			})
		}else {
			var bk_arr = "";
			
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
            if (typeof(callback)!="undefined") eval(callback);
	        //$("#"+b).change();
		}
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
		if (para["datagrid"]||"") var datagrid = para["datagrid"]; else{tip("未传入datagrid");return false;}
		if (para["addurl"]||"") var addurl = para["addurl"]; else{tip("未传入addurl");return false;}
		var num = para["tagNum"] = para["tagNum"]||getOneTagNum();
		var isMulti = para["isMulti"] = para["isMulti"]||"n";
		var dataSearch = para["dataSearch"] = (typeof(para["dataSearch"])=="undefined"?"":para["dataSearch"]).split(',');
		var dataBack = para["dataBack"] = typeof(para["dataBack"])=="undefined"?dataSearch:para["dataBack"].split(',');
		var width = para["width"] = para["width"]||"";
		var height = para["height"] = para["height"]||"";
		var title = para["title"] = para["title"]||"";
		var rowId = para["rowId"] = para["rowId"]||"";

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
	            		 var trid = $("#"+tableModelArr[num]["tableId"]+"_tbody tr td [fieldname='id']").eq('0').val();
	            	 }
	            	 if (trid!="") {
			             for(var j=0; j<dataSearch.length; j++){
			            	 var fieldname_dataSearch = dataSearch[j];
			            	 var fieldname_dataBack = dataBack[j];
		            		 var fieldname_val = rowsData[i][fieldname_dataSearch];
		            		 $("#"+trid+"_"+fieldname_dataBack).val(fieldname_val);
		            		 $("#"+trid+"_"+fieldname_dataBack).attr("title",fieldname_val);
		            		 $("#"+trid+"_"+fieldname_dataBack).trigger("change");
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
		var title = para["title"] = para["title"]||"";
		var width = para["width"] = para["width"]||"";
		var height = para["height"] = para["height"]||"";
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

	//鼠标悬浮事件
	function onHover(form,fields){
		var fieldstr = "";
		$.each(fields.split(","),function(k,v){
			if(v!="") fieldstr += ",[field='"+v+"'] .editStatus";
		})
		fieldstr = fieldstr||",[field] .editStatus";//默认全部字段都监听悬浮事件
		$("#"+form).on("mouseover mouseout",fieldstr.substr(1),function(event){
			var inputText = $(this).html();
			if(event.type == "mouseover"){
				//鼠标悬浮
				var bodyW = $("body").outerWidth();
				var bodyH = $("body").outerHeight();
				var clientY = event.clientY;
				var clientX = event.clientX;
				var styleStr = "";
				if(clientX+400 < bodyW)
					styleStr += "left:"+(clientX+10)+"px;";
				else
					styleStr += "right:"+(bodyW-clientX+10)+"px;";
				if(clientY+200 < bodyH)
					styleStr += "top:"+(clientY+10)+"px;";
				else
					styleStr += "bottom:"+(bodyH-clientY+10)+"px;";
				$("body").append("<div class='tempHover' style='display:table;'>"+inputText+"</div>");
				if(inputText && $(".tempHover:last").width() > $(this).width()){
					$("body").append("<div class='onHover' style='word-wrap:break-word;max-width:400px;position:absolute;"+styleStr+"padding:10px 20px;background:rgba(0,0,0,0.7);color:#ffffff;'>"+inputText+"</div>");
				}
			}else if(event.type == "mouseout"){
				//鼠标离开
				$(".tempHover:last").remove();
				$(".onHover:last").remove();
			}
		})
	}
	
	function dd(){
		var aa=new Date();
		return aa.getHours()+":"+aa.getMinutes()+":"+aa.getSeconds()+"."+aa.getMilliseconds();
	}
	
	
	function loadMarketSelect(p){
		 
		//a,b,url,fieldname,catalog,key,value,val,callback
		var num = p["tagNum"]||"";
		var changestr = p["changestr"]||"";
		var a = p['a']||"";
		var b = p['b']||"";
		var url = p['url']||"";
		var fieldname = p['fieldname']||"parentId";
		var catalog = p['catalog']||"obj";
		var key = p['key']||"key";
		var value = p['value']||"value";
		var val = p['val']||($('#'+b).length>0?$('#'+b)[0].getAttribute("value"):"");
		var callback = p['callback']||"";
		if(p['url']||""){
			loadSelectMarketExec({a:a,b:b,url:url,fieldname:fieldname,catalog:catalog,key:key,value:value,val:val,callback:callback});
		}else{
			$("#"+tableModelArr[num]['tableId']+"_tbody tr").each(function(k,v){
				var trid = this.id;
				$.each(changestr.split(","),function(k,v){
					if (v!="")
						eval($('#'+trid+'_'+v+'').attr('onchange'));
						//loadSelectExec({a:trid+'_costoneId',b:trid+'_costId',url:'costTypeController.do?selectCostType',val:$('#'+trid+'_costId')[0].getAttribute("value")});
				})
			})
		}
	}

	//集联查询
	function loadSelectMarketExec(p){
		//a,b,url,fieldname,catalog,key,value,val,callback
		var a = p['a'];
		var b = p['b'];
		var url = p['url'];
		var fieldname = p['fieldname']||"parentId";
		var catalog = p['catalog']||"obj";
		var key = p['key']||"key";
		var value = p['value']||"value";
		var val = p['val']||"";
		var callback = p['callback']||"";
		
		var idval = $("#"+a).val();
		if (idval!=""){
			$.ajax({
				url:url,
				data:fieldname+"="+idval,
				type:"post",
				success:function(bk_json){
					var bk_arr = typeof(bk_json)=="string"?JSON.parse(bk_json):bk_json;

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
		             if (typeof(callback)!="undefined") eval(callback);
			        //$("#"+b).change();
				}
			})
		}else {
			var bk_arr = "";
			
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
            if (typeof(callback)!="undefined") eval(callback);
	        //$("#"+b).change();
		}
        return true;
	}
	
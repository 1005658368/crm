<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/context/mytags.jsp"%>
<style type="text/css">
	.j-homePage .myTable span.tabs-icon{		
		position:absolute;
		border-radius:10px;
		width: 20px;
	    left: 50px;
	    display: inline-block;
	    top: 10px;
	    line-height: 16px;
	    display:none;
	    font-weight:500;
	}		
	.j-homePage .myTable span.mySelected{		
		color:#fff;
		background-color:red;	
		display:block;
	}	
	.j-homePage .myTable a.tabs-inner{
		position:relative;		
	}
	.tabs li a.tabs-inner{
		padding-right:25px;
	}
	tr.trSelected{
		background-color:#FBEC88;
	}
	.Iknow {
		color:red;
		cursor:pointer;
		font-size:24px !important;
	}
	.Iknow:hover {
		color:blue;
	}
	.l-btn-text.icon-search.l-btn-icon-left{margin-top:0!important}
	.j-homePage .content div .homeHeader{width:100%;text-align:left;background:#f3f5f6}
	.homeHeader p{display: inline-block; line-height: 22px;  margin-top: 10px;}
	.homeHeader{    padding-bottom: 8px;}
	
	
.tabs li a.tabs-inner {
  color: #fff;
  background-color: #1c8fbd;
  border-color: #1c8fbd;}
  
  .tabs li.tabs-selected a.tabs-inner {
  color: #000;	
  background-color: #f3f5f6;
  border-color: #f3f5f6;}
  
  
</style>
<div style="margin-top: 14px;height:100%">
		<div class="j-homePage">
		<div class="content">
			<h1>TPM</h1>
			<h2>TPM营销费用管理系统</h2>
			<p>Welcome to visit TPM</p>
			
			<div id="myTable" class="easyui-tabs myTable" style="width:700px;height:auto">
				<div title="待审批" style="padding:10px">
				 <div class="homeHeader">
					<p>
						<label for="" class="width78">流程编号：</label>
						<input id="procinstid" name="procintid" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">发起人：</label>
						<input id="realName" name="realName" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">所属分部：</label>
						<input id="depart" name="depart" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">主题：</label>
						<input id="topic" name="topic" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					
					</p>
					<p>
						<label for="" class="width78">发起时间：</label> <input type="text" id="query_date"
							class="Wdate" style="width: 140px; padding:0"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){setVisitDate(dp)}})">
					</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset();">重置</a>
		       		</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="goSearch();">查询</a>
		       		</p>
				</div> 
				<div style="width:100%;height:300px;overflow:auto !important" >
					<table class="" title="" style="height:auto;"
							data-options="singleSelect:true,method:'get'">
						<thead>
							<tr>
								<th data-options="field:'taskId',width:80" style="display:none ;">编号</th>
								<th data-options="field:'procinstid',width:80" >流程编号</th>
								<th data-options="field:'realName',width:100" style="width:50px;">发起人</th>
								<th data-options="field:'depart',width:100">所属分部</th>
								<th data-options="field:'processName',width:80">主题</th>
								<th data-options="field:'createTime',width:80">发起时间</th>
								<!-- <th data-options="field:'lastApproveTime',width:250">上节点审批通过时间</th> -->
							</tr>
						</thead>
					</table>
				</div>
				</div>
				<div title="沟通&nbsp;&nbsp;" style="padding:10px">
				<div class="homeHeader">
					<p>
						<label for="" class="width78">流程编号：</label>
						<input id="procinstid_communicate" name="procintid" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">发起人：</label>
						<input id="realName_communicate" name="realName" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">审批节点：</label>
						<input id="tastRealName_communicate" name="tastRealName" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">主题：</label>
						<input id="topic_communicate" name="topic" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					
					</p>
					<!-- <p>
						<label for="" class="width78">沟通时间：</label> <input type="text" id="beginDate_communicate"
							class="Wdate" style="width: 140px" padding:0;
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}',onpicked:function(dp){setVisitDate(dp)}})">
					</p> -->
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetCommunicate();">重置</a>
		       		</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="goSearchCommunicate();">查询</a>
		       		</p>
				</div>
				<div style="width:100%;height:300px;overflow:auto !important" >
					<table class="" title="" style="height:auto;"
							data-options="singleSelect:true,method:'get'">
						<thead>
							<tr>
								<th data-options="field:'itemid',width:80" style="display: none;">编号</th>
								<th data-options="field:'procinstid',width:80" >流程编号</th>
								<th data-options="field:'productid',width:100" style="width:50px;">发起人</th>
								<th data-options="field:'listprice',width:80">主题</th>
								<th data-options="field:'tastRealName',width:80">当前审批节点</th>
								<th data-options="field:'unitcost',width:80">沟通发起时间</th>
								<!-- <th data-options="field:'attr1',width:250">沟通发起时间</th> -->
							</tr>
						</thead>
					</table>
				</div>
				</div>
				<div title="传阅&nbsp;&nbsp;" style="padding:10px">
				<div class="homeHeader">
					<p>
						<label for="" class="width78">流程编号：</label>
						<input id="procinstid_circulation" name="procintid" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">发起人：</label>
						<input id="realName_circulation" name="realName" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
				
					<p>
						<label for="" class="width78">主题：</label>
						<input id="topic_circulation" name="topic_circulation" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					
					</p>
					<p>
						<label for="" class="width78">传阅时间：</label> <input type="text" id="query_date_circulation"
							class="Wdate" style="width: 140px; padding:0"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){setVisitDate(dp)}})">
					</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetCirculation();">重置</a>
		       		</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="goSearchCirculation();">查询</a>
		       		</p>
				</div> 
				<div style="width:100%;height:300px;overflow:auto !important" >
					<table class="" title="" style="height:auto;"
							data-options="singleSelect:true,method:'get'">
						<thead>
							<tr>
							    <th data-options="field:'itemid',width:80">操作</th>
								<th data-options="field:'itemid',width:80" style="display:none ;">编号</th>
								<th data-options="field:'procinstid',width:80" >流程编号</th>
								<th data-options="field:'productid',width:100" style="width:50px;">发起人</th>
								<th data-options="field:'listprice',width:80">主题</th>
								<th data-options="field:'unitcost',width:80">传阅发起时间</th>
								<!-- <th data-options="field:'attr1',width:250">传阅抄送时间</th> -->
							</tr>
						</thead>
					</table>
				</div>
				</div>
				<div title="抄送&nbsp;&nbsp;" style="padding:10px">
				<div class="homeHeader">
					<p>
						<label for="" class="width78">流程编号：</label>
						<input id="procinstid_copy" name="procintid" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">发起人：</label>
						<input id="realName_copy" name="realName" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
				
					<p>
						<label for="" class="width78">主题：</label>
						<input id="topic_copy" name="topic_copy" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					
					</p>
					<p>
						<label for="" class="width78">抄送时间：</label> <input type="text" id="query_date_copy"
							class="Wdate" style="width: 140px; padding:0"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){setVisitDate(dp)}})">
					</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetCopy();">重置</a>
		       		</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="goSearchCopy();">查询</a>
		       		</p>
				</div> 
				<div style="width:100%;height:300px;overflow:auto !important" >
					<table class="" title="" style="height:auto;"
							data-options="singleSelect:true,method:'get'">
						<thead>
							<tr>
							    <th data-options="field:'itemid',width:80">操作</th>
								<th data-options="field:'itemid',width:80" style="display:none ;">编号</th>
								 <th data-options="field:'procinstid',width:80" >流程编号</th>
								<th data-options="field:'productid',width:80" style="width:50px;">发起人</th>
								<th data-options="field:'listprice',width:80">主题</th>
								<th data-options="field:'unitcost',width:100">抄送发起时间</th>
								<!-- <th data-options="field:'attr1',width:250">传阅抄送时间</th> -->
							</tr>
						</thead>
					</table>
				</div>
				</div>
				
				<div title="驳回&nbsp;&nbsp;" style="padding:10px">
				<div class="homeHeader">
					<p>
						<label for="" class="width78">流程编号：</label>
						<input id="procinstid_back" name="procintid" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
					<p>
						<label for="" class="width78">发起人：</label>
						<input id="realName_back" name="realName" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					</p>
				
					<p>
						<label for="" class="width78">主题：</label>
						<input id="topic_back" name="topic_back" style="border:1px solid #54A5D5;width:140px;height:20px;"></input>
					
					</p>
				
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetBack();">重置</a>
		       		</p>
					<p style="float:right;margin-right:15px">
		           		<a id="" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="goSearchBack();">查询</a>
		       		</p>
				</div> 
				<div style="width:100%;height:300px;overflow:auto !important" >
					<table class="" title="" style="height:auto;"
							data-options="singleSelect:true,method:'get'">
						<thead>
							<tr>
							<th data-options="field:'itemid',width:80">操作</th>
							 <th data-options="field:'procinstid',width:80" >流程编号</th>
								<th data-options="field:'productid',width:100" style="width:50px;">发起人</th>
								<th data-options="field:'listprice',width:80">主题</th>
								<th data-options="field:'tastRealName',width:80">当前审批节点</th>
								<th data-options="field:'attr1',width:250">驳回原因</th>
							</tr>
						</thead>
					</table>
				</div>
				</div>
				
			</div>
		</div>	
	</div>
<script type="text/javascript">
	/*$(function(){
		var eleLi = $(".tabs").children("li");
		for(var i = 0; i < eleLi.length; ++i){
				eleLi.css("position:relative");
				eleLi.append("<span style='position:absolute;'>0</span>");
		}
	});*/
	$("body").on("click",".Iknow",function(e){
		var busId = $(this).attr("data-busId");
		var type = $(this).attr("data-type");
		e = e || window.event; 
	    if(e.stopPropagation) { //W3C阻止冒泡方法  
	        e.stopPropagation();  
	    } else {  
	        e.cancelBubble = true; //IE阻止冒泡方法  
	    }
	    //alert(123);
    	$.ajax({
            url : "workflowFysysController.do?doLook",
            type : 'post',
            data : {busId: busId,type:type},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
                console.log(d);
                if (d.success) {
            		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(2),"workflowFysysController.do?loadCirculationTask",2);
            		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(3),"workflowFysysController.do?loadSendCopyTask",3);
            		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(4),"workflowFysysController.do?backTask",4);
                }
            }
        });
	});
	
	function loadList(tab,url,index){
		$.ajax({
            url : url,
            type : 'post',
            data : {},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
                if (d.success) {	
                	//console.log(d.obj,tab);
                    loadTemple(d.obj,tab,index);
                }
            }
        });		
	}	
	
	$("#myTable").tabs({	    
	    onSelect:function(title,index){
			var tab = $("#myTable").tabs('getSelected'); 
			//tab.panel('refresh', 'workflowFysysController.do?loadWorkFlowTask');
	    	var target = this;
	    	//console.log(tab);
	    	var url = "";
	    	ran=123;
	    	if(index == 0){
	    		url = "workflowFysysController.do?loadWorkFlowTask&ran="+ran;	    		
	    	}else if(index == 1){
	    		url = "workflowFysysController.do?loadCommunicateTask&ran="+ran;	    		
	    	}else if(index == 2){
	    		url = "workflowFysysController.do?loadCirculationTask&ran="+ran;	    		
	    	}else if(index == 3){
	    		url = "workflowFysysController.do?loadSendCopyTask&ran="+ran;
	    	}else{
	    		url = "workflowFysysController.do?backTask&ran="+ran;
	    	}
	    	$.ajax({
	            url : url,
	            type : 'post',
	            data : {},
	            cache : false,
	            success : function(data) {
	                var d = $.parseJSON(data);
	                console.log(d);
	                if (d.success) {		//d.obj
	                    loadTemple(d.obj,tab,index);
	                }
	            }
	        });
	    }	    	    
	});
	loadList($("#myTable").children(".tabs-panels").children(".panel").eq(1),"workflowFysysController.do?loadCommunicateTask",1);
	loadList($("#myTable").children(".tabs-panels").children(".panel").eq(2),"workflowFysysController.do?loadCirculationTask",2);
	loadList($("#myTable").children(".tabs-panels").children(".panel").eq(3),"workflowFysysController.do?loadSendCopyTask",3);
	loadList($("#myTable").children(".tabs-panels").children(".panel").eq(4),"workflowFysysController.do?backTask",4);
	/* setInterval(function(){ */
		ran=Math.random();
		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(0),"workflowFysysController.do?loadWorkFlowTask&ran="+ran,0);
		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(1),"workflowFysysController.do?loadCommunicateTask&ran="+ran,1);
		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(2),"workflowFysysController.do?loadCirculationTask&ran="+ran,2);		
		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(3),"workflowFysysController.do?loadSendCopyTask&ran="+ran,3);
		loadList($("#myTable").children(".tabs-panels").children(".panel").eq(4),"workflowFysysController.do?backTask&ran="+ran,4);
	/* },600*1000); */
	
	function pageFresh(){
		location.reload();
	}
	$(".j-homePage").on("click",".detailPage",function(){
		detailPage(this);
	})
	function detailPage(_this){
		var target = _this;
		$(target).addClass("trSelected");
		$(target).siblings("tr").removeClass("trSelected");
		
		var id = $(target).children("td:first").attr("data-id");
		var taskId = $(target).children("td:first").text();
		var procDefKey = $(target).children("td:first").attr("data-procDefKey");
		var childrenUrl = $(target).children("td:first").attr("data-url");
		var type = $(target).children("td:first").attr("data-type");
		var communicateId = $(target).children("td:first").attr("data-communicateId");
		
		//url参数
		var extraParams = "";
		var extraPramStartNum = childrenUrl.indexOf("&");
		if( extraPramStartNum >= 0){
			extraParams = childrenUrl.substring(extraPramStartNum+1,childrenUrl.length);
		}
		var url = "workflowFysysController.do?workflowFysysToApproval&id="+id+"&taskId="+taskId+"&procDefKey="+procDefKey+"&approveType="+type+"&communicateId="+communicateId+"&childrenUrl="+childrenUrl+"&extraParams="+extraParams;
		/**
		
	    createwindowExt("工作流", url, 1200, 500,{
			cancelVal : '关闭',
       		cancel : function() {
	             iframe = this.iframe.contentWindow;
	             loadList($("#myTable").children(".tabs-panels").children(".panel").eq(0),"workflowFysysController.do?loadWorkFlowTask",0);
	             
	             return true;
	        },
	        okVal: '确定',
	        ok : function() {
	             iframe = this.iframe.contentWindow;	             
	             if (typeof(callback)!="undefined") eval(callback);
	             loadList($("#myTable").children(".tabs-panels").children(".panel").eq(0),"workflowFysysController.do?loadWorkFlowTask",0);
	             
	             return true;
	        }
	    });
	    */
	   var width = 1200;
	   var height = 500;
	    if (width == "100%") {
	        width = window.top.document.body.offsetWidth;
	    }
	    if (height == "100%") {
	        height = window.top.document.body.offsetHeight - 100;
	    }
	    var myOptions = {
	            content : 'url:' + url,
	            //lock : true,
	            width : width,
	            height : height,
	            title : "工作流",
	            opacity : 0.3,
	            cache : false,
	        /* 为true等价于function(){} */
	    };
	    $.extend(myOptions, null);
	    safeShowDialog(myOptions);
	    
	    
	}
	
	//刷新主页
	function refreshMainPage(){
    	var maintabs = $('#maintabs');
		maintabs.tabs('select',"首页");
        var currTab = $('#maintabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        $('#maintabs').tabs('update', {
            tab : currTab,
            options : {
                content : createFrame(url)
            }
        })
	}
	
	function loadTemple2(arr,aim,index){
		var dom = "<tbody>";
		var count = 0;
		for(var i = 0; i < arr.length; ++i){
			dom += '<tr class="detailPage">';
			if(index==4){
				dom += '<td data-id="' + arr[i].id + '" data-procDefKey="' + arr[i].procDefKey + '" data-type="' + arr[i].type + '" data-url="' + arr[i].url + '" data-communicateId="' + arr[i].communicateId + '"><span class="Iknow" data-busId="' + arr[i].busId + '">&times;</span></td>';
			}else{
				dom += '<td style="display: none;" data-id="' + arr[i].id + '" data-procDefKey="' + arr[i].procDefKey + '" data-type="' + arr[i].type + '" data-url="' + arr[i].url + '" data-communicateId="' + arr[i].communicateId + '">' + arr[i].taskId + '</td>';
			}
			dom += '<td>' + arr[i].procinstid + '</td>';
			dom += '<td>' + arr[i].realName + '</td>';
			if(arr[i].submitName){
				dom += '<td>' + arr[i].submitName + '</td>';
			}else{
				dom += '<td></td>';
			}
			
			if(index==4){
				dom += '<td>' + arr[i].lastApproveTime + '</td>';		
			}else{
				dom += '<td>' + arr[i].createTime + '</td>';		
			}
			
			if(index==4){
				dom += '<td>' + arr[i].rejectReason + '</td>';		
			}else{
				dom += '<td>' + arr[i].lastApproveTime + '</td>';		
			}
				
			dom += "</tr>";
			count++;
		}
		dom += "</tbody>";
		$(aim).find("table").children("tbody").remove();
		$(aim).find("table").append(dom);
		if(count > 0){
			$("#myTable").find(".tabs").children().eq(index).find(".tabs-icon").addClass("mySelected").text(count);
		}else {
			$("#myTable").find(".tabs").children().eq(index).find(".tabs-icon").removeClass("mySelected").text(0);
		}
	}
	
	function loadTemple(arr,aim,index){
		var dom = "<tbody>";
		var count = 0;
		for(var i = 0; i < arr.length; ++i){
			dom += '<tr class="detailPage">';
			//第一列，操作
			if(index!=0&&index!=1){
				dom += '<td data-id="' + arr[i].id + '" data-procDefKey="' + arr[i].procDefKey + '" data-type="' + arr[i].type + '" data-url="' + arr[i].url + '" data-communicateId="' + arr[i].communicateId + '"><span class="Iknow" data-busId="' + arr[i].busId + '" data-type="'+ index + '">&times;</span></td>';
			}
			//第二列
			if(index!=4){
				dom += '<td style="display: none;" data-id="' + arr[i].id + '" data-procDefKey="' + arr[i].procDefKey + '" data-type="' + arr[i].type + '" data-url="' + arr[i].url + '" data-communicateId="' + arr[i].communicateId + '">' + arr[i].taskId + '</td>';
			}
			dom += '<td>' + arr[i].procinstid + '</td>';
			dom += '<td>' + arr[i].realName + '</td>';
			if (index == 0) {
				dom += '<td>' + arr[i].depart + '</td>';
			}
			if(arr[i].submitName){
				dom += '<td>' + arr[i].submitName + '</td>';
			}else{
				dom += '<td></td>';
			}
			if(index==1||index==4){
				if(arr[i].tastRealName){
					dom += '<td>' + arr[i].tastRealName + '</td>';
				}else{
					dom += '<td></td>';
				}
			}else{
				dom += '<td>' + arr[i].createTime + '</td>';		
			}
			
			if(index==4){
				if(arr[i].rejectReason){
					dom += '<td>' + arr[i].rejectReason + '</td>';
				}else{
					dom += '<td></td>';
				}
			}
			/* else{
				if(index != 0){
					dom += '<td>' + arr[i].lastApproveTime + '</td>';		
				}
			} */
				
			dom += "</tr>";
			count++;
		}
		dom += "</tbody>";
		$(aim).find("table").children("tbody").remove();
		$(aim).find("table").append(dom);
		if(count > 0){
			$("#myTable").find(".tabs").children().eq(index).find(".tabs-icon").addClass("mySelected").text(count);
		}else {
			$("#myTable").find(".tabs").children().eq(index).find(".tabs-icon").removeClass("mySelected").text(0);
		}
	}	
	
	//刷新
	function refreach(){
		parent.$('#mm-tabupdate').click();
	}
	
	function goSearch(){
		var tab = $("#myTable").tabs('getSelected'); 
		var procinstid = $("#procinstid").val();
		var realName = $("#realName").val();
		var depart = $("#depart").val();
		var submitName = $("#topic").val();
		var queryDate = $("#query_date").val();
		var index=0;
	    var ran=Math.random();
	    url = "workflowFysysController.do?loadWorkFlowTaskNew&ran="+ran;
		$.ajax({
            url : url,
            type : 'post',
            data : {procinstid:procinstid,
            		realName:realName,
            		depart:depart,
            		submitName:submitName,
            		queryDate:queryDate},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
          
                console.log(d);
                if (d.success) {		//d.obj
                    loadTemple(d.obj,tab,index);
                }
            }
        });
	}
	
	function reset(){
		$("#procinstid").val("");
		$("#realName").val("");
		$("#depart").val("");
		$("#topic").val("");
		$("#query_date").val("");
		goSearch();
	}
	function goSearchCommunicate(){
		var tab = $("#myTable").tabs('getSelected'); 
		var procinstid = $("#procinstid_communicate").val();
		var realName = $("#realName_communicate").val();
		var tastRealName = $("#tastRealName_communicate").val();
	    var submitName = $("#topic_communicate").val();
		var index=1;
	    var ran=Math.random();
	    url = "workflowFysysController.do?loadCommunicateTaskNew&ran="+ran;
		$.ajax({
            url : url,
            type : 'post',
            data : {procinstid:procinstid,
            		realName:realName,
            		tastRealName:tastRealName,
            		submitName:submitName},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
          
                console.log(d);
                if (d.success) {		//d.obj
                    loadTemple(d.obj,tab,index);
                }
            }
        });
	}	
	function resetCommunicate(){
		$("#procinstid_communicate").val("");
		$("#realName_communicate").val("");
		$("#tastRealName_communicate").val("");
		$("topic_communicate").val("");
		goSearchCommunicate();
	}
	function goSearchCirculation(){
		var tab = $("#myTable").tabs('getSelected'); 
		var procinstid = $("#procinstid_circulation").val();
		var realName = $("#realName_circulation").val();
/* 		var tastRealName = $("#tastRealName_circulation").val();
 */		var submitName = $("#topic_circulation").val();
		var queryDate = $("#query_date_circulation").val();
		var index=2;
	    var ran=Math.random();
	    url = "workflowFysysController.do?loadCirculationTaskNew&ran="+ran;
		$.ajax({
            url : url,
            type : 'post',
            data : {procinstid:procinstid,
            		realName:realName,
            		submitName:submitName,
            		queryDate:queryDate},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
          
                console.log(d);
                if (d.success) {		//d.obj
                    loadTemple(d.obj,tab,index);
                }
            }
        });
	}
	function resetCirculation(){
		$("#procinstid_circulation").val("");
		$("#realName_circulation").val("");
 		$("#topic_circulation").val("");
		$("#query_date_circulation").val("");
		goSearchCirculation();
	}
	function goSearchCopy(){
		var tab = $("#myTable").tabs('getSelected'); 
		var procinstid = $("#procinstid_copy").val();
		var realName = $("#realName_copy").val();
		var submitName = $("#topic_copy").val();
		var queryDate = $("#query_date_copy").val();
		var index=3;
	    var ran=Math.random();
	    url = "workflowFysysController.do?loadSendCopyTaskNew&ran="+ran;
		$.ajax({
            url : url,
            type : 'post',
            data : {procinstid:procinstid,
            		realName:realName,
            		submitName:submitName,
            		queryDate:queryDate},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
          
                console.log(d);
                if (d.success) {		//d.obj
                    loadTemple(d.obj,tab,index);
                }
            }
        });
	}
	function resetCopy(){
		$("#procinstid_copy").val("");
		$("#realName_copy").val("");
		$("#topic_copy").val("");
		$("#query_date_copy").val("");
		goSearchCopy();
	}
	function goSearchBack(){
		var tab = $("#myTable").tabs('getSelected'); 
		var procinstid = $("#procinstid_back").val();
		var realName = $("#realName_back").val();
		var submitName = $("#topic_back").val();
		var index=4;
	    var ran=Math.random();
	    url = "workflowFysysController.do?backTaskNew&ran="+ran;
		$.ajax({
            url : url,
            type : 'post',
            data : {procinstid:procinstid,
            		realName:realName,
            		submitName:submitName},
            cache : false,
            success : function(data) {
                var d = $.parseJSON(data);
          
                console.log(d);
                if (d.success) {		//d.obj
                    loadTemple(d.obj,tab,index);
                }
            }
        });
	}
	function resetBack(){
		$("#procinstid_back").val("");
		$("#realName_back").val("");
		$("#topic_back").val("");
		goSearchBack();
	}
	
</script>
	
</div>
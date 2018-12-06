<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<script type="text/javascript" src="plug-in/hy/js/main.js"></script>
<link href="plug-in/hy/css/main.css" rel="stylesheet">
<script type="text/javascript" src="plug-in/hy/js/hytable.js"></script>
<link href="plug-in/hy/css/hytable.css" rel="stylesheet">

<style>
	.fillAct {width:100%;padding:50px 0 0 10px;}
	.fillAct tr {line-height:25px;}
	*[name='searchColums'] select {padding: 0; width: 126px; height: 20px;}
	*[name='searchColumsCenter'] select {padding: 0; width: 126px; height: 20px;}
	.tabs-wrap-tips {position:relative;color:red;text-align: left;}
	.tabs-wrap-tips span {line-height:25px;margin:0 5px;}
	/**
    .datagrid .datagrid-pager{border-style: solid; position:fixed;bottom:0;width:100%; }
    **/
</style>
<div class="easyui-layout" fit="true" >
	<div id="datagridListtb" style="padding:3px; height: auto">
		<div name="searchColums">
			<div style="padding-left:28px;padding-top:-5px;float:left;">
				<span>角色名称:</span>
				<input id="terminalName" name="terminalName" />
			</div>
			<div class="clear"></div>
		</div>
		<div style="height:30px;padding-top:5px;margin-top:5px" class="datagrid-toolbar">
            <span style="float:left">
                <a id="" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add('${addUri}');">加入候选框</a>
            </span>
		<span style="float: right">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="datagridListsearch()">查询</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset('datagridList')">重置</a>
            </span>
	</div>
	</div>
	<div region="west" split="true" title="待选框" style="width:600px">
		<t:datagrid newFlag="true" name="datagridList" actionUrl="roleController.do?roleGrid" autoLoadData="true" fitColumns="false" fit="true" idField="id" queryMode="group" checkbox="true" onLoadSuccess="" pageSize="60">
			<t:dgCol title="ID" hidden="true" field="id" sortable="false"></t:dgCol>
			<t:dgCol title="角色名称" field="roleName" sortable="false"></t:dgCol>
			<t:dgCol title="角色编码" field="roleCode" sortable="false"></t:dgCol>
		</t:datagrid>
	</div>

	<div id="datagridListCentertb" style="padding:3px; height: auto">
		<div style="height:30px;" class="datagrid-toolbar">
            <span style="float:left">
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="del('${delUri}')" >移除</a>
            </span>
			<span style="float:right">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="datagridListCentersearch()">查询</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset('datagridListCenter')">重置</a>
            </span>
		</div>
	</div>

	<div region="center" split="true" title="已选框" style="width:550px;">
		<t:datagrid newFlag="true" name="datagridListCenter" actionUrl="userController.do?userRoleGrid&id=${userId }" fitColumns="false" fit="true" idField="id" queryMode="group" checkbox="true" pagination="false">
			<t:dgCol title="ID" hidden="true" field="id" sortable="false"></t:dgCol>
			<t:dgCol title="角色名称" field="roleName" sortable="false"></t:dgCol>
			<t:dgCol title="角色编码" field="roleCode" sortable="false"></t:dgCol>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
    //添加
    function add(uri){
        var rows = $('#datagridList').datagrid('getSelections');
        if(rows.length < 1){
            tip("请选择数据。");
            return;
        }
		var liststr=JSON.stringify(rows);
		var para={liststr:liststr};
		<%--var para="custId=${custId}&custName=${custName}&liststr="+liststr;--%>
		$.ajax({
			type: 'post',
			url: uri,
			data: para,
			success: function(data) {
				var data = JSON.parse(data);
				if (data["success"]){
					datagridListsearch();
					datagridListCentersearch();
				}else{
					tip(data["msg"]);
					$.messager.progress('close');
				}
			}
		});
        $('#datagridList').datagrid('clearSelections');
    }

    //移除候选框
    function del(uri){
        var rows=$("#datagridListCenter").datagrid("getSelections");
        var liststr=JSON.stringify(rows);
        var para={liststr:liststr};
//        var para="liststr="+liststr;
        $.ajax({
            type: 'post',
            url: uri,
            data: para,
            success: function(data) {
                var data = JSON.parse(data);
                if (data["success"]){
                    datagridListsearch();
                    datagridListCentersearch();
                    //获取父页面并调用父页面的方法
//                    var api = frameElement.api;
//                    W = api.opener;
//                    $.each(rows,function(k,v){
//                        W.showFence(k, v,false);
//                        W.selectedRowMap.remove(v.id,v.id);
//                    })
                }else{
                    tip(data["msg"]);
                    $.messager.progress('close');
                }
            }
        });
        $('#datagridListCenter').datagrid('clearSelections');
    }

</script>

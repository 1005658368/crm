<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
	<t:datagrid name="roleList" actionUrl="roleController.do?roleGrid" idField="id" singleSelect="true">
		<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>
		<t:dgCol title="common.operation" field="opt"></t:dgCol>
		<t:dgFunOpt funname="delRole(id)" title="common.delete"></t:dgFunOpt>
		<t:dgFunOpt funname="userListbyrole(id,roleName)" title="common.user"></t:dgFunOpt>
		<t:dgFunOpt funname="setfunbyrole(id,roleName)" title="permission.set"></t:dgFunOpt>
		<t:dgCol title="common.role.name" field="roleName" query="true"></t:dgCol>
		<t:dgCol title="common.role.code" field="roleCode" query="true"></t:dgCol>
		<t:dgCol title="角色类型" field="roleType" query="true"  replace="菜单角色_0,权限角色_1,流程角色_2"></t:dgCol>
		<t:dgCol title="停用状态" field="stopType" query="false"  replace="启用_0,停用_1"></t:dgCol>
		<t:dgToolBar title="common.add.param" langArg="common.role" icon="icon-add" url="roleController.do?addorupdate&opid=0" funname="add"></t:dgToolBar>
		<t:dgToolBar title="common.edit.param" langArg="common.role" icon="icon-edit" url="roleController.do?addorupdate&opid=1" funname="update"></t:dgToolBar>
<%--
		<t:dgToolBar title="查看关联流程" langArg="common.role"  icon="icon-see" url="roleController.do?gofindprocess" funname="findpeocess"></t:dgToolBar>
		<t:dgToolBar title="导入" langArg="common.role"  icon="icon-putout"  funname="importRole"></t:dgToolBar>
		<t:dgToolBar title="导出" langArg="common.role"  icon="icon-put"  funname="exportRole"></t:dgToolBar>
		<t:dgToolBar title="停用" langArg="common.role"  icon="icon-user_lock"  funname="stop(0)"></t:dgToolBar>
		<t:dgToolBar title="启用" langArg="common.role"  icon="icon-user_unlock"  funname="lock(1)"></t:dgToolBar>
--%>
	</t:datagrid>
	<div>&nbsp</div>
</div>
</div>
<div region="east" style="width: 600px;" split="true">
<div tools="#tt" class="easyui-panel" title='<t:mutiLang langKey="permission.set"/>' style="padding: 10px;" fit="true" border="false" id="function-panel"></div>
</div>
<div id="tt"></div>
<script type="text/javascript">
var gridname1 = "";
var windowapi1 = frameElement ? frameElement.api : undefined;
if (typeof (windowapi1) != 'undefined') {
    var W1 = windowapi1.opener;// 内容页中调用窗口实例对象接口
}
function importRole(){
	importDataByXml({impName:'role'});
}
function exportRole(){
	EispExcelExport("roleController.do?export","roleList");
}
function setfunbyrole(id,roleName) {
	$("#function-panel").panel(
		{
			title :roleName+ ':' + '<t:mutiLang langKey="current.permission"/>',
			href:"roleController.do?roleFunction&roleId=" + id
		}
	);
	$('#function-panel').panel("refresh" );
}

function setfunbyphone(id,roleName){
	$("#function-panel").panel(
			{
				title :roleName+":当前权限",
				href:"phoneMenuitemsController.do?phoneMenuForRole&roleId=" + id
			}
		);
		$('#function-panel').panel("refresh" );
		
	}
//update-start--Author:gaofeng Date:20140822 for：查看角色的所有用户信息
function userListbyrole(id,roleName,roleType) {
	$("#function-panel").panel(
		{
			title :roleName+ ':' + '<t:mutiLang langKey="common.user"/>',
			href:"roleController.do?roleUser&roleId=" + id
		}
	);
	$('#function-panel').panel("refresh" );
	
}
//update-end--Author:gaofeng Date:20140822 for：查看角色的所有用户信息
//删除角色
function delRole(id){
	var tabName= 'roleList';
	var url= 'roleController.do?delRole&id='+id;
	$.dialog.confirm('<t:mutiLang langKey="confirm.delete.this.record"/>', function(){
		doSubmit(url,tabName);
		rowid = '';
		$("#function-panel").html("");//删除角色后，清空对应的权限
	}, function(){
	});
}


//停用
function stop(type){
    var rowsData =  $('#roleList').datagrid('getSelections');
    for(var i in rowsData){
        var id=rowsData[i].id;

    }
    $.ajax({
        url:"roleController.do?stopRole",
        data:{"id":id,"stopType":type},
        type:"post",
        cache:false,
        dataType:"json",
        success:function(data){
            if(data.success){
                var msg=data.msg;
                tip(msg);
                $('#roleList').datagrid('reload');
            }
        }

    });
}


//启用
function lock(type){
    var rowsData =  $('#roleList').datagrid('getSelections');
    for(var i in rowsData){
        var id=rowsData[i].id;

    }
    $.ajax({
        url:"roleController.do?stopRole",
        data:{"id":id,"stopType":type},
        type:"post",
        cache:false,
        dataType:"json",
        success:function(data){
            if(data.success){
                var msg=data.msg;
                tip(msg);
                $('#roleList').datagrid('reload');
            }
        }

    });
}



//查看角色关联流程
function findpeocess(title, url, id, width, height) {
    gridname1 = id;
    var rowsData = $('#' + id).datagrid('getSelections');
    if (!rowsData || rowsData.length == 0) {
        tip('请选择查看项目');
        return;
    }
    if (rowsData.length > 1) {
        tip('请选择一条记录再查看');
        return;
    }

    url += '&id=' + rowsData[0].id;
    createwindow1(title, url, width, height);
}
function createwindow1(title, addurl, width, height) {
	createwindowExt1(title, addurl, width, height, {});
}
function createwindowExt1(title, addurl, width, height, options) {
    width = width ? width : 900;
    height = height ? height : 500;
    if (width == "100%") {
        width = window.top.document.body.offsetWidth;
    }
    if (height == "100%") {
        height = window.top.document.body.offsetHeight - 100;
    }
    var myOptions = {
            content : 'url:' + addurl,
            lock : true,
            width : width,
            height : height,
            title : title,
            opacity : 0.3,
            cache : false,
            cancelVal : '关闭',
            cancel : true
        /* 为true等价于function(){} */
    };
    $.extend(myOptions, options);
    safeShowDialog1(myOptions);
}
function safeShowDialog1(option) {
	if (typeof(windowapi1) === 'undefined') {
		return $.dialog(option).zindex();
	} else {
		return W1.$.dialog($.extend(option, {parent: windowapi1})).zindex();
	}
}
/* // queryModel为single时，点击查询调用的方法
function roleListsearchbox(value, name) {
    var queryParams = $('#roleList').datagrid('options').queryParams;
    initParamsTemp(queryParams);
    queryParams[name] = value;
    queryParams.searchfield = name;
	// 重新设置页面为第一页 
	refreshPageNumber('roleList');
    $('#roleList').datagrid('reload');
}

// 重新查询的时候，刷新页码
function refreshPageNumber(id) {
	// 获取dataGrid的列表对象属性
	var $datagrid = $("#" + id).datagrid("options");
	if ($datagrid != undefined) {
		$datagrid.pageNumber = 1;
	}

	// 获取dataGrid的分页对象
	var $getPager = $("#" + id).datagrid('getPager');
	var $pagination = $($getPager).pagination("options");
	if ($pagination != undefined) {
		$pagination.pageNumber = 1;
	}
} */
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<script>
    $(function() {
        var datagrid = $("#userListtb");
        datagrid.find("div[name='searchColums']").append($("#tempSearchColums div[name='searchColums']").html());
    });
</script>
<style>
	.datagrid .datagrid-pager {position:fixed;bottom:0;width:100%; }
</style>
<div id="tempSearchColums" style="display: none">
    <div name="searchColums">
        <!-- <span style="display:-moz-inline-box;display:inline-block;">
            <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 50px;text-align:right;" title="状态">
                	状态：
            </span>
            <select id="status" style="width: 80px;">
            	<option value="">--请选择--</option>
            	<option value="1">激活</option>
            	<option value="0">未激活</option>
            </select>
        </span> -->
    </div>
</div>

<%--update-end--Author:Biz  Date:20140827 for：添加 组织机构查询条件--%>

<t:datagrid name="userList" singleSelect="false"  actionUrl="userController.do?userGrid" fit="true" fitColumns="false" idField="id" queryMode="group" checkbox="true">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="登陆账号"  field="userName" query="false" width="100"></t:dgCol>
	<%--<t:dgCol title="组织机构" field="departName" query="false" width="80"></t:dgCol>--%>
	<%-- <t:dgCol title="createDate" field="createDate" query="false" width="80"></t:dgCol> --%>
    <%--<t:dgCol title="组织ID" field="departId" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
	<t:dgCol title="用户名" hidden="false" field="realName" query="false" width="80" ></t:dgCol>
	<t:dgCol title="性别" field="sex" hidden="false" dictionary="sex" query="false" width="80" ></t:dgCol>
	<%--<t:dgCol title="角色" field="roleName" sortable="false"  width="80"></t:dgCol>--%>
	<%--<t:dgCol title="职位名称" field="posName"  query="false"   width="80"></t:dgCol>--%>
	<t:dgCol title="手机号码" field="mobilePhone"  width="80" query="false"></t:dgCol>
	<t:dgCol title="常用邮箱" field="email"  width="80" query="false"></t:dgCol>
	<t:dgCol title="员工编码" field="userCode"  width="80" query="false"></t:dgCol>
	<%--<t:dgCol title="人员级别" field="employeepos"  width="80" query="false"></t:dgCol>--%>
	<%--<t:dgCol title="上级职位" field="parentPosName"  width="80" query="false"></t:dgCol>--%>
	<%--<t:dgCol title="上级姓名" field="parentPosUserName"  width="80" query="false"></t:dgCol>--%>
	<%--<t:dgCol title="common.status" sortable="true" field="status" replace="common.active_1,common.inactive_0,锁定_3"  width="50" query="false"></t:dgCol>--%>
	<%--<t:dgCol title="创建人" field="createBy" width="80" query="false"></t:dgCol>--%>
	<%--<t:dgCol title="创建时间" field="createDate"  width="80" query="false"></t:dgCol>--%>
	<%--<t:dgCol title="锁定时间" field="lockTime"  width="80" query="false"></t:dgCol>--%>
</t:datagrid>

 	<%-- 查询字段列表开始 --%>
  	<div id="userListTbForQuery" style="padding: 3px; height: auto">
        <div name="searchColums">
            <span style="padding-left: 15px;">
                <span>登陆账号:</span>
                <input class="inputxt" type="text" name="userName" id="userName" style="width: 120px" >
            </span>
            <span style="padding-left: 15px;">
                <span>　用户名:</span>
                <input class="inputxt" type="text" name="realName" id="realName" style="width: 120px" >
            </span>
            <%--<span style="padding-left: 15px;">--%>
                <%--<span>职位名称:</span>--%>
            	<%--<input class="inputxt" type="text" name="posName" id="posName" style="width: 120px" >--%>
            <%--</span>--%>
            <%--<span style="padding-left: 15px;">--%>
                <%--<span>组织机构:</span> 	--%>
                <%--<t:comboTree url="departController.do?getOrgTree" name="departId" id="departId" ></t:comboTree>--%>
            <%--</span>--%>
	  	    <%--<span style="padding-left: 10px; padding-top: 5px;">--%>
		        <%--<span>状态：</span>--%>
	            <%--<span>--%>
		            <%--<select name="status" id="status" style="width: 120px;">--%>
			        	<%--<option id="status"  value="">---请选择---</option>--%>
			        	<%--<option id="status" value="1">激活</option>--%>
			        	<%--<option id="status" value="0">未激活</option>--%>
		       		<%--</select>--%>
		       	<%--</span>--%>
	       	<%--</span>--%>
	       	<br/>
            <span style="padding-left: 15px;">
                <span>手机号码:</span>
                <input class="inputxt" type="text" name="mobilePhone" id="mobilePhone" style="width: 120px" >
            </span>
             <span style="padding-left: 15px;">
                <span>常用邮箱:</span>
                <input class="inputxt" type="text" name="email" id="email" style="width: 120px" >
            </span>
            <span style="padding-left: 15px;">
                <span>员工编码:</span>
                <input class="inputxt" type="text" name="userCode" id="userCode" style="width: 120px" >
            </span>
            <%--<span style="padding-left: 15px;">--%>
                <%--<span>上级职位:</span>--%>
                <%--<input class="inputxt" type="text" name="parentPosName" id="userCode" style="width: 120px;margin-top:3px" >--%>
            <%--</span>--%>
            <%--<span style="padding-left: 15px;">--%>
                <%--<span>上级姓名:</span>--%>
                <%--<input class="inputxt" type="text" name="parentPosUserName" id="userCode" style="width: 120px" >--%>
            <%--</span>--%>
           
        </div>
        <div style="height: 30px; padding-top: 10px;">
            <span style="float: left;">
               	<a id="TSBaseUser_add" href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('用户录入','userController.do?addorupdate','userList',null,null);">新建</a>
				<a id="TSBaseUser_update" href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('用户编辑','userController.do?addorupdate','userList',null,null);">修改</a>
				<a id="TSBaseUser_role" href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('分配角色','userController.do?userRole','userList','1000px',null);">分配角色</a>
				<%--<a id="TSBaseUser_lockObj" href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="lockObj('锁定用户','userController.do?lock','userList',null,null);">锁定</a>--%>
               	<%--<a id="TSBaseUser_unlockObj" href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="unlockObj('解锁用户','userController.do?unlock','userList',null,null);">解锁</a>--%>
               	<%--<a id="TSBaseUser_editPostion" href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="editPostion('职位分配','userController.do?editPostion','userList',null,null);">分配职位</a>             --%>
           		<%--<a id="TSBaseUser_ExportXls" href="#"  class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="ExportXls()">导出</a>--%>
           		<%--<a id="TSBaseUser_ImportXls" href="#"  class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="importDataByXml({impName:'user'})">导入</a>--%>
           		<%--<!-- <a id="TSBaseUser_ImportUpdateXls" href="#"  class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="importUpdateDataByXml()">批量编辑导入</a> -->--%>
           		<%--<a id="TSBaseUser_ImportUpdateXls" href="#"  class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="userImportXlsUpdate()">批量编辑导入</a>--%>
           		<%--<a id="TSBaseUser_editRecord" href="#" id="editRecord" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="editRecord()">编辑记录</a>--%>
           		<%--<a id="TSBaseUser_lockObj_ByTime" href="#" id="lockObjByTime" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="lockObjByTime()">定时锁定</a>--%>
            </span>
            <span style="float:right;">
                <a id="" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="userListsearch();">查询</a>
                <a id="" href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset('userList');">重置</a>
            </span>
        </div>
 </div>
 <%@include file="/webpage/common/operation.jsp" %>



<script type="text/javascript" src="plug-in/watermark/watermark.js"></script>
<script type="text/javascript">
$(function(){
	var userName = "${userName}";

	var myDate = new Date();
	var content = userName + "\n" + myDate.toLocaleDateString();
	var divH =$(".datagrid-toolbar").height()+80;
	console.log(divH)
	watermark(content,50,divH,80,30,0.1,"18px",20);

});
//禁止复制
/* document.body.oncopy=document.body.onselectstart=document.body.oncontextmenu=function(){ return false;}  */

function lockObj() {  //锁定用户
	var title = "";
	var status  ="";
	var url = "userController.do?lock";
	var id = "userList";
	var rowsData = $("#"+id).datagrid('getSelections');
	if (rowsData == null || rowsData.length == 0) {
		tip("请选择要被锁定的用户");
		return false;
	}if (rowsData.length > 1) {
        tip('请选择一条记录再进行操作');
        return;
    };
	if(rowsData.length > 1 ){
		tip("请选择一位用户");
		return false;
	}
	status = rowsData[0].status;
	if("0" == status){
		tip("请选择已激活的用户！");
		return false;
	}
// 	url = url+'&id='+rowsData[0].id;
// 	url += '&id=' + rowsData[0].id +'&loginAccount=' + rowsData[0].userName + '&departName=' + rowsData[0].departName + '&posName=' + rowsData[0].posName + '&roleName=' + rowsData[0].roleName + '&realName=' + rowsData[0].realName + '&mobilePhone=' + rowsData[0].mobilePhone + '&email=' + rowsData[0].email + '&userCode=' + rowsData[0].userCode + '&status=' + rowsData[0].status;
// 	url;
	$.dialog.confirm('是否锁定用户？', function(){
		lockuploadify(url);
		}, function(){
	});
}
function lockuploadify(url) {
	var rowsData = $("#userList").datagrid('getSelections');
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		data:{
			id : rowsData[0].id,
			loginAccount : rowsData[0].userName ,
			departName : rowsData[0].departName,
			posName : rowsData[0].posName ,
			roleName : rowsData[0].roleName ,
			realName : rowsData[0].realName,
			mobilePhone : rowsData[0].mobilePhone ,
			email : rowsData[0].email,
			userCode : rowsData[0].userCode,
			status : rowsData[0].status,
			employeepos : rowsData[0].employeepos
		},
		error : function() {// 请求失败处理函数
			tip("激活失败！");
		
		},success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
		}
	});
}

function unlockObj() {//解锁用户
	var title  = "";
	var ststus = "";
	var url = "userController.do?unlock";
	var ids = "userList";
	var rowsData = $('#'+ids).datagrid('getSelections');
	if (rowsData == null || rowsData.length==0) {
		tip("请选择要被解锁的用户");
		//tip('<t:mutiLang langKey="please.select.unlock.item"/>');
		return false;
	}if (rowsData.length > 1) {
        tip('请选择一条记录再进行操作');
        return;
    };
	if(rowsData.length > 1){
		tip("请选择一位用户");
		return false;
	}
	status  = rowsData[0].status;
/*	if("1" == status){
		tip("请选择未激活的用户！");
		return false;
	}*/
// 	url = url+'&ids='+rowsData[0].id;
// 	url += '&id=' + rowsData[0].id +'&loginAccount=' + rowsData[0].userName + '&departName=' + rowsData[0].departName + '&posName=' + rowsData[0].posName + '&roleName=' + rowsData[0].roleName + '&realName=' + rowsData[0].realName + '&mobilePhone=' + rowsData[0].mobilePhone + '&email=' + rowsData[0].email + '&userCode=' + rowsData[0].userCode + '&status=' + rowsData[0].status;
	$.dialog.confirm('<t:mutiLang langKey="是否解锁选中用户 ？"/>', function(){
		unlockuploadify(url);
	}, function(){
	});
}

function unlockuploadify(url) {
	var rowsData = $('#userList').datagrid('getSelections');
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		data : {
			id : rowsData[0].id,
			loginAccount : rowsData[0].userName,
			departName : rowsData[0].departName,
			posName : rowsData[0].posName,
			roleName : rowsData[0].roleName,
			realName : rowsData[0].realName,
			mobilePhone : rowsData[0].mobilePhone,
			email : rowsData[0].email,
			userCode : rowsData[0].userCode,
			status : rowsData[0].status,
			employeepos : rowsData[0].employeepos
		},
		error : function() {// 请求失败处理函数
			tip("激活失败！");
		},
		success : function(data) {
			var b = $.parseJSON(data);
			if (b.success) {
			var msg = b.msg;
				tip(msg);
				reloadTable();
			}
			
		}
	});
}

//用户批量编辑
function userImportXlsUpdate() {
	openuploadwin2('Excel导入', 'updateUserExoController.do?uploadUserCheckBox', "userList");
}
//批量修改模板下载
function userImportXlsGetModel() {
	EispExcelExport("updateUserExoController.do?getUserImpModel","userList");
}

/**
 * 创建上传页面窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openuploadwin2(title, url, name, width, height) {
	gridname = name;
    safeShowDialog({
        content : 'url:' + url,
        lock : true,
        cache : false,
        button : [{
            name : "下载模板",
            callback : function() {
            	EispExcelExport("updateUserExoController.do?getUserImpModel","userList");
            	return false;
            }
        }, {
            name : "开始上传",
            callback : function() {
            	 iframe = this.iframe.contentWindow;
                 saveObj();
                 return false;
            },
            focus : true
        }, {
            name : "取消上传",
            callback : function() {
                iframe = this.iframe.contentWindow;
                iframe.cancel();
            }
        } ]
    });
}
//用户批量修改
function importUpdateDataByXml() {
	var url="updateUserExoController.do?uploadUser";
	var title="导入";
	$.dialog({
		content:"url:"+url,
	    lock : true,
	    title: title,
	    max:false,
	    width:660,
	    height:400,
	    button: [
		        {
		            name: "导入",
		            callback: function(){
		            	iframe = this.iframe.contentWindow;
						iframe.upload();
						return false;
		            }
		        },
		        {
		            name: "模板下载",
		            callback: function(){
		            	iframe = this.iframe.contentWindow;
						iframe.downTemplete();
						return false;
		            }
		        },
		        {
		            name: "关闭",
		            callback: function(){
		            	this.close();
		            	 reloadTable();
		            }
		        }
		]
	}).zindex();
}



function remakePassword(url, id){
	var rowsData = $('#userList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择一条数据');
		return;
	};
	url = 'userController.do?remakePassword&id='+rowsData[0].id;
	$.dialog.confirm('是否重制密码为：123456', function(){
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数
			},
			success : function(data) {
				var d = $.parseJSON(data);
				tip(d.msg);
			}
		});
	}, function(){
	});
}

function editPostion() {
	var rowsData = $('#userList').datagrid('getSelections');
	var url = '';
	if (!rowsData || rowsData.length==0) {
		tip('请选择一条数据');
		return;
	}if (rowsData.length > 1) {
        tip('请不要选择多条数据');
        return;
    };
   // openwindow('职位管理','userController.do?editPostion&id=' +rowsData[0].id,'职位管理',800,500);
    url = 'userController.do?editPostion&id=' + rowsData[0].id +'&loginAccount=' + rowsData[0].userName + '&departName=' + rowsData[0].departName + '&posName=' + rowsData[0].posName + '&roleName=' + rowsData[0].roleName + '&realName=' + rowsData[0].realName + '&mobilePhone=' + rowsData[0].mobilePhone + '&email=' + rowsData[0].email + '&userCode=' + rowsData[0].userCode + '&status=' + rowsData[0].status + '&employeepos=' + rowsData[0].employeepos;
	createselectwindow('职位管理', url, 800,500,reloadUserList);
}
var reloadUserList=function(){
	$("#userList").datagrid("reload");
}

//用户（批量）删除
function deleteUser(){
	var ids = [];
	var userNames = [];
	var id = "";
	var rows = $('#userList').datagrid('getSelections');
	if (!rows || rows.length==0) {
		tip('请选择要删除的数据');
		return;
	}
	for(var i=0;i < rows.length;i++){
		if(rows[i].userName == 'admin'){
			tip("所选数据不能包含登陆账号为[admin]");
			return;
		}
		userNames.push(rows[i].userName);
		ids.push(rows[i].id);
	}
	if(rows.length > 0){
		$.dialog.confirm('是否删除选中数据？',function(r){
			if(r){
				$.ajax({
					type:"post",
			        url:'userController.do?deleteUser',
			        data:{
			        	ids:ids.join(','),
			        	userNames:userNames.join(',')
			        },
			        dataType:"json",
			        cache:false,
			        success:function(data) {
			        	if(true == data.success){
			        		tip(data.msg);
							$('#userList').datagrid('reload');
			        	}
			        	if(false == data.success){
			        		tip(data.msg);
			        	}
			        },
			        error:function() {
			            $.ligerDialog.error('服务器繁忙，请稍后再试');
			        }
				});
			}
		});
	}
}
</script>

<script type="text/javascript">
//    var windowapi = frameElement.api, W = windowapi.opener;
    function choose_297e201048183a730148183ad85c0001() {
        if (typeof(windowapi) == 'undefined') {
            $.dialog({content: 'url:departController.do?departSelect', zIndex: 2100, title: '<t:mutiLang langKey="common.department.list"/>', lock: true, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [
                {name: '<t:mutiLang langKey="common.confirm"/>', callback: clickcallback_297e201048183a730148183ad85c0001, focus: true},
                {name: '<t:mutiLang langKey="common.cancel"/>', callback: function () {
                }}
            ]});
        } else {
            $.dialog({content: 'url:departController.do?departSelect', zIndex: 2100, title: '<t:mutiLang langKey="common.department.list"/>', lock: true, parent: windowapi, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [
                {name: '<t:mutiLang langKey="common.confirm"/>', callback: clickcallback_297e201048183a730148183ad85c0001, focus: true},
                {name: '<t:mutiLang langKey="common.cancel"/>', callback: function () {
                }}
            ]});
        }
    }
    function clearAll_297e201048183a730148183ad85c0001() {
        if ($('#departname').length >= 1) {
            $('#departname').val('');
            $('#departname').blur();
        }
        if ($("input[name='departname']").length >= 1) {
            $("input[name='departname']").val('');
            $("input[name='departname']").blur();
        }
        $('#orgIds').val("");
    }
    function clickcallback_297e201048183a730148183ad85c0001() {
        iframe = this.iframe.contentWindow;
        var departname = iframe.getdepartListSelections('departname');
        if ($('#departname').length >= 1) {
            $('#departname').val(departname);
            $('#departname').blur();
        }
        if ($("input[name='departname']").length >= 1) {
            $("input[name='departname']").val(departname);
            $("input[name='departname']").blur();
        }
        var id = iframe.getdepartListSelections('id');
        if (id !== undefined && id != "") {
            $('#orgIds').val(id);
            $("input[name='orgIds']").val(id);
        }
    }
  //导入
   /*  function ImportXls(){
    	openuploadwins('Excel导入', 'userController.do?upload', "userList");
    }

    function openuploadwins(title, url, name) {
    	gridname = name;
        $.dialog({
            content : 'url:' + url,
            cache : false,
            button : [ {
                name : "开始上传",
                callback : function() {
                    iframe = this.iframe.contentWindow;
                    iframe.upload();
                    window.location.href='userController.do?user&time='+Math.random();
                    return false;
                },
                focus : true
            }, {
                name : "取消上传",
                callback : function() {
                    iframe = this.iframe.contentWindow;
                    iframe.cancel();
                }
            } ]
        }).zindex();
    } */
    
    //模板下载
	/* function downloadExcelUserTemplate(){
		EispExcelExport("userController.do?downloadExcelUserTemplate","userList");
	} */
	//导出
	function ExportXls() {
		EispExcelExport("userController.do?exportXls","userList");
	}
	
	//查看用户编辑记录
	function editRecord(){
		var rows = $('#userList').datagrid('getSelections');
	    if (rows.length == 1) {
	        var url = "userController.do?toUserLogList&userId="+ rows[0].id;
			$.dialog({
		          content : 'url:' + url,
		          title : "用户编辑记录",
		          cache : false,
		          lock : true,
		          width : 1200,
		          height : 520,
		          zIndex : 1000
		      });
	    }else{
	    	tip("请选择一条要操作的数据！");
	    }
	}
	
	//根据时间锁定用户
	function lockObjByTime(){
		var ids = [];
		var rows = $('#userList').datagrid('getSelections');
        status = rows[0].status;
        if("0" == status){
            tip("请选择已激活的用户！");
            return false;
        }


		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
        var url = "userController.do?lockUserByTime&userIds="+ ids;
        createwindow('锁定用户', url, 400,200);
	}



	//编辑用户
	function update(title, url, id, width, height) {
	    gridname = id;
	    var rowsData = $('#' + id).datagrid('getSelections');
	    if (!rowsData || rowsData.length == 0) {
	        tip('请选择编辑项目');
	        return;
	    }
	    if (rowsData.length > 1) {
	        tip('请选择一条记录再编辑');
	        return;
	    }

	    url += '&id=' + rowsData[0].id + '&departName=' + rowsData[0].departName + '&posName=' + rowsData[0].posName + '&roleName=' + rowsData[0].roleName + '&realName=' + rowsData[0].realName + '&mobilePhone=' + rowsData[0].mobilePhone + '&email=' + rowsData[0].email + '&userCode=' + rowsData[0].userCode + '&status=' + rowsData[0].status + '&employeepos=' + rowsData[0].employeepos;
	    createwindow(title, url, width, height);
	}
	
	//设备解绑
	function terminalUnBunding(){
		var ids = [];
		var userNames = [];
		var id = "";
		var rows = $('#userList').datagrid('getSelections');
		if (!rows || rows.length==0) {
			tip('请选择要操作的数据');
			return;
		}
		
		for(var i=0;i < rows.length;i++){
			ids.push(rows[i].id);
		}
		
		if(rows.length > 0){
			$.dialog.confirm('是否对选中数据设备解绑？',function(r){
				if(r){
					$.ajax({
						type:"post",
				        url:'userController.do?terminalDoUnBunding',
				        data:{
				        	ids:ids.join(','),
				        	userNames:userNames.join(',')
				        },
				        dataType:"json",
				        cache:false,
				        success:function(data) {
				        	if(true == data.success){
				        		tip(data.msg);
								$('#userList').datagrid('reload');
				        	}
				        	if(false == data.success){
				        		tip(data.msg);
				        	}
				        },
				        error:function() {
				            $.ligerDialog.error('服务器繁忙，请稍后再试');
				        }
					});
				}
			});
		}
	}
	//var rowsData = $('#userList').datagrid('getSelections');
function unbundling(){
	var rowsData = $('#userList').datagrid('getSelections');

	if(!rowsData || rowsData.length == 0){
		tip("请选择要操作的数据");
		return;
	}
	if(rowsData.length>1){
		tip('请不要选择多条数据');
		return;
	};
	var openId=rowsData[0].openId;
	if(openId==null||openId==""){
		tip("请选择已绑定微信的数据");
		return;
	}
	var userId=rowsData[0].id;
	$.dialog.confirm('是否解绑微信？',function(r){
		$.ajax({
			type:"post",
			url:"userController.do?unbundling",
			data:{
				id:userId
			},
			dataType:"json",
	        cache:false,
			success:function(data){
				if(true == data.success){
	        		tip(data.msg);
					$('#userList').datagrid('reload');
	        	}
			}
		});
	});
}
</script>
<%--update-end--Author:Biz  Date:20140827 for：添加 组织机构查询条件：弹出 选择组织机构列表 相关操作--%>

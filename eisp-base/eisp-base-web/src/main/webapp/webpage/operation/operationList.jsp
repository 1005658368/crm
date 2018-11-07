<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div  class="easyui-layout" fit="true">
	<div  region="center" border="true">
		<div  class="easyui-accordion" fit="true" border="false">
			<%--第一个页签--%>
			<div id="general" style="padding: 1px;" title="操作管理" >
				<div  border="false" style="padding: 3px;height:400px; background: #E1F0F2;">
					<t:datagrid name="operationList"  actionUrl="functionController.do?opdategrid&functionId=${functionId}" idField="id">
						<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
						<t:dgCol title="operate.name" field="operationname" ></t:dgCol>
						<t:dgCol title="operate.code" field="operationcode"></t:dgCol>
						<t:dgCol title="common.type" field="operationType" replace="common.hide_0,operationType.disabled_1"></t:dgCol>
						<t:dgCol title="common.status" field="status" replace="common.enable_0,common.disable_1"></t:dgCol>
						<!--	<t:dgCol title="permission.name" field="TSFunction_functionName"></t:dgCol>-->
						<t:dgCol title="common.operation" field="opt" ></t:dgCol>
						<t:dgDelOpt url="functionController.do?delOperation&id={id}" title="common.delete"></t:dgDelOpt>
						<t:dgFunOpt funname="editoperation(id,operationname)" title="common.edit"></t:dgFunOpt>
						<t:dgToolBar title="common.add.param" langArg="common.operation" icon="icon-add" url="functionController.do?addorupdateop&functionId=${functionId}" funname="add"></t:dgToolBar>
						<%-- <t:dgToolBar title="操作编辑" icon="icon-edit" url="functionController.do?addorupdateop&functionId=${functionId}" funname="update"></t:dgToolBar>--%>
					</t:datagrid>
				</div>
			</div>
		</div>
		<%--第一个页签结束（datagrid后多一个div）--%>

		<%--第二个页签（datagrid后多一个div）--%>
<%--		<div id="gen" style="padding: 1px;" title="功能管理" >
			<div  border="false" style="padding: 1px; height:400px;background: #E1F0F2;">
			
				<t:datagrid name="functionDataRoleList" checkbox="true" fitColumns="flase" fit="true" actionUrl="functionController.do?dataGridFunctionDataRole&functionId=${functionId}" idField="id">
					<t:dgCol title="id" field="id" hidden="true"></t:dgCol>
    				<t:dgCol  title="功能角色ID" field="dataRoleId"  width = "120" hidden="true"></t:dgCol>
    				<t:dgCol  title="功能名称" field="actionName"  width = "120"></t:dgCol>
    				<t:dgCol  title="数据角色名称" field="roleName"  width = "120"></t:dgCol>
					<t:dgCol title="数据角色编码" field="roleCode" width="120"></t:dgCol>
					<t:dgCol title="功能地址" field="url" width="120"></t:dgCol>
					<t:dgToolBar title="功能录入"  icon="icon-add" url="functionController.do?addOrUpdateFunctionDataRole&functionId=${functionId}" funname="add"></t:dgToolBar>
					<t:dgToolBar title="操作编辑" icon="icon-edit" url="functionController.do?addOrUpdateFunctionDataRole&functionId=${functionId}" funname="update"></t:dgToolBar>
					<t:dgToolBar title="删除"  icon="icon-remove" url="functionController.do?deleteFunctionDataRole" funname="deleteALLSelect"></t:dgToolBar>
				</t:datagrid>
			
			
			
&lt;%&ndash; 				<t:datagrid name="menuActionList" checkbox="true" fitColumns="flase" fit="true" actionUrl="functionController.do?findTSMenuActionList&functionId=${functionId}" idField="id">
					<t:dgCol title="id" field="id" hidden="true"></t:dgCol>
					<t:dgCol title="功能编码" field="actionCode" width="70"></t:dgCol>
					<t:dgCol title="功能名称" field="actionName" width="100"></t:dgCol>
					<t:dgCol title="分组" field="groupName" width="70"></t:dgCol>
					<t:dgCol title="common.operation" field="opt" width="70" ></t:dgCol>
					<t:dgDelOpt url="functionController.do?deleteMenuAction&id={id}" title="common.delete"></t:dgDelOpt>
					<t:dgFunOpt funname="editMenuAction(id)" title="common.edit"></t:dgFunOpt>
					<t:dgCol  title="id" field="id" hidden="true"></t:dgCol>
    				<t:dgCol  title="角色名称" field="roleName"  width = "120"></t:dgCol>
					<t:dgCol title="角色编码" field="roleCode" width="120"></t:dgCol>
					<t:dgToolBar title="功能录入"  icon="icon-add" url="functionController.do?goMenuActionForm&functionId=${functionId}" funname="addRole"></t:dgToolBar>
					<t:dgToolBar title="删除"  icon="icon-remove" url="functionController.do?doDelete" funname="deleteALLSelect"></t:dgToolBar>
				</t:datagrid> &ndash;%&gt;
			</div>
		</div>--%>
	</div>
</div>
		
	<%-- </div>
	第二个页签结束（datagrid后多一个div）
	第三个页签（datagrid后多一个div）
	<div id="menuActionGroup" style="padding: 1px;" title="功能组管理" >
		<div  border="false" style="padding: 1px; height:400px;background: #E1F0F2;">
			<t:datagrid name="menuActionGroupList" actionUrl="functionController.do?findTSMenuActionGroupList&functionId=${functionId}" idField="id">
				<t:dgCol title="id" field="id" hidden="true"></t:dgCol>
				<t:dgCol title="功能组名称" field="groupName" width="120"></t:dgCol>
				<t:dgCol title="是否单选" field="singleSelection" replace="是_1,否_0" width="50"></t:dgCol>
				<t:dgCol title="common.operation" field="opt" width="70" ></t:dgCol>
				<t:dgDelOpt url="functionController.do?deleteMenuActionGroup&id={id}" title="common.delete"></t:dgDelOpt>
				<t:dgFunOpt funname="editMenuActionGroup(id)" title="common.edit"></t:dgFunOpt>
				<t:dgToolBar title="功能录入"  icon="icon-add" url="functionController.do?goMenuActionGroupForm&functionId=${functionId}" funname="add"></t:dgToolBar>
			</t:datagrid>
		</div>
	</div>
</div>
第三个页签结束（datagrid后多一个div）
 

</div>
</div>
</div>
--%>
<script type="text/javascript">
    function editoperation(operationId,operationname)
    {
        createwindow("<t:mutiLang langKey="common.edit.param" langArg="common.operation"/>","functionController.do?addorupdateop&functionId=${functionId}&id="+operationId);
    }
    /**
     * 功能编辑
     * @param operationId
     */
    /* function editMenuAction(operationId)
    {
        createwindow("功能编辑","functionController.do?goMenuActionForm&functionId=${functionId}&id="+operationId);
    } */

    function editMenuActionGroup(operationId)
    {
        createwindow("功能组编辑","functionController.do?goMenuActionGroupForm&functionId=${functionId}&id="+operationId);
    }
    function addRole(title, url, dgname, width, height){
		url = 'functionController.do?goMenuActionForm';
		gridname = dgname;
		createAddCustRelWindow(title, url, 770, 450);
	}
  //子页面关闭后，父页面完成回调
    var callBack = function(data){
    	$("#menuActionList").datagrid("reload");
    }
  //重写弹出添加按钮窗口
    function createAddCustRelWindow(title, addurl, width, height) {
        width = width ? width : 700;
        height = height ? height : 320;
        if (width == "100%" || height == "100%") {
            width = window.top.document.body.offsetWidth;
            height = window.top.document.body.offsetHeight - 100;
        }
        if (typeof (windowapi) == 'undefined') {
            $.dialog({
                content : 'url:' + addurl,
                lock : true,
                zIndex:1990,
                width : width,
                height : height,
                title : title,
                opacity : 0.3,
                cache : false,
                button : [ {
                    name : "确定",
                    callback : function() {
                    	$("#menuActionList").datagrid('reload');
                    },
                    focus : true 
                }], 
                cancelVal : '关闭',
	            cancel:function(){
	            	iframe = this.iframe.contentWindow;
	            	return true;
	            }
            }).zindex();
        } else {
            W.$.dialog({
                content : 'url:' + addurl,
                lock : true,
                width : width,
//                 zIndex:29990,
                height : height,
                parent : windowapi,
                title : title,
                opacity : 0.3,
                cache : false,
				button : [{
	                    name : "确定",
	                    callback : function() {
	                    	var api = frameElement.api, W = api.opener;
	                        iframe = this.iframe.contentWindow;
	                        iframe.addRoles();console.log("fu");
	                        $('#menuActionList').datagrid('reload');
	                        return true;
	                    },
	                    focus : true 
	                }], 
                cancelVal : '关闭',
                cancel:function(){
                	iframe = this.iframe.contentWindow;
                	$('#menuActionList').datagrid('reload');
                	return true;
                }
            }).zindex();
        }
    }
</script>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    $(function() {
        $('#functionid').tree({
            checkbox : true,
            url : 'roleController.do?setAuthority&roleId=${roleId}',
            onLoadSuccess : function(node) {
//                expandAll();
            },
            onClick: function(node){
                var isRoot =  $('#functionid').tree('getChildren', node.target);
                if(isRoot==''){
                    var roleId = $("#roleId").val();
                    $('#operationListpanel').panel("refresh", "roleController.do?operationListForFunction&functionId="+node.id+"&roleId="+roleId);
                    //$('#actionListpanel').panel("refresh", "roleController.do?menuRoleFunctionForDataRole&functionId="+node.id+"&menuRoleId="+roleId);
                }else {
                }
            }
        });
        $("#functionListPanel").panel(
            {
                title :"菜单列表",
                tools:[{iconCls:'icon-save',handler:function(){mysubmit();}}]
            }
        );
        $("#operationListpanel").panel(
            {
                title :"菜单设置列表",
                tools:[{iconCls:'icon-save',handler:function(){submitOperation();}}]
            }
        );
        $("#actionListpanel").panel(
            {
                title :"功能设置列表",
                tools:[{iconCls:'icon-save',handler:function(){submitAction();}}]
            }
        );
    });
    function mysubmit() {
        var roleId = $("#roleId").val();
        var s = GetNode();
        doSubmit("roleController.do?updateAuthority&rolefunctions=" + s + "&roleId=" + roleId);
    }
    function GetNode() {
        var node = $('#functionid').tree('getChecked');
        var cnodes = '';
        var pnodes = '';
        var pnode = null; //保存上一步所选父节点
        for ( var i = 0; i < node.length; i++) {
            if ($('#functionid').tree('isLeaf', node[i].target)) {
                cnodes += node[i].id + ',';
                pnode = $('#functionid').tree('getParent', node[i].target); //获取当前节点的父节点
                while (pnode!=null) {//添加全部父节点
                    pnodes += pnode.id + ',';
                    pnode = $('#functionid').tree('getParent', pnode.target);
                }
            }
        }
        cnodes = cnodes.substring(0, cnodes.length - 1);
        pnodes = pnodes.substring(0, pnodes.length - 1);
        return cnodes + "," + pnodes;
    };

    function expandAll() {
        var node = $('#functionid').tree('getSelected');
        if (node) {
            $('#functionid').tree('expandAll', node.target);
        } else {
            $('#functionid').tree('expandAll');
        }
    }
    function selecrAll() {
        var node = $('#functionid').tree('getRoots');
        for ( var i = 0; i < node.length; i++) {
            var childrenNode =  $('#functionid').tree('getChildren',node[i].target);
            for ( var j = 0; j < childrenNode.length; j++) {
                $('#functionid').tree("check",childrenNode[j].target);
            }
        }
    }

    function reset() {
        $('#functionid').tree('reload');
    }

    $('#selecrAllBtn').linkbutton({
    });
    $('#resetBtn').linkbutton({
    });
</script>
<style>
	.panel-header{display: block!important;}
</style>
<div class="easyui-layout" fit="true">
	<div region="west" style="padding: 1px;width:300px">
		<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="functionListPanel">
			<input type="hidden" name="roleId" value="${roleId}" id="roleId">
			<a id="selecrAllBtn" onclick="selecrAll();">全选</a>
			<a id="resetBtn" onclick="reset();">重置</a>
			<ul id="functionid"></ul>
		</div>
	</div>
	<div region="center" style="width: 300px; overflow: hidden;" split="true">
		<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="operationListpanel"></div>
	</div>
	<%--<div region="east" style="width: 150px; overflow: hidden;" split="true">--%>
		<%--<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="actionListpanel"></div>--%>
	<%--</div>--%>
</div>

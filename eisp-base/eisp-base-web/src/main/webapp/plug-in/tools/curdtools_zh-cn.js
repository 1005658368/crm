﻿﻿//﻿var jq = jQuery.noConflict();
/**
 * 增删改工具栏
 */
/*
 * window.onerror = function() { return true; };
 */
var iframe;// iframe操作对象
var win;// 窗口对象
var gridname = "";// 操作datagrid对象名称
var windowapi = frameElement ? frameElement.api : undefined;
if (typeof (windowapi) != 'undefined') {
    var W = windowapi.opener;// 内容页中调用窗口实例对象接口
}
function upload(curform) {
    upload();
}

/**
 * 获取可以在嵌套的对话框页面中安全使用无需要担心其中的lhgDialog插件重入问题的jquery实例
 * @returns
 */
function getSafeJq() {
	if (typeof(windowapi) === 'undefined') {
		return $;
	} else {
		return W.$;
	}
}

function safeShowDialog(option) {
	if (typeof(windowapi) === 'undefined') {
		return $.dialog(option).zindex();
	} else {
		return W.$.dialog($.extend(option, {parent: windowapi})).zindex();
	}
}

/**
 * 添加事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 */
function add(title, addurl, gname, width, height) {
    gridname = gname;
    createwindow(title, addurl, width, height);
}
/**
 * 树列表添加事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 */
function addTreeNode(title, addurl, gname) {
    if (rowid != '') {
        addurl += '&id=' + rowid;
    }
    gridname = gname;
    createwindow(title, addurl);
}
/**
 * 更新事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
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

    url += '&id=' + rowsData[0].id;
    createwindow(title, url, width, height);
}

/**
 * 如果页面是详细查看页面，无效化所有表单元素，只能进行查看
 */
$(function() {
    if (location.href.indexOf("load=detail") != -1) {
        $(":input").attr("disabled", "true");
        // $(":input").attr("style","border:0;border-bottom:1 solid
        // black;background:white;");
    }
});

/**
 * 查看详细事件打开窗口
 * 
 * @param title
 *            查看框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
function detail(title, url, id, width, height) {
    var rowsData = $('#' + id).datagrid('getSelections');
    // if (rowData.id == '') {
    // tip('请选择查看项目');
    // return;
    // }

    if (!rowsData || rowsData.length == 0) {
        tip('请选择查看项目');
        return;
    }
    if (rowsData.length > 1) {
        tip('请选择一条记录再查看');
        return;
    }
    url += '&load=detail&id=' + rowsData[0].id;
    createdetailwindow(title, url, width, height);
}

/**
 * 多记录刪除請求
 * 
 * @param title
 * @param url
 * @param gname
 * @return
 */
function deleteALLSelect(title, url, gname) {
    gridname = gname;
    var ids = [];
    var rows = $("#" + gname).datagrid('getSelections');
    if (rows.length > 0) {
        getSafeJq().dialog.confirm("你确定永久删除该数据吗?", function(r) {
            if (r) {
                for ( var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                }
                $.ajax({
                    url : url,
                    type : 'post',
                    data : {
                        ids : ids.join(',')
                    },
                    cache : false,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        var msg = d.msg;
                        tip(msg);
                        if (d.success) {
                            reloadTable();
                            $("#" + gname).datagrid('unselectAll');
                            ids = '';
                        }
                    },
                    error:function () {
                        tip("客户端请求失败");
                    }
                });
            }
        });
    } else {
        tip("请选择需要删除的数据");
    }
}

/**
 * 查看时的弹出窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createdetailwindow(title, addurl, width, height) {
	createwindowExt(title, addurl, width, height, {
		ok: false,
		cancelVal : '关闭',
		cancel : true
	});
}

/**
 * 全屏编辑
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
function editfs(title, url) {
    var name = gridname;
    if (rowid == '') {
        tip('请选择编辑项目');
        return;
    }
    url += '&id=' + rowid;
    openwindow(title, url, name, 800, 500);
}
// 删除调用函数
function delObj(url, name) {
    gridname = name;
    createdialog('删除确认 ', '确定删除该记录吗 ?', url, name);
}
// 删除调用函数
function confuploadify(url, id) {
    getSafeJq().dialog.confirm('确定删除吗', function() {
        deluploadify(url, id);
    }, function() {
    }).zindex();
}
/**
 * 执行删除附件
 * 
 * @param url
 * @param index
 */
function deluploadify(url, id) {
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        url : url,// 请求的action路径
        error : function() {// 请求失败处理函数
        },
        success : function(data) {
            var d = $.parseJSON(data);
            if (d.success) {
                $("#" + id).remove();// 移除SPAN
                m.remove(id);// 移除MAP对象内字符串
            }

        }
    });
}
// 普通询问操作调用函数
function confirm(url, content, name) {
    createdialog('提示信息 ', content, url, name);
}
/**
 * 提示信息
 */
function tip_old(msg) {
    //$.dialog.setting.zIndex = 1980;
    getSafeJq().dialog.tips(msg, 1);
}

/**
 * 提示信息, 在整个屏幕的右下角弹出
 */
function newTip(msg) {
    //$.dialog.setting.zIndex = 1980;
    getSafeJq().messager.show({
        title : '提示信息',
        msg : msg,
        timeout : 1000 * 3
    });
}

/**
 * 提示信息: 在当前的对话框右下角弹出
 * @param msg
 */
function tip(msg) {
	$.messager.show({
        title : '提示信息',
        height:'150',
        width:'350',
        msg : msg,
        timeout : 1000 * 3
    });
}
/**
 * 提示信息像alert一样
 */
function alertTip(msg, title) {
    //$.dialog.setting.zIndex = 1980;
    title = title ? title : "提示信息";
    getSafeJq().dialog({
        title : title,
        icon : 'tips.gif',
        content : msg
    }).zindex();
}
/**
 * 创建添加或编辑窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createwindow(title, addurl, width, height) {
	createwindowExt(title, addurl, width, height, {});
}
/**
 * 创建上传页面窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openuploadwin(title, url, name, width, height, callBackFun) {
	var dialogdata = {
			"callBackFun" : callBackFun
	};
	gridname = name;
    safeShowDialog({
    	data: dialogdata,
        content : 'url:' + url,
        lock : true,
        cache : false,
        button : [ {
            name : "开始上传",
            callback : function() {
                iframe = this.iframe.contentWindow;
                if(typeof iframe.beforeUpload === "function"){
                	iframe.beforeUpload();
                }
                iframe.upload();
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
/**
 * 导入功能跳转
 * @param title
 * @param url
 * @param name
 * @param width
 * @param height
 * @returns
 */
function openuploadwinOther(title, url, name,aftrUploadFun){
	 gridname = name;
	 safeShowDialog({
	     content : 'url:' + url,
	     lock : true,
	     cache : false,
	     button : [ {
	         name : "开始上传",
	         callback : function() {
	             iframe = this.iframe.contentWindow;
	             iframe.upload();
	             if (!(typeof(aftrUploadFun)=="undefined" || aftrUploadFun == '')) {
	            	 iframe.setFunParam(aftrUploadFun);
				 }
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
/**
 * 创建查询页面窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function opensearchdwin(title, url, width, height) {
	safeShowDialog({
        content : 'url:' + url,
        title : title,
        lock : true,
        height : height,
        cache : false,
        width : width,
        opacity : 0.3,
        button : [ {
            name : '查询',
            callback : function() {
                iframe = this.iframe.contentWindow;
                iframe.searchs();
            },
            focus : true
        }, {
            name : '取消',
            callback : function() {

            }
        } ]
    });
}
/**
 * 创建不带按钮的窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openwindow(title, url, name, width, height) {
	gridname = name;
	width = width || 'auto';
	height = height || 'auto';

	safeShowDialog({
		content : 'url:' + url,
		title : title,
		cache : false,
		lock : true,
		width : width,
		height : height
	});
}

/**
 * 创建询问窗口
 * 
 * @param title
 * @param content
 * @param url
 */
function createdialog(title, content, url, name) {
    getSafeJq().dialog.confirm(content, function() {
        doSubmit(url, name);
        rowid = '';
    }, function() {
    }).zindex();
}
/**
 * 执行保存
 * 
 * @param url
 * @param gridname
 */
function saveObj() {
    $('#btn_sub', iframe.document).click();
}

/**
 * 执行AJAX提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxSubForm(url) {
    $('#myform', iframe.document).form('submit', {
        url : url,
        onSubmit : function() {
            iframe.editor.sync();
        },
        success : function(r) {
            tip('操作成功');
            reloadTable();
        }
    });
}
/**
 * 执行查询
 * 
 * @param url
 * @param gridname
 */
function search() {

    $('#btn_sub', iframe.document).click();
    iframe.search();
}

/**
 * 执行操作
 * 
 * @param url
 * @param index
 */
function doSubmit(url, name, data) {
    gridname = name;
    // --author：JueYue
    // ---------date：20140227---------for：把URL转换成POST参数防止URL参数超出范围的问题
    var paramsData = data;
    if (!paramsData) {
        paramsData = new Object();
        if (url.indexOf("&") != -1) {
            var str = url.substr(url.indexOf("&") + 1);
            url = url.substr(0, url.indexOf("&"));
            var strs = str.split("&");
            for ( var i = 0; i < strs.length; i++) {
                paramsData[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
            }
        }
    }
    // --author：JueYue
    // ---------date：20140227---------for：把URL转换成POST参数防止URL参数超出范围的问题
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        data : paramsData,
        url : url,// 请求的action路径
        error : function() {// 请求失败处理函数
        },
        success : function(data) {
            var d = $.parseJSON(data);
            if (d.success) {
                var msg = d.msg;
                tip(msg);
                reloadTable();
            }
        }
    });

}
/**
 * 退出确认框
 * 
 * @param url
 * @param content
 * @param index
 */
function exit(url, content) {
    getSafeJq().dialog.confirm(content, function() {
        window.location = url;
    }, function() {
    }).zindex();
}
/**
 * 模板页面ajax提交
 * 
 * @param url
 * @param gridname
 */
function ajaxdoSub(url, formname) {
    $('#' + formname).form('submit', {
        url : url,
        onSubmit : function() {
            editor.sync();
        },
        success : function(r) {
            tip('操作成功');
        }
    });
}

/**
 * ajax提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxdoForm(url, formname) {
    $('#' + formname).form('submit', {
        url : url,
        onSubmit : function() {
        },
        success : function(r) {
            tip('操作成功');
        }
    });
}

/**
 * 打开一个子窗体. (预期子窗体中包含一个t:formValid标签)
 * @param title
 * @param url
 * @param okbutton
 * @param closebutton
 */
function opensinglewin(title, id, url, width,height) {
	var myOptions = {
            content : 'url:' + url,
            lock : true,
            width : width,
            height : height,
            title : title,
            opacity : 0.3,
            cache : false,
            cancelVal : '确定',
            cancel : function(){
            	$("#"+id).datagrid('reload');
            }
    };
    safeShowDialog(myOptions);
}

function opensubwin(title, url, saveurl, okbutton, closebutton) {
    safeShowDialog({
        content : 'url:' + url,
        title : title,
        lock : true,
        opacity : 0.3,
        button : [ {
            name : okbutton,
            callback : function() {
                iframe = this.iframe.contentWindow;
                win = frameElement.api.opener;// 来源页面
                $('#btn_sub', iframe.document).click();
                return false;
            }
        }, {
            name : closebutton,
            callback : function() {
            }
        } ]

    });
}

function openauditwin(title, url, saveurl, okbutton, backbutton, closebutton) {
    safeShowDialog({
        content : 'url:' + url,
        title : title,
        lock : true,
        opacity : 0.3,
        button : [ {
            name : okbutton,
            callback : function() {
                iframe = this.iframe.contentWindow;
                win = $.dialog.open.origin;// 来源页面
                $('#btn_sub', iframe.document).click();
                return false;
            }
        }, {
            name : backbutton,
            callback : function() {
                iframe = this.iframe.contentWindow;
                win = frameElement.api.opener;// 来源页面
                $('#formobj', iframe.document).form('submit', {
                    url : saveurl + "&code=exit",
                    onSubmit : function() {
                        $('#code').val('exit');
                    },
                    success : function(r) {
                        $.dialog.tips('操作成功', 2);
                        win.location.reload();
                    }
                });

            }
        }, {
            name : closebutton,
            callback : function() {
            }
        } ]

    });
}
/* 获取Cookie值 */
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1)
                c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}
// 添加标签
function addOneTab(subtitle, url, icon) {
    var indexStyle = getCookie("EISPINDEXSTYLE");
    if (indexStyle == 'sliding' || indexStyle == 'bootstrap') {
        // shortcut和bootstrap风格的tab跳转改为直接跳转
        window.location.href = url;
    } else {
        if (icon == '') {
            icon = 'icon folder';
        }
        window.top.$.messager.progress({
            text : '页面加载中....',
            interval : 300
        });
        window.top.$('#maintabs').tabs({
            onClose : function(subtitle, index) {
                window.top.$.messager.progress('close');
            }
        });
        if (window.top.$('#maintabs').tabs('exists', subtitle)) {
            window.top.$('#maintabs').tabs('select', subtitle);
            if (url.indexOf('isHref') != -1) {
                window.top.$('#maintabs').tabs('update', {
                    tab : window.top.$('#maintabs').tabs('getSelected'),
                    options : {
                        title : subtitle,
                        href : url,
                        // content : '<iframe src="' + url + '" frameborder="0"
                        // style="border:0;width:100%;height:99.4%;"></iframe>',
                        closable : true,
                        icon : icon
                    }
                });
            } else {
                window.top.$('#maintabs').tabs(
                        'update',
                        {
                            tab : window.top.$('#maintabs').tabs('getSelected'),
                            options : {
                                title : subtitle,
                                content : '<iframe src="' + url
                                        + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
                                // content : '<iframe src="' + url + '"
                                // frameborder="0"
                                // style="border:0;width:100%;height:99.4%;"></iframe>',
                                closable : true,
                                icon : icon
                            }
                        });
            }
        } else {
            if (url.indexOf('isHref') != -1) {
                window.top.$('#maintabs').tabs('add', {
                    title : subtitle,
                    href : url,
                    closable : true,
                    icon : icon
                });
            } else {
                window.top.$('#maintabs').tabs(
                        'add',
                        {
                            title : subtitle,
                            content : '<iframe src="' + url
                                    + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
                            closable : true,
                            icon : icon
                        });
            }
        }
    }
}
// 关闭自身TAB刷新父TABgrid
function closetab(title) {
    // 暂时先不刷新
    // window.top.document.getElementById('tabiframe').contentWindow.reloadTable();
    // window.top.document.getElementById('maintabs').contentWindow.reloadTable();
    window.top.$('#maintabs').tabs('close', title);
    // tip("添加成功");
}

// popup
// object: this name:需要选择的列表的字段 code:动态报表的code
function inputClick(obj, name, code) {
    $.dialog.setting.zIndex = 2000;
    if (name == "" || code == "") {
        alert("popup参数配置不全");
        return;
    }
    safeShowDialog({
            content : "url:cgReportController.do?popup&id=" + code,
            lock : true,
            title : "选择",
            width : 800,
            height : 400,
            cache : false,
            ok : function() {
                iframe = this.iframe.contentWindow;
                var selected = iframe.getSelectRows();
                if (selected == '' || selected == null) {
                    alert("请选择");
                    return false;
                } else {
                    var str = "";
                    $.each(selected, function(i, n) {
                        if (i == 0)
                            str += n[name];
                        else
                            str += "," + n[name];
                    });
                    $(obj).val("");
                    // $('#myText').searchbox('setValue', str);
                    $(obj).val(str);
                    return true;
                }

            },
            cancelVal : '关闭',
            cancel : true
        /* 为true等价于function(){} */
    });
}

function inputClickCheckbox(obj, name, code) {
    $.dialog.setting.zIndex = 2000;
    if (name == "" || code == "") {
        alert("popup参数配置不全");
        return;
    }
    safeShowDialog({
            content : "url:cgReportController.do?popup&id=" + code,
            lock : true,
            title : "选择",
            width : 800,
            height : 400,
            cache : false,
            ok : function() {
                iframe = this.iframe.contentWindow;
                var selected = iframe.getSelectRows();
                if (selected == '' || selected == null) {
                    alert("请选择");
                    return false;
                } else {
                    var str = "";
                    var find = "td[field=\"" + name + "\"]";
                    $(iframe.document).find('input[type="checkbox"][name="ck"]:checked').each(function(i) {
                        var val = $(this).parent().parent().parent().find(find).text();
                        if (i == 0) {
                            str += val;
                        } else {
                            str += "," + val;
                        }
                    });

                    $(obj).val(str);
                    return true;
                }

            },
            cancelVal : '关闭',
            cancel : true
        /* 为true等价于function(){} */
    });
}

/*
 * 自定义url的弹出 obj:要填充的控件,可以为多个，以逗号分隔 name:列表中对应的字段,可以为多个，以逗号分隔（与obj要对应）
 * url：弹出页面的Url
 */
function popClick(obj, name, url) {
//    $.dialog.setting.zIndex = 2001;
    var names = name.split(",");
    var objs = obj.split(",");
    safeShowDialog({
            content : "url:" + url,
            lock : true,
            title : "选择",
            width : 700,
            height : 400,
            cache : false,
            ok : function() {
                iframe = this.iframe.contentWindow;
                var selected = iframe.getSelectRows();
                if (selected == '' || selected == null) {
                    alert("请选择");
                    return false;
                } else {
                    for ( var i1 = 0; i1 < names.length; i1++) {
                        var str = "";
                        $.each(selected, function(i, n) {
                            if (i == 0)
                                str += n[names[i1]];
                            else {
                                str += ",";
                                str += n[names[i1]];
                            }
                        });
                        if ($("#" + objs[i1]).length >= 1) {
                            $("#" + objs[i1]).val("");
                            $("#" + objs[i1]).val(str);
                        } else {
                            $("input[name='" + objs[i1] + "']").val("");
                            $("input[name='" + objs[i1] + "']").val(str);
                        }
                    }
                    return true;
                }

            },
            cancelVal : '关闭',
            cancel : true
        /* 为true等价于function(){} */
        });
}

/*
 * 自定义弹出列表 target: 要填充的控件ID，可以多个，以逗号分隔，与name要一一对应 src:列表中对应的字段,可以为多个，以逗号分隔
 * url：弹出页面的Url
 */
function popGridZJH(title, url, width, height) {
    //$.dialog.setting.zIndex = 2009;
	safeShowDialog({
            content : "url:" + url,
            lock : true,
            title : title,
            width : width,
            height : height,
            cache : false,
            ok : function() {
                iframe = this.iframe.contentWindow;
                saveObj();
                return false;
            },
            cancelVal : '关闭',
            cancel : true
        });
}

/*
 * 自定义弹出列表 target: 要填充的控件ID，可以多个，以逗号分隔，与name要一一对应 src:列表中对应的字段,可以为多个，以逗号分隔
 * url：弹出页面的Url
 */
function popGrid(target, src, url, width, height) {
//    $.dialog.setting.zIndex = 2009;
	safeShowDialog({
            content : "url:" + url,
            lock : true,
            title : "选择",
            width : width,
            height : height,
            cache : false,
            init : function() {
                var hiddenField = '<input type="hidden" id="targetField" name="targetField" value="' + target
                        + '">' + '<input type="hidden" id="srcField" name="srcField" value="' + src + '">';
                iframe = this.iframe.contentWindow;
                iframe.$("body").append(hiddenField);
            }
        });
}

/*
 * 自定义弹出树 name:列表中对应的字段,可以为多个，以逗号分隔 url：弹出页面的Url
 */
function popTree(name, url, width, height) {
//    $.dialog.setting.zIndex = 2001;
	safeShowDialog({
            content : "url:" + url,
            lock : true,
            title : "选择",
            width : width,
            height : height,
            cache : false,
            init : function() {
                var hiddenField = '<input type="hidden" id="targetField" name="targetField" value="' + name + '">';
                iframe = this.iframe.contentWindow;
                iframe.$("body").append(hiddenField);
            }
        });
}

/**
 * eisp Excel 导出 代入查询条件
 */
function EispExcelExport(url, datagridId) {
    var queryParams = $('#' + datagridId).datagrid('options').queryParams;
    $('#' + datagridId + 'tb').find('*').each(function() {
        queryParams[$(this).attr('name')] = $(this).val();
    });
    var params = '&';
    $.each(queryParams, function(key, val) {
        params += '&' + key + '=' + val;
    });
    var fields = '&field=';
    $.each($('#' + datagridId).datagrid('options').columns[0], function(i, val) {
        if (val.field != 'opt') {
            fields += val.field + ',';
        }
    });
    window.location.href = url + encodeURI(fields + params);
}
/**
 * 自动完成的解析函数
 * 
 * @param data
 * @returns {Array}
 */
function eispAutoParse(data) {
    var parsed = [];
    $.each(data.rows, function(index, row) {
        parsed.push({
            data : row,
            result : row,
            value : row.id
        });
    });
    return parsed;
}
/**
 * 
 * @param tips提示信息
 * @param url
 *            请求地址
 * @param params
 *            请求参数
 * @param callbackfunc
 *            callback函数设置
 * @param async
 *            是否同步
 * @param timout
 *            超时设置
 */
function ajaxRequest(jsonconfig) {
    var tips = jsonconfig.tips;
    var param = jsonconfig.data;
    if (tips == "" || tips == null) {
        tips = "确认执行?";
    }
    var timout = jsonconfig.timout;
    if (timout == null || timout == "") {
        timout = 9000;
    }
    getSafeJq().dialog.confirm(tips, function() {
        window.top.$.messager.progress({
            text : '操作执行中....',
            interval : 300
        });
        $.ajax({
            type : "post",
            url : jsonconfig.url,
            data : jsonconfig.params || '',
            timeout : timout,
            success : function(r) {
                var callMethod = function(method) {
                    method(r);
                }
                callMethod(jsonconfig.callbackfun);// 提交成功回调
                window.top.$.messager.progress('close');
            },
            error : function() {
                window.top.$.messager.progress('close');
                $.dialog.tips('服务器繁忙，请稍后再试');
            }
        });
    })
}

// 允许指定扩展选项的创建窗体方法
function createwindowExt(title, addurl, width, height, options) {
    width = width ? width : 700;
    height = height ? height : 400;
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
            ok : function() {
                iframe = this.iframe.contentWindow;
                saveObj();
                return false;
            },
            cancelVal : '关闭',
            cancel : true
        /* 为true等价于function(){} */
    };
    $.extend(myOptions, options);
    safeShowDialog(myOptions);
}

/**
 * 提交前弹出提示语
 */
function createwindowExtAct(title, addurl, width, height, options,actId) {
	debugger;
    width = width ? width : 700;
    height = height ? height : 400;
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
            ok : function() {
            	iframe = this.iframe.contentWindow;
            	$.ajax({
	 		         type : "post",
	 		         url : 'xpsActController.do?getActItem&actId='+actId,
	 		         dataType:"json",
	 		         async: false,
	 		         success : function(data) {
	 		        	 if(data.type>0){
	 		        		$.dialog.confirm("分配的门店将重置！你是否要更改?", function(){
	 			                saveObj();
	 			                return true;
	 		            	});
	 		            	return false;
	 		        	 }else{
	 		        		saveObj();
	 		        	 }
	 		         },
	 		         error : function() {
	 		             $.ligerDialog.error('提交失败');
	 		         }
	 		     });
            	return false;
            },
            cancelVal : '关闭',
            cancel : true
    };
    $.extend(myOptions, options);
    safeShowDialog(myOptions);
}

/**
 * 创建无按钮窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createwindownobutton(title, addurl, width, height) {
	createwindowExt(title, addurl, width, height, {ok : false, cancel : false});
}

//创建选择弹窗， 要求打开的页面实现getSelections方法
function createselectwindow(title, addurl, width, height, onSelect) {
	createwindowExt(title, addurl, width, height, {
		ok : function() {
			iframe = this.iframe.contentWindow;
			if (typeof (iframe.getSelections) === 'function') {
				var selected = iframe.getSelections();
				if (typeof (onSelect) === 'function') {
					onSelect(selected);
				}
			}
			this.iframe.api.close();
			return false;
		}
	});
}


//金额数字转中文大写
function Arabia_to_Chinese(Num) {
	debugger;
	for (i = Num.length - 1; i >= 0; i--) {
		Num = Num.replace(",", "")//替换tomoney()中的“,”
		Num = Num.replace(" ", "")//替换tomoney()中的空格
	}
	Num = Num.replace("￥", "")//替换掉可能出现的￥字符
	if (isNaN(Num)) { //验证输入的字符是否为数字
		return;
	}
	//---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
	part = String(Num).split(".");
	newchar = "";
	//小数点前进行转化
	for (i = part[0].length - 1; i >= 0; i--) {
		if (part[0].length > 10) {
			alert("位数过大，无法计算");
			return "";
		} //若数量超过拾亿单位，提示
		tmpnewchar = ""
		perchar = part[0].charAt(i);
		switch (perchar) {
		case "0":
			tmpnewchar = "零" + tmpnewchar;
			break;
		case "1":
			tmpnewchar = "一" + tmpnewchar;
			break;
		case "2":
			tmpnewchar = "二" + tmpnewchar;
			break;
		case "3":
			tmpnewchar = "三" + tmpnewchar;
			break;
		case "4":
			tmpnewchar = "四" + tmpnewchar;
			break;
		case "5":
			tmpnewchar = "五" + tmpnewchar;
			break;
		case "6":
			tmpnewchar = "六" + tmpnewchar;
			break;
		case "7":
			tmpnewchar = "七" + tmpnewchar;
			break;
		case "8":
			tmpnewchar = "八" + tmpnewchar;
			break;
		case "9":
			tmpnewchar = "九" + tmpnewchar;
			break;
		}
		switch (part[0].length - i - 1) {
		case 0:
			tmpnewchar = tmpnewchar + "元";
			break;
		case 1:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "十";
			break;
		case 2:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "百";
			break;
		case 3:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "千";
			break;
		case 4:
			tmpnewchar = tmpnewchar + "万";
			break;
		case 5:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "十";
			break;
		case 6:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "百";
			break;
		case 7:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "千";
			break;
		case 8:
			tmpnewchar = tmpnewchar + "亿";
			break;
		case 9:
			tmpnewchar = tmpnewchar + "十";
			break;
		}
		newchar = tmpnewchar + newchar;
	}
	//小数点之后进行转化
	if (Num.indexOf(".") != -1) {
		if (part[1].length > 2) {
			tip("小数点之后只能保留两位,系统将自动截段");
			part[1] = part[1].substr(0, 2)
		}
		for (i = 0; i < part[1].length; i++) {
			tmpnewchar = ""
			perchar = part[1].charAt(i)
			switch (perchar) {
			case "0":
				tmpnewchar = "零" + tmpnewchar;
				break;
			case "1":
				tmpnewchar = "一" + tmpnewchar;
				break;
			case "2":
				tmpnewchar = "二" + tmpnewchar;
				break;
			case "3":
				tmpnewchar = "三" + tmpnewchar;
				break;
			case "4":
				tmpnewchar = "四" + tmpnewchar;
				break;
			case "5":
				tmpnewchar = "五" + tmpnewchar;
				break;
			case "6":
				tmpnewchar = "六" + tmpnewchar;
				break;
			case "7":
				tmpnewchar = "七 " + tmpnewchar;
				break;
			case "8":
				tmpnewchar = "八" + tmpnewchar;
				break;
			case "9":
				tmpnewchar = "九" + tmpnewchar;
				break;
			}
			if (i == 0)
				tmpnewchar = tmpnewchar + "角";
			if (i == 1)
				tmpnewchar = tmpnewchar + "分";
			newchar = newchar + tmpnewchar;
		}
	}
	//替换所有无用汉字
	while (newchar.search("零零") != -1)
		newchar = newchar.replace("零零", "零");
	newchar = newchar.replace("零亿", "亿");
	newchar = newchar.replace("亿万", "亿");
	newchar = newchar.replace("零万", "万");
	newchar = newchar.replace("零元", "元");
	newchar = newchar.replace("零角", "");
	newchar = newchar.replace("零分", "");
	if (newchar.charAt(newchar.length - 1) == "元"
			|| newchar.charAt(newchar.length - 1) == "角")
		newchar = newchar + "整"
		//  document.write(newchar);
	return newchar;
}

function EispExcelExportTest(url, datagridId) {
    var queryParams = $('#' + datagridId).datagrid('options').queryParams;
    $('#' + datagridId + 'tb').find('*').each(function() {
        queryParams[$(this).attr('name')] = $(this).val();
    });
    var params = '&';
    $.each(queryParams, function(key, val) {
        params += '&' + key + '=' + val;
    });
    var fields = '&field=';
    $.each($('#' + datagridId).datagrid('options').columns[0], function(i, val) {
        if (val.field != 'opt') {
            fields += val.field + ',';
        }
    });
    var paramsa = '&paramsName=';
    $.each(queryParams, function(key, val) {
        paramsa += key + ",";
    });
    window.location.href = url + encodeURI(fields + params + paramsa);
}

function selectGetValueToSetname(sourceId,targetId){
	$("#"+sourceId).change(function(){
		$('#'+targetId).val($(this).find("option:selected").text());
	});
}


function addTab(subtitle, url, icon){
	var progress = $("div.messager-progress");
    if(progress.length){return;}
    rowid="";
    
    var maintabsId = parent.$('#maintabs');
    if (!maintabsId.tabs('exists', subtitle)) {
        //判断是否进行href方式打开tab，默认为iframe方式
        if(url.indexOf('isHref') != -1){
            maintabsId.tabs('add', {
                title : subtitle,
                href : url,
                closable : true,
                icon : icon
            }); 
        }else{
            maintabsId.tabs('add', {
                title : subtitle,
                content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
                closable : true,
                icon : icon
            });     
        }
    } else {
        maintabsId.tabs('select', subtitle);
        $.messager.progress('close');
    }
    parent.tabClose();
}

//公用验证form表单
var ValidformCallbackFun = "";
function pubValidForm(para){
	//$.Tipmsg.r=null;
	ValidformCallbackFun = para["callback"]||"";
	validfailback = para["validfailback"]||"";
	$("#"+para['formId']+"").Validform({
		tiptype:function(msg){
			if (msg!="通过信息验证！"){
				if(validfailback != "") eval(validfailback.replace(/MSG/g,"\'"+msg+"\'"));
				else tip(msg);
			}
		},
		tipSweep:true,
		ajaxPost:true,
		beforeSubmit:function(){
			if(ValidformCallbackFun!=""){
				eval(ValidformCallbackFun);
			}
			return false;
		},
		callback:"",
		datatype:{
			"float":function(gets,obj,curform,regxp){
				var num = typeof(obj.attr("datatype-float-num"))=="undefined"?2:Number(obj.attr("datatype-float-num"));
				var negative = (typeof(obj.attr("datatype-float-negative"))=="undefined"||obj.attr("datatype-float-negative")!="y"?"":"(|-)");
				var patt1=new RegExp("^"+negative+"([1-9][\\d]{0,9}|0)(\\.[\\d]{1,"+num+"})?$");
				if (patt1.exec(gets) != null && patt1.exec(gets)[0] == gets)
					return true;
				else 
					return false;
			},
			"float2":function(gets,obj,curform,regxp){
				var num = typeof(obj.attr("datatype-float-num"))=="undefined"?2:Number(obj.attr("datatype-float-num"));
				var negative = (typeof(obj.attr("datatype-float-negative"))=="undefined"||obj.attr("datatype-float-negative")!="n"?"(|-)":"");
				var patt1=new RegExp("^"+negative+"([1-9][\\d]{0,9}|0)(\\.[\\d]{1,"+num+"})?$");
				if (patt1.exec(gets) != null && patt1.exec(gets)[0] == gets)
					return true;
				else 
					return false;
			}
		}
	});
}
//新增多个TAB
function addTabMore(subtitle, url, icon) {
	var progress = $("div.messager-progress");
	//if(progress.length){return;}
	rowid="";
	$.messager.progress({
		text : loading,
		interval : 200
	});
	if (!$('#maintabs').tabs('exists', subtitle)) {
		//判断是否进行href方式打开tab，默认为iframe方式
		if(url.indexOf('isHref') != -1){
			$('#maintabs').tabs('add', {
				title : subtitle,
				href : url,
				closable : true,
				icon : icon
			});	
		}else{
			
			$('#maintabs').tabs('add', {
				title : subtitle,
				content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				closable : true,
				icon : icon
			});		
			
		}

	} else {
		$('#maintabs').tabs('select', subtitle);
		$.messager.progress('close');
	}

	// $('#maintabs').tabs('select',subtitle);
	tabClose();

}

function importDataByXml(cfg) {
	if(!cfg.impName) {
		alert("未配置impName参数！");
		return;
	}
	gridname=cfg.gridName;
	$.dialog({
		content:"url:importController.do?importData&impName=" + cfg.impName,
	    lock : true,
	    title: "导入",
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
		            name: "测试导入",
		            callback: function(){
		            	iframe = this.iframe.contentWindow;
						iframe.testUpload();
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


//获取 任意 日期
function GetDateStr(AddYear,AddMonth,AddDay) {
	AddYear?AddYear:AddYear=0;
	AddMonth?AddMonth:AddMonth=0;
	AddDay?AddDay:AddDay=0;
  var dd = new Date();
  dd.setFullYear(dd.getFullYear()+AddYear);
  dd.setMonth(dd.getMonth()+AddMonth);
  dd.setDate(dd.getDate()+AddDay);
  var y = dd.getFullYear();
  var m = dd.getMonth()+1;
  var d = dd.getDate();
		m=checkTime(m);
		d=checkTime(d);
  return y+"-"+m+"-"+d;
}
function checkTime(i)
{
if (i<10) 
{i="0" + i}
return i
}
/**
 * 避免NaN
 * @param value
 * @returns {Number}
 */
function numberIsNull(value){
	if (value == '') {
		value = 0;
	}
	return value;
}
//附件上传跳转
function addAttachments(width,height,otherId,isLook,aftrUploadFun,isTip){
	if (otherId == '') {
		tip('无关键id');
		return ;
	}
	width = width ? width : 700;
	height = height ? height : 400;
    if (width == "100%") {
        width = window.top.document.body.offsetWidth;
    }
    if (height == "100%") {
        height = window.top.document.body.offsetHeight - 100;
    }
    var myOptions = {};
    //解决多个tab无父页面
    if (isLook == '2') {
    	myOptions = {
            content : 'url:systemController.do?uploadFiles&otherId=' + otherId + '&isLook=' + isLook,
            lock : true,
            width : width,
            height : height,
            title : '上传附件',
            opacity : 0.3,
            cache : false,
            cancelVal : '关闭',
            cancel : true,/* 为true等价于function(){} */
            zIndex:999999
        };
	}else if(isLook == '1'){
    	myOptions = {
            content : 'url:systemController.do?uploadFiles&otherId=' + otherId + '&isLook=' + isLook,
            lock : true,
            width : width,
            height : height,
            title : '上传附件',
            opacity : 0.3,
            cache : false,
            cancelVal : '关闭',
            cancel : true
            /* 为true等价于function(){} */
        };
    }else{
    	myOptions = {
            content : 'url:systemController.do?uploadFiles&otherId=' + otherId + '&isLook=' + isLook+'&isTip='+isTip,
            lock : true,
            width : width,
            height : height,
            title : '上传附件',
            opacity : 0.3,
            cache : false,
            ok : function() {
                iframe = this.iframe.contentWindow;
                iframe.uploadFile();
                //saveObj();
                if (!(typeof(aftrUploadFun)=="undefined" || aftrUploadFun == '')) {
                    debugger;
                    iframe.setFunParam(aftrUploadFun);
                }
                return false;
            },
            cancelVal : '关闭',
            cancel : true
            /* 为true等价于function(){} */
        };
    }
    $.extend(myOptions, {});
    safeShowDialog(myOptions);
}

//----------------------------动态进度条----------------------------//
var interval = null; //定时器

function dynamicProgress(functionKey) {
	$.messager.progress({interval:0});
	interval = setInterval("getProgressInfo('"+functionKey+"')", 1000); //启动定时器，每1秒执行showImportInfo方法一次
}

//显示导入详情
function getProgressInfo(functionKey){
	var param = {};
	if(functionKey != 'undefined') {
		param.functionKey = functionKey;
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		data : param,
		url:"progressController.do?getProgress",// 请求的action路径
		error : function() {// 请求失败处理函数
			
		},
		success : function(data) {
			var result = $.parseJSON(data);
			if(result.obj != null) {
				$($.messager.progress("bar")).progressbar('setValue', ((result.obj.current/result.obj.total) * 100).toFixed(2)); 
	    	} else {
	    		window.clearInterval(interval);
	    	}
		}
	});
}

function closeDynamicProgress() {
	window.clearInterval(interval);
	$.messager.progress("close");
}
/*------------提交弹出框star------------------*/
//点击提交时弹出框
function popupBoxSubmit(dataArr){
	if(!checkIsCouldContinue(dataArr)){
		return ;
	}
	var dataTemp = "&type=" + dataArr.type + "&clazz=" + dataArr.clazz + "&srcNum=" + dataArr.srcNum;
	var myOptions = {
	    content : "url:activitiController.do?submitApplication&otherId=" + dataArr.otherId + "&type=" + dataArr.type,
	    lock : true,
	    width : 600,
	    height : 350,
	    title : "提交申请",
	    opacity : 0.3,
	    cache : true,
	    async: false,
	    init : function() {
	        iframe = this.iframe.contentWindow;
	      	//传递参数
			iframe.setParams(dataArr.starSubmitFun,dataTemp);
	        return false;
	     	} ,
	    button: [
		        {
		            name: "提交",
		            callback: function(){
		            	var iframe = this.iframe.contentWindow;
					iframe.uploadFile("");
						return false;
		            }
		        }
		]
	};
	safeShowDialog(myOptions);
}
function checkIsCouldContinue(dataArr){
	if(typeof(dataArr.type)=="undefined" || dataArr.type == ''){
		tip("请设置提交申请的来源类型");
		return false;
	}
	if(typeof(dataArr.clazz)=="undefined" || dataArr.clazz == ''){
		tip("请设置提交申请的来源entity类");
		return false;
	}
	if(typeof(dataArr.starSubmitFun)=="undefined" || dataArr.starSubmitFun == ''){
		tip("请设置提交方法");
		return false;
	}
	return true;
}
/*---------------提交弹出框end-------------------------*/
package org.crmframework.core.tag.easyui;

import org.crmframework.core.util.StringUtil;
import org.crmframework.core.util.oConvertUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 
 * @author Biz
 *
 */
public class BaseTag extends TagSupport {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    protected String type = "default";// 加载类型

    public static String jsVersion;
    
    static {
    	jsVersion = ResourceBundle.getBundle("connection/sysConfig").getString("jsVersion");
    	if (StringUtil.isEmpty(jsVersion)) {
    		jsVersion = "1";
    	}
    }
    
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            StringBuffer sb = new StringBuffer();

            String types[] = type.split(",");

            // 插入多语言脚本
            String lang = (String) ((HttpServletRequest) this.pageContext.getRequest()).getSession().getAttribute(
                            "lang");
            if(StringUtil.isEmpty(lang)){
            	lang = "zh-cn";
            }
            String langjs = StringUtil.replace(
                            "<script type=\"text/javascript\" src=\"plug-in/mutiLang/{0}.js?v=" + jsVersion + "\"></script>", "{0}", lang);
            sb.append(langjs);

            // update-begin--Author:Biz Date:20140521 for：云桌面图标拖拽、用户自定义桌面
            if (oConvertUtils.isIn("jquery-webos", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/sliding/js/jquery-1.7.1.min.js?v=" + jsVersion + "\"></script>");
            } else if (oConvertUtils.isIn("jquery", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js?v=" + jsVersion + "\"></script>");
            }
            // update-end--Author:Biz Date:20140521 for：云桌面图标拖拽、用户自定义桌面
            if (oConvertUtils.isIn("ckeditor", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/ckeditor/ckeditor.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/ckeditorTool.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("ckfinder", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/ckfinder/ckfinder.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/ckfinderTool.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("easyui", types)) {
            	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/newLeftStyle/css/fenster.css?v=" + jsVersion + "\">");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js?v=" + jsVersion + "\"></script>");
                sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/default/easyui.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css?v=" + jsVersion + "\">");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/extends/datagrid-scrollview.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/extends/datagrid-dnd.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("DatePicker", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("jqueryui", types)) {
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/jquery-ui-1.9.2.custom.min.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("jqueryui-sortable", types)) {
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.core.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.widget.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.mouse.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.sortable.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("prohibit", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/prohibitutil.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("designer", types)) {
                sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/jquery-1.7.2.min.js?v=" + jsVersion + "\"></script>");
                sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/designer/easyui/easyui.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/designer/easyui/icon.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/jquery.easyui.min.1.3.0.js?v=" + jsVersion + "\"></script>");

                // 加载easyui多语言
                sb.append(StringUtil
                                .replace("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/locale/{0}.js?v=" + jsVersion + "\"></script>",
                                                "{0}", lang));

                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\'text/javascript\' src=\'plug-in/jquery/jquery-autocomplete/lib/jquery.bgiframe.min.js\'></script>");
                sb.append("<script type=\'text/javascript\' src=\'plug-in/jquery/jquery-autocomplete/lib/jquery.ajaxQueue.js\'></script>");
                sb.append("<script type=\'text/javascript\' src=\'plug-in/jquery/jquery-autocomplete/jquery.autocomplete.min.js\'></script>");
                sb.append("<link href=\"plug-in/designer/designer.css?v=" + jsVersion + "\" type=\"text/css\" rel=\"stylesheet\" />");
                sb.append("<script src=\"plug-in/designer/draw2d/wz_jsgraphics.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\'plug-in/designer/draw2d/mootools.js\'></script>");
                sb.append("<script src=\'plug-in/designer/draw2d/moocanvas.js\'></script>");
                sb.append("<script src=\'plug-in/designer/draw2d/draw2d.js\'></script>");
                sb.append("<script src=\"plug-in/designer/MyCanvas.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/ResizeImage.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/event/Start.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/event/End.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/connection/MyInputPort.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/connection/MyOutputPort.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/connection/DecoratedConnection.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/Task.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/UserTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/ManualTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/ServiceTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/gateway/ExclusiveGateway.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/gateway/ParallelGateway.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/boundaryevent/TimerBoundary.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/boundaryevent/ErrorBoundary.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/subprocess/CallActivity.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/ScriptTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/MailTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/ReceiveTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/task/BusinessRuleTask.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/designer.js?v=" + jsVersion + "\"></script>");
                sb.append("<script src=\"plug-in/designer/mydesigner.js?v=" + jsVersion + "\"></script>");

            }
            if (oConvertUtils.isIn("tools", types)) {
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/common.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js?v=" + jsVersion + "\"></script>");

                sb.append(StringUtil.replace(
                                "<script type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js?v=" + jsVersion + "\"></script>",
                                "{0}", lang));

                sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js?v=" + jsVersion + "\"></script>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-plugs/hftable/jquery-hftable.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("toptip", types)) {
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/toptip/css/css.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/toptip/manhua_msgTips.js?v=" + jsVersion + "\"></script>");
            }
            if (oConvertUtils.isIn("autocomplete", types)) {
                sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery/jquery-autocomplete/jquery.autocomplete.css?v=" + jsVersion + "\" type=\"text/css\"></link>");
                sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-autocomplete/jquery.autocomplete.min.js?v=" + jsVersion + "\"></script>");
            }
            out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}

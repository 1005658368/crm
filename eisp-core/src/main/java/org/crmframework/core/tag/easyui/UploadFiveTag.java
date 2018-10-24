package org.crmframework.core.tag.easyui;

import org.crmframework.core.util.StringUtil;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 
 * 类描述：上传标签
 * 
 * Biz
 * 
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class UploadFiveTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    protected String uploader;// url
    protected String name;// 控件名称
    protected String formData;// 参数名称
    protected String extend = "";// 上传文件的扩展名
    protected String buttonText = "浏览";// 按钮文本
    protected boolean multi = true;// 是否多文件
    protected String queueID = "filediv";// 文件容器ID
    protected boolean dialog = true;// 是否是弹出窗口模式
    protected String callback;
    protected boolean auto = false;// 是否自动上传
    protected String onUploadSuccess;// 上传成功处理函数
    protected boolean view = false;// 生成查看删除链接
    protected boolean isTip = true;//是否需要提示
    protected boolean isReloadTabLe = true;//是否刷新主界面的数据
    protected boolean isAutoClose = true;//是否自动关闭
    protected boolean tipInParentPage = true;//tip提示在父页面    
    
    private boolean changeStringValueToboolean(String value){
    	if (value != null && !value.equals("")) {
    		if (value.equals("false")) {
    			return false;				
			}
		}
    	return true;
    }
    public void setTipInParentPage(String tipInParentPage) {
    	this.tipInParentPage = changeStringValueToboolean(tipInParentPage);
    }
    public void setIsAutoClose(String isAutoClose) {
    	this.isAutoClose = changeStringValueToboolean(isAutoClose);
    }
    public void setIsReloadTabLe(String isReloadTabLe) {
    	this.isReloadTabLe = changeStringValueToboolean(isReloadTabLe);
    }
    public void setIsTip(String isTip) {
		this.isTip = changeStringValueToboolean(isTip);				
    }
    public void setView(boolean view) {
        this.view = view;
    }

    public void setOnUploadSuccess(String onUploadSuccess) {
        this.onUploadSuccess = onUploadSuccess;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public void setDialog(boolean dialog) {
        this.dialog = dialog;
    }

    public void setQueueID(String queueID) {
        this.queueID = queueID;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        StringBuffer sb = new StringBuffer();
        if ("pic".equals(extend)) {
            extend = "*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
        }
        if (extend.equals("office")) {
            extend = "*.doc;*.docx;*.txt;*.ppt;*.pptx;*.xls;*.xlsx;*.html;*.htm";
        }
        if("picOffice".equals(extend)){
        	 extend = "*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.ico;*.tif;*.doc;*.docx;*.txt;*.ppt;*.pptx;*.xls;*.xlsx;*.html;*.htm";
        }
        if("picOfficeZip".equals(extend)){
       	 extend = "*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.ico;*.tif;*.doc;*.docx;*.txt;*.ppt;*.pptx;*.xls;*.xlsx;*.html;*.htm;*.rar;*.zip;*.tar;*.jar;*.iso;*.z;*.arj;*.gzip";
       }
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadifive/uploadifive.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadifive/jquery.uploadifive.js?v=2\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>");
        sb.append("<script type=\"text/javascript\">" + "var flag = false;" + "var fileitem=\"\";"
                + "var fileKey=\"\";" + "var serverMsg=\"\";" + "var m = new Map();" + ""
                + "function initFunUploadifive(){$(\'#"
                + id
                + "\').uploadifive({"
                + "buttonText:\'"
                + buttonText
                + "\',"
                + "auto:"
                + auto
                + ","
                + "progressData:\'speed\',"
                + "multi:"
                + multi
                + ","
                + "height:25,"
                + "overrideEvents:[\'onDialogClose\'],"
                + "fileTypeDesc:\'文件格式:\',"
                + "queueID:\'"
                + queueID
                + "\',"
                + "fileType:\'"
                + extend
                + "\',"
                + "fileSizeLimit:\'30MB\',"
                + "uploadScript:\'"
                + getUploader());
        if (formData != null) {
        	String formDataTemp = formData;
        	formDataTemp = formDataTemp.replace("{", "").replace("}", "");
        	for (String str : formDataTemp.split(",")) {
        		if (StringUtil.isNotEmpty(str)) {
        			String[] tempStrs = str.split(":");
        			if (null!=tempStrs && tempStrs.length > 1) {
        				String keyStr = tempStrs[0];
        				if ("otherId".equals(keyStr)) {
        					String valStr = tempStrs[1];
        					if (StringUtil.isNotEmpty(valStr) && !"undefined".equals(valStr) && !"null".equals(valStr) && !"''".equals(valStr)) {
        						break;
							}else{
								formDataTemp = "$(\'#" + keyStr + "\').val()";
								formData = formData.replace("''", formDataTemp);
								break;
							}
        				}
					}
				}
			}
            sb.append("formData:" + formData);
        };
        sb.append(" ," + "onQueueComplete : function(queueData) { ");
        if (dialog) {
        	//if(typeof(frameElement.api.opener.reloadTable) != 'undefined') frameElement.api.opener.reloadTable();
            sb.append("var win = frameElement.api.opener;");
            if (isReloadTabLe) {
            	sb.append("if(typeof(frameElement.api.opener.reloadTable) != 'undefined')win.reloadTable();");
			}
            if (isTip) {
            	if (tipInParentPage) {
            		sb.append("win.tip(serverMsg);");
				}else{
					sb.append("tip(serverMsg);");
				}
			}
            if (isAutoClose) {
            	sb.append("frameElement.api.close();");
			}
        } else {
            if (callback != null)
                sb.append("" + callback + "();");
        }
        if (view) {
            sb.append("$(\"#viewmsg\").html(m.toString());");
            sb.append("$(\"#fileKey\").val(fileKey);");
        }
        sb.append("},");
        sb.append("successTimeout : 600,");
        // 上传成功处理函数
        sb.append("onUploadComplete : function(file, data, response) {");
        sb.append("var d=$.parseJSON(data);");
        sb.append("serverMsg = d.msg;");
        if (view) {
            sb.append("var fileitem=\"<span id=\'\"+d.attributes.id+\"\'><a href=\'#\' onclick=openwindow(\'文件查看\',\'\"+d.attributes.viewhref+\"\',\'70%\',\'80%\') title=\'查看\'>\"+d.attributes.name+\"</a><img border=\'0\' onclick=confuploadify(\'\"+d.attributes.delurl+\"\',\'\"+d.attributes.id+\"\') title=\'删除\' src=\'plug-in/uploadifive/img/uploadify-cancel.png\' widht=\'15\' height=\'15\'>&nbsp;&nbsp;</span>\";");
            sb.append("m.put(d.attributes.id,fileitem);");
            sb.append("fileKey=d.attributes.fileKey;");
        }
        if (onUploadSuccess != null) {
            //sb.append("alert(serverMsg);");
            sb.append(onUploadSuccess + "(d,file,response);");
        }else{
            sb.append("if(d.success){");
            sb.append("var win = frameElement.api.opener;");
            if (isTip) {
            	if (tipInParentPage) {
//               	 sb.append("win.tip(d.msg);");// 上传提示
				}else{
//					sb.append("tip(d.msg);");
				}
			}
            sb.append("}");
        }
        sb.append("},");
        sb.append("onError : function(errorType){");
        sb.append("tip(errorType);");
        sb.append("},"
                + "onProgress : function(file, e) { "
                // +"tip('<span>文件上传成功:'+totalBytesUploaded/1024 + ' KB 已上传 ,总数' + totalBytesTotal/1024 + ' KB.</span>');"
                + "}" + "});" + "} $(function(){initFunUploadifive();});" + "function upload() {" + "	$(\'#" + id + "\').uploadifive('upload');"
                + "		return flag;" + "}" + "function cancel() {" + "$(\'#" + id
                + "\').uploadifive('cancel');" + "}" + "</script>");
        sb.append("<span id=\"" + id + "span\"><input type=\"file\" name=\"" + name + "\" id=\"" + id + "\" /></span>");

        if (view) {
            sb.append("<span id=\"viewmsg\"></span>");
            sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
        }
        
        return sb;
    }

    /**
     * 获取上传路径,修改jsessionid传不到后台的bug jueyue --- 20130916
     * 
     * @return
     */
    private String getUploader() {
        return uploader + "&sessionId=" + pageContext.getSession().getId() + "',";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

}
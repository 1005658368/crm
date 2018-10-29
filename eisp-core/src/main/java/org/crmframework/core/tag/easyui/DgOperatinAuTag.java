package org.crmframework.core.tag.easyui;

import com.crm.crm.base.mdm.entity.TSOperation;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.ApplicationContextUtil;
import org.crmframework.core.util.Globals;
import org.crmframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author BIZ 类描述：列表工具条标签
 */
public class DgOperatinAuTag extends TagSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operationCode;// 按钮的操作Code
	private String classcss;//样式
	private String plain;
	private String icon;
	private String fname;
	private String showname;
	@Autowired
	private static SystemService systemService;
    @Override
    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            StringBuffer sb = new StringBuffer();
            if (!oConvertUtils.isEmpty(operationCode)) {
            	Set<String> soper=new HashSet<String>();
            	installOperationCode(operationCode, soper);
            	if (soper!=null&&soper.size()>0) {//拥有权限
            		sb.append("<a href=\"#\" class="+classcss+" plain="+plain+" icon="+icon+" onclick="+fname+" >"+showname+"</a>");
				}
            }else{
            	sb.append("<a href=\"#\" class="+classcss+" plain="+plain+" icon="+icon+" onclick="+fname+" >"+showname+"</a>");
            }
            out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    
    private void installOperationCode(String operationCode, Set<String> soper) {
       if (!oConvertUtils.isEmpty(operationCode)) {
    	    //获取当前连接下的操作权限按钮
            Set<String> operationCodes = (Set<String>) super.pageContext.getRequest().getAttribute(Globals.OPERATIONCODES);
            if (null != operationCodes) {
                List<String> operationCodesStr = new ArrayList<String>();
                for (String MyoperationCode : operationCodes) {
                    if (oConvertUtils.isEmpty(MyoperationCode))
                        break;
                    systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                    TSOperation operation = systemService.getEntity(TSOperation.class, MyoperationCode);
                    operationCodesStr.add(operation.getOperationcode());
                }
                if (operationCodesStr.contains(operationCode)) {//获取有权限的按钮
                	soper.add(operationCode);
                }
            }
        }
    }
    
	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}


	public String getClasscss() {
		return classcss;
	}

	public void setClasscss(String classcss) {
		this.classcss = classcss;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}
}

package org.crmframework.core.util;

import java.util.HashMap;
import java.util.Map;


public final class Globals implements java.io.Serializable{
    public static Short OPERATION_TYPE_HIDE = 0;// 页面
    /**
     * 没有勾选的操作code
     */
    public static String NOAUTO_OPERATIONCODES = "noauto_operationCodes";
    /**
     * 勾选了的操作code
     */
    public static String OPERATIONCODES = "operationCodes";
    /**
     * 配置系统是否开启按钮权限控制
     */
    public static boolean BUTTON_AUTHORITY_CHECK = true;
    static {
        String button_authority_eisp = ResourceUtil.getSessionattachmenttitle("button.authority.eisp");
        if ("true".equals(button_authority_eisp)) {
            BUTTON_AUTHORITY_CHECK = true;
        }
    }
}

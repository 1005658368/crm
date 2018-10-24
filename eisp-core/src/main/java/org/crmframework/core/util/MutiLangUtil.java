package org.crmframework.core.util;

import com.crm.crm.pojo.entity.MutiLangEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.crmframework.core.service.MutiLangServiceI;
import java.util.List;

/**
 * 字符串处理及转换工具类
 * 
 * @author Biz
 */
public class MutiLangUtil {
    private static Log logger = LogFactory.getLog(MutiLangUtil.class);

    private static MutiLangServiceI mutiLangService;

    /**
     * 通用删除消息方法
     * 
     * @param param_lang_key
     *            如：common.delete.success.param
     * @return XXX删除成功，如多语言删除成功
     */
    public static String paramDelSuccess(String param_lang_key) {
        String message = getMutiLangInstance().getLang("common.delete.success.param", param_lang_key);
        return message;
    }

    // add-begin--Author:Biz Date:20140727 for：通用删除消息方法
    /**
     * 通用删除消息方法
     *
     * @param param_lang_key
     *            如：common.delete.fail.param
     * @return XXX删除失败，如系统图标失败，正在使用的图标，不允许删除。
     */
    public static String paramDelFail(String param_lang_key) {
        String message = getMutiLangInstance().getLang("common.delete.fail.param", param_lang_key);
        return message;
    }

    // add-end--Author:Biz Date:20140727 for：通用删除消息方法

    /**
     * 通用更新成功消息方法
     * 
     * @param param_lang_key
     *            如：common.edit.success.param
     * @return XXX更新成功，如多语言删除成功
     */
    public static String paramUpdSuccess(String param_lang_key) {
        String message = getMutiLangInstance().getLang("common.edit.success.param", param_lang_key);
        return message;
    }

    /**
     * 通用更新失败消息方法
     * 
     * @param param_lang_key
     *            如：common.edit.success.param
     * @return XXX更新失败，如多语言更新失败
     */
    public static String paramUpdFail(String param_lang_key) {
        String message = getMutiLangInstance().getLang("common.edit.fail.param", param_lang_key);
        return message;
    }

    /**
     * 通用添加消息方法
     * 
     * @param param_lang_key
     *            如：common.edit.success.param
     * @return XXX录入成功，如多语言录入成功
     */
    public static String paramAddSuccess(String param_lang_key) {
        String message = getMutiLangInstance().getLang("common.add.success.param", param_lang_key);
        return message;
    }

    /**
     * 通用国际化tree方法
     * 
     * @param treeList
     *            , mutiLangService
     */
    public static void setMutiTree(List<?> treeList) {
        if (CollectionUtils.isEmpty(treeList))
            return;
        ;

        for (Object treeItem : treeList) {
            ReflectHelper reflectHelper = new ReflectHelper(treeItem);
            String lang_key = (String) reflectHelper.getMethodValue("text"); // treeItem.getText();
            String lang_context = getMutiLangInstance().getLang(lang_key);
            reflectHelper.setMethodValue("text", lang_context);
        }
    }

    /**
     * 检查国际化内容或lang_key是否已经存在
     * 
     * @param lang_key
     * @return 如果存在则返回true，否则false
     */
    public static boolean existLangKey(String lang_key) {
        List<MutiLangEntity> langKeyList = getMutiLangInstance().findByProperty(MutiLangEntity.class, "langKey",lang_key);
        if (!langKeyList.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 检查国际化内容或context是否已经存在
     * 
     * @param lang_context
     * @return 如果存在则返回true，否则false
     */
    public static boolean existLangContext(String lang_context) {
        List<MutiLangEntity> langContextList = getMutiLangInstance().findByProperty(MutiLangEntity.class,"langContext", lang_context);
        if (!langContextList.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 通用得到MutiLangService方法
     * 
     * @return mutiLangService实例
     */
    public static MutiLangServiceI getMutiLangInstance() {
        if (mutiLangService == null) {
            mutiLangService = ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
        }
        return mutiLangService;
    }

    public static String doMutiLang(String title, String langArg) {
        String context = getMutiLangInstance().getLang(title, langArg);
        return context;
    }

}

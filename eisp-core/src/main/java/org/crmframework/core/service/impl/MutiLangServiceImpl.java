package org.crmframework.core.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.crmframework.core.entity.MutiLangEntity;
import org.crmframework.core.service.MutiLangServiceI;
import org.crmframework.core.util.BrowserUtils;
import org.crmframework.core.util.ContextHolderUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class MutiLangServiceImpl extends CommonServiceImpl implements MutiLangServiceI {

    /** 初始化语言信息，TOMCAT启动时直接加入到内存中 **/
    @Override
    public void initAllMutiLang() {
        List<MutiLangEntity> mutiLang = this.commonDao.loadAll(MutiLangEntity.class);
        for (MutiLangEntity mutiLangEntity : mutiLang) {
            MutiLangEntity.mutiLangMap.put(mutiLangEntity.getLangKey() + "_" + mutiLangEntity.getLangCode(),mutiLangEntity.getLangContext());
        }
    }

    /** 取 o_muti_lang.lang_key 的值返回当前语言的值 **/
    @Override
    public String getLang(String langKey) {
        // 如果为空，返回空串，防止返回null
        if (langKey == null) {
            return "";
        }
        String language = BrowserUtils.getBrowserLanguage(ContextHolderUtils.getRequest());

//        if (request.getSession().getAttribute("lang") != null) {
//            language = (String) request.getSession().getAttribute("lang");
//        }

        String langContext = MutiLangEntity.mutiLangMap.get(langKey + "_" + language);

        if (StringUtils.isEmpty(langContext)) {
            langContext = MutiLangEntity.mutiLangMap.get("common.notfind.langkey" + "_"+ ContextHolderUtils.getSession().getAttribute("lang"));
            if ("null".equals(langContext) || langContext == null || "?".equals(langContext)) {
                langContext = "";
            }
            langContext = langContext + langKey;
        }
        return langContext;
    }

    @Override
    public String getLang(String lanKey, String langArg) {
        String langContext = "";
        if (StringUtils.isEmpty(langArg)) {
            langContext = getLang(lanKey);
        } else {
            String[] argArray = langArg.split(",");
            langContext = getLang(lanKey);

            for (int i = 0; i < argArray.length; i++) {
                String langKeyArg = argArray[i].trim();
                String langKeyContext = getLang(langKeyArg);
                langContext = StringUtils.replace(langContext, "{" + i + "}", langKeyContext);
            }
        }
        return langContext;
    }

    /** 刷新多语言cach **/
    @Override
    public void refleshMutiLangCach() {
        MutiLangEntity.mutiLangMap.clear();
        initAllMutiLang();
    }

}

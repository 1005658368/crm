package org.crmframework.core.service;

import org.crmframework.core.common.service.CommonService;

public interface MutiLangServiceI extends CommonService {

    public void initAllMutiLang();

    public String getLang(String lang_key);

    public String getLang(String lang_key, String args);

    public void refleshMutiLangCach();

}

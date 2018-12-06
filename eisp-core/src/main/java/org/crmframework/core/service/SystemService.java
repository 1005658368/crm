package org.crmframework.core.service;


import org.crmframework.core.common.service.CommonService;

import java.util.List;

/**
 * 
 * @author Biz
 * 
 */
public interface SystemService extends CommonService {
    void setNoOptCode();

    void initAllTypeGroups();
}

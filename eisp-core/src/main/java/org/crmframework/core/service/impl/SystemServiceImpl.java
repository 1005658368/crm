package org.crmframework.core.service.impl;

import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.crmframework.core.service.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService {

}

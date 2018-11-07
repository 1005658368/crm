package org.crmframework.core.service.impl;

import com.crm.crm.base.mdm.entity.TSOperation;
import com.crm.crm.base.mdm.entity.TSUser;
import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.ClientManager;
import org.crmframework.core.util.ContextHolderUtils;
import org.crmframework.core.util.ResourceUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService {
    @Resource
    MiniDaoFastQueryService miniDaoFastQueryService;

    @Override
    public void setNoOptCode(){
        HttpServletRequest request=ContextHolderUtils.getRequest();
        TSUser user= ClientManager.getClient().getUser();
        String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
        String sql=" " +
                " select o.* from t_s_operation o " +
                " join t_s_function f on f.id=o.functionid " +
                " where 1=1 " +
                " and f.functionurl=:functionUrl " +
                " and o.id not in " +
                " (" +
                " select o.id from t_s_role_function rf " +
                " join t_s_function f on f.id=rf.functionid " +
                " join t_s_role_user ru on ru.roleid=rf.roleid " +
                " join t_s_base_user u on u.id=ru.userid " +
                " join t_s_operation o on rf.operation like '%'||o.id||'%' " +
                " where 1=1 " +
                " and f.functionurl=:functionUrl " +
                " and u.id=:userId " +
                " ) "
                ;
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("functionUrl",requestPath);
        paramMap.put("userId",user.getId());
        List<TSOperation> noauto_operationCodes=miniDaoFastQueryService.queryReturnMinidaoList(sql,paramMap,TSOperation.class);
        request.setAttribute("noauto_operationCodes",noauto_operationCodes);
    }

}

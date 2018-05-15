package org.crmframework.core.aop;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Hiberate拦截器：实现创建人，创建时间，创建人名称自动注入; 修改人,修改时间,修改人名自动注入;
 * 
 * @author Biz
 */
public class HiberAspect extends EmptyInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HiberAspect.class);
    private static final long serialVersionUID = 1L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return true;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,String[] propertyNames, Type[] types) {
        return true;
    }
}

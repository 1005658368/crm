package org.crmframework.core.listener;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/** Hibernate事件编织入Spring容器中.
 * <p>Hibernate事件编织入Spring容器中.<br>
 * @author liukai
 * @version v1.0
 */
@Component
public class HibernateEventWiring {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private LoggerListener loggerListener;

    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
//		registry.setListeners(EventType.POST_COMMIT_INSERT, loggerListener);
//		registry.appendListeners(EventType.POST_COMMIT_INSERT, loggerListener);
		registry.prependListeners(EventType.POST_COMMIT_INSERT, loggerListener);
    }
}

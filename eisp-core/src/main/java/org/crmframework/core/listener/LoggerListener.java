package org.crmframework.core.listener;

import com.crm.crm.base.mdm.entity.BaseEntity;
import org.crmframework.core.util.ResourceUtil;
import org.crmframework.core.util.StringUtil;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.springframework.stereotype.Component;

/**
 * 日志监听器，监听CURD操作
 * <p>
 * 监听Hibernate的Insert，Update，Delete操作。<br>
 * 
 * @author liukai
 * @version v1.0
 */
@Component
public class LoggerListener implements PostInsertEventListener{

	private static final long serialVersionUID = 1L;
	 String nameProperty="";
	/**
	 * 新增日志记录
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object object = event.getEntity();
		BaseEntity baseEntity=(BaseEntity)object;
		System.out.println("hibernate监听器onSave方法的ID:"+baseEntity.getId());
	}

}

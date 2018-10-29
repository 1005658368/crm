package org.crmframework.core.util;

import com.crm.crm.base.mdm.entity.TSUser;


/**
 * 在线用户对象
 * 
 * @author JueYue
 * @date 2013-9-28
 * @version 1.0
 */
public class Client implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private TSUser user;

	public TSUser getUser() {
        return user;
    }

    public void setUser(TSUser user) {
        this.user = user;
    }


}

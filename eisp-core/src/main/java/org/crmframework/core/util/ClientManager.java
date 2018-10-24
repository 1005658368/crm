package org.crmframework.core.util;

import javax.servlet.http.HttpSession;

public class ClientManager{

    public static Client getClient() {
		HttpSession session=ContextHolderUtils.getSession();
		Client client=(Client)session.getAttribute(session.getId());
        return client;
    }

	public static Client getClient(HttpSession session) {
		Client client=(Client)session.getAttribute(session.getId());
		return client;
	}

	public static void setClient(Client client){
		HttpSession session=ContextHolderUtils.getSession();
		session.setAttribute(session.getId(),client);
	}

}

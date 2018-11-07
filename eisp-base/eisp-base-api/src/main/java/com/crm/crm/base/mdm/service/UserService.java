package com.crm.crm.base.mdm.service;

import com.crm.crm.base.mdm.entity.TSUser;
import com.crm.crm.common.vo.MiniDaoPage;

public interface UserService {
	String test(String test);

    TSUser checkUserExits(TSUser user);

    MiniDaoPage<TSUser> findUserList(TSUser user, int page, int rows);
}

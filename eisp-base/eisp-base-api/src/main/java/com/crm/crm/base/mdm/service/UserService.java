package com.crm.crm.base.mdm.service;

import com.crm.crm.pojo.entity.TSUser;

public interface UserService {
	String test(String test);

    TSUser checkUserExits(TSUser user);
}

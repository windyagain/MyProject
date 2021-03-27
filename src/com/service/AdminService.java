package com.service;

import java.util.Map;

import com.model.Admin;

public interface AdminService {

	Map<String,Object> adminLogin(Admin admin);
	 Map<String, Object> updateAdminPwd(Admin admin);
}

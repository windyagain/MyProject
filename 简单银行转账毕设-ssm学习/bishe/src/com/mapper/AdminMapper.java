package com.mapper;

import java.util.List;

import com.model.Admin;
import com.model.User;

public interface AdminMapper {

	List<Admin> getOneAdmin(Admin admin);
	Integer updateAdminPwdByAdminId(Admin admin);
	List<Admin> getAdminByAid(Integer aid);
}

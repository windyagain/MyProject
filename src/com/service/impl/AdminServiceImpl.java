package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.AdminMapper;
import com.mapper.LogMapper;
import com.model.Admin;
import com.model.Log;
import com.model.User;
import com.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Resource
	public AdminMapper adminMapper;
	@Resource
    public LogMapper logMapper;
	
	@Override
	public Map<String, Object> adminLogin(Admin admin) {
		
		Map<String, Object> map = new HashMap<String,Object>();	
		String mes = "登陆成功,将跳转到首页！";
		String url = "/bishe/admin/index.jsp";
		Integer flag =1;
		if("".equals(admin.getAdmin_name()) || "".equals(admin.getAdmin_pwd())) {
			mes = "登陆用户名和密码必须填写";
			flag = 0;
		}
		List<Admin> dbAdminList = new ArrayList<Admin>(); 
		Admin dbAdmin = new Admin();
		if(flag > 0) {
			dbAdminList = adminMapper.getOneAdmin(admin);
			if(dbAdminList.size() < 1) {
				mes = "登陆失败";
				url = "/bishe/admin/login.jsp";
			}else {
				dbAdmin = dbAdminList.get(0);
				logMapper.insertAdLog(dbAdmin.getAid(), "管理员"+dbAdmin.getAdmin_name()+"登陆后台");
			}	
		}else {
			url = "/bishe/admin/login.jsp";
		}
		map.put("data", dbAdmin);
		map.put("mes", mes);
		map.put("res", dbAdminList.size());
		map.put("url", url);
			
		return map;
	}
	
	
	@Override
	public Map<String, Object> updateAdminPwd(Admin admin) {
		// TODO Auto-generated method stub
		Integer flag = 1;
		Integer row1 = 0;
		String mes = "管理员登陆密码修改成功！";
		String url = "/bishe/user/findAllUser";
		if("".equals(admin.getAdmin_pwd()) || "".equals(admin.getOld_admin_pwd())) {
			flag = 0;
			mes = "密码都要填写！";	
		}else if("".equals(admin.getAid())) {
			flag = 0;
			mes = "访问错误！";	//checkPwd放的是原始密码
		}
		
		List<Admin> clist1 = null;
		Admin dbAdmin = new Admin();
		
		try {
			clist1 = adminMapper.getAdminByAid(admin.getAid());	
		}catch(Exception e) {
			
		}
		System.out.println(clist1);
		if(clist1.size()>0) {
			dbAdmin = clist1.get(0);	
		}else {
			flag = 0;
			mes = "访问不合法！";	
		}
		if(flag ==1)
		if(admin.getAdmin_pwd().equals(dbAdmin.getAdmin_pwd()) ) {
			flag = 0;
			mes = "修改的密码与原密码一致！";	
		}else if(!admin.getOld_admin_pwd().equals(dbAdmin.getAdmin_pwd())) {
			flag = 0;
			mes = "原始密码输入错误！";	
		}
	
		if(flag > 0) {
			try{
				row1 = adminMapper.updateAdminPwdByAdminId(admin);
				if(row1 > 0) {
					logMapper.insertAdLog(dbAdmin.getAid(), "管理员"+dbAdmin.getAdmin_name()+"成功修改密码");	
				}
				
			}
			catch(Exception e) {
				System.out.print(e.getMessage());
			}
		}
		if(row1 < 1){
			url = "/bishe/admin/updateAdminPwd.jsp";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("res", row1);
		map.put("mes", mes);
		map.put("url", url);
		return map;
}
	}

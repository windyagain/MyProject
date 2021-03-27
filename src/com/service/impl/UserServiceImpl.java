package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.LogMapper;
import com.mapper.UserMapper;
import com.mapper.userCardMapper;
import com.model.Admin;
import com.model.Card;
import com.model.Log;
import com.model.User;
import com.model.UserCard;
import com.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
    public UserMapper userMapper;
	@Resource
    public userCardMapper uCardMapper;
	@Resource
    public LogMapper logMapper;
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		 List<User> findAllUser = userMapper.findAllUser();
	     return findAllUser;
	}
	@Override
	public List<User> findOneUser(User user) {
		List<User> findOneUser = userMapper.findOneUser(user);
	     return findOneUser;
	}
	@Override
	public Integer insertUser(User user) {
		// TODO Auto-generated method stub
		Integer rows = 0;
		
		try {
			rows = userMapper.insertUser(user);
			if(rows > 0) {
				Log log = new Log();
				log.setUid(user.getUid());
				log.setContent("用户"+user.getUsername()+"注册成功");
				logMapper.insertLog(log);
			}
		}catch(Exception e) {
			
		}
		
		return rows;
	}
	@Override
	public Map<String, Object> updateUserPwd(User user) {
		// TODO Auto-generated method stub
		Integer flag = 1;
		Integer row1 = 0;
		String mes = "登陆密码修改成功！";
		
		if("".equals(user.getPassword()) || "".equals(user.getCheckPwd())) {
			flag = 0;
			mes = "密码都要填写！";	
		}else if("".equals(user.getUid())) {
			flag = 0;
			mes = "访问错误！";	//checkPwd放的是原始密码
		}
		
		List<User> clist1 = null;
		User uNow = new User();
		
		try {
			clist1 = userMapper.findOneUserByUserId(user.getUid());	
		}catch(Exception e) {
			
		}
		//System.out.println(clist1);
		if(clist1.size()>0) {
			uNow = clist1.get(0);	
		}else {
			flag = 0;
			mes = "访问不合法！";	
		}
		if(user.getPassword().equals(uNow.getPassword()) ) {
			flag = 0;
			mes = "修改的密码与原密码一致！";	
		}else if(!user.getCheckPwd().equals(uNow.getPassword())) {
			flag = 0;
			mes = "原始密码输入错误！";	
		}
		
		if(user.getPassword().equals(uNow.getPassword()) ) {
			flag = 0;
			mes = "修改的密码与原密码一致！";	
		}
		if(flag > 0) {
			try{
				row1 = userMapper.updateUserPwdByUserId(user);
				if(row1 > 0) {
					Log log = new Log();
					log.setUid(uNow.getUid());
					log.setContent("用户"+uNow.getUsername()+"修改密码成功");
					logMapper.insertLog(log);	
				}
				
			}
			catch(Exception e) {
				System.out.print(e.getMessage());
			}
		}
	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("res", row1);
		map.put("mes", mes);
		return map;
	}
	@Override
	public Map<String, Object> deleteUserByUid(User user,Admin admin) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		String url = "/bishe/user/findAllUser";
		Integer rows = 0;
		String mes = "用户删除成功!";
		Integer flag = 1;
		if(user.getUid() == null || user.getUsername() == null ||
				"".equals(user.getUsername()) || admin.getAid() == null || 
				admin.getAdmin_name() == null) {
			mes = "访问错误";
			flag = 0;
		}
		List<UserCard>  ucList = uCardMapper.getCardsByUid(user.getUid());
		if(flag == 1 && ucList != null && ucList.size() > 0) {
			mes = "请先删除相关数据";
			flag = 0;
		}
		if(flag > 0) {
			try {
				rows = userMapper.delUserByUserId(user.getUid());
				logMapper.insertAdLog(admin.getAid(), "管理员"+admin.getAdmin_name()+"删除用户"+user.getUsername());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("res", rows);
		map.put("mes", mes);
		map.put("url", url);
		return map;
	}
	@Override
	public Map<String, Object> adInsertUser(User user, Admin admin) {
		Map<String, Object> map = new HashMap<String,Object>();
		String url = "/bishe/user/findAllUser";
		Integer rows = 0;
		String mes = "用户添加成功!";
		Integer flag = 1;
		if(user.getUsername() == null || user.getCheckPwd()==null || user.getCheckPwd()==null ||
				"".equals(user.getUsername()) || admin.getAid() == null || 
				admin.getAdmin_name() == null || "".equals(user.getCheckPwd())
				|| user.getEmail() == null || "".equals(user.getEmail())) {
			mes = "访问错误";
			flag = 0;
		}else if(!user.getCheckPwd().equals(user.getPassword())) {
			mes = "两次密码不一致";
			flag = 0;
		}
		
		if(flag > 0) {
			try {
				rows = userMapper.insertUser(user);
				logMapper.insertAdLog(admin.getAid(), "管理员"+admin.getAdmin_name()+"添加用户: "+user.getUsername());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rows = 0;
			}
		}
		if(rows < 1) {
			url = "/bishe/admin/addUser.jsp";
		}
		map.put("res", rows);
		map.put("mes", mes);
		map.put("url", url);
		return map;
	}
	


}

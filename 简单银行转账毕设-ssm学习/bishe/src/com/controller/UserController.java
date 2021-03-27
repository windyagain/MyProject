package com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.Admin;
import com.model.Card;
import com.model.Log;
import com.model.User;
import com.service.LogService;
import com.service.UserService;
import com.service.impl.UtilsMd5;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    
    @RequestMapping("/findAllUser")
    public ModelAndView adminFindAllUser(HttpServletRequest request,User user){
        List<User> listUser =  userService.findAllUser();
       // System.out.println(user);
       
        //request.setAttribute("listUser", listUser);
        //request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listUser", listUser);
        modelAndView.setViewName("/admin/allUser");
        return modelAndView;
    }
    @RequestMapping("/login")
    public ModelAndView userLogin(HttpServletRequest request,@Validated  User user,BindingResult result,HttpSession session){
    	System.out.println("yes");
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(result.hasErrors()) {
    		List<FieldError> errors = result.getFieldErrors();
    		for(FieldError fieldError : errors) {
    			map.put(fieldError.getField(),fieldError.getDefaultMessage());
    			
    		}
    		System.out.println(map);
    		ModelAndView modelAndView = new ModelAndView();
    		modelAndView.addObject("fieldError", map);
    		modelAndView.setViewName("/login");
    		return modelAndView;
    	}
    	
    	user.setPassword(UtilsMd5.MD5(user.getPassword()));
        List<User> listUser =  userService.findOneUser(user);
        //System.out.println(user);
        String url = "/bishe/login.jsp";
        String mes = "登陆失败！将跳转到登陆页面";
        ModelAndView modelAndView = new ModelAndView();
        if(listUser.size() > 0) {
        	mes = "登陆成功！将跳转到首页！";
        	url = "/bishe/home/index.jsp";
        	session.setAttribute("user", listUser.get(0));
        	
        	modelAndView.setViewName(url);
        	//日志记录
        	User uNow = new User();
        	uNow = listUser.get(0);
			Log log = new Log();
			log.setUid(uNow.getUid());
			log.setContent("用户"+uNow.getUsername()+"成功登陆。");
			logService.insertOneLog(log);
        	
        }
        
        modelAndView.setViewName("/mes");	
        //modelAndView.addObject("listUser", listUser);
        modelAndView.addObject("mes",mes);
        modelAndView.addObject("url", url);
        
        return modelAndView;
    }
    
    @RequestMapping("/register")
    public ModelAndView userRegister(HttpServletRequest request,User user){
    	Integer rows = 0;
       // System.out.println(user);
        String url = "/bishe/register.jsp";
        String mes = "注册失败！将跳转到登陆页面";
        Integer flag = 1;
        ModelAndView modelAndView = new ModelAndView();
        mes = "注册失败！用户名已经被使用";
    	url = "/bishe/register.jsp";
        if(!user.getCheckPwd().equals(user.getPassword()))
        {
        	mes = "注册失败，两次输入密码不一致！";
        	flag = 0;
        }else if("".equals(user.getUsername()) || "".equals(user.getEmail()) 
        || "".equals(user.getCheckPwd()) || "".equals(user.getPassword())) {
        	mes = "数据不能为空!";
        	flag =0;
        }

    	if(flag > 0) {
		try {
			user.setPassword(UtilsMd5.MD5(user.getPassword()));
            rows = userService.insertUser(user);
          } catch (Exception e) {
            e.printStackTrace();
          }
    	}
        
        if(rows == 1) {
        	mes = "注册成功！";
        	url = "/bishe/login.jsp";
        }
        
        modelAndView.addObject("mes",mes);
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/updatePwd")
    public ModelAndView updatePwd(HttpServletRequest request,User user)throws Exception{
    	user.setPassword(UtilsMd5.MD5(user.getPassword()));
    	user.setCheckPwd(UtilsMd5.MD5(user.getCheckPwd()));
    	Map<String,Object> map =  userService.updateUserPwd(user);
        ModelAndView modelAndView = new ModelAndView();
        String url = "/bishe/home/main.jsp";
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/loginout")
    public ModelAndView userLoginOut(HttpServletRequest request,User user,HttpSession session){
        
        //System.out.println(user);
        String url = "/bishe/login.jsp";
        String mes = "退出成功！将跳转到登陆页面";
        ModelAndView modelAndView = new ModelAndView();
        session = request.getSession();
        session.removeAttribute("user");
        
        modelAndView.setViewName("/mes");	
        //modelAndView.addObject("listUser", listUser);
        modelAndView.addObject("mes",mes);
        modelAndView.addObject("url", url);
        
        return modelAndView;
    }
    
    @RequestMapping("/deleteUser")
    public ModelAndView adDeleteUserByUid(HttpServletRequest request,User user,Admin admin){
    	 
    	Map<String, Object> map = new HashMap<String,Object>();
    	map = userService.deleteUserByUid(user,admin);
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    //
    @RequestMapping("/adInsertUser")
    public ModelAndView adInsertUser(HttpServletRequest request,User user,Admin admin){
    	Map<String, Object> map = new HashMap<String,Object>();
    	map = userService.adInsertUser(user, admin);
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
	@RequestMapping("/editUser")
	public ModelAndView editItemSubmit(Model model,HttpServletRequest request,User user,BindingResult bindingResult,MultipartFile pictureFile)throws Exception{
		
		
		//原始文件名称
		String pictureFile_name =  pictureFile.getOriginalFilename();

		if(pictureFile != null && pictureFile_name != null && pictureFile_name.length() > 0) {

			//新文件名称
			String newFileName = UUID.randomUUID().toString()+pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
			
			//上传图片
			File uploadPic = new java.io.File("E:/storage/develop/upload/temp/"+newFileName);
			if(!uploadPic.exists()){//E:\\storage\\develop\\upload\\temp\\
				uploadPic.mkdirs();
			}
			//向磁盘写文件
			pictureFile.transferTo(uploadPic);

			
		}

		//调用service更新用户信息，
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("mes","头像上传测试");
        modelAndView.addObject("url", "/bishe/home/personal.jsp");
        modelAndView.setViewName("/mes");
        return modelAndView;
		//重定向sss
		//return "redirect:queryItems.action";
		//页面转发
		//return "forward:queryItems.action";
		//return "success";
	}
}

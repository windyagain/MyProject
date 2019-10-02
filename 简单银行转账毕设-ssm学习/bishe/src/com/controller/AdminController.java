package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.Admin;
import com.model.Log;
import com.model.User;
import com.service.AdminService;
import com.service.LogService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private LogService logService;

    @Autowired
    private AdminService adminService;
    
    @RequestMapping("/login")
    public ModelAndView adminLogin(HttpServletRequest request,Admin admin,HttpSession session){
    	
    	Map<String, Object> map =  adminService.adminLogin(admin);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        if(Integer.parseInt(map.get("res").toString()) > 0)
        session.setAttribute("admin", map.get("data"));
        modelAndView.setViewName("/mes");
        System.out.println(map);
        return modelAndView;	
        	
        }
    
    @RequestMapping("/loginout")
    public ModelAndView adminLoginOut(HttpServletRequest request,HttpSession session){
        
        String url = "/bishe/admin/login.jsp";
        String mes = "退出成功！将跳转到登陆页面";
        ModelAndView modelAndView = new ModelAndView();
        session = request.getSession();
        session.removeAttribute("admin");
        
        modelAndView.setViewName("/mes");	
        //modelAndView.addObject("listUser", listUser);
        modelAndView.addObject("mes",mes);
        modelAndView.addObject("url", url);
        return modelAndView;
    }
    
    @RequestMapping("/updateAdminPwd")
    public ModelAndView updatePwd(HttpServletRequest request,Admin admin)throws Exception{
    	Map<String,Object> map =  adminService.updateAdminPwd(admin);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url",map.get("url"));
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
}

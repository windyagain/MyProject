package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.Log;
import com.model.User;
import com.service.LogService;
import com.service.UserService;

@Controller
@RequestMapping("/log")
public class LogController {
	
    @Autowired
    private LogService logService;
    @RequestMapping("/getLogs")
    public ModelAndView getAllLogs(HttpServletRequest request){
        List<Log> listLog =  logService.getLogs();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listLog", listLog);
        modelAndView.setViewName("/admin/logs");
        return modelAndView;
    }
    
    @RequestMapping("/getLogsByUid")
    public ModelAndView findAllUser(HttpServletRequest request,Log log,HttpSession session){
        List<Log> listLog =  logService.getLogsByUid(log);

        //设置一个session用于测试阶段
/*        User user = new User();
        user.setUid(1);
        user.setUsername("zhansan");
        user.setEmail("123@qq");
        session.setAttribute("user", user);*/
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listLog", listLog);
        modelAndView.setViewName("/home/logs");
        return modelAndView;
    }
    
    
    @RequestMapping("/deleteLogByLogId")
    public ModelAndView delLogByLogId(HttpServletRequest request,Log log){
    	List<Log> list = logService.getLogByLogId(log);
    	Map<String, Object> map = new HashMap<String,Object>();
    	ModelAndView modelAndView = new ModelAndView();
    	Log log1 = new Log();
    	String url = "";
    	if(list.size() > 0) {
    		map =  logService.deleteByLogId(log);
    		log1 = list.get(0);
            url = "/bishe/log/getLogsByUid?uid="+log1.getUid();
            
    	}else {
    		url = "/bishe/home/main.jsp";
    		map.put("mes","日志删除失败！");
    	}
    	modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    @RequestMapping("/deleteLog")
    public ModelAndView adDeleteLog(HttpServletRequest request,Log log){
    	List<Log> list = logService.getLogByLogId(log);
    	Map<String, Object> map = new HashMap<String,Object>();
    	ModelAndView modelAndView = new ModelAndView();
    	String url = "";
    	if(list.size() > 0) {
    		map =  logService.deleteByLogId(log);
            url = "/bishe/log/getLogs";
    	}else {
    		url = "/bishe/admin/main.jsp";
    		map.put("mes","日志删除失败！");
    	}
    	modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
}

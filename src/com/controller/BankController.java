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
import com.model.Bank;
import com.service.BankService;



@Controller
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private BankService bankService;
    @RequestMapping("/getAllBank")
    public ModelAndView findAllBank(HttpServletRequest request){
    	List<Bank> listBanks =  bankService.getAllBanks();
        ModelAndView modelAndView = new ModelAndView();
        //System.out.println(listBanks);
        modelAndView.addObject("listBanks", listBanks);
        modelAndView.setViewName("/home/askForCard");
        return modelAndView;
    }
    
    @RequestMapping("/adGetAllBank")
    public ModelAndView adGetAllBank(HttpServletRequest request,HttpSession session){
    	List<Bank> listBanks =  bankService.getAllBanks();
        ModelAndView modelAndView = new ModelAndView();
        //设置下admin
/*        Admin admin = new Admin();
        admin.setAdmin_name("admin");
        admin.setAdmin_pwd("admin");
        
        admin.setAid(1);
        session.setAttribute("admin",admin );*/
        modelAndView.addObject("listBanks", listBanks);
        modelAndView.setViewName("/admin/bankList");
        return modelAndView;
    }
    
    @RequestMapping("/insertBank")
    public ModelAndView insertBank(HttpServletRequest request,Bank bank,Admin admin){
    	Map<String,Object> map=  bankService.InsertBank(bank, admin);
    	//System.out.println("admin is "+ admin);
        ModelAndView modelAndView = new ModelAndView(); 
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    @RequestMapping("/delBank")
    public ModelAndView delBank(HttpServletRequest request,Bank bank,Admin admin){
    	Map<String,Object> map=  bankService.DeleteBank(bank, admin);
    	//System.out.println("admin is "+ admin);
        ModelAndView modelAndView = new ModelAndView(); 
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
}

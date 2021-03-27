package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.Admin;
import com.model.Bank;
import com.model.Card;
import com.model.User;
import com.service.BankService;
import com.service.CardService;
import com.service.UserService;


@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private BankService bankService;
    @Autowired
    private UserService userService;
    @RequestMapping("/getAllCard")
    public ModelAndView findAllCard(HttpServletRequest request,Integer uid){
    	List<Map<String, Object>> listCards =  cardService.findAllCardByUid(uid);
        //System.out.println(uid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listCards", listCards);
        modelAndView.setViewName("/home/cards");
        return modelAndView;
    }
    
    @RequestMapping("/adGetAllCard")
    public ModelAndView adminGetAllCard(HttpServletRequest request){
    	List<Map<String, Object>> listCards =  cardService.getAllCards();
       
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listCards", listCards);
        modelAndView.setViewName("/admin/card");
        return modelAndView;
    }
    @RequestMapping("/transform")
    public ModelAndView transformMoney(HttpServletRequest request,Card card){
    	Map<String,Object> map =  cardService.updateCardMoney(card);
        ModelAndView modelAndView = new ModelAndView();
        String url = "/bishe/card/getAllCard?uid="+map.get("uid");
       // System.out.println(url);
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/storeMoney")
    public ModelAndView storeMoney(HttpServletRequest request,Card card)throws Exception{
    	Map<String,Object> map =  cardService.StoreCardMoney(card);
        ModelAndView modelAndView = new ModelAndView();
        String url = "/bishe/card/getAllCard?uid="+map.get("uid");
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/takeMoney")
    public ModelAndView takeMoney(HttpServletRequest request,Card card)throws Exception{
    	Map<String,Object> map =  cardService.TakeCardMoney(card);
        ModelAndView modelAndView = new ModelAndView();
        String url = "/bishe/card/getAllCard?uid="+map.get("uid");
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/editPayPwd")
    public ModelAndView editPayPwd(HttpServletRequest request,Card card)throws Exception{
    	Map<String,Object> map =  cardService.EditCardPwd(card);
        ModelAndView modelAndView = new ModelAndView();
        String url = "/bishe/card/getAllCard?uid="+map.get("uid");
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/askForCard")
    public ModelAndView askForCard(HttpServletRequest request,Card card,Integer uid)throws Exception{
    	Map<String,Object> map =  cardService.insertCard(card,uid);
        ModelAndView modelAndView = new ModelAndView();
        String url = "/bishe/card/getAllCard?uid="+map.get("uid");
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", url);
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
    
    @RequestMapping("/adInsertCardForm")
    public ModelAndView adInsertCardForm(HttpServletRequest request)throws Exception{
    	
    	List<Bank> listBanks =  bankService.getAllBanks();

        List<User> listUser =  userService.findAllUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listBanks",listBanks);
        modelAndView.addObject("listUser",listUser);
        modelAndView.setViewName("/admin/addCard");
        
        return modelAndView;
    }
    
    @RequestMapping("/adInsertCard")
    public ModelAndView adminInsertCard(HttpServletRequest request,Card card,User user,Admin admin)throws Exception{
    	Map<String,Object> map =  cardService.adInsertCard(card,user,admin);
    	
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        modelAndView.setViewName("/mes");
        
        return modelAndView;
    }
    //
    
    @RequestMapping("/adminDeleteCard")
    public ModelAndView adminDeleteCard(HttpServletRequest request,Card card,User user,Admin admin)throws Exception{
    	Map<String,Object> map =  cardService.adDeleteCard(card,user,admin);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mes",map.get("mes"));
        modelAndView.addObject("url", map.get("url"));
        modelAndView.setViewName("/mes");
        return modelAndView;
    }
}

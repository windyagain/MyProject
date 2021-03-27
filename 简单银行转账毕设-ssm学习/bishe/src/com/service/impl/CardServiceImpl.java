package com.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mapper.CardMapper;
import com.mapper.LogMapper;
import com.mapper.UserMapper;
import com.mapper.userCardMapper;
import com.model.Admin;
import com.model.Card;
import com.model.Log;
import com.model.User;
import com.model.UserCard;
import com.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService {
	@Resource
    public CardMapper cardMapper;
	@Resource
    public userCardMapper userCardMapper;
	@Resource
	public LogMapper logMapper;
	@Override
	public List<Map<String, Object>> findAllCardByUid(Integer uid) {
		 List<Map<String, Object>> allCards = cardMapper.getAllCardsByUid(uid);
		return allCards;
	}
	@Override
	public List<Map<String, Object>> getAllCards() {
		 List<Map<String, Object>> allCards = cardMapper.getAllCards();
		return allCards;
	}
	@Override  
	public Map<String,Object> updateCardMoney(Card card) {
		Card card1 = new Card();
		Card card2 = new Card();
		UserCard ucard = new UserCard();
		UserCard ucard2 = new UserCard();
		List<Card> clist1 = new ArrayList<Card>();
		List<Card> clist2 = new ArrayList<Card>();
		clist1 = cardMapper.findOneCardByCardId(card.getCard_id());
		clist2 = cardMapper.findOneCardByCardNum(card.getCard_num());
		Integer flag = 1;
		String mes = "转账成功！";
		if(clist1.size() < 1 || clist2.size() < 1 ) {
			flag =0; 
			mes = "访问不合法！";
		}else {
			card1 = clist1.get(0);
			card2 = clist2.get(0);
		}
		if(clist1.size() > 0 && clist2.size() > 0)
		if( "".equals(card1.getCard_id()) || "".equals(card2.getCard_id())) {
			flag =0; 
			mes = "数据丢失！";
		}else if(!card1.getPay_pwd().equals(card.getPay_pwd())) {
			flag =0; 
			mes = "支付密码错误！";
		}
		else if(card1.getCard_id() == card2.getCard_id()) {
			flag =0;
			mes = "请勿给自己转账！";
		}else if(card1.getMoney().compareTo(card.getMoney()) < 0 ) {
			flag =0;
			mes = "余额不足！";
		}else if("".equals(card2.getCard_id())) {
			flag =0;
			mes = "转账的卡号不存在！";
		}
		
		List<UserCard> lc = userCardMapper.getUidByCardId(card.getCard_id());
		List<UserCard> lc2 = userCardMapper.getUidByCardId(card2.getCard_id());
		ucard = lc.get(0);
		ucard2 = lc2.get(0);
		Integer row1 = 0;
		Integer row2 = 0;
		if(flag > 0) {
			card1.setMoney(card1.getMoney().subtract(card.getMoney()));
			card2.setMoney(card2.getMoney().add(card.getMoney()));

			//更新操作
			
			//logMapper.insertAdLog(1, "异常回滚测试");
					row1 = cardMapper.updateCardByCardId(card1);
					//row2 = cardMapper.updateCardByCardId(card2);
					row2 = cardMapper.updateCardByCardId(card2);
					//int i = 1/0; 检测事务是否可以成功。
					if(row2 > 0 && row1 > 0) {
						Log log = new Log();
						log.setUid(ucard.getUid());
						log.setContent("卡号为 "+card1.getCard_num()+"的账户向 卡号为  "+card2.getCard_num()+"转账"+card.getMoney()+"元");
						logMapper.insertLog(log);
						Log log1 = new Log();
						log1.setUid(ucard2.getUid());
						log1.setContent("卡号为 "+card2.getCard_num()+"的账户 收到来自卡号为  "+card1.getCard_num()+"的转账，金额 "+card.getMoney()+"元");
						logMapper.insertLog(log1);
					}else {
						mes = "转账失败";
					}
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("res", row1>0 && row2>0 ? 1: 0);
		map.put("mes", mes);
		map.put("uid", ucard.getUid());
		return map;
	}

	@Override
	public Map<String, Object> StoreCardMoney(Card card) {
		// TODO Auto-generated method stub
		Integer flag = 1;
		Integer row1 = 0;
		String mes = "存款成功！";
		BigDecimal zero = new BigDecimal("0.00");
		if("".equals(card.getCard_id())) {
			flag = 0;
			mes = "存款失败！";	
		}else if(card.getMoney().compareTo(zero) < 0) {
			flag = 0;
			mes = "存款输入错误！";
		}
		List<Card> clist1 = null;
		Card cNow = null;
		try {
			clist1 = cardMapper.findOneCardByCardId(card.getCard_id());	
		}catch(Exception e) {
			
		}
		
		if(clist1.size()>0) {
			cNow = clist1.get(0);	
		}else {
			flag = 0;
			mes = "访问不合法！";	
		}
		List<UserCard> lc = userCardMapper.getUidByCardId(card.getCard_id());
		if(cNow != null && flag == 1) {
			try{
				cNow.setMoney(cNow.getMoney().add(card.getMoney()));
				row1 = cardMapper.updateCardByCardId(cNow);
				if(row1 > 0) {
					Log log = new Log();
					log.setUid(lc.get(0).getUid());
					log.setContent("向卡号为 "+cNow.getCard_num()+"的银行卡存款"+card.getMoney()+"元");
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
		map.put("uid", lc.get(0).getUid());
		
		return map;
	}

	@Override
	public Map<String, Object> TakeCardMoney(Card card) {
		// TODO Auto-generated method stub
		Integer flag = 1;
		Integer row1 = 0;
		String mes = "取款成功！";
		BigDecimal zero = new BigDecimal("0.00");
		if("".equals(card.getCard_id())) {
			flag = 0;
			mes = "取款失败！";	
		}else if(card.getMoney().compareTo(zero) < 0) {
			flag = 0;
			mes = "取款输入错误！";
		}
		List<Card> clist1 = null;
		Card cNow = null;
		try {
			clist1 = cardMapper.findOneCardByCardId(card.getCard_id());	
		}catch(Exception e) {
			
		}
		
		if(clist1.size()>0) {
			cNow = clist1.get(0);	
		}else {
			flag = 0;
			mes = "访问不合法！";	
		}
		if(cNow.getMoney().compareTo(card.getMoney()) < 0 ) {
			flag =0;
			mes = "余额不足！";
		}
		List<UserCard> lc = userCardMapper.getUidByCardId(card.getCard_id());
		if(cNow != null && flag == 1) {
			try{
				cNow.setMoney(cNow.getMoney().subtract(card.getMoney()));
				row1 = cardMapper.updateCardByCardId(cNow);
				if(row1 > 0) {
					Log log = new Log();
					log.setUid(lc.get(0).getUid());
					log.setContent("从卡号为 "+cNow.getCard_num()+"的银行卡取款"+card.getMoney()+"元");
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
		map.put("uid", lc.get(0).getUid());
		return map;
	}

	
	@Override
	public Map<String, Object> EditCardPwd(Card card) {
		// TODO Auto-generated method stub
		Integer flag = 1;
		Integer row1 = 0;
		String mes = "支付密码修改成功！";
		
		if("".equals(card.getPay_pwd()) || "".equals(card.getOld_pay_pwd())) {
			flag = 0;
			mes = "新旧密码都要填写！";	
		}else if("".equals(card.getCard_id())) {
			flag = 0;
			mes = "访问错误！";	
		}
		List<Card> clist1 = null;
		Card cNow = new Card();
		try {
			clist1 = cardMapper.findOneCardByCardId(card.getCard_id());	
		}catch(Exception e) {
			
		}
		
		if(clist1.size()>0) {
			cNow = clist1.get(0);	
		}else {
			flag = 0;
			mes = "访问不合法！";	
		}
		if(card.getPay_pwd().equals(cNow.getPay_pwd()) ) {
			flag = 0;
			mes = "修改的密码与原密码一致！";	
		}else if(!card.getOld_pay_pwd().equals(cNow.getPay_pwd())) {
			flag = 0;
			mes = "原始密码输入错误！";	
		}
		List<UserCard> lc = userCardMapper.getUidByCardId(card.getCard_id());
		if(flag > 0) {
			try{
				row1 = cardMapper.updateCardPwdByCardId(card);
				if(row1 > 0) {
					Log log = new Log();
					log.setUid(lc.get(0).getUid());
					log.setContent("成功修改卡号为"+cNow.getCard_num()+ "的支付密码");
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
		map.put("uid", lc.get(0).getUid());
		return map;
	}

	@Override
	public Map<String,Object> insertCard(Card card,Integer uid) {
		// TODO Auto-generated method stub
		List<Card> list = cardMapper.findMaxCardNum();
		Card dbCard = list.get(0);
		Integer flag =1;	
		String mes = "开卡成功！";
		if("".equals(card.getU_name()) || "".equals(card.getBank_id()) || 
				   "".equals(card.getPay_pwd()) || "".equals(card.getOld_pay_pwd())
						|| uid == null || card.getU_name() == null || card.getBank_id() == null
						|| card.getPay_pwd() == null || card.getOld_pay_pwd() == null || card.getPay_pwd() ==null) {
					flag =0;
					mes = "数据不可为空！";
				}else if(!card.getPay_pwd().equals(card.getOld_pay_pwd())) {
			flag =0;
			mes = "两次密码不一致！";
		}
		
		//System.out.println(dbCard.getCard_num());
		card.setCard_num(dbCard.getCard_num()+1);

		Integer rows = 0;
		Integer rows2 = 0;
		if(flag > 0) {
			try {
				rows = cardMapper.insertCard(card);
				if(rows>0) {
					UserCard userCard = new UserCard();
					Card newOne = null;
					List<Card> lc = cardMapper.findOneCardByCardNum(dbCard.getCard_num()+1);
					newOne = lc.get(0);
					userCard.setCard_id(newOne.getCard_id());
					userCard.setUid(uid);

					rows2 = userCardMapper.insertUserCard(userCard);
					if(rows2 > 0) {
						Log log = new Log();
						log.setUid(uid);
						log.setContent("开卡成功，卡号为"+(dbCard.getCard_num()+1));
						logMapper.insertLog(log);
					}
				}else {
					mes = "开卡失败！";
				}
				 
			}catch(Exception e) {
				mes = "开卡失败！";
				rows = 0;
				System.out.println(e.getMessage());
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("res", rows);
		map.put("mes", mes);
		map.put("uid", uid);
		return map;
	}
	
	@Override
	public Map<String,Object> adInsertCard(Card card,User user,Admin admin) {
		// TODO Auto-generated method stub
		List<Card> list = cardMapper.findMaxCardNum();
		Card dbCard = list.get(0);
		Integer flag =1;	
		String mes = "开卡成功！";
		String url = "/bishe/card/adGetAllCard";
		if("".equals(card.getU_name().trim()) || "".equals(card.getBank_id()) || 
		   "".equals(card.getPay_pwd().trim()) || "".equals(card.getOld_pay_pwd().trim())
				|| user.getUid() == null || card.getU_name() == null || card.getBank_id() == null
				|| card.getPay_pwd() == null || card.getOld_pay_pwd() == null
				|| admin.getAid() ==null || admin.getAdmin_name() == null) {
			flag =0;
			mes = "数据不可为空！";
		}else if(!card.getPay_pwd().equals(card.getOld_pay_pwd())) {
			flag =0;
			mes = "两次密码不一致！";
		}
		
		card.setCard_num(dbCard.getCard_num()+1);
		Integer rows = 0;
		Integer rows2 = 0;
		if(flag > 0) {
			try {
				rows = cardMapper.insertCard(card);
				if(rows>0) {
					UserCard userCard = new UserCard();
					Card newOne = null;
					List<Card> lc = cardMapper.findOneCardByCardNum(dbCard.getCard_num()+1);
					newOne = lc.get(0);
					//System.out.println("---->"+lc.get(0));
					userCard.setCard_id(newOne.getCard_id());
					userCard.setUid(user.getUid());
					//System.out.println(userCard);
					rows2 = userCardMapper.insertUserCard(userCard);
					if(rows2 > 0) {
						logMapper.insertAdLog(admin.getAid(), "管理员"+admin.getAdmin_name()+"添加卡号为："+newOne.getCard_num() + "的银行卡");
					}
				}else {
					mes = "开卡失败！";
				}
				 
			}catch(Exception e) {
				mes = "开卡失败！";
				rows = 0;
				rows2 = 0;
				//System.out.println(e.getMessage());
			}
		}
		if(rows < 1 || rows2 < 1) {
			url = "/bishe/card/adInsertCardForm";
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("res", rows);
		map.put("mes", mes);
		map.put("url", url);
		return map;
	}
	@Override
	public Map<String, Object> adDeleteCard(Card card, User user, Admin admin) {
		Map<String,Object> map = new HashMap<String,Object>();
		String mes = "删除成功!";
		Integer rows = 0;
		Integer rows2 = 0;
		Integer flag = 1;
		String url = "/bishe/card/adGetAllCard";
		if(card.getCard_id() == null || card.getCard_id().equals("") || user.getUsername() == null ||
				admin.getAdmin_name() == null || "".equals(admin.getAdmin_name()) ||
				admin.getAid() == null|| card.getCard_num() == null ) {
			flag = 0;
			mes = "请求不合法!";
		}else if(card.getMoney().compareTo(BigDecimal.ZERO) > 0){
			flag = 0;
			mes = "删除失败，请确保这张卡内余额为0!";
		}
		if(flag > 0) {
			try {
				rows = userCardMapper.deleteWithCardId(card.getCard_id());
				rows2 = cardMapper.deleteByCardId(card.getCard_id());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rows = 0;
				rows2= 0;
			}
		}
	    if(flag > 0)
		if(rows < 0 || rows2 < 0) {
			mes = "删除失败！";
		}else {
			logMapper.insertAdLog(admin.getAid(), "管理员"+admin.getAdmin_name()+"删除属于"+user.getUsername()+"账户的、卡号为："+card.getCard_num() + "的银行卡");
		}
		map.put("mes", mes);
		map.put("url", url);
		return map;
	}
	
}

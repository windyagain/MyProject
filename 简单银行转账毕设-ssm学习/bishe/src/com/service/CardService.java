package com.service;

import java.util.List;
import java.util.Map;

import com.model.Admin;
import com.model.Card;
import com.model.User;


public interface CardService {
	List<Map<String, Object>> findAllCardByUid(Integer uid);
	Map<String,Object> updateCardMoney(Card card);
	Map<String,Object> StoreCardMoney(Card card);
	Map<String,Object> TakeCardMoney(Card card);
	Map<String,Object> EditCardPwd(Card card);
	Map<String,Object> insertCard(Card card,Integer uid);
	List<Map<String, Object>> getAllCards();
	Map<String,Object> adInsertCard(Card card,User user,Admin admin);
	Map<String,Object> adDeleteCard(Card card,User user,Admin admin);
}

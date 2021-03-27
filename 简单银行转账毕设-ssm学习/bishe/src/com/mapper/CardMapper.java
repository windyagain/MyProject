package com.mapper;

import java.util.List;

import com.model.Card;
import java.util.Map;

public interface CardMapper {
	List<Map<String, Object>> getAllCardsByUid(Integer uid);
	Integer updateCardByCardId(Card card);
	List<Card>  findOneCardByCardNum(Integer card_num);
	List<Card> findOneCardByCardId(Integer card_id);
	Integer updateCardPwdByCardId(Card card);
	Integer insertCard(Card card);
	List<Card> findMaxCardNum();
	List<Map<String, Object>> getAllCards();
	Integer deleteByCardId(Integer card_id);
	List<Card> findOneCardByBankId(Integer bank_id);
}

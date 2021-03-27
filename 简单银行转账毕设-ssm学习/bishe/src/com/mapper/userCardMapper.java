package com.mapper;

import java.util.List;

import com.model.UserCard;



public interface userCardMapper {
	List<UserCard> getUidByCardId(Integer card_id);
	List<UserCard> findMaxCardNum();
	Integer insertUserCard(UserCard userCard);
	List<UserCard> getCardsByUid(Integer uid);
	Integer deleteWithCardId(Integer card_id);
}

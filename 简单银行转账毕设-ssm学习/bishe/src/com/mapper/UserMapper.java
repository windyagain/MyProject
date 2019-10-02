package com.mapper;

import java.util.List;

import com.model.Card;
import com.model.User;

public interface UserMapper {
	List<User> findAllUser();
	List<User> findOneUser(User user);
	Integer insertUser(User user);
	Integer updateUserPwdByUserId(User user);
	List<User> findOneUserByUserId(Integer uid);
	Integer delUserByUserId(Integer uid);
}

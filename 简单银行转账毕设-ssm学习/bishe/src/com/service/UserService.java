package com.service;

import java.util.List;
import java.util.Map;

import com.model.Admin;
import com.model.Card;
import com.model.User;

public interface UserService {
    List<User> findAllUser();
    List<User> findOneUser(User user);
    Integer insertUser(User user);
    Map<String,Object> updateUserPwd(User user);
    Map<String, Object> deleteUserByUid(User user,Admin admin);
    Map<String, Object> adInsertUser(User user,Admin admin);
}
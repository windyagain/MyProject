package com.mlxg.service;

import com.mlxg.netty.ChatMsg;
import com.mlxg.pojo.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User findUserById(String id);

    User findUserByUserName(String username);

    User saveUser(User user) throws IOException;

    User editUser(User user);

    List<User> toGetMyFriends(String userId);

    String saveMsg(ChatMsg chatMsg);

    void updateMsgSigned(List<String> msgIdList);

    List<com.mlxg.pojo.ChatMsg> getUnReadMsgList(String acceptUserId);
}

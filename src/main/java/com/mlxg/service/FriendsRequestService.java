package com.mlxg.service;

import com.mlxg.pojo.FriendsRequest;
import com.mlxg.pojo.User;

import java.util.List;

public interface FriendsRequestService {

    Integer addOneFriendsRequest(String myUserId, String friendUserName);

    List<User> getFriendsRequest(String userId);

    Integer operFriendRequest(String acceptUserId, String sendUserId);

    Integer deleteOneFriendRequest(String acceptUserId, String sendUserId);
}

package com.mlxg.mapper;

import com.mlxg.pojo.FriendsRequest;

import java.util.List;

public interface FriendsRequestMapper {
    int deleteByPrimaryKey(String id);

    int insert(FriendsRequest record);

    int insertSelective(FriendsRequest record);

    FriendsRequest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FriendsRequest record);

    int updateByPrimaryKey(FriendsRequest record);

    List<FriendsRequest> selectMyFriendRequests(String userId);

    Integer updateStatus(FriendsRequest record);
}
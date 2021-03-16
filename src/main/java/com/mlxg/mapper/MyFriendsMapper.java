package com.mlxg.mapper;

import com.mlxg.pojo.MyFriends;

import java.util.List;

public interface MyFriendsMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyFriends record);

    int insertSelective(MyFriends record);

    MyFriends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyFriends record);

    int updateByPrimaryKey(MyFriends record);

    List<MyFriends> selectFriendsByMyUserId(String myUserId);

    List<MyFriends> selectMyFriends(String myUserId);
}
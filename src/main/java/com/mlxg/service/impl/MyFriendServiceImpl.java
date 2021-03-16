package com.mlxg.service.impl;

import com.mlxg.mapper.MyFriendsMapper;
import com.mlxg.pojo.MyFriends;
import com.mlxg.service.MyFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFriendServiceImpl implements MyFriendService {

    @Autowired
    private MyFriendsMapper myFriendsMapper;

    @Override
    public Boolean isMyFriend(String myuserid, String fdId) {
        List<MyFriends> myFriends = myFriendsMapper.selectFriendsByMyUserId(myuserid);
        boolean isAlreadyMyFriend = false;
        if (myFriends != null || fdId != null) {
            for (MyFriends mf : myFriends) {
                if (fdId.equals(mf.getId())) {
                    isAlreadyMyFriend = true;
                    break;
                }
            }
        }
        return isAlreadyMyFriend;
    }
}

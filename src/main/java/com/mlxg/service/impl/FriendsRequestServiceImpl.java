package com.mlxg.service.impl;

import com.mlxg.mapper.FriendsRequestMapper;
import com.mlxg.mapper.MyFriendsMapper;
import com.mlxg.mapper.UserMapper;
import com.mlxg.pojo.FriendsRequest;
import com.mlxg.pojo.MyFriends;
import com.mlxg.pojo.User;
import com.mlxg.service.FriendsRequestService;
import com.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendsRequestServiceImpl implements FriendsRequestService {

    @Autowired
    private Sid sid;

    @Autowired
    private FriendsRequestMapper friendsRequestMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyFriendsMapper myFriendsMapper;


    @Override
    public Integer addOneFriendsRequest(String myUserId, String friendUserName) {
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setSendUserId(myUserId);
        User friend = userMapper.selectByUserName(friendUserName);
        friendsRequest.setAcceptUserId(friend.getId());
        friendsRequest.setRequestDateTime(new Date());
        String insertId = sid.nextShort();
        friendsRequest.setId(insertId);
        Example example;
        int rows = friendsRequestMapper.insert(friendsRequest);
        return rows;
    }

    @Override
    public List<User> getFriendsRequest(String userId) {
        List<FriendsRequest> fds = friendsRequestMapper.selectMyFriendRequests(userId);
        List<User> userList = new ArrayList<>();
        for (FriendsRequest friendsRequest : fds) {
            if (friendsRequest != null && friendsRequest.getStatus().intValue() == 1) {
                User u = userMapper.selectByPrimaryKey(friendsRequest.getSendUserId());
                userList.add(u); // 正常情况不应该一个个查，至少一次查询所有，或者从redis拿数据。
            }
        }
        return userList;
    }

    @Override
    @Transactional
    public Integer operFriendRequest(String acceptUserId, String sendUserId) {
        MyFriends me = new MyFriends();
        String id = sid.nextShort();
        me.setId(id);
        me.setMyUserId(acceptUserId);
        me.setMyFriendUserId(sendUserId);

        // for friend
//        id = sid.nextShort();
//        MyFriends friend = new MyFriends();
//        friend.setId(id);
//        friend.setMyUserId(sendUserId);
//        friend.setMyFriendUserId(acceptUserId);
//        Integer res2 = myFriendsMapper.insert(friend);
        Integer res1 = myFriendsMapper.insert(me);
        FriendsRequest record = new FriendsRequest();
        record.setAcceptUserId(acceptUserId);
        record.setSendUserId(sendUserId);
        record.setStatus(0);
        friendsRequestMapper.updateStatus(record);
        return res1;
    }
}

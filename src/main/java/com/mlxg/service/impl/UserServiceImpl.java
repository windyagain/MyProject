package com.mlxg.service.impl;

import com.mlxg.enums.MsgSignFlagEnum;
import com.mlxg.mapper.ChatMsgMapper;
import com.mlxg.mapper.MyFriendsMapper;
import com.mlxg.mapper.UserMapper;
import com.mlxg.mapper.UserMapperCustom;
import com.mlxg.netty.ChatMsg;
import com.mlxg.pojo.MyFriends;
import com.mlxg.pojo.User;
import com.mlxg.service.UserService;
import com.mlxg.utils.FastDFSClient;
import com.mlxg.utils.FileUtils;
import com.mlxg.utils.QRCodeUtils;
import com.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyFriendsMapper myFriendsMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private UserMapperCustom userMapperCustom;

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public User saveUser(User user) {
        String userId = sid.nextShort();
        // 为每个注册用户生成一个唯一的二维码
        String qrCodePath = "I:\\JavaWork202011\\bd-images\\" + userId + "qrcode.png";
        // 创建二维码文件信息
        qrCodeUtils.createQRCode(qrCodePath, "bird_qrcode:" + user.getUsername());
        MultipartFile multipartFile = FileUtils.fileToMultipart(qrCodePath);
        String qrCodeUrl = "";
        try {
            qrCodeUrl = fastDFSClient.uploadQRCode(multipartFile);
            System.out.println(qrCodeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setId(userId);
        user.setQrcode(qrCodeUrl);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User editUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        User dbUser = userMapper.selectByPrimaryKey(user.getId());
        return dbUser;
    }

    @Override
    public List<User> toGetMyFriends(String userId) {
        List<MyFriends> myFriends = myFriendsMapper.selectMyFriends(userId);
        List<User> userList = new ArrayList<>();
        User u = null;
        for (MyFriends mf : myFriends) {
            if (mf.getMyUserId().equals(userId)) {
                u = userMapper.selectByPrimaryKey(mf.getMyFriendUserId());
                userList.add(u);
            } else if(mf.getMyFriendUserId().equals(userId)) {
                u = userMapper.selectByPrimaryKey(mf.getMyUserId());
                userList.add(u);
            }
        }
        return userList;
    }

    @Override
    public String saveMsg(ChatMsg chatMsg) {
        com.mlxg.pojo.ChatMsg msgDB = new com.mlxg.pojo.ChatMsg();
        String msgId = sid.nextShort();
        msgDB.setId(msgId);
        msgDB.setAcceptUserId(chatMsg.getReceiverId());
        msgDB.setSendUserId(chatMsg.getSenderId());
        msgDB.setCreateTime(new Date());
        msgDB.setSignFlag(MsgSignFlagEnum.unsign.type);
        msgDB.setMsg(chatMsg.getMsg());

        chatMsgMapper.insert(msgDB);

        return msgId;
    }

    @Override
    public void updateMsgSigned(List<String> msgIdList) {
       userMapperCustom.batchUpdateMsgSigned(msgIdList);
    }

    @Override
    public List<com.mlxg.pojo.ChatMsg> getUnReadMsgList(String acceptUserId) {
        List<com.mlxg.pojo.ChatMsg> result = chatMsgMapper.getUnReadMsgListByAcceptUid(acceptUserId);
        return result;
    }

}

package com.mlxg.controller;

import com.mlxg.enums.SearchFriendsStatusEnum;
import com.mlxg.pojo.FriendsRequest;
import com.mlxg.pojo.User;
import com.mlxg.service.FriendsRequestService;
import com.mlxg.utils.IWdzlJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/friendsrequest")
public class FriendsRequestController {

    @Autowired
    private FriendsRequestService friendsRequestService;

    @RequestMapping("/addFriendRequest")
    @ResponseBody
    public IWdzlJSONResult addFriendRequest(String myUserId, String friendUserName) {
        if (StringUtils.isBlank(myUserId) || StringUtils.isBlank(friendUserName)) {
            return IWdzlJSONResult.errorMsg("访问非法!");
        }

        int rows = friendsRequestService.addOneFriendsRequest(myUserId, friendUserName);
        if (rows == 1) {
            return IWdzlJSONResult.ok("发送成功！");
        } else if(rows == 2) {
            return IWdzlJSONResult.errorMsg("用户已经是好友了！");
        } else {
            return IWdzlJSONResult.errorMsg("发送失败，请重新添加！");
        }
    }

    @RequestMapping("/queryFriendRequest")
    @ResponseBody
    public IWdzlJSONResult queryFriendRequest(String userId) {
        if (StringUtils.isBlank(userId)) {
            return IWdzlJSONResult.errorMsg("访问非法!");
        }
        List<User> friendsRequestsList = friendsRequestService.getFriendsRequest(userId);
        return IWdzlJSONResult.ok(friendsRequestsList);
    }

    @RequestMapping("/operFriendRequest")
    @ResponseBody
    public IWdzlJSONResult operFriendRequest(String acceptUserId, String sendUserId, Integer operType) {
        if (StringUtils.isBlank(acceptUserId) || StringUtils.isBlank(sendUserId)) {
            return IWdzlJSONResult.errorMsg("访问非法!");
        }
        Integer rows = 0;
        if (operType.intValue() != 1) {
            // 删除该条记录
            rows = friendsRequestService.deleteOneFriendRequest(acceptUserId, sendUserId);
            return IWdzlJSONResult.ok();
        } else {
            try {
                rows = friendsRequestService.operFriendRequest(acceptUserId, sendUserId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (rows.intValue() != 1) {
            return IWdzlJSONResult.ok();
        } else {
            return IWdzlJSONResult.errorMsg("内部错误");
        }
    }

}

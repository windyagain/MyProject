package com.mlxg.controller;

import com.mlxg.bo.UserBo;
import com.mlxg.enums.SearchFriendsStatusEnum;
import com.mlxg.pojo.MyFriends;
import com.mlxg.pojo.User;
import com.mlxg.service.MyFriendService;
import com.mlxg.service.UserService;
import com.mlxg.utils.FastDFSClient;
import com.mlxg.utils.FileUtils;
import com.mlxg.utils.IWdzlJSONResult;
import com.mlxg.utils.MD5Utils;
import com.mlxg.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private MyFriendService myFriendService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUserById(String id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        System.out.println("---> very good  " + user);
        return user;
    }

    @RequestMapping("/myFriends")
    @ResponseBody
    public IWdzlJSONResult myFriends(String userId) {
       List<User> userList = userService.toGetMyFriends(userId);
       return IWdzlJSONResult.ok(userList);
    }


    @RequestMapping("/registerOrlogin")
    @ResponseBody
    public IWdzlJSONResult registerOrlogin(User user) throws IOException {
        User dbuser = userService.findUserByUserName(user.getUsername());
        if(dbuser != null) {
            if(!dbuser.getPassword().equals(MD5Utils.getPwd(user.getPassword()))) {
                return IWdzlJSONResult.errorMsg("密码不正确！");
            }
        } else {
            user.setNickname("憨皮");
            user.setQrcode("22");
            user.setPassword(MD5Utils.getPwd(user.getPassword()));
            user.setFaceImage("M00/00/00/wKgBFWA8PkCALyeIAA7Ql1gJPWg528_150x150.png");
            user.setFaceImageBig("M00/00/00/wKgBFWA8PkCALyeIAA7Ql1gJPWg528.png");
            dbuser = userService.saveUser(user);
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(dbuser, userVo);
        return IWdzlJSONResult.ok(userVo);
    }

    @RequestMapping("/setNickname")
    @ResponseBody
    public IWdzlJSONResult setNickname(User user) {
        if(user.getId() == null || "".equals(user.getNickname())) {
            return IWdzlJSONResult.errorMsg("昵称不可为空！");
        }
        User dbuser = userService.editUser(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(dbuser, userVo);
        return IWdzlJSONResult.ok(userVo);
    }


    @RequestMapping("/searchFriend")
    @ResponseBody
    public IWdzlJSONResult SearchFriend(String myUserId, String friendUserName) {
        if (StringUtils.isBlank(myUserId) || StringUtils.isBlank(friendUserName)) {
            return IWdzlJSONResult.errorMsg("访问非法!");
        }
        User friend = userService.findUserByUserName(friendUserName);
        if (friend == null) {
            return IWdzlJSONResult.errorMsg(SearchFriendsStatusEnum.USER_NOT_EXIST.msg);
        }
        Boolean isMyFriend = false;
        isMyFriend = myFriendService.isMyFriend(myUserId, friend.getId());
        if (friend.getId().equals(myUserId)) {
            return IWdzlJSONResult.errorMsg(SearchFriendsStatusEnum.NOT_YOURSELF.msg);
        } else if (isMyFriend) {
            return IWdzlJSONResult.errorMsg(SearchFriendsStatusEnum.ALREADY_FRIENDS.msg);
        }
        return IWdzlJSONResult.ok(friend);
    }

    public static Long getTimestamp(Date date){
        if (null == date) {
            return (long) 0;
        }
        String timestamp = String.valueOf(date.getTime());
        return Long.valueOf(timestamp);
    }

    @RequestMapping("/uploadFaceBase64")
    @ResponseBody
    public IWdzlJSONResult uploadFaceImage(UserBo userBo) throws Exception {
        // 获取前端传的base64，然后转为文件对象进行上传
        String base64Data = userBo.getFaceData();
        String userFacePath = "I:/JavaWork202011/bd-images/" + userBo.getUserId() + getTimestamp(new Date())  + "userFaceBase64.png";

        if(userBo.getUserId() == null) {
            return IWdzlJSONResult.errorMsg("上传失败！");
        }

        // 调用FileUtils类中的方法将base64字符串转为文件对象
        FileUtils.base64ToFile(userFacePath, base64Data);
        MultipartFile multipartFile = FileUtils.fileToMultipart(userFacePath);
        // 获取fastDFS上传图片的路径
        String url = fastDFSClient.uploadBase64(multipartFile);
        System.out.println(url);
        String thump = "_150x150.";
        String[] arr = url.split("\\.");
        String thumpImgUrl = arr[0] + thump + arr[1];

        // 更新用户头像
        User user = new User();
        user.setId(userBo.getUserId());
        user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);

        User result = userService.editUser(user);
        return IWdzlJSONResult.ok(result);
    }

}

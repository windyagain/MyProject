package com.mlxg.mapper;



import com.mlxg.vo.FriendsRequestVO;
import com.mlxg.vo.MyFriendsVO;

import java.util.List;

public interface UserMapperCustom {
    List<FriendsRequestVO> queryFriendRequestList(String acceptUserId);
    List<MyFriendsVO> queryMyFriends(String userId);
    void batchUpdateMsgSigned(List<String> msgIdList);
}

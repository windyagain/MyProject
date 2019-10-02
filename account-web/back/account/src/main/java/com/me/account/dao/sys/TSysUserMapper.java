package com.me.account.dao.sys;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.me.account.entity.sys.TSysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSysUserMapper extends BaseMapper<TSysUser> {
    List<TSysUser> findAllUsers();
    /**
     * 登录验证用户名
     */
    public List<TSysUser> loginCheckUserName(String userName);
}

package com.me.account.service.sys.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.me.account.common.utils.MD5;
import com.me.account.dao.sys.TSysUserMapper;
import com.me.account.entity.sys.Criteria;
import com.me.account.entity.sys.TSysUser;
import com.me.account.service.sys.TSysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
@Transactional(rollbackFor = Exception.class)
public class TSysUserServiceImpl implements TSysUserService {
    @Resource
    private TSysUserMapper tSysUserMapper;
    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> userInfos = tSysUserMapper.selectMaps(new EntityWrapper<TSysUser>());
        return userInfos;
    }

    @Override
    public String selectUserByLogin(Criteria criteria) throws Exception{
        String userName = criteria.getAsString("userName");
        String passwordIn = criteria.getAsString("passwordIn");
        String getpass = MD5.encrypt(passwordIn, userName);
        System.out.println(userName+"----"+passwordIn+"----enc: "+getpass);
        // 条件查询
        List<TSysUser> list = tSysUserMapper.loginCheckUserName(userName);
        if (null == list || list.size() != 1) {
            // 没有此用户名
            return "00";
        }
        TSysUser dataBaseUser = list.get(0);
        String md5Pwd = MD5.encrypt(passwordIn, userName);
        if (!md5Pwd.equals(dataBaseUser.getPassword())) {
            // 密码不正确
            return "11";
        }
        // controller中取出放到session中
        criteria.put("baseUser", dataBaseUser);
        return "01";
    }

    @Override
    public TSysUser getUserInfoByPk(Long pkSysUser) throws Exception {
        //List<Map<String, Object>> list = tSysUserMapper.selectMaps(new EntityWrapper<TSysUser>().eq("pk_sys_user",pkSysUser));
        List<Map<String, Object>> list = tSysUserMapper.selectMaps(new EntityWrapper<TSysUser>().eq("pk_sys_user",pkSysUser));
        Map<String, Object> tempMap = new HashMap<String,Object>();

        if(list.size()>0){
            tempMap = list.get(0);
            String userStr = JSON.toJSONString(tempMap);
            TSysUser sysUser = JSONObject.parseObject(userStr,TSysUser.class);
            return sysUser;
        }else{
            return null;
        }
    }
}

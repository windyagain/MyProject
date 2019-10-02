package com.me.account.controller.sys;


import com.me.account.common.utils.WebUtils;
import com.me.account.entity.sys.TSysUser;
import com.me.account.service.sys.impl.TSysUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private TSysUserServiceImpl tSysUserServiceImpl;
    @GetMapping(value = "/getUserInfo1")
    public String getUserInfo(@RequestBody Map map) {
        try {
            String pkSysUser = map.get("pkSysUser")==null?"":map.get("pkSysUser").toString();
            if(StringUtils.isBlank(pkSysUser)){
                return WebUtils.getData(false, "主键不能为空", null);
            }
            //System.out.println("userName========"+userName);
            TSysUser sysUser = tSysUserServiceImpl.getUserInfoByPk(Long.parseLong(pkSysUser));
            return WebUtils.getData(true, "", sysUser);
        } catch (Exception e) {
            logger.error("getUserInfo-method-Exception: ", e);
            return WebUtils.getData(false, "获取用户信息异常", null);
        }
    }

    @GetMapping(value = "/getUserInfo")
    public String getUserInfoByToken(@RequestParam Map map) {
        try {
            String pkSysUser = map.get("token")==null?"":map.get("token").toString();
            if(StringUtils.isBlank(pkSysUser)){
                return WebUtils.getData(false, "主键不能为空", null);
            }
            //System.out.println("userName========"+userName);
            TSysUser sysUser = tSysUserServiceImpl.getUserInfoByPk(Long.parseLong(pkSysUser));
            return WebUtils.getData(true, "", sysUser);
        } catch (Exception e) {
            logger.error("getUserInfo-method-Exception: ", e);
            return WebUtils.getData(false, "获取用户信息异常", null);
        }
    }
}

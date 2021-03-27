package com.me.account.controller.sys;

import com.alibaba.fastjson.JSON;
import com.me.account.common.staticconst.WebConstants;
import com.me.account.common.utils.WebUtils;
import com.me.account.entity.sys.TSysUser;
import com.me.account.entity.sys.Criteria;
import com.me.account.service.sys.impl.TSysUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private TSysUserServiceImpl tSysUserServiceImpl;
    @PostMapping("/doLogin")
    public String login(@RequestBody Map map, HttpSession session){
        try {
            String account = map.get("account")==null?"":map.get("account").toString();
            String password = map.get("password")==null?"":map.get("password").toString();
           // String validateCode = map.get("validateCode")==null?"":map.get("validateCode").toString();
            if (StringUtils.isBlank(account)) {
                return WebUtils.getData(false, "帐号不能为空！",null);
            }
            if (StringUtils.isBlank(password)) {
                return WebUtils.getData(false, "密码不能为空！",null);
            }
//            if (StringUtils.isBlank(validateCode)) {
//                return WebUtils.getData(false, "验证码不能为空！",null);
//            }
//            logger.info("account=="+account+"IP=="+this.getIpAddr(request)+"验证码=="+validateCode);

            //获得验证码
           // String sessionValidateCode = (String)session.getAttribute("VALIDATE_CODE");
            //验证码失效
            //session.setAttribute("VALIDATE_CODE", null);
            //校验验证码
           // boolean flag=validateCode.equalsIgnoreCase(sessionValidateCode);

//            if(!flag){
//                return WebUtils.getData(false, "验证码错误！",null);
//            }

            Criteria criteria = new Criteria();
            criteria.clear();
            criteria.put("userName", account);
            criteria.put("passwordIn", password);
            String result = this.tSysUserServiceImpl.selectUserByLogin(criteria);
            if ("01".equals(result)) {
                TSysUser baseUser = (TSysUser) criteria.get("baseUser");
                session.setAttribute(WebConstants.SESSION_USER_NO, String.valueOf(baseUser.getPkSysUser()));
                session.setAttribute(WebConstants.CURRENT_USER, baseUser);
                String temp = session.getAttribute(WebConstants.SESSION_USER_NO).toString();
                logger.info("登陆成功----"+baseUser.getUserName());
                String temp2 = (String)session.getAttribute(WebConstants.SESSION_USER_NO);
                //更新用户登录信息
                TSysUser updateUser = new TSysUser();
                Date date = new Date();
//                updateUser.setPkSysUser(baseUser.getPkSysUser());
//                String ip = this.getIpAddr(request);
//                updateUser.setLastLoginTime(date);
//                updateUser.setLastLoginIp(ip);
//                tSysUserService.updateByPrimaryKey(updateUser);
                Map mp = new HashMap();
                mp.put("token",baseUser.getPkSysUser());
                return WebUtils.getData(true, "登陆成功！",mp);
            } else if ("00".equals(result)) {
                return WebUtils.getData(false, "用户名错误！",null);
            } else {
                return WebUtils.getData(false, "密码错误！",null);
            }
        } catch (Exception e) {
            logger.error("login-method-Exception: ", e);
            return WebUtils.getData(false, "用户登录异常！",null);
        }
    }
    @GetMapping("/list")
    public List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = this.tSysUserServiceImpl.getList();
        System.out.println("ye oks");
        return list;
    }

    /**
     * 退出登录
     */
    @PostMapping( "/logout")
    public void logout(HttpSession session) {
        try {
            session.invalidate();
        } catch (Exception e) {
            logger.error("logout-method-Exception: ", e);
        }
    }


}

package com.me.account.entity.sys;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TSysUser {
    private static final long serialVersionUID = 1L;
    private Long pkSysUser;//用户ID
    private String userName;//用户名称
    private String password;//用户密码
    private String userType;//用户类型
    private String userLevel;//用户级别
    private String mobile;//手机号码
    private String phone;//电话号码
    private String area;//所属地区
    private String email;//电子信箱
    private String remark;//用户备注
    private Date lastLoginTime;//最后登录时间
    private String lastLoginIp;//最后登录IP
    private Integer isValid;//有效标识
    private String createBy;//CREATE_BY
    private Date createDate;//CREATE_DATE
    private String updateBy;//UPDATE_BY
    private Date updateDate;//UPDATE_DATE
    private String realName;//真实姓名
    private String isCheck;//发布新闻是否需要审核
}

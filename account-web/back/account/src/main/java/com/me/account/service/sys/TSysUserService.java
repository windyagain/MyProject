package com.me.account.service.sys;

import com.me.account.entity.sys.Criteria;
import com.me.account.entity.sys.TSysUser;

import java.util.List;
import java.util.Map;

public interface TSysUserService {
    List<Map<String,Object>> getList();
    /**
     * 用户登录查找
     *
     * @param criteria
     * @return 00：失败，01：成功 ,其他情况
     */
    public String selectUserByLogin(Criteria criteria) throws Exception;

    /**
     * 根据主键获取记录信息
     */
    public TSysUser getUserInfoByPk(Long pkSysUser)  throws Exception;
}

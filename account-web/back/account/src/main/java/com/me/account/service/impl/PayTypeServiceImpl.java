package com.me.account.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.account.dao.PayTypeMapper;
import com.me.account.entity.PayType;
import com.me.account.service.PayTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("PayTypeService")
@Transactional(rollbackFor = Exception.class)
public class PayTypeServiceImpl implements PayTypeService {
    @Resource
    private PayTypeMapper payTypeMapper;

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> userInfos = payTypeMapper.selectMaps(new EntityWrapper<PayType>());
        return userInfos;
    }
}

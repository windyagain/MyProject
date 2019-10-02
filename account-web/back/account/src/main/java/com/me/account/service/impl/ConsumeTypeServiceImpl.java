package com.me.account.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.account.dao.ConsumeTypeMapper;
import com.me.account.entity.ConsumeType;
import com.me.account.service.ConsumeRecordService;
import com.me.account.service.ConsumeTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("ConsumeTypeService")
@Transactional(rollbackFor = Exception.class)
public class ConsumeTypeServiceImpl implements ConsumeTypeService {
    @Resource
    private ConsumeTypeMapper consumeTypeMapper;

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> infos = consumeTypeMapper.selectMaps(new EntityWrapper<ConsumeType>());
        return infos;
    }
}

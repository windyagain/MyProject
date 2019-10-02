package com.me.account.service;

import com.me.account.entity.ConsumeRecord;
import com.me.account.entity.sys.ComboPager;
import com.me.account.entity.sys.Criteria;
import com.me.account.entity.sys.Pager;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ConsumeRecordService {
    List<Map<String,Object>> getList();
    List<ConsumeRecord> getConsumeRecordByUserPkId(BigInteger tsysUserId);
    public Integer addConsumeRecord(ConsumeRecord cr)  throws Exception;

    /**
     * 分页查询列表
     */
    public ComboPager queryListByPager(Criteria example, Pager pager) throws Exception;
    public Map queryList(Criteria example) throws Exception;
    public Map queryListAccordingConsumeMonth(Criteria example) throws Exception;

}

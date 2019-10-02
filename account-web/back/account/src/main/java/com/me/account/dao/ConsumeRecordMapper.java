package com.me.account.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.me.account.entity.ConsumeRecord;
import com.me.account.entity.sys.Criteria;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ConsumeRecordMapper extends BaseMapper<ConsumeRecord> {

    List<ConsumeRecord> getConsumeRecordByUserPkId(BigInteger tsysUserId);

    /**
     * 获取消费记录表序列
     */
    public Long seqConsumeRecord();

    /**
     * 不同条件查询
     */
    public List<ConsumeRecord> selectByCondition(Criteria example) throws Exception;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Criteria example);

    /**
     * 根据条件查询记录
     */
    public List<ConsumeRecord> selectByExample(Criteria example);
}

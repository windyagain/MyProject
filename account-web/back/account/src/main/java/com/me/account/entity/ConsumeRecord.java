package com.me.account.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class ConsumeRecord {
    private Long consumeRecordId;
    //消费类型
    private Long consumeTypeIdFk;
    @TableField(exist = false)
    private String consumeType;
    //支付类型
    private Long payTypeIdFk;
    @TableField(exist = false)
    private String payType;

    private Date consumeTime;
    private BigDecimal consumeAmount;
    private String consumeDesc;
    private String consumeAddr;
    private Long tsysUserIdFk;

}

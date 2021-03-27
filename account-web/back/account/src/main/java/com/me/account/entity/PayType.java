package com.me.account.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PayType {
    private Long pay_type_id;
    private String pay_type;
    private String pay_type_desc;

}

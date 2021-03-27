package com.model;

import java.math.BigDecimal;

public class Card {

	private Integer card_id;
	private Integer card_num;
	private String u_name;
	private String bank_id;
	private BigDecimal money;
	private String pay_pwd;
	private String old_pay_pwd;
	public Integer getCard_id() {
		return card_id;
	}
	public void setCard_id(Integer card_id) {
		this.card_id = card_id;
	}

	public String getOld_pay_pwd() {
		return old_pay_pwd;
	}
	public void setOld_pay_pwd(String old_pay_pwd) {
		this.old_pay_pwd = old_pay_pwd;
	}
	public Integer getCard_num() {
		return card_num;
	}
	public void setCard_num(Integer card_num) {
		this.card_num = card_num;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getPay_pwd() {
		return pay_pwd;
	}
	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}
	@Override
	public String toString() {
		return "Card [card_id=" + card_id + ", card_num=" + card_num + ", u_name=" + u_name + ", bank_id=" + bank_id
				+ ", money=" + money + ", pay_pwd=" + pay_pwd + "]";
	}
	
}

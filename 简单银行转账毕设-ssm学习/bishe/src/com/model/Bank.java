package com.model;

public class Bank {

	private Integer bank_id;
	private String bank_name;
	public Integer getBank_id() {
		return bank_id;
	}
	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	@Override
	public String toString() {
		return "Bank [bank_id=" + bank_id + ", bank_name=" + bank_name + "]";
	}
	
}

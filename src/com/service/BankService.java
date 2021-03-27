package com.service;

import java.util.List;
import java.util.Map;

import com.model.Admin;
import com.model.Bank;

public interface BankService {
	
	List<Bank> getAllBanks();
	Map<String,Object> InsertBank(Bank bank,Admin admin);
	Map<String,Object> DeleteBank(Bank bank,Admin admin);
}

package com.mapper;

import java.util.List;
import com.model.Bank;

public interface BankMapper{
	List<Bank> getAllBanks();
	Integer insertBank(Bank bank);
	Integer deleteByBankId(Bank bank);
	List<Bank> getOneBank(Bank bank);
}

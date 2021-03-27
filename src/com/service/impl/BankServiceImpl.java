package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.BankMapper;
import com.mapper.CardMapper;
import com.mapper.LogMapper;
import com.mapper.UserMapper;
import com.model.Admin;
import com.model.Bank;
import com.model.Card;
import com.model.Log;
import com.service.BankService;

@Service
@Transactional
public class BankServiceImpl implements BankService{

	@Resource
    public BankMapper bankMapper;
	@Resource
	public LogMapper logMapper;
	@Resource
	public CardMapper cardMapper;
	@Override
	public List<Bank> getAllBanks() {
		List<Bank> bankList =  bankMapper.getAllBanks();
		return bankList;
	}
	@Override
	public Map<String, Object> InsertBank(Bank bank,Admin admin) {
		Map<String, Object> map = new HashMap<String,Object>();
			Integer rows =0;
			String mes = "银行添加成功,将跳转至银行列表页面";
			Integer flag = 1;
			String url = "/bishe/bank/adGetAllBank";
			if("".equals(bank.getBank_name()) || "".equals(admin.getAdmin_name()) || "".equals(admin.getAid())) {
				mes = "数据不合法";
				flag = 0;
			}
			if(flag > 0) {
				try {
					rows = bankMapper.insertBank(bank);
					logMapper.insertAdLog(admin.getAid(), "管理员"+admin.getAdmin_name()+"添加"+bank.getBank_name()+"类型银行卡");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rows < 1) {
				mes = "银行添加失败！";
			}else {
				//插入日志
				
			}
			map.put("res",rows);
			map.put("mes", mes);
			map.put("url", url);

			return map;
	}
	@Override
	public Map<String, Object> DeleteBank(Bank bank, Admin admin) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		Integer rows =0;
		String mes = "删除成功,将跳转至银行列表页面";
		Integer flag = 1;
		String url = "/bishe/bank/adGetAllBank";
		
		Bank dbBank = new Bank();
		//System.out.println(admin);
		/*if("".equals(admin.getAdmin_name()))System.out.println("ok");
		else System.out.println("not ok");*/
		//if(admin.getAid() == null  )Syste.out.println("aid is null");
		//if(admin.getAdmin_name() == null) {System.out.println("yes is ok");}
		//if(admin.getAid() != null)System.out.println("integer is ");
		if("".equals(bank.getBank_id()) || "".equals(admin.getAdmin_name()
	) || "".equals(admin.getAid()) || admin.getAdmin_name() == null || admin.getAid() == null
				) {
			mes = "数据不合法";
			flag = 0;
		}
		List<Card> cList =  cardMapper.findOneCardByBankId(bank.getBank_id());
		if(cList.size() > 0) {
			mes = "请先删除使用本银行类型的数据！";
			flag = 0;
		}
		if(flag > 0) {
			dbBank = bankMapper.getOneBank(bank).get(0);
			try {
				rows = bankMapper.deleteByBankId(bank);
				if(rows < 1) {
					mes = "银行类型删除失败！";
				}else {
					//插入日志
					logMapper.insertAdLog(admin.getAid(), "管理员"+admin.getAdmin_name()+"删除"+dbBank.getBank_name()+"类型银行卡");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		map.put("res",rows);
		map.put("mes", mes);
		map.put("url", url);

		return map;
	}

}

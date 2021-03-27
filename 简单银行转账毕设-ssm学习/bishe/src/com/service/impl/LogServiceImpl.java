package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.CardMapper;
import com.mapper.LogMapper;
import com.model.Bank;
import com.model.Log;
import com.service.LogService;

@Service
@Transactional
public class LogServiceImpl implements LogService{

	@Resource
    public LogMapper logMapper;
	@Override
	public List<Log> getLogs() {
		// TODO Auto-generated method stub
		List<Log> listLogs = logMapper.getLogs();
		return listLogs;
	}
	@Override
	public List<Log> getLogsByUid(Log log) {
		// TODO Auto-generated method stub
		List<Log> listLogs = logMapper.getLogByUid(log);
		return listLogs;
	}
	@Override
	public Map<String, Object> deleteByLogId(Log log) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		
		Integer rows = 0;
		String mes = "日志删除成功!";
		try {
			
			rows = logMapper.deleteByLogId(log.getLog_id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("res", rows);
		map.put("mes", mes);
		return map;
	}
	

	@Override
	public List<Log> getLogByLogId(Log log) {
		// TODO Auto-generated method stub
		List<Log> list = null;

		try {
			
			list = logMapper.getLogByLogId(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Integer insertOneLog(Log log) {
		// TODO Auto-generated method stub
		Integer rows =0;
		try {
			rows = logMapper.insertLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}
	
}

package com.service;

import java.util.List;
import java.util.Map;

import com.model.Log;

public interface LogService {
	List<Log> getLogs();
	List<Log> getLogsByUid(Log log);
	Map<String,Object> deleteByLogId(Log log);
	List<Log> getLogByLogId(Log log);
	Integer insertOneLog(Log log);
}

package com.mapper;

import java.util.List;

import com.model.Log;

public interface LogMapper {
	List<Log> getLogs();
	List<Log> getLogByUid(Log log);
	List<Log> getLogByLogId(Log log);
	Integer insertLog(Log log);
	Integer deleteByLogId(Integer log_id);
	Integer insertAdLog(Integer aid,String content);
}

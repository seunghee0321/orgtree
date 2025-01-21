package com.dbinc.cloudoffice.orgtree.mapper.api;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.SyncLogVO;

//@Repository
@Mapper
public interface SyncLogMapper {
	int insertSyncLog(SyncLogVO syncLogVO);
	int selectSuccessSyncLog(Map<String, Object> paramsMap);
	int selectSuccessSyncLogCnt(Map<String, Object> paramsMap);
}

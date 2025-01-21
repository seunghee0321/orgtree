package com.dbinc.cloudoffice.orgtree.mapper.api;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface SyncDeptMasterMapper {
	int deleteSyncDeptMaster(Map<String, Object> paramsMap);
	int insertSyncDeptMaster(Map<String, Object> paramsMap);
	int insertDeptMasterFromSync(Map<String, Object> paramsMap);
}

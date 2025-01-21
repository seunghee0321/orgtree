package com.dbinc.cloudoffice.orgtree.mapper.api;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface SyncEmpMasterMapper {
	int deleteSyncEmpMaster(Map<String, Object> paramsMap);
	int insertSyncEmpMaster(Map<String, Object> paramsMap);
	int insertEmpMasterFromSync(Map<String, Object> paramsMap);
	int insertEmpMasterFromSyncForApi(Map<String, Object> paramsMap);
	int insertEmpConfigFromSync(Map<String, Object> paramsMap);
	int insertEmpAuthorityFromSync(Map<String, Object> paramsMap);
}

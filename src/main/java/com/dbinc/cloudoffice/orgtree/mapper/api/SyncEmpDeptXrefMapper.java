package com.dbinc.cloudoffice.orgtree.mapper.api;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface SyncEmpDeptXrefMapper {
	int deleteSyncEmpDeptXref(Map<String, Object> paramsMap);
	int insertSyncEmpDeptXref(Map<String, Object> paramsMap);
	int insertEmpDeptXrefFromSync(Map<String, Object> paramsMap);
}

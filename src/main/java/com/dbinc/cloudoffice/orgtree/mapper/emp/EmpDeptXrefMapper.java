package com.dbinc.cloudoffice.orgtree.mapper.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.EmpDeptXrefListVO;

//@Repository
@Mapper
public interface EmpDeptXrefMapper {
	List<EmpDeptXrefListVO> selectEmpDeptXrefList(Map<String, Object> paramsMap);
	int updateEmpDeptXref(Map<String, Object> paramsMap);
	int insertEmpDeptXref(Map<String, Object> paramsMap);
	int deleteEmpDeptXref(Map<String, Object> paramsMap);
	int updateEmpDeptXrefDetail(Map<String, Object> paramsMap);
	int deleteEmpDeptXrefDetail(Map<String, Object> paramsMap);
	int insertUpdateEmpDeptXref(Map<String, Object> paramsMap);
	int deleteEmpDeptXrefFromSync(Map<String, Object> paramsMap);
	int deleteAllEmpDeptXref(Map<String, Object> paramsMap);
}
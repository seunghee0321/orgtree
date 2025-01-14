package com.dbinc.cloudoffice.orgtree.mapper.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.EmpDetailVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpListVO;

@Repository
@Mapper
public interface EmpMapper {
	int selectEmpListCnt(Map<String, Object> paramsMap);
	List<EmpListVO> selectEmpList(Map<String, Object> paramsMap);
	EmpDetailVO selectEmpInfo(Map<String, Object> paramsMap);
	int updateEmpMaster(Map<String, Object> paramsMap);
	int insertEmpMaster(Map<String, Object> paramsMap);
	int insertUpdateEmpMaster(Map<String, Object> paramsMap);
	int deleteEmpMaster(Map<String, Object> paramsMap);
	int replaceEmpMaster(Map<String, Object> paramsMap);
	int updateEmpMasterXrefInfo(Map<String, Object> paramsMap);
	int deleteEmpMasterFromSync(Map<String, Object> paramsMap);
}

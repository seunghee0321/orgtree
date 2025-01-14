package com.dbinc.cloudoffice.orgtree.mapper.dept;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.DeptMasterVO;

@Repository
@Mapper
public interface DeptMasterMapper {
	List<DeptMasterVO> selectDeptList(Map<String, Object> paramsMap);
	String selectParentDeptCd(Map<String, Object> paramsMap);
	DeptMasterVO selectDeptInfo(Map<String, Object> paramsMap);
	int insertDeptInfo(Map<String, Object> paramsMap);
	int updateDeptInfo(Map<String, Object> paramsMap);
	int updateDeptParent(Map<String, Object> paramsMap);
	int insertUpdateDeptInfo(Map<String, Object> paramsMap);
	int updateDeptMasterUseNot(Map<String, Object> paramsMap);
	DeptMasterVO selectDeptCdByParent(Map<String, Object> paramsMap);
	int selectDeptCdByDeptNmCnt(Map<String, Object> paramsMap);
	DeptMasterVO selectDeptCdByDeptNm(Map<String, Object> paramsMap);
}

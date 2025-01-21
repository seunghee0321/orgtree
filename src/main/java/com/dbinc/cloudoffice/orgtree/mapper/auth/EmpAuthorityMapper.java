package com.dbinc.cloudoffice.orgtree.mapper.auth;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.EmpAuthListVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpAuthorityVO;

//@Repository
@Mapper
public interface EmpAuthorityMapper {
	int insertEmpAuthority(Map<String, Object> paramsMap);
	int deleteEmpAuthority(Map<String, Object> paramsMap);
	int insertUpdateEmpAuthority(Map<String, Object> paramsMap);
	int insertEmpRoleAdmin(EmpAuthorityVO empAuthorityVO);
	int deleteEmpRoleAdmin(EmpAuthorityVO empAuthorityVO);
	int updateEmpAuthorityXref(Map<String, Object> paramsMap);
	int selectEmpAuthorityListCnt(Map<String, Object> paramsMap);
	List<EmpAuthListVO> selectEmpAuthorityList(Map<String, Object> paramsMap);
	int selectEmpRoleUserListCnt(Map<String, Object> paramsMap);
	List<EmpAuthListVO> selectEmpRoleUserList(Map<String, Object> paramsMap);
	int deleteEmpAuthorityFromSync(Map<String, Object> paramsMap);
}

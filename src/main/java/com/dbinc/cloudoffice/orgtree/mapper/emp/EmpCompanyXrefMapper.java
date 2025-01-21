package com.dbinc.cloudoffice.orgtree.mapper.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.EmpCompanyXrefVO;

//@Repository
@Mapper
public interface EmpCompanyXrefMapper {
	int selectEmpCompanyXrefListCnt(Map<String, Object> paramsMap);
	List<EmpCompanyXrefVO> selectEmpCompanyXrefList(Map<String, Object> paramsMap);
	int deleteEmpCompanyXref(EmpCompanyXrefVO empCompanyXrefVO);
	int insertEmpCompanyXref(EmpCompanyXrefVO empCompanyXrefVO);
}
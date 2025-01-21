package com.dbinc.cloudoffice.orgtree.mapper.company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;

//@Repository
@Mapper
public interface CompanyMapper {
	List<CompanyMasterVO> selectCompanySelBox(Map<String, Object> paramsMap);
	List<CompanyMasterVO> selectCompanyList(Map<String, Object> paramsMap);
	int selectCompanyListCnt(Map<String, Object> paramsMap);
	List<CompanyMasterVO> selectEpSyncCompanyList(Map<String, Object> paramsMap);
	CompanyMasterVO selectCompanyForSync(Map<String, Object> paramsMap);

	void updateApiAuthKey(Map<String, Object> paramsMap);
	String selectApiAuthKey(String domainId, String companyCd);
}

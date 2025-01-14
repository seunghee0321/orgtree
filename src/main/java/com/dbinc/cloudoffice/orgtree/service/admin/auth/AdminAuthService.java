package com.dbinc.cloudoffice.orgtree.service.admin.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbinc.cloudoffice.orgtree.mapper.auth.EmpAuthorityMapper;
import com.dbinc.cloudoffice.orgtree.mapper.company.CompanyMapper;
import com.dbinc.cloudoffice.orgtree.mapper.config.EmpConfigMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpCompanyXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpDeptXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpMapper;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.util.Util;
import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpAuthListVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpAuthorityVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpCompanyXrefVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminAuthService {

	private final EmpAuthorityMapper empAuthorityMapper;
	private final EmpCompanyXrefMapper empCompanyXrefMapper;
	private final CompanyMapper companyMapper;
	
	/**
	 * @author minwest61
	 * @apiNote EMP_AUTHORITY에서 ROLE_ADMIN인 사용자(관리자) 목록 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectEmpAuthorityList(Map<String, Object> paramsMap) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    List<EmpAuthListVO> empAuthList = new ArrayList<>();
	    int page = Integer.parseInt(paramsMap.get("page").toString());
	    int rows = Integer.parseInt(paramsMap.get("rows").toString());
	    int total = 1; // 총 페이지 수
	    int records = empAuthorityMapper.selectEmpAuthorityListCnt(paramsMap);
	    
        paramsMap.putAll(Util.getPagingForSQL(page, rows, total, records));
        if (records < 1)
        {
        	page = 0;
        	total = 0;
        }
        else 
        {
        	page = (int) paramsMap.remove("page");
        	total = (int) paramsMap.remove("total");
        	empAuthList = empAuthorityMapper.selectEmpAuthorityList(paramsMap); // EMP_AUTHORITY 테이블에서 ROLE_ADMIN인 관리자 목록 조회
        }
	    
	    rrVO.setPage(String.valueOf(page));
	    rrVO.setTotal(String.valueOf(total));
	    rrVO.setRecords(String.valueOf(records));
	    rrVO.setData(empAuthList);
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote  EMP_AUTHORITY에서 ROLE_ADMIN이 아닌 사용자 목록 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectEmpRoleUserList(Map<String, Object> paramsMap) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    List<EmpAuthListVO> empAuthList = new ArrayList<>();
	    int page = Integer.parseInt(paramsMap.get("page").toString());
	    int rows = Integer.parseInt(paramsMap.get("rows").toString());
	    int total = 1; // 총 페이지 수
	    int records = empAuthorityMapper.selectEmpRoleUserListCnt(paramsMap);
	    
        paramsMap.putAll(Util.getPagingForSQL(page, rows, total, records));
        if (records < 1)
        {
        	page = 0;
        	total = 0;
        }
        else 
        {
        	page = (int) paramsMap.remove("page");
        	total = (int) paramsMap.remove("total");
        	empAuthList = empAuthorityMapper.selectEmpRoleUserList(paramsMap); // EMP_AUTHORITY 테이블에서 ROLE_ADMIN이 아닌 사용자 목록 조회
        }
	    
	    rrVO.setPage(String.valueOf(page));
	    rrVO.setTotal(String.valueOf(total));
	    rrVO.setRecords(String.valueOf(records));
	    rrVO.setData(empAuthList);
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote 관리자 권한 INSERT
	 * @param empAuthorityArr
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO insertEmpRoleAdmin(EmpAuthorityVO[] empAuthorityArr) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    
	    int resultCnt = 0;
	    for(EmpAuthorityVO obj : empAuthorityArr) {
	    	obj.setAuthorityName("ROLE_ADMIN");
	    	int result = empAuthorityMapper.insertEmpRoleAdmin(obj); // EMP_AUTHORITY 테이블에 ROLE_ADMIN 권한 INSERT
	    	
	    	// EMP_COMPANY_XREF 테이블에 관리자 INSERT
	    	EmpCompanyXrefVO empCompanyXrefVO = new EmpCompanyXrefVO();
	    	empCompanyXrefVO.domainId =  obj.getDomainId();
	    	empCompanyXrefVO.companyCd =  obj.getCompanyCd();
	    	empCompanyXrefVO.email =  obj.getEmail();
	    	empCompanyXrefVO.createUsr =  obj.getCreateUsr();
	    	int resultXref = empCompanyXrefMapper.insertEmpCompanyXref(empCompanyXrefVO);
	    	if(result > 0 && resultXref > 0) resultCnt += 1;
	    }

	    if(resultCnt == empAuthorityArr.length)
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote 관리자 권한 DELETE
	 * @param empAuthorityArr
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO deleteEmpRoleAdmin(EmpAuthorityVO[] empAuthorityArr) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    
	    int resultCnt = 0;
	    for(EmpAuthorityVO obj : empAuthorityArr) {
	    	obj.setAuthorityName("ROLE_ADMIN");
	    	int result = empAuthorityMapper.deleteEmpRoleAdmin(obj); // EMP_AUTHORITY 테이블에서 DELETE
	    	
	    	// EMP_COMPANY_XREF 테이블에서 관리자 DELETE
	    	EmpCompanyXrefVO empCompanyXrefVO = new EmpCompanyXrefVO();
	    	empCompanyXrefVO.domainId =  obj.getDomainId();
	    	empCompanyXrefVO.email =  obj.getEmail();
	    	int resultXref = empCompanyXrefMapper.deleteEmpCompanyXref(empCompanyXrefVO);
	    	if(result > 0 && resultXref > 0) resultCnt += 1;
	    }

	    if(resultCnt == empAuthorityArr.length)
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote 관리자 할당 법인목록(EMP_COMPANY_XREF) 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectEmpCompanyXref(Map<String, Object> paramsMap) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    List<EmpCompanyXrefVO> empCompanyXrefList = new ArrayList<>();
	    int page = Integer.parseInt(paramsMap.get("page").toString());
	    int rows = Integer.parseInt(paramsMap.get("rows").toString());
	    int total = 1; // 총 페이지 수
	    int records = empCompanyXrefMapper.selectEmpCompanyXrefListCnt(paramsMap);
	    
        paramsMap.putAll(Util.getPagingForSQL(page, rows, total, records));
        if (records < 1)
        {
        	page = 0;
        	total = 0;
        }
        else 
        {
        	page = (int) paramsMap.remove("page");
        	total = (int) paramsMap.remove("total");
        	empCompanyXrefList = empCompanyXrefMapper.selectEmpCompanyXrefList(paramsMap);
        }
	    
	    rrVO.setPage(String.valueOf(page));
	    rrVO.setTotal(String.valueOf(total));
	    rrVO.setRecords(String.valueOf(records));
	    rrVO.setData(empCompanyXrefList);
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote EMP_COMPANY_XREF에 관리자 할당 법인 DELETE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO deleteEmpCompanyXref(EmpCompanyXrefVO[] empCompanyXrefArr) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    
	    int resultCnt = 0;
	    for(EmpCompanyXrefVO obj : empCompanyXrefArr) {
	    	int result = empCompanyXrefMapper.deleteEmpCompanyXref(obj);
	    	if(result > 0) resultCnt += 1;
	    }

	    if(resultCnt == empCompanyXrefArr.length)
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote 관리자에게 할당할 수 있는 법인(EMP_COMPANY_XREF) 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectCompanyList(Map<String, Object> paramsMap) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    List<CompanyMasterVO> companyList = new ArrayList<CompanyMasterVO>();
	    int page = Integer.parseInt(paramsMap.get("page").toString());
	    int rows = Integer.parseInt(paramsMap.get("rows").toString());
	    int total = 1; // 총 페이지 수
	    int records = companyMapper.selectCompanyListCnt(paramsMap);
	    
        paramsMap.putAll(Util.getPagingForSQL(page, rows, total, records));
        if (records < 1)
        {
        	page = 0;
        	total = 0;
        }
        else 
        {
        	page = (int) paramsMap.remove("page");
        	total = (int) paramsMap.remove("total");
        	companyList = companyMapper.selectCompanyList(paramsMap);
        }
	    
	    rrVO.setPage(String.valueOf(page));
	    rrVO.setTotal(String.valueOf(total));
	    rrVO.setRecords(String.valueOf(records));
	    rrVO.setData(companyList);
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote EMP_COMPANY_XREF에 관리자 할당 법인목록 INSERT
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO insertEmpCompanyXref(EmpCompanyXrefVO[] empCompanyXrefArr) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    
	    int resultCnt = 0;
	    // 선택한 법인 모두 INSERT
	    for(EmpCompanyXrefVO obj : empCompanyXrefArr) {
	    	int result = empCompanyXrefMapper.insertEmpCompanyXref(obj);
	    	if(result > 0) resultCnt += 1;
	    }

	    if(resultCnt == empCompanyXrefArr.length)
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
	    
	    return rrVO;	
	}
}

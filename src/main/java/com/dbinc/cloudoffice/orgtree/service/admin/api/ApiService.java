package com.dbinc.cloudoffice.orgtree.service.admin.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbinc.cloudoffice.orgtree.mapper.api.SyncDeptMasterMapper;
import com.dbinc.cloudoffice.orgtree.mapper.api.SyncEmpDeptXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.api.SyncEmpMasterMapper;
import com.dbinc.cloudoffice.orgtree.mapper.api.SyncLogMapper;
import com.dbinc.cloudoffice.orgtree.mapper.auth.EmpAuthorityMapper;
import com.dbinc.cloudoffice.orgtree.mapper.company.CompanyMapper;
import com.dbinc.cloudoffice.orgtree.mapper.config.EmpConfigMapper;
import com.dbinc.cloudoffice.orgtree.mapper.dept.DeptMasterMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpDeptXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpMapper;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional 
@RequiredArgsConstructor
public class ApiService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final SyncEmpMasterMapper syncEmpMasterMapper;
	private final SyncDeptMasterMapper syncDeptMasterMapper;
	private final SyncEmpDeptXrefMapper syncEmpDeptXrefMapper;
	private final SyncLogMapper syncLogMapper;
	private final CompanyMapper companyMapper;
	private final EmpMapper empMapper;
	private final EmpDeptXrefMapper empDeptXrefMapper;
	private final EmpConfigMapper empConfigMapper;
	private final EmpAuthorityMapper empAuthorityMapper;
	private final DeptMasterMapper deptMasterMapper;

	/**
	 * @author minwest61
	 * @apiNote API 호출해 EP 데이터 -> SYNC 테이블에 동기화
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception 
	 */
	public void syncEpData() throws Exception
	{
		SyncLogVO syncLogVO = new SyncLogVO();
		syncLogVO.setSyncDiv("TOSYNC");
		Map<String, Object> paramsMap = new HashMap<>();
		
		try {
			// COMPANY_MASTER 테이블에서 AUTO_EP_SYNC_YN이 Y인 회사에 대해서만 동기화
			List<CompanyMasterVO> companyList = companyMapper.selectEpSyncCompanyList(paramsMap);
			for(CompanyMasterVO company : companyList) {
				String tempDomainId = company.getTempDomainId();
				String epEmpSyncApiUrl = company.getEpEmpSyncApiUrl();
				String epDeptSyncApiUrl = company.getEpDeptSyncApiUrl();
				String epEmpDeptXrefSyncApiUrl = company.getEpEmpDeptXrefSyncApiUrl();
				String authKey = company.getApiAuthKey(); // API 호출 인증키
				
				paramsMap.put("domainId", company.getDomainId());
				paramsMap.put("companyCd", company.getCompanyCd());
				paramsMap.put("epEmpSyncApiUrl", epEmpSyncApiUrl);
				paramsMap.put("epDeptSyncApiUrl", epDeptSyncApiUrl);
				paramsMap.put("epEmpDeptXrefSyncApiUrl", epEmpDeptXrefSyncApiUrl);
				paramsMap.put("tempDomainId", tempDomainId);
				paramsMap.put("authKey", authKey);
				paramsMap.put("createUsr", "SYSTEM");
				
				syncLogVO.setTempDomainId(tempDomainId);
				syncLogVO.setDomainId(company.getDomainId());
				syncLogVO.setCompanyCd(company.getCompanyCd());
				syncLogVO.setCreateUsr("SYSTEM");				
				syncLogVO.setEmpSyncResult("F");syncLogVO.setDeptSyncResult("F"); syncLogVO.setEmpDeptSyncResult("F"); // SYNC_LOG 테이블에 LOG 남기기위해 F로 초기화(Null 방지)
				
				List<EmpMasterVO> empList = getEpEmpList(paramsMap); // EP_EMP 데이터 가져옴
				List<DeptMasterVO> deptList = getEpDeptList(paramsMap); // EP_DEPT 데이터 가져옴
				List<EmpDeptXrefListVO> empDeptXreflist = getEpEmpDeptXrefList(paramsMap); // EP_EMP_DEPT_XREF 데이터 가져옴
				
				paramsMap.put("empList", empList);
				paramsMap.put("deptList", deptList);
				paramsMap.put("empDeptXreflist", empDeptXreflist);
				
				// Emp 데이터 동기화
				syncEmpMasterMapper.deleteSyncEmpMaster(paramsMap);
				syncEmpMasterMapper.insertSyncEmpMaster(paramsMap);
				syncLogVO.setEmpSyncResult("S");
				
				// Dept 데이터 동기화
				syncDeptMasterMapper.deleteSyncDeptMaster(paramsMap);
				syncDeptMasterMapper.insertSyncDeptMaster(paramsMap);
				syncLogVO.setDeptSyncResult("S");
				
				// EmpDeptXref 데이터 동기화
				syncEmpDeptXrefMapper.deleteSyncEmpDeptXref(paramsMap);
				syncEmpDeptXrefMapper.insertSyncEmpDeptXref(paramsMap);
				syncLogVO.setEmpDeptSyncResult("S");

	      		syncLogMapper.insertSyncLog(syncLogVO);
			}
		}
		catch (Exception e) 
		{
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}
	}
	
	/**
	 * @author minwest61
	 * @apiNote EP-EMP API 호출해 EMP 데이터 가져옴
	 * @param paramsMap
	 * @return List<EmpMasterVO>
	 * @exception Exception
	 */
	public List<EmpMasterVO> getEpEmpList(Map<String, Object> paramsMap) throws Exception
	{
		String empUrl = paramsMap.get("epEmpSyncApiUrl") 
				+ "domainName=" + paramsMap.get("domainId") 
				+ "&company=" + paramsMap.get("companyCd")
				+ "&authKey=" + paramsMap.get("authKey");
		URL url = new URL(empUrl);
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setDoOutput(true); // 출력 가능 상태로 변경
	    conn.connect();
	    
	    // 데이터  읽어오기
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 한글 깨짐 방지
	    StringBuilder sb = new StringBuilder();
	    String line = "";
	    while((line = br.readLine()) != null)
	    {
	    	sb.append(line);
	    }
	    conn.disconnect();
	    
	    JSONObject jsonObj = (JSONObject) new JSONParser().parse(sb.toString());
	    // API 인증 실패시 에러 처리
	    String resultCode = jsonObj.get("resultCode").toString();
	    if(!"SUCCESS".equals(resultCode)) 
	    {
	    	logger.debug("=============API Call Failed=============");
	    	throw new Exception();
	    }
	    
	    JSONArray jsonArr = (JSONArray) jsonObj.get("empList"); // empList 데이터를 JSONArray로 변환

		List<EmpMasterVO> empList = new ArrayList<EmpMasterVO>();
		// 한명씩 empVO에 넣어 empList에 추가
		for(Object arr : jsonArr) {
			JSONObject obj = (JSONObject) arr;
			EmpMasterVO empVO = new EmpMasterVO();
			empVO.setDomainId(obj.get("domainName").toString());
			empVO.setCompanyCd(obj.get("company").toString());
			empVO.setEmail(obj.get("email").toString());
			empVO.setEmpNm(obj.get("name").toString());
			empVO.setEmpEngNm(obj.get("engName").toString());
			empVO.setEmpNo(obj.get("empNo").toString());
			empVO.setPosCd( obj.get("positionCode").toString());
			empVO.setDeptCd(obj.get("deptCode").toString());
			empVO.setEnterDt(obj.get("entryDate").toString());
			empVO.setQuitDt(obj.get("quitDate").toString());
			empVO.setManualMngYn(obj.get("manualMngYn").toString());
			empVO.setHiddenYn(obj.get("hiddenYn").toString());
			empVO.setEmpStatusCd( obj.get("stateStatusCode").toString());
			empVO.setJobTelNo( obj.get("jobTelNo").toString());
			empVO.setMobileTelNo(obj.get("mobileTelNo").toString());
			empVO.setCreateUsr(paramsMap.get("createUsr").toString());
			empList.add(empVO);
		}
		return empList;
	}
	
	/**
	 * @author minwest61
	 * @apiNote EP-DEPT API 호출해 DEPT 데이터 가져옴
	 * @param paramsMap
	 * @return List<DeptMasterVO>
	 * @exception Exception
	 */
	public List<DeptMasterVO> getEpDeptList(Map<String, Object> paramsMap) throws Exception
	{
		String deptUrl = paramsMap.get("epDeptSyncApiUrl") 
				+ "domainName=" + paramsMap.get("domainId") 
				+ "&company=" + paramsMap.get("companyCd") 
				+ "&authKey=" + paramsMap.get("authKey");
		URL url = new URL(deptUrl);
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setDoOutput(true); // 출력 가능 상태로 변경
	    conn.connect();
	    
	    // 데이터  읽어오기
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 한글 깨짐 방지
	    StringBuilder sb = new StringBuilder();
	    String line = "";
	    while((line = br.readLine()) != null)
	    {
	    	sb.append(line);
	    }
	    conn.disconnect();
	    
	    JSONObject jsonObj = (JSONObject) new JSONParser().parse(sb.toString());
	    // API 인증 실패시 에러 처리
	    String resultCode = jsonObj.get("resultCode").toString();
	    if(!"SUCCESS".equals(resultCode)) 
	    {
	    	logger.debug("=============API Call Failed=============");
	    	throw new Exception();
	    }
	    JSONArray jsonArr = (JSONArray) jsonObj.get("deptList"); // deptList 데이터를 JSONArray로 변환
	    
		List<DeptMasterVO> deptList = new ArrayList<DeptMasterVO>();
		for(Object arr : jsonArr) {
			JSONObject obj = (JSONObject) arr;
			DeptMasterVO deptVO = new DeptMasterVO();
			deptVO.setDomainId(obj.get("domainName").toString());
			deptVO.setCompanyCd(obj.get("company").toString());
			deptVO.setDeptCd(obj.get("deptCode").toString());
			deptVO.setDeptNm(obj.get("deptName").toString());
			//deptVO.setDeptEngNm(obj.get("domainName").toString());
			deptVO.setParentDeptCd(obj.get("parentDeptCode").toString());
			deptVO.setDeptOrd(obj.get("deptOrd").toString());
			deptVO.setManualMngYn("N");
			deptVO.setDeptUseYn(obj.get("useYn").toString());
			deptVO.setCreateUsr(paramsMap.get("createUsr").toString());
			deptList.add(deptVO);
		}
		return deptList;
	}
	
	/**
	 * @author minwest61
	 * @apiNote EP-DEPT_XREF API 호출해 EMP_DEPT_XREF 데이터 가져옴
	 * @param paramsMap
	 * @return List<EmpDeptXrefListVO>
	 * @exception Exception
	 */
	public List<EmpDeptXrefListVO> getEpEmpDeptXrefList(Map<String, Object> paramsMap) throws Exception
	{
		String empDeptXrefUrl = paramsMap.get("epEmpDeptXrefSyncApiUrl") 
				+ "domainName=" + paramsMap.get("domainId") 
				+ "&company=" + paramsMap.get("companyCd") 
				+ "&authKey=" + paramsMap.get("authKey");
		URL url = new URL(empDeptXrefUrl);
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setDoOutput(true); // 출력 가능 상태로 변경
	    conn.connect();
	    
	    // 데이터  읽어오기
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 한글 깨짐 방지
	    StringBuilder sb = new StringBuilder();
	    String line = "";
	    while((line = br.readLine()) != null)
	    {
	    	sb.append(line);
	    }
	    conn.disconnect();
	    
	    JSONObject jsonObj = (JSONObject) new JSONParser().parse(sb.toString());
	    // API 인증 실패시 에러 처리
	    String resultCode = jsonObj.get("resultCode").toString();
	    if(!"SUCCESS".equals(resultCode)) 
	    {
	    	logger.debug("=============API Call Failed=============");
	    	throw new Exception();
	    }
	    JSONArray jsonArr = (JSONArray) jsonObj.get("deptEmpList"); // deptEmpList 데이터를 JSONArray로 변환
	    
		List<EmpDeptXrefListVO> empDeptXrefList = new ArrayList<EmpDeptXrefListVO>();

		for(Object arr : jsonArr) {
			JSONObject obj = (JSONObject) arr;
			EmpDeptXrefListVO empDeptXrefListVO = new EmpDeptXrefListVO();
			empDeptXrefListVO.setDomainId(obj.get("domainName").toString());
			empDeptXrefListVO.setCompanyCd(paramsMap.get("companyCd").toString());
			empDeptXrefListVO.setDeptCd(obj.get("deptCode").toString());
			empDeptXrefListVO.setEmail(obj.get("email").toString());
			empDeptXrefListVO.setEmpNo(obj.get("empNo").toString());
			empDeptXrefListVO.setAddYn(obj.get("addYn").toString());
			empDeptXrefListVO.setHeadYn(obj.get("headYn").toString());
			empDeptXrefListVO.setCreateUsr(paramsMap.get("createUsr").toString());
			empDeptXrefList.add(empDeptXrefListVO);
		}
		return empDeptXrefList;
	}
	
	/**
	 * @author minwest61
	 * @apiNote SYNC 테이블 데이터를 실제 데이터로 INSERT
	 * @param 
	 * @return
	 * @exception Exception
	 */
	public void insertSyncData() throws Exception 
	{
	  SyncLogVO syncLogVO = new SyncLogVO();
	  syncLogVO.setSyncDiv("TODB");
	  try {
  		Map<String, Object> paramsMap = new HashMap<>();
  		List<CompanyMasterVO> companyList = companyMapper.selectEpSyncCompanyList(paramsMap);
  		for(CompanyMasterVO company : companyList) {
			  syncLogVO.setDomainId(company.getDomainId());
			  syncLogVO.setCompanyCd(company.getCompanyCd());
			  syncLogVO.setTempDomainId(company.getTempDomainId());

			  syncLogVO.setEmpSyncResult("F");
			  syncLogVO.setDeptSyncResult("F");
			  syncLogVO.setEmpDeptSyncResult("F");
        
  			paramsMap.put("domainId", company.getDomainId());
  			paramsMap.put("companyCd", company.getCompanyCd());
  			paramsMap.put("tempDomainId", company.getTempDomainId());
  			paramsMap.put("createUsr", "SYSTEM");

  			int success = syncLogMapper.selectSuccessSyncLog(paramsMap);
  			if(success < 1) continue; // 당일 성공 Log가 있는 Company만 진행

  			// 수동관리='Y' 제외 EMP 관련 테이블 데이터 삭제
  			empMapper.deleteEmpMasterFromSync(paramsMap);
  			empConfigMapper.deleteEmpConfigFromSync(paramsMap);
  			empAuthorityMapper.deleteEmpAuthorityFromSync(paramsMap);
  			// 수동관리='Y' 제외 EMP 데이터 동기화(SYNC -> EMP)
  			syncEmpMasterMapper.insertEmpMasterFromSync(paramsMap);
  			syncEmpMasterMapper.insertEmpConfigFromSync(paramsMap);
  			syncEmpMasterMapper.insertEmpAuthorityFromSync(paramsMap);
			syncLogVO.setEmpSyncResult("S");

			empDeptXrefMapper.deleteEmpDeptXrefFromSync(paramsMap); // 수동관리='Y' 제외 EMP_DEPT_XREF 관련 테이블 데이터 삭제
  			syncEmpDeptXrefMapper.insertEmpDeptXrefFromSync(paramsMap); // 수동관리='Y' 제외 EMP_DEPT_XREF 데이터 동기화(SYNC -> EMP)
  			syncLogVO.setEmpDeptSyncResult("S");

  			deptMasterMapper.updateDeptMasterUseNot(paramsMap);  // 수동관리='Y' 제외 DEPT_MASTER 테이블의 USE_YN을 'N'로 수정
  			syncDeptMasterMapper.insertDeptMasterFromSync(paramsMap); // 수동관리='Y' 제외 DEPT 데이터 동기화(SYNC -> DEPT)
  			syncLogVO.setDeptSyncResult("S");

  			syncLogMapper.insertSyncLog(syncLogVO);
  		}
	  }
	  catch (Exception e)
    	{
		  syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}
	}

	/**
	 * 외부 API로부터 사용자 데이터 sync 테이블에 동기화
	 * @param request
	 * @throws Exception
	 */
	public void toSyncEmpData(SyncEmpRequest request) throws Exception {
		SyncLogVO syncLogVO = new SyncLogVO();
		syncLogVO.setSyncDiv("TOSYNC");
		Map<String, Object> paramsMap = new HashMap<>();

		try {
			paramsMap.put("domainId", request.getDomainId());
			paramsMap.put("companyCd", request.getCompanyCd());
			CompanyMasterVO company = companyMapper.selectCompanyForSync(paramsMap);
			syncLogVO.setTempDomainId(company.tempDomainId);
			syncLogVO.setDomainId(company.getDomainId());
			syncLogVO.setCompanyCd(company.getCompanyCd());
			syncLogVO.setCreateUsr("SYSTEM");
			syncLogVO.setEmpSyncResult("F");
			syncLogVO.setDeptSyncResult("P");
			syncLogVO.setEmpDeptSyncResult("P"); // SYNC_LOG 테이블에 LOG 남기기위해 F로 초기화(Null 방지)

			request.getEmpList().stream()
					.forEach(v -> isValidCompanyCd(request.getCompanyCd(), v.getCompanyCd()));

			paramsMap.put("createUsr", "SYSTEM");
			paramsMap.put("tempDomainId", company.tempDomainId);
			paramsMap.put("empList", request.getEmpList());

			// Emp 데이터 동기화
			syncEmpMasterMapper.deleteSyncEmpMaster(paramsMap);
			syncEmpMasterMapper.insertSyncEmpMaster(paramsMap);
			syncLogVO.setEmpSyncResult("S");

			syncLogMapper.insertSyncLog(syncLogVO);
		} catch (Exception e) {
			if (e instanceof SQLException) {
				throw new SQLException();
			}
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}

		toDBEmpData(paramsMap, syncLogVO);
	}

	public void toDBEmpData(Map<String, Object> paramsMap, SyncLogVO syncLogVO) throws Exception
	{
		syncLogVO.setSyncDiv("TODB");
		try {
			syncLogVO.setEmpSyncResult("F");
			syncLogVO.setDeptSyncResult("P");
			syncLogVO.setEmpDeptSyncResult("P");

			paramsMap.put("syncCode", "emp");
			int success = syncLogMapper.selectSuccessSyncLogCnt(paramsMap);
			if(success < 1) throw new Exception("데이터 동기화에 실패했습니다. TOSYNC FAILED");

			paramsMap.put("createUsr", "SYSTEM");
			// 수동관리='Y' 제외 EMP 관련 테이블 데이터 삭제
			empMapper.deleteEmpMasterFromSync(paramsMap);
			empConfigMapper.deleteEmpConfigFromSync(paramsMap);
			empAuthorityMapper.deleteEmpAuthorityFromSync(paramsMap);

			// 수동관리='Y' 제외 EMP 데이터 동기화(SYNC -> EMP)
			syncEmpMasterMapper.insertEmpMasterFromSyncForApi(paramsMap);
			syncEmpMasterMapper.insertEmpConfigFromSync(paramsMap);
			syncEmpMasterMapper.insertEmpAuthorityFromSync(paramsMap);

			syncLogVO.setEmpSyncResult("S");
			syncLogMapper.insertSyncLog(syncLogVO);
		}
		catch (Exception e)
		{
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}
	}

	/**
	 * 외부 API로부터 부서 데이터 sync 테이블에 동기화
	 * @param request
	 * @throws Exception
	 */
	public void toSyncDeptData(SyncDeptRequest request) throws Exception {
		SyncLogVO syncLogVO = new SyncLogVO();
		syncLogVO.setSyncDiv("TOSYNC");
		Map<String, Object> paramsMap = new HashMap<>();

		try {
			//company 조회
			paramsMap.put("domainId", request.getDomainId());
			paramsMap.put("companyCd", request.getCompanyCd());
			CompanyMasterVO company = companyMapper.selectCompanyForSync(paramsMap);
			syncLogVO.setTempDomainId(company.tempDomainId);
			syncLogVO.setDomainId(company.getDomainId());
			syncLogVO.setCompanyCd(company.getCompanyCd());
			syncLogVO.setCreateUsr("SYSTEM");
			syncLogVO.setEmpSyncResult("P");
			syncLogVO.setDeptSyncResult("F");
			syncLogVO.setEmpDeptSyncResult("P"); // SYNC_LOG 테이블에 LOG 남기기위해 F로 초기화(Null 방지)

			request.getDeptList().stream()
					.forEach(v -> isValidCompanyCd(request.getCompanyCd(), v.getCompanyCd()));

			paramsMap.put("createUsr", "SYSTEM");
			paramsMap.put("tempDomainId", company.tempDomainId);
			paramsMap.put("deptList", request.getDeptList());

			// Dept 데이터 동기화
			syncDeptMasterMapper.deleteSyncDeptMaster(paramsMap);
			syncDeptMasterMapper.insertSyncDeptMaster(paramsMap);
			syncLogVO.setDeptSyncResult("S");

			syncLogMapper.insertSyncLog(syncLogVO);
		} catch (Exception e) {
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}

		toDBDeptData(paramsMap, syncLogVO);
	}

	public void toDBDeptData(Map<String, Object> paramsMap, SyncLogVO syncLogVO) throws Exception
	{
		syncLogVO.setSyncDiv("TODB");
		try {
			syncLogVO.setEmpSyncResult("P");
			syncLogVO.setDeptSyncResult("F");
			syncLogVO.setEmpDeptSyncResult("P");

			paramsMap.put("syncCode", "dept");
			int success = syncLogMapper.selectSuccessSyncLogCnt(paramsMap);
			if(success < 1) throw new Exception("데이터 동기화에 실패했습니다. TOSYNC FAILED");

			paramsMap.put("createUsr", "SYSTEM");
			deptMasterMapper.updateDeptMasterUseNot(paramsMap);  // 수동관리='Y' 제외 DEPT_MASTER 테이블의 USE_YN을 'N'로 수정
			syncDeptMasterMapper.insertDeptMasterFromSync(paramsMap); //// 수동관리='Y' 제외 DEPT 데이터 동기화(SYNC -> DEPT)

			syncLogVO.setDeptSyncResult("S");
			syncLogMapper.insertSyncLog(syncLogVO);
		}
		catch (Exception e)
		{
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}
	}

	/**
	 * 외부 API로부터 겸직 데이터 sync 테이블에 동기화
	 * @param request
	 * @throws Exception
	 */
	public void toSyncEmpDeptXrefData(SyncEmpDeptXrefRequest request) throws Exception {
		SyncLogVO syncLogVO = new SyncLogVO();
		syncLogVO.setSyncDiv("TOSYNC");
		Map<String, Object> paramsMap = new HashMap<>();

		try {
			paramsMap.put("domainId", request.getDomainId());
			paramsMap.put("companyCd", request.getCompanyCd());
			CompanyMasterVO company = companyMapper.selectCompanyForSync(paramsMap);
			syncLogVO.setTempDomainId(company.tempDomainId);
			syncLogVO.setDomainId(company.getDomainId());
			syncLogVO.setCompanyCd(company.getCompanyCd());
			syncLogVO.setCreateUsr("SYSTEM");
			syncLogVO.setEmpSyncResult("P");
			syncLogVO.setDeptSyncResult("P");
			syncLogVO.setEmpDeptSyncResult("F"); // SYNC_LOG 테이블에 LOG 남기기위해 F로 초기화(Null 방지)

			request.getEmpDeptXrefList().stream()
					.forEach(v -> isValidCompanyCd(request.getCompanyCd(), v.getCompanyCd()));

			paramsMap.put("createUsr", "SYSTEM");
			paramsMap.put("tempDomainId", company.tempDomainId);
			paramsMap.put("empDeptXreflist", request.getEmpDeptXrefList());

			// EmpDeptXref 데이터 동기화
			syncEmpDeptXrefMapper.deleteSyncEmpDeptXref(paramsMap);
			syncEmpDeptXrefMapper.insertSyncEmpDeptXref(paramsMap);
			syncLogVO.setEmpDeptSyncResult("S");

			syncLogMapper.insertSyncLog(syncLogVO);
		} catch (Exception e) {
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}

		toDBEmpDeptXrefData(paramsMap, syncLogVO);
	}

	public void toDBEmpDeptXrefData(Map<String, Object> paramsMap, SyncLogVO syncLogVO) throws Exception
	{
		syncLogVO.setSyncDiv("TODB");
		try {
			syncLogVO.setEmpSyncResult("P");
			syncLogVO.setDeptSyncResult("P");
			syncLogVO.setEmpDeptSyncResult("F");

			paramsMap.put("syncCode", "empDept");
			int success = syncLogMapper.selectSuccessSyncLogCnt(paramsMap);
			if(success < 1) throw new Exception("데이터 동기화에 실패했습니다. TOSYNC FAILED");

			paramsMap.put("createUsr", "SYSTEM");
			empDeptXrefMapper.deleteEmpDeptXrefFromSync(paramsMap); // 수동관리='Y' 제외 EMP_DEPT_XREF 관련 테이블 데이터 삭제
			syncEmpDeptXrefMapper.insertEmpDeptXrefFromSync(paramsMap); // 수동관리='Y' 제외 EMP_DEPT_XREF 데이터 동기화(SYNC -> EMP)
			syncLogVO.setEmpDeptSyncResult("S");
			syncLogMapper.insertSyncLog(syncLogVO);
		}
		catch (Exception e)
		{
			syncLogMapper.insertSyncLog(syncLogVO); // SYNC_LOG_[DOMAIN] 테이블에 동기화 성공/실패 여부 INSERT
			throw e;
		}
	}

	public String getApiKey(SessionUser sessionUser) {
		return companyMapper.selectApiAuthKey(sessionUser.getDomainId(), sessionUser.getCompanyCd());
	}

	public String createApiKey(Map<String, Object> paramsMap) {
		int keyLength = 32;
		String apiKey = generateRandomApiKey(keyLength);
		paramsMap.put("apiKey", apiKey);
		companyMapper.updateApiAuthKey(paramsMap);

		return apiKey;
	}

	private static String generateRandomApiKey(int keyLength) {
		SecureRandom random = new SecureRandom();
		byte[] apiKeyBytes = new byte[keyLength];
		random.nextBytes(apiKeyBytes);

		// 바이트 배열을 Base64 인코딩하여 문자열로 변환
		return Base64.getEncoder().encodeToString(apiKeyBytes);
	}

	private boolean isValidCompanyCd(String companyCd, String empCompanyCd) {
		if(!companyCd.equals(empCompanyCd)) {
			throw new IllegalArgumentException("데이터에 잘못된 법인코드가 포함되어 있습니다.");
		}
		return true;
	}

	// 25/1/15 추가, mysql에서 테스터 데이터 불러오기
	// 재직상태코드(EMP_STATUS_CD)가 Y인 직원을 불러옴
	private final JdbcTemplate jdbcTemplate;

	public List<EmpMasterVO> getActiveEmployeeList() {
		String sql = "SELECT domain_id AS domainId, company_cd AS companyCd, email, emp_nm AS empNm, " +
				"emp_eng_nm AS empEngNm, emp_no AS empNo, pos_cd AS posCd, dept_cd AS deptCd, " +
				"enter_dt AS enterDt, quit_dt AS quitDt, manual_mng_yn AS manualMngYn, " +
				"emp_status_cd AS empStatusCd, hidden_yn AS hiddenYn, job_tel_no AS jobTelNo, " +
				"mobile_tel_no AS mobileTelNo, update_usr AS updateUsr, update_dt AS updateDt, " +
				"create_usr AS createUsr, create_dt AS createDt " +
				"FROM emp_master WHERE emp_status_cd = 'Y'";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			EmpMasterVO emp = new EmpMasterVO();
			emp.setDomainId(rs.getString("domainId"));
			emp.setCompanyCd(rs.getString("companyCd"));
			emp.setEmail(rs.getString("email"));
			emp.setEmpNm(rs.getString("empNm"));
			emp.setEmpEngNm(rs.getString("empEngNm"));
			emp.setEmpNo(rs.getString("empNo"));
			emp.setPosCd(rs.getString("posCd"));
			emp.setDeptCd(rs.getString("deptCd"));
			emp.setEnterDt(rs.getString("enterDt"));
			emp.setQuitDt(rs.getString("quitDt"));
			emp.setManualMngYn(rs.getString("manualMngYn"));
			emp.setEmpStatusCd(rs.getString("empStatusCd"));
			emp.setHiddenYn(rs.getString("hiddenYn"));
			emp.setJobTelNo(rs.getString("jobTelNo"));
			emp.setMobileTelNo(rs.getString("mobileTelNo"));
			emp.setUpdateUsr(rs.getString("updateUsr"));
			emp.setUpdateDt(rs.getString("updateDt"));
			emp.setCreateUsr(rs.getString("createUsr"));
			emp.setCreateDt(rs.getString("createDt"));
			return emp;
		});
	}

}

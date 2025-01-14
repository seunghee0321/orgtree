package com.dbinc.cloudoffice.orgtree.controller.admin.api;

import com.dbinc.cloudoffice.orgtree.mapper.company.CompanyMapper;
import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dbinc.cloudoffice.orgtree.service.admin.api.ApiService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/rest/admin/api")
public class ApiRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String API_KEY_HEADER = "API-KEY";
	private final ApiService apiService;
	private final CompanyMapper companyMapper;

	/**
	 * @author 
	 * @apiNote EP 연동 API 호출해 SYNC 테이블에 데이터 동기화
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="EP -> SYNC(배치용)")
	@GetMapping(value="syncEpData.do")
	public void syncEpData() throws Exception {
	    try {
			apiService.syncEpData();
		} catch (Exception e) {
			logger.debug(e.toString());
		}
	}
	
	/**
	 * @author 
	 * @apiNote SYNC 테이블 데이터 동기화
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="SYNC -> DATA(배치용)")
	@GetMapping(value="insertSyncData.do")
	public void insertSyncData() throws Exception {
		try {
			apiService.insertSyncData();
		} catch (Exception e) {
			logger.debug(e.toString());
		}
	}

	@ApiOperation(value="외부API연동 - EMP")
	@PostMapping(value="/sync/emp")
	public ResponseEntity<RestResultVO> syncEmpData(HttpServletRequest httpServletRequest, @RequestBody SyncEmpRequest request) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		boolean chkAuth = checkApiKey(httpServletRequest, request.getDomainId(), request.getCompanyCd());
		if(!chkAuth) {
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg("ApiKey 인증에 실패했습니다.");
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}

		try {
			apiService.toSyncEmpData(request);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.debug(e.toString());
			rrVO.setResultMsg("API Call Failed");
			rrVO.setResultCode(C.FAIL);
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value="외부API연동 - DEPT")
	@PostMapping(value="/sync/dept")
	public ResponseEntity<RestResultVO> syncDeptData(HttpServletRequest httpServletRequest, @RequestBody SyncDeptRequest request) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		boolean chkAuth = checkApiKey(httpServletRequest, request.getDomainId(), request.getCompanyCd());
		if(!chkAuth) {
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg("ApiKey 인증에 실패했습니다.");
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}
		try {
			apiService.toSyncDeptData(request);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
		catch (Exception e) {
			logger.debug(e.toString());
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg("API Call Failed");
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value="외부API연동 - EMP_DEPT_XREF")
	@PostMapping(value="/sync/empDept")
	public ResponseEntity<RestResultVO> syncEmpDeptXrefData(HttpServletRequest httpServletRequest, @RequestBody SyncEmpDeptXrefRequest request) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		boolean chkAuth = checkApiKey(httpServletRequest, request.getDomainId(), request.getCompanyCd());
		if(!chkAuth) {
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg("ApiKey 인증에 실패했습니다.");
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}
		try {
			apiService.toSyncEmpDeptXrefData(request);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.debug(e.toString());
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg("API Call Failed");
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value="외부API연동용 인증key 조회")
	@GetMapping(value="/sync/apiKey")
	public ResponseEntity<RestResultVO> getApiKey(HttpSession httpSession) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		String apiKey = apiService.getApiKey(sessionUser);

		rrVO.setDataOne(apiKey);
		rrVO.setResultCode(C.SUCCESS);
		rrVO.setResultMsg(C.SUCCESS_MSG);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}

	@ApiOperation(value="외부API연동용 인증key 발급")
	@PostMapping(value="/sync/apiKey")
	public ResponseEntity<RestResultVO> createApiKey(HttpSession httpSession) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		Map<String, Object> paramsMap = new HashMap<>();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("domainId", sessionUser.getDomainId());
		paramsMap.put("companyCd", sessionUser.getCompanyCd());
		paramsMap.put("updateUsr", sessionUser.getEmail());
		String apiKey = apiService.createApiKey(paramsMap);

		rrVO.setDataOne(apiKey);
		rrVO.setResultCode(C.SUCCESS);
		rrVO.setResultMsg(C.SUCCESS_MSG);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}

	public boolean checkApiKey(HttpServletRequest request, String domainId, String companyCd) {
		// 요청 헤더에서 API-Key 값을 가져옵니다.
		String reqApiKey = request.getHeader(API_KEY_HEADER);
		if(reqApiKey == null || "".equals(reqApiKey)) {
			return false;
		}
		String apiKey = companyMapper.selectApiAuthKey(domainId, companyCd);

		if (!apiKey.equals(reqApiKey)) {
			return false;
		}
		return true;
	}
}

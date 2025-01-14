package com.dbinc.cloudoffice.orgtree.controller.admin.emp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.admin.emp.AdminEmpService;
import com.dbinc.cloudoffice.orgtree.service.common.code.CodeService;
import com.dbinc.cloudoffice.orgtree.service.user.emp.UserEmpService;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/rest/admin/emp")
public class AdminEmpRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final HttpSession httpSession;
	private final AdminEmpService adminEmpService;
	private final UserEmpService userEmpService;
	private final CodeService codeService;
	
	/**
	 * @author 
	 * @apiNote 사용자 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectEmpList.do")
	public ResponseEntity<RestResultVO> selectEmpList(
			@RequestParam  Map<String, Object> paramsMap,
			HttpServletRequest request) throws Exception
	 {
        RestResultVO rrVO = new RestResultVO();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
 
        switch(paramsMap.get("searchCond").toString()) 
        {
	        case "empNm" :
	        	paramsMap.put("empNm", paramsMap.get("searchTxt").toString());
	        	break;
	        case "email" :
	        	paramsMap.put("email", paramsMap.get("searchTxt").toString());
	        	break;
	        case "empNo" :
	        	paramsMap.put("empNo", paramsMap.get("searchTxt").toString());
	        	break;
	        case "jobTelNo" :
	        	paramsMap.put("jobTelNo", paramsMap.get("searchTxt").toString());
	        	break;
	        case "mobileTelNo" :
	        	paramsMap.put("mobileTelNo", paramsMap.get("searchTxt").toString());
	        	break;
	        default :
	        	break;
        }
        paramsMap.put("includeHidden", "Y"); // 숨김 사용자 표시여부

        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail())
      		  || StringUtils.isBlank(paramsMap.get("searchCond").toString()))
          throw new Exception();
		else {
			rrVO = userEmpService.selectEmpList(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 상세 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectEmpInfo.do")
	public ResponseEntity<RestResultVO> selectEmpInfo(
			HttpServletRequest request, 
			@RequestParam  Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());

        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.selectEmpInfo(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 상세 업데이트
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/updateEmpInfo.do")
	public ResponseEntity<RestResultVO> updateEmpInfo(
			HttpServletRequest request,
		    @RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		paramsMap.put("updateUsr", sessionUser.getEmail());
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.updateEmpInfo(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 등록
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/insertEmpInfo.do")
	public ResponseEntity<RestResultVO> insertEmpInfo(
			HttpServletRequest request,
		    @RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");

		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		paramsMap.put("addYn", "N");
		paramsMap.put("authorityName", "ROLE_USER");
		paramsMap.put("createUsr", sessionUser.getEmail());
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.insertEmpInfo(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 삭제
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/deleteEmpInfo.do")
	public ResponseEntity<RestResultVO> deleteEmpInfo(
			HttpServletRequest request,
		    @RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.deleteEmpInfo(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 겸직 목록 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectEmpDeptXrefList.do")
	public ResponseEntity<RestResultVO> selectEmpDeptXrefList(
			HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        
        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.selectEmpDeptXrefList(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 겸직 정보 수정
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/updateEmpDeptXrefDetail.do")
	public ResponseEntity<RestResultVO> updateEmpDeptXrefDetail(
			HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("updateUsr", sessionUser.getEmail());
        
        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.updateEmpDeptXrefDetail(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 겸직 정보 삭제
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/deleteEmpDeptXrefDetail.do")
	public ResponseEntity<RestResultVO> deleteEmpDeptXrefDetail(
			HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        
        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminEmpService.deleteEmpDeptXrefDetail(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 겸직 정보 등록
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/insertEmpDeptXrefDetail.do")
	public ResponseEntity<RestResultVO> insertEmpDeptXrefDetail(
			HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap
		    ) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("createUsr", sessionUser.getEmail());
        
        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = adminEmpService.insertEmpDeptXrefDetail(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 목록 엑셀 다운로드
	 * @param response
	 * @param exDomainId
	 * @param exCompanyCd
	 * @param exSearchCond
	 * @param exSearchTxt
	 * @param exCompanyStatusCd
	 * @param exAddYn
	 * @param exManualMngYn
	 * @param exDeptCd
	 * @param exIncDeptUseYn
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/empListExcelDown.do")
	public void empListExcelDown(
			 HttpServletResponse response,
			 @RequestParam String exDomainId,
			 @RequestParam String exCompanyCd,
			 @RequestParam String exSearchCond,
			 @RequestParam String exSearchTxt,
			 @RequestParam String exCompanyStatusCd,
			 @RequestParam String exAddYn,
			 @RequestParam String exManualMngYn,
			 @RequestParam String exDeptCd,
			 @RequestParam String exIncDeptUseYn
		    ) throws Exception
	{
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("domainId", exDomainId);
        paramsMap.put("companyCd", exCompanyCd);
        paramsMap.put("companyStatusCd", exCompanyStatusCd);
        paramsMap.put("addYn", exAddYn);
        paramsMap.put("manualMngYn", exManualMngYn);
        paramsMap.put("deptCd", exDeptCd);
        paramsMap.put("incDeptUseYn", exIncDeptUseYn);
        paramsMap.put("includeHidden", "Y");
        switch(exSearchCond) 
        {
	        case "empNm" :
	        	paramsMap.put("empNm", exSearchTxt);
	        	break;
	        case "email" :
	        	paramsMap.put("email", exSearchTxt);
	        	break;
	        case "empNo" :
	        	paramsMap.put("empNo", exSearchTxt);
	        	break;
	        case "jobTelNo" :
	        	paramsMap.put("jobTelNo", exSearchTxt);
	        	break;
	        case "mobileTelNo" :
	        	paramsMap.put("mobileTelNo", exSearchTxt);
	        	break;
	        default :
	        	break;
        }
        
        Workbook wb = adminEmpService.empListExcelDown(paramsMap);
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=EmpList.xlsx");
        wb.write(response.getOutputStream());
        wb.close();
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 엑셀 업로드 폼 다운로드
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/empExcelFormDown.do")
	public void empExcelFormDown(HttpServletResponse response) throws Exception
	{
    Workbook wb = adminEmpService.empExcelFormDown();
    response.setContentType("ms-vnd/excel");
    response.setHeader("Content-Disposition", "attachment;filename=EmpUploadForm.xlsx");
    wb.write(response.getOutputStream());
    wb.close();
	}
	
	/**
	 * @author 
	 * @apiNote 사용자 엑셀 업로드
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/empListExcelUp.do")
	public ResponseEntity<RestResultVO> empListExcelUp(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
				throw new Exception();
		else {
			MultipartFile file = null;
			Iterator<String> iterator = request.getFileNames();
			if (iterator.hasNext()) {
				file = request.getFile(iterator.next());
			}

			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("file", file);
			paramsMap.put("createUsr", sessionUser.getEmail());

			rrVO = adminEmpService.empListExcelUp(paramsMap);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 재직상태/직위 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectCodeDetailList.do")
	public ResponseEntity<RestResultVO> selectCodeDetailList(
			@RequestParam Map<String, Object> paramsMap,
			HttpServletRequest request) throws Exception
	 {
        RestResultVO rrVO = new RestResultVO();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        
        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = codeService.selectCodeDetail(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote Google 관리콘솔 데이터 연동
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/googleUploadEmpAndDept.do")
	public ResponseEntity<RestResultVO> googleUploadEmpAndDept(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		RestResultVO rrVO = new RestResultVO();

		try {
			SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
			if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
				throw new Exception();
			else {
				MultipartFile file = null;
				Iterator<String> iterator = request.getFileNames();
				if (iterator.hasNext()) {
					file = request.getFile(iterator.next());
				}

				Map<String, Object> paramsMap = new HashMap<>();
				paramsMap.put("file", file);
				paramsMap.put("domainId", request.getParameter("domainId"));
				paramsMap.put("companyCd", request.getParameter("companyCd"));
				paramsMap.put("createUsr", sessionUser.getEmail());

				rrVO = adminEmpService.googleUploadEmpAndDept(paramsMap);
				rrVO.setResultCode(C.SUCCESS);
				rrVO.setResultMsg(C.SUCCESS_MSG);
				return new ResponseEntity<>(rrVO, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg(C.FAIL_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.BAD_REQUEST);
		}
	}
}

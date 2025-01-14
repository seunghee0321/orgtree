package com.dbinc.cloudoffice.orgtree.controller.admin.dept;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
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
import com.dbinc.cloudoffice.orgtree.service.admin.dept.AdminDeptService;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/rest/admin/dept")
public class AdminDeptRestController {
	private final HttpSession httpSession;
	private final AdminDeptService adminDeptService;
	
	/**
	 * @author R
	 * @apiNote 부서 정보 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectDeptInfo.do")
	public ResponseEntity<RestResultVO> selectDeptInfo(HttpServletRequest request, @RequestParam Map<String, Object> paramsMap) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
	        throw new Exception();
		else 
		{
			rrVO = adminDeptService.selectDeptInfo(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 부서 등록
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/insertDeptInfo.do")
	public ResponseEntity<RestResultVO> insertDeptInfo(HttpServletRequest request
			, @RequestParam Map<String, Object> paramsMap) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("deptUseYn", "Y"); // 부서 사용여부
		paramsMap.put("usrId", sessionUser.getEmail());
	    
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminDeptService.insertDeptInfo(paramsMap);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 부서 정보 수정
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/updateDeptInfo.do")
	public ResponseEntity<RestResultVO> updateDeptInfo(HttpServletRequest request
			, @RequestParam Map<String, Object> paramsMap) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("usrId", sessionUser.getEmail());
	    
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminDeptService.updateDeptInfo(paramsMap);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 부서 상위부서 수정(Drag&Drop)
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/updateDeptParent.do")
	public ResponseEntity<RestResultVO> updateDeptParent(HttpServletRequest request
			, @RequestParam Map<String, Object> paramsMap) throws Exception
	{
		RestResultVO rrVO = new RestResultVO();
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("usrId", sessionUser.getEmail());
	    
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminDeptService.updateDeptParent(paramsMap);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 부서 엑셀 업로드 양식 다운로드
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/deptExcelFormDown.do")
	public void deptExcelFormDown(HttpServletResponse response) throws Exception
	{
		
        Workbook wb = adminDeptService.deptExcelFormDown();
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=DeptUploadForm.xlsx");
        wb.write(response.getOutputStream());
        wb.close();
	}
	
	/**
	 * @author 
	 * @apiNote 부서 엑셀 업로드
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/deptListExcelUp.do")
	public ResponseEntity<RestResultVO> deptListExcelUp(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        
		 if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else 
		{
			MultipartFile file = null;
			Iterator<String> iterator = request.getFileNames();
			if (iterator.hasNext()) {
				file = request.getFile(iterator.next());
			}

			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("file", file);
			paramsMap.put("createUsr", sessionUser.getEmail());

			rrVO = adminDeptService.deptListExcelUp(paramsMap);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
		 
	}
}

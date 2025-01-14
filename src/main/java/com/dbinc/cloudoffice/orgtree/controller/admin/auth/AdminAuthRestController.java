package com.dbinc.cloudoffice.orgtree.controller.admin.auth;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.admin.auth.AdminAuthService;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.EmpAuthorityVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpCompanyXrefVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rest/admin/auth")
public class AdminAuthRestController {
	private final HttpSession httpSession;
	private final AdminAuthService adminAuthService;

	/**
	 * @author 
	 * @apiNote 관리자 목록 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/selectEmpAuthorityList.do")
	public ResponseEntity<RestResultVO> selectEmpAuthorityList(HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("languageCd", sessionUser.getLanguageCd());

		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminAuthService.selectEmpAuthorityList(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}

	/**
	 * @author 
	 * @apiNote 사용자 목록 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/selectEmpRoleUserList.do")
	public ResponseEntity<RestResultVO> selectEmpRoleUserList(HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("languageCd", sessionUser.getLanguageCd());

		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminAuthService.selectEmpRoleUserList(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}

	/**
	 * @author 
	 * @apiNote 관리자 등록
	 * @param request
	 * @param empAuthorityArr
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/insertEmpRoleAdmin.do")
	public ResponseEntity<RestResultVO> insertEmpRoleAdmin(HttpServletRequest request, @RequestBody EmpAuthorityVO[] empAuthorityArr ) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			if(empAuthorityArr.length <= 0)
				throw new Exception();
			else {
				for(EmpAuthorityVO obj : empAuthorityArr) {
					obj.setCreateUsr(sessionUser.getEmail());
				}
				rrVO = adminAuthService.insertEmpRoleAdmin(empAuthorityArr);
				rrVO.setResultCode(C.SUCCESS);
				rrVO.setResultMsg(C.SUCCESS_MSG);
				return new ResponseEntity<>(rrVO, HttpStatus.OK);
			}
		}
	}
	
	/**
	 * @author 
	 * @apiNote 관리자 삭제
	 * @param request
	 * @param empAuthorityArr
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/deleteEmpRoleAdmin.do")
	public ResponseEntity<RestResultVO> deleteEmpRoleAdmin(HttpServletRequest request, @RequestBody EmpAuthorityVO[] empAuthorityArr ) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			if(empAuthorityArr.length <= 0)
				throw new Exception();
			else {
				rrVO = adminAuthService.deleteEmpRoleAdmin(empAuthorityArr);
				rrVO.setResultCode(C.SUCCESS);
				rrVO.setResultMsg(C.SUCCESS_MSG);
				return new ResponseEntity<>(rrVO, HttpStatus.OK);
			}
		}
	}
	
	/**
	 * @author 
	 * @apiNote 관리자 할당 법인목록 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/selectEmpCompanyXref.do")
	public ResponseEntity<RestResultVO> selectEmpCompanyXref(HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("languageCd", sessionUser.getLanguageCd());

		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminAuthService.selectEmpCompanyXref(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 관리자 할당 법인 삭제
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/deleteEmpCompanyXref.do")
	public ResponseEntity<RestResultVO> deleteEmpCompanyXref(HttpServletRequest request, @RequestBody EmpCompanyXrefVO[] empCompanyXrefArr ) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			if(empCompanyXrefArr.length <= 0)
				throw new Exception();
			else {
				rrVO = adminAuthService.deleteEmpCompanyXref(empCompanyXrefArr);
				rrVO.setResultCode(C.SUCCESS);
				rrVO.setResultMsg(C.SUCCESS_MSG);
				return new ResponseEntity<>(rrVO, HttpStatus.OK);
			}
		}
	}
	
	/**
	 * @author 
	 * @apiNote 관리자 할당을 위한 법인 목록 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/selectCompanyList.do")
	public ResponseEntity<RestResultVO> selectCompanyList(HttpServletRequest request,
			@RequestParam Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			rrVO = adminAuthService.selectCompanyList(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}
	
	/**
	 * @author 
	 * @apiNote 관리자 할당 법인 추가
	 * @param request
	 * @param empCompanyXrefArr
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/insertEmpCompanyXref.do")
	public ResponseEntity<RestResultVO> insertEmpCompanyXref(HttpServletRequest request, @RequestBody EmpCompanyXrefVO[] empCompanyXrefArr ) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd())
				|| StringUtils.isBlank(sessionUser.getEmail()))
			throw new Exception();
		else {
			if(empCompanyXrefArr.length <= 0)
				throw new Exception();
			else {
				for(EmpCompanyXrefVO obj : empCompanyXrefArr) {
					obj.setCreateUsr(sessionUser.getEmail());
				}
				rrVO = adminAuthService.insertEmpCompanyXref(empCompanyXrefArr);
				rrVO.setResultCode(C.SUCCESS);
				rrVO.setResultMsg(C.SUCCESS_MSG);
				return new ResponseEntity<>(rrVO, HttpStatus.OK);
			}
		}
	}
}

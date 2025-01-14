package com.dbinc.cloudoffice.orgtree.controller.user.emp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.common.code.CodeService;
import com.dbinc.cloudoffice.orgtree.service.user.emp.UserEmpService;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.DeptMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rest/user/emp")
public class UserEmpRestConrtroller {

	private final HttpSession httpSession;
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
	@PostMapping(value="/selectEmpList.do")
	public ResponseEntity<RestResultVO> selectEmpList(
		    @RequestParam Map<String,Object> paramsMap,
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
        paramsMap.put("includeHidden", "N"); // 숨김 사용자 표시여부
        
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
	 * @apiNote 부서 트리 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectDeptList.do")
	public ResponseEntity<RestResultVO> selectDeptList(
			HttpServletRequest request, 
			@RequestParam Map<String,Object> paramsMap) throws Exception
	 {
		RestResultVO rrVO = new RestResultVO();
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");

		List<DeptMasterVO> deptList = new ArrayList<>();
		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		paramsMap.put("parentDeptCd", "0");
		if(paramsMap.get("deptCd") == null) paramsMap.put("deptCd", "");
		
		if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			deptList = userEmpService.selectDeptList(deptList, paramsMap);
			rrVO.setData(deptList);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	 }
	
	/**
	 * @author 
	 * @apiNote 재직상태 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectEmpStatusList.do")
	public ResponseEntity<RestResultVO> selectEmpStatusList(
			@RequestParam Map<String, Object> paramsMap,
			HttpServletRequest request) throws Exception
	 {
        RestResultVO rrVO = new RestResultVO();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("codeDiv", "CM001");
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
	 * @apiNote 부서 전체 사용자 조회
	 * @param request
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/selectDeptEmpList.do")
	public ResponseEntity<RestResultVO> selectDeptEmpList(
			@RequestParam Map<String, Object> paramsMap,
			HttpServletRequest request) throws Exception
	 {
        RestResultVO rrVO = new RestResultVO();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        
        if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
            throw new Exception();
		else {
			rrVO = userEmpService.selectDeptEmpList(paramsMap);
			rrVO.setResultCode(C.SUCCESS);
			rrVO.setResultMsg(C.SUCCESS_MSG);
			return new ResponseEntity<>(rrVO, HttpStatus.OK);
		}
	}

}

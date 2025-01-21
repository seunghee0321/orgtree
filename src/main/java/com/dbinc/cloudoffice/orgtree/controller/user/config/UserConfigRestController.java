package com.dbinc.cloudoffice.orgtree.controller.user.config;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.user.config.UserConfigService;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.EmpConfigVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/rest/user/config")
public class UserConfigRestController
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final HttpSession httpSession;
  private final UserConfigService userConfigService;
  
  /**
   * @author sunjeehun
   * @apiNote EMP_CONFIG 테이블에서 한건 조회 
   * @param request
   * @return
   * @throws Exception
   */
  @GetMapping(value="/selectMyEmpConfig.do")
  public ResponseEntity<RestResultVO> selectMyEmpConfig(HttpServletRequest request) throws Exception
  {
    RestResultVO rrVO = new RestResultVO();
    Map<String, Object> paramsMap = new HashMap<>();
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
    
    paramsMap.put("domainId", sessionUser.getDomainId());
    paramsMap.put("companyCd", sessionUser.getCompanyCd());
    paramsMap.put("email", sessionUser.getEmail());
    
    if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
        throw new Exception();
	else {
		rrVO = userConfigService.selectMyEmpConfig(paramsMap);
		rrVO.setResultCode(C.SUCCESS);
		rrVO.setResultMsg(C.SUCCESS_MSG);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}
  }
  
  /**
   * @author sunjeehun
   * @apiNote EMP_CONFIG 테이블에 데이터 저장 : 무조건 delete -> insert
   * @param empConfigVO
   * @param request
   * @return
   * @throws Exception
   */
  @PostMapping(value="/insertMyEmpConfig.do")
  public ResponseEntity<RestResultVO> insertMyEmpConfig(EmpConfigVO empConfigVO, HttpServletRequest request) throws Exception
  {
    RestResultVO rrVO = new RestResultVO();
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
    
    if (StringUtils.isBlank(sessionUser.getDomainId()) || StringUtils.isBlank(sessionUser.getCompanyCd()) || StringUtils.isBlank(sessionUser.getEmail()))
        throw new Exception();
	else {
		empConfigVO.setDomainId(sessionUser.getDomainId());
		empConfigVO.setCompanyCd(sessionUser.getCompanyCd());
		empConfigVO.setEmail(sessionUser.getEmail());
		empConfigVO.setUpdateUsr(sessionUser.getEmail());
		empConfigVO.setCreateUsr(sessionUser.getEmail());

		rrVO = userConfigService.insertMyEmpConfig(empConfigVO);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}
  }
}

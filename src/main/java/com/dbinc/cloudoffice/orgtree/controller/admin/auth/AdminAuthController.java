package com.dbinc.cloudoffice.orgtree.controller.admin.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.user.emp.UserEmpService;
import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/auth")
public class AdminAuthController {
	
	private final HttpSession httpSession;
	private final UserEmpService userEmpService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/empRoleMngView.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView empRoleMngView() throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/auth/empRoleMngView");
    	if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
        
    	Map<String, Object> paramsMap = new HashMap<>();
        List<CompanyMasterVO> companyList = new ArrayList<>();
        RestResultVO rrVO = new RestResultVO();

        // 회사 목록 조회
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("domainId", sessionUser.getDomainId());
		paramsMap.put("email", sessionUser.getEmail());
        rrVO = userEmpService.selectCompanySelBox(paramsMap);
        companyList = (List<CompanyMasterVO>)rrVO.getData();
        mav.addObject("companyList", companyList);
        
    	return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/empRoleAddModal.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView empRoleAddModal() throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/auth/empRoleAddModal");
    	if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
        
    	Map<String, Object> paramsMap = new HashMap<>();
        List<CompanyMasterVO> companyList = new ArrayList<>();
        RestResultVO rrVO = new RestResultVO();

        // 회사 목록 조회
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("domainId", sessionUser.getDomainId());
		paramsMap.put("email", sessionUser.getEmail());
        rrVO = userEmpService.selectCompanySelBox(paramsMap);
        companyList = (List<CompanyMasterVO>)rrVO.getData();
        mav.addObject("companyList", companyList);
        
    	return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/empCompanyMngView.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView empCompanyMngView() throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/auth/empCompanyMngView");
    	if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
        
    	Map<String, Object> paramsMap = new HashMap<>();
        List<CompanyMasterVO> companyList = new ArrayList<>();
        RestResultVO rrVO = new RestResultVO();

        // 회사 목록 조회
        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("domainId", sessionUser.getDomainId());
		paramsMap.put("email", sessionUser.getEmail());
        rrVO = userEmpService.selectCompanySelBox(paramsMap);
        companyList = (List<CompanyMasterVO>)rrVO.getData();
        mav.addObject("companyList", companyList);
        
    	return mav;
	}
	
	@RequestMapping(value="/empCompanyAddModal.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView empCompanyAddModal() throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/auth/empCompanyAddModal");
    	if (sessionUser != null) mav.addObject("sessionUser", sessionUser);

    	return mav;
	}

	@RequestMapping(value="/authKeyMngView.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView authKeyMngView() throws Exception
	{
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		ModelAndView mav = new ModelAndView("admin/auth/authKeyMngView");
		if (sessionUser != null) mav.addObject("sessionUser", sessionUser);

		return mav;
	}

}

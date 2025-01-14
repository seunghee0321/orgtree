package com.dbinc.cloudoffice.orgtree.controller.admin.dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.admin.dept.AdminDeptService;
import com.dbinc.cloudoffice.orgtree.service.user.emp.UserEmpService;
import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.DeptMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/dept")
public class AdminDeptController {
	private final HttpSession httpSession;
	private final UserEmpService userEmpService;
	private final AdminDeptService adminDeptService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/deptMasterListView.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView empMasterListView() throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/dept/deptMasterListView");
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
	
	@RequestMapping(value="/deptModifyView.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deptModifyView(String domainId, String companyCd, String deptCode) throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/dept/deptModifyView");
    	if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
    	
    	Map<String, Object> paramsMap = new HashMap<>();
    	DeptMasterVO deptList = new DeptMasterVO();
        RestResultVO rrVO = new RestResultVO();

        paramsMap.put("languageCd", sessionUser.getLanguageCd());
        paramsMap.put("domainId", domainId);
		paramsMap.put("companyCd", companyCd);
		paramsMap.put("deptCd", deptCode);
		
        rrVO = adminDeptService.selectDeptInfo(paramsMap); // 부서 정보 조회
        deptList = (DeptMasterVO) (rrVO.getDataOne());

    	mav.addObject("deptList",deptList);
    	return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/deptTreeViewModal.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deptTreeViewModal() throws Exception
	{
	    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	    ModelAndView mav = new ModelAndView("admin/dept/deptTreeViewModal");
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
}

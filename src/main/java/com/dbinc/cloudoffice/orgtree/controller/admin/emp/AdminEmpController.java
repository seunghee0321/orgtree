package com.dbinc.cloudoffice.orgtree.controller.admin.emp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;
import com.dbinc.cloudoffice.orgtree.service.common.code.CodeService;
import com.dbinc.cloudoffice.orgtree.service.user.emp.UserEmpService;
import com.dbinc.cloudoffice.orgtree.vo.CodeVO;
import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/emp")
public class AdminEmpController
{
  private final HttpSession httpSession;
  private final UserEmpService userEmpService;
  private final CodeService codeService;
  
  @SuppressWarnings("unchecked")
  @RequestMapping(value="/empMasterListView.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView empMasterListView() throws Exception
  {
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
    ModelAndView mav = new ModelAndView("admin/emp/empMasterListView");
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
  @RequestMapping(value="/empMasterDetailView.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView empMasterDetailView(@RequestParam String companyCd, @RequestParam String email, @RequestParam String domainId) throws Exception {
	  SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	  ModelAndView mav = new ModelAndView("admin/emp/empMasterDetailView");
	  if(sessionUser != null) mav.addObject("sessionUser", sessionUser);
	  
	  Map<String, Object> paramsMap = new HashMap<>();
	  List<CodeVO> companyStatusCdList = new ArrayList<>();
	  List<CodeVO> companyPosList = new ArrayList<>();
	  RestResultVO rrVO = new RestResultVO();
	  
	  // 재직상태 조회
	  paramsMap.put("domainId", domainId);
	  paramsMap.put("companyCd", companyCd);
	  paramsMap.put("codeDiv", "CM001");
	  paramsMap.put("languageCd", sessionUser.getLanguageCd());
	  rrVO = codeService.selectCodeDetail(paramsMap);
	  companyStatusCdList = (List<CodeVO>)rrVO.getData();
	  
	  // 직위 조회
	  paramsMap.put("codeDiv", "CM002");
	  rrVO = codeService.selectCodeDetail(paramsMap);
	  companyPosList = (List<CodeVO>)rrVO.getData();
	    
	  mav.addObject("languageCd", sessionUser.getLanguageCd());
	  mav.addObject("email", email);
	  mav.addObject("domainId", domainId);
	  mav.addObject("companyStatusCdList", companyStatusCdList);
	  mav.addObject("companyPosList", companyPosList);
	  mav.addObject("languageCd", sessionUser.getLanguageCd());
	  
	  return mav;
  }
  
  @SuppressWarnings("unchecked")
  @RequestMapping(value="/empMasterAddView.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView empMasterAddView(@RequestParam String companyCd, @RequestParam String deptCd, @RequestParam String deptNm) throws Exception {
	  SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
	  ModelAndView mav = new ModelAndView("admin/emp/empMasterAddView");
	  if(sessionUser != null) mav.addObject("sessionUser", sessionUser);
	  
	  Map<String, Object> paramsMap = new HashMap<>();
	  List<CompanyMasterVO> companyList = new ArrayList<>();
	  RestResultVO rrVO = new RestResultVO();
	  
	  // 회사 목록 조회
	  paramsMap.put("languageCd", sessionUser.getLanguageCd());
	  paramsMap.put("domainId", sessionUser.getDomainId());
	  paramsMap.put("email", sessionUser.getEmail());
	  rrVO = userEmpService.selectCompanySelBox(paramsMap);
	  companyList = (List<CompanyMasterVO>)rrVO.getData();
	   
	  mav.addObject("companyCd", companyCd);
	  mav.addObject("deptCd", deptCd);
	  mav.addObject("deptNm", deptNm);
	  mav.addObject("companyList", companyList);
	  mav.addObject("languageCd", sessionUser.getLanguageCd());
	  
	  return mav;
  }
  
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/empDeptXrefViewModal.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView empDeptXrefViewModal() throws Exception {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		ModelAndView mav = new ModelAndView("admin/emp/empDeptXrefViewModal");
		if (sessionUser != null) mav.addObject("sessionUser", sessionUser);

		Map<String, Object> paramsMap = new HashMap<>();
		List<CompanyMasterVO> companyList = new ArrayList<>();
		RestResultVO rrVO = new RestResultVO();

		// 회사 목록 조회
		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		paramsMap.put("domainId", sessionUser.getDomainId());
		paramsMap.put("email", sessionUser.getEmail());
		rrVO = userEmpService.selectCompanySelBox(paramsMap);
		companyList = (List<CompanyMasterVO>) rrVO.getData();
		
		mav.addObject("companyList", companyList);

		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/empDeptXrefAddModal.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView empDeptXrefAddModal() throws Exception {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
		ModelAndView mav = new ModelAndView("admin/emp/empDeptXrefAddModal");
		if (sessionUser != null) mav.addObject("sessionUser", sessionUser);

		Map<String, Object> paramsMap = new HashMap<>();
		List<CompanyMasterVO> companyList = new ArrayList<>();
		List<CodeVO> companyPosList = new ArrayList<>();
		RestResultVO rrVO = new RestResultVO();

		// 회사 목록 조회
		paramsMap.put("languageCd", sessionUser.getLanguageCd());
		paramsMap.put("domainId", sessionUser.getDomainId());
		paramsMap.put("email", sessionUser.getEmail());
		rrVO = userEmpService.selectCompanySelBox(paramsMap);
		companyList = (List<CompanyMasterVO>) rrVO.getData();
		
		mav.addObject("companyList", companyList);
		mav.addObject("companyPosList", companyPosList);

		return mav;
	}
}

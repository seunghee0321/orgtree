package com.dbinc.cloudoffice.orgtree.controller.user.emp;

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
import com.dbinc.cloudoffice.orgtree.service.user.emp.UserEmpService;
import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user/emp")
public class UserEmpController
{
  private final HttpSession httpSession;
  private final UserEmpService userEmpService;
  
  @SuppressWarnings("unchecked")
  @RequestMapping(value="/empMasterListView.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView empMasterListView() throws Exception
  {
    
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
    ModelAndView mav = new ModelAndView("user/emp/empMasterListView");
    if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
    
    Map<String, Object> paramsMap = new HashMap<>();
    List<CompanyMasterVO> companyList = new ArrayList<>();
    RestResultVO rrVO = new RestResultVO();

    // 회사 목록 조회
    paramsMap.put("languageCd", sessionUser.getLanguageCd());
    paramsMap.put("domainId", sessionUser.getDomainId());
    rrVO = userEmpService.selectCompanySelBox(paramsMap);
    companyList = (List<CompanyMasterVO>)rrVO.getData();
  
    mav.addObject("companyList", companyList);
    
    return mav;
  }
}

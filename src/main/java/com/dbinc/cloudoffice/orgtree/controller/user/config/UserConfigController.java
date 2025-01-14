package com.dbinc.cloudoffice.orgtree.controller.user.config;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbinc.cloudoffice.orgtree.security.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user/config")
public class UserConfigController
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final HttpSession httpSession;
  
  @RequestMapping(value="/empConfigView.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView empConfigView() throws Exception
  {
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
    ModelAndView mav = new ModelAndView("user/config/empConfigView");
    if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
    
    return mav;
  }
}

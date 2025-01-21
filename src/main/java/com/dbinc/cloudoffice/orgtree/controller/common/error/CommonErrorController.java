package com.dbinc.cloudoffice.orgtree.controller.common.error;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/common/error")
public class CommonErrorController
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @RequestMapping(value="/error.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView errorView(HttpServletRequest request) throws Exception
  {
    ModelAndView mav = new ModelAndView("common/error/error");
    return mav;
  }
  
  @RequestMapping(value="/accessDenied.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView accessDeniedView(HttpServletRequest request) throws Exception
  {
    ModelAndView mav = new ModelAndView("common/error/accessDenied");
    return mav;
  }
}

package com.dbinc.cloudoffice.orgtree.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggerInterceptor implements HandlerInterceptor
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    if (logger.isDebugEnabled())
    {
      logger.debug("■ LoggerInterceptor.preHandle() : START =================================");
      logger.debug("◆ Request URL:" + request.getRequestURL().toString());
      Map<?, ?> m = request.getParameterMap();
      Set<?> s = m.entrySet();
      Iterator<?> it = s.iterator();
      
      while (it.hasNext())
      {
        Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();
        String key = entry.getKey();
        String[] value = entry.getValue();
        logger.debug("◆ " + key + "=" + value[0]);
      }
      logger.debug("■ LoggerInterceptor.preHandle() : END =================================");
    }
    
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
      throws Exception
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception
  {
    // TODO Auto-generated method stub

  }
}

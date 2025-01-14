package com.dbinc.cloudoffice.orgtree.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener 
{
  @SuppressWarnings("unused")
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Override
  public void sessionCreated (HttpSessionEvent se)
  {
    se.getSession().setMaxInactiveInterval(60 * 120); // 세션만료 120분
  }
  
  @Override
  public void sessionDestroyed (HttpSessionEvent se) {}
}

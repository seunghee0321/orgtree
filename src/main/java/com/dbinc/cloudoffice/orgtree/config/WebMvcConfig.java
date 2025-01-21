package com.dbinc.cloudoffice.orgtree.config;

import java.util.Locale;

import jakarta.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.dbinc.cloudoffice.orgtree.interceptor.LoggerInterceptor;
import com.dbinc.cloudoffice.orgtree.listener.SessionListener;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
  @Autowired 
  LoggerInterceptor loggerInterceptor;
  
  /**
   * INDEX페이지 지정.
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry)
  {
    registry.addRedirectViewController("/", "/user/emp/empMasterListView.do");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }
  
  /**
   * 메시지 소스 생성
   */
  @Bean
  public ReloadableResourceBundleMessageSource messageSource()
  {
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

    source.setBasename("classpath:/messages/message");

    // 기본 인코딩을 지정한다.
    source.setDefaultEncoding("UTF-8");

    // 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다.
    source.setCacheSeconds(60);

    // 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
    source.setUseCodeAsDefaultMessage(true);
    return source;
  }
  
  /**
   * 변경된 언어정보를 기억할 로케일 리졸버 생성, 세션에 저장
   */
  @Bean 
  public LocaleResolver localeResolver()
  { 
    SessionLocaleResolver localeResolver = new SessionLocaleResolver(); 
    localeResolver.setDefaultLocale(Locale.KOREA); 
    return localeResolver; 
  }
  
  /**
   * 언어 변경을 위한 인터셉터를 생성한다.
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor()
  {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("lang");
    return interceptor;
  }
  
  /**
   * 세션유지시간 설정 
   */
  @Bean
  public HttpSessionListener httpSessionListener()
  {
    return new SessionListener();
  }
  
  /**
   * 인터셉터를 등록한다.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry)
  {
    registry.addInterceptor(localeChangeInterceptor());
    registry.addInterceptor(loggerInterceptor).addPathPatterns("/rest/**");
  }

  // 전역 CORS 설정 추가 1/16
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:8090", // 필요 없으면 제거 가능?
                    "chrome-extension://ibegofdgpaggjeecinamkadbcbbgjcba")
            .allowedMethods("GET", "POST", "PUT", "DELETE");
  }

}

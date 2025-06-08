//package com.dbinc.cloudoffice.orgtree.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter
//{
//  @Autowired
//  private AuthenticationSuccessHandler authSuccessHandler;
//
//  @Autowired
//  private AuthenticationFailureHandler authFailureHandler;
//
//  @Autowired
//  private AccessDeniedHandler accessDeniedHandler;
//
//  private final CustomOAuth2UserService customOAuth2UserService;
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception
//  {
//    http.csrf()
//        .disable()
//        .headers()
//        .frameOptions()
//        .disable()
//        .and()
//      .authorizeRequests()
//        .antMatchers("/" , "/service" , "/resources/**" , "/create", "/common/**", "/AdminLTE2/**", "/error/**").permitAll()
//        .antMatchers("/rest/admin/api/**").permitAll()
//        //.antMatchers("/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
//        .antMatchers("/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
//        .antMatchers("/rest/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
//        .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
//        .antMatchers("/rest/admin/**").hasRole(Role.ADMIN.name())
//        .anyRequest().authenticated()
//        .and()
//      .logout()
//        .logoutUrl("/logoutProcess.do")
//        .logoutSuccessUrl("/")
//        .deleteCookies("JSESSIONID")
//        .permitAll()
//        .and()
//      .oauth2Login()
//        .userInfoEndpoint()
//        .userService(customOAuth2UserService)
//        .and()
//        .successHandler(authSuccessHandler)
//        .failureHandler(authFailureHandler)
//        .and()
//        .exceptionHandling()
//        .accessDeniedHandler(accessDeniedHandler);
//  }
//
//  @Bean
//  public AuthenticationSuccessHandler authSuccessHandler()
//  {
//    return new CustomAuthenticationSuccessHandler("/");
//  }
//
//  @Bean
//  public AuthenticationFailureHandler authFailureHandler()
//  {
//    return new CustomAuthenticationFailureHandler("/common/error/error.do");
//  }
//
//  @Bean
//  public AccessDeniedHandler accessDeniedHandler()
//  {
//    return new CustomAccessDeniedHandler("/common/error/accessDenied.do");
//  }
//}

package com.dbinc.cloudoffice.orgtree.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/service", "/resources/**", "/create", "/common/**", "/error/**").permitAll()
                    .requestMatchers("/rest/admin/api/**").permitAll()
                    //.requestMatchers("/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                    .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/rest/user/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/rest/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .logout(logout -> logout
                    .logoutUrl("/logoutProcess.do")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                    .permitAll()
            )
            .oauth2Login(oauth2 -> oauth2
                    .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                    .successHandler(authSuccessHandler())
                    .failureHandler(authFailureHandler())
            )
            .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));

    return http.build();
  }

  @Bean
  public AuthenticationSuccessHandler authSuccessHandler() {
    return new CustomAuthenticationSuccessHandler("/");
  }

  @Bean
  public AuthenticationFailureHandler authFailureHandler() {
    return new CustomAuthenticationFailureHandler("/common/error/error.do");
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler("/common/error/accessDenied.do");
  }
}

package com.dbinc.cloudoffice.orgtree.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final EmpMasterRepository empMasterRepository;
  private final EmpAuthorityRepository empAuthorityRepository;
  private final HttpSession httpSession;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
  {
    logger.debug("■ CustomOAuth2UserService.loadUser.");

    OAuth2UserService delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);
    List<EmpAuthority> empAuthorities = new ArrayList<>();
    Set<GrantedAuthority> authorities = new HashSet<>();

    // 현재 로그인 진행중인 서비스를 구분하는 코드, 지금은 구글만 사용하는 불필요한 기능이지만 이후 네이버 로그인 연동시 필요
    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    // OAuth2 로그인 진행 시 키가되는 필드값을 이야기 합니다. PK와 같은 의미, 구글만 기본 코드 sub를 지원
    String userNameAttributedName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
    OAuthAttributes attributes = OAuthAttributes.of(userNameAttributedName, oAuth2User.getAttributes());

    // 사용자 기본정보 조회 
    Optional<EmpMaster> empMaster = empMasterRepository.findByEmail(attributes.getEmail());

    // 사용자가 있으면
    if (empMaster.isPresent())
    {
      // 사용자 회사 정보가 있는지 체크 
      if (empMaster.get().getCompanyMaster() != null)
      {
        // 사용자 회사 정보 사용여부에 따라 진행 여부 판단.
        if (!"Y".equals(empMaster.get().getCompanyMaster().getUseYn()))
          throw new InvalidCompanyException("User Company Invalid Exception");
      }
      else
      {
        throw new InvalidCompanyException("User Company Invalid Exception");
      }

      // 사용자 ROLE 정보 조회 (리스트 조회) 후 유무에 따라 진행 여부 판단
      empAuthorities = empAuthorityRepository.findByEmail(attributes.getEmail());

      if (empAuthorities == null || empAuthorities.isEmpty())
        throw new UserAuthorityNotFoundException("User Authority Not Found Exception");
      else
      {
        for(int i = 0 ; i < empAuthorities.size() ; i++)
          authorities.add(new SimpleGrantedAuthority(empAuthorities.get(i).getAuthorityName()));
      }

      // 사용자 정보를 이용해 세션 객체 생성 후 세션에 저장
      SessionUser sessionUser = new SessionUser(empMaster);
      sessionUser.setPicture(attributes.getAttributes().get("picture").toString());
      httpSession.setAttribute("LOGIN_SESSION_USER", sessionUser);
    }
    else
    {
      throw new UsernameNotFoundException("User Email Not Found Exception");
    }

//    logger.debug("oAuth2User.toString() >>>>>>>>>>>>>>> " + oAuth2User.toString());
//    logger.debug("registrationId >>>>>>>>>>>>>>> " + registrationId);
//    logger.debug("userNameAttributedName >>>>>>>>>>>>>>> " + userNameAttributedName);
//    logger.debug("attributes.getEmail >>>>>>>>>>>>>>> " + attributes.getEmail());
//    logger.debug("attributes.getName >>>>>>>>>>>>>>> " + attributes.getName());

    return new DefaultOAuth2User(authorities, attributes.getAttributes(), attributes.getNameAttributeKey());
  }
}
package com.sample.sbsample.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 인증과정에서 실패하거나 인증헤더(Authorization)를 보내지 않게되는 경우 401(UnAuthorized) 라는 응답값을 받게 될때 처리하는 부분
 * Response에 401이 떨어질만한 에러가 발생할 경우 해당로직을 타게되어, commence라는 메소드를 실행
 */

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    
    private static final long serialVersionUID = 6439559666353980335L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        
        if (log.isDebugEnabled()) {
            log.debug("::: JwtAuthenticationEntryPoint.commence :::");
        }
        
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized [권한이 존재하지 않습니다.]");
    }
}

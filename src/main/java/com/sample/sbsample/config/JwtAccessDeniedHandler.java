package com.sample.sbsample.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 인증과정에서 실패하거나 인증헤더(Authorization)를 보내지 않게되는 경우 403(forbidden) 라는 응답값을 받게 될때 처리하는 부분
 * Response에 403이 떨어질만한 에러가 발생할 경우 해당로직을 타게되어, commence라는 메소드를 실행
 */

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        if (log.isDebugEnabled()) {
            log.debug("::: JwtAccessDeniedHandler.handle :::");
        }
        
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden [권한이 존재하지 않습니다.]");
		
	}
}

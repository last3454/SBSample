package com.sample.sbsample.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    private final RequestMatcher apiRequests = new AntPathRequestMatcher("/api/**");
    private final RequestMatcher uploadRequests = new AntPathRequestMatcher("/upload/**");
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// AntPathRequestMatcher 에 설정된 자원정보와 클라이언트의 요청정보(request)에 저장된 url 정보가 일치하는지 비교
		if (!apiRequests.matches(request) && !uploadRequests.matches(request)) {
			filterChain.doFilter(request, response);
			return;
		}

        if (log.isDebugEnabled()) {
            StringBuffer sbLog = new StringBuffer();
            sbLog.append("\n").append("URI : ").append(request.getRequestURI());
            sbLog.append("\n").append("Method : ").append(request.getMethod());
            log.debug(sbLog.toString());
        }
            
        filterChain.doFilter(request, response);
	}
}

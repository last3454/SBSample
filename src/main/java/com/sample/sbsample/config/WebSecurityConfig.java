package com.sample.sbsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * prePostEnabled = true
 * Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우 @PreAuthorize 어노테이션을 사용하는데, 해당 어노테이션에 대한 설정을 활성화시키는 어노테이션입니다.
 * securedEnabled = true
 * Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우 @Secured 어노테이션을 사용하는데, 해당 어노테이션에 대한 설정을 활성화시키는 어노테이션입니다.
 */

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //ROLE 관련 권한을 확장해서 사용하고 싶을때 오픈시키면 된다.
public class WebSecurityConfig {
	
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;	
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	//디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        return (web) -> web.ignoring().antMatchers("/swagger-resources/**", "/swagger-ui.html", "/swagger/**");
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    	return configuration.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	log.info("security config..............");
    	
        http
        	.httpBasic().disable()  // 기본설정 사용안함 - 기본설정은 비인증시 로그인 화면으로 이동됨.
        	.csrf().disable()		// csrf 보안 disable 처리
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함
            .and()
        	.authorizeRequests()
//        		.antMatchers(HttpMethod.OPTIONS).permitAll()
        		.antMatchers("/api/auth/signin").permitAll()
        		.antMatchers("/api/**").authenticated()
        		.anyRequest().permitAll()
        	.and()
        		.exceptionHandling()
        		.authenticationEntryPoint(jwtAuthenticationEntryPoint) //401 에러 관련해서 처리
        		.accessDeniedHandler(jwtAccessDeniedHandler)  //403 에러 관련해서 처리, 인증을 이부분엣
        	.and()
        		.formLogin().disable();
        
        	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}

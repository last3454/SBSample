package com.sample.sbsample.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("v1")
				.pathsToMatch("/api/**")
				.build();
	}
	
	@Bean
	public OpenAPI springSOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("MyBeaker API")
						.description("아모레퍼시픽 MyBeaker API 명세서 입니다.")
						.version("0.1"));
	}
}

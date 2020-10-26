package com.hardik.abn.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig is to configure and enable swagger UI. You can use
 * Swagger UI using {host}/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Account api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket accountApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.hardik.abn.assessment.controller")).build();
	}

}
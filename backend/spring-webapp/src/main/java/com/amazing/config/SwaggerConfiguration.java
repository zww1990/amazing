package com.amazing.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 * 
 * @author home
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = SwaggerProperties.SWAGGER_PROPERTIES_PREFIX, name = "enabled", havingValue = "true")
@ConditionalOnWebApplication
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {
	@Bean
	public Docket api(SwaggerProperties props) {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(props.getBasePackage())).build()
				.apiInfo(new ApiInfo(props.getTitle(), props.getDescription(), props.getVersion(),
						props.getTermsOfServiceUrl(), new Contact(props.getName(), props.getUrl(), props.getEmail()),
						props.getLicense(), props.getLicenseUrl(), Collections.emptyList()));
	}

	@Bean
	public CsrfController csrfController() {
		return new CsrfController();
	}

	@RequestMapping
	public static class CsrfController {
		@GetMapping("/csrf")
		@ResponseBody
		public Object csrf() {
			return Arrays.asList("你好，世界！");
		}
	}
}

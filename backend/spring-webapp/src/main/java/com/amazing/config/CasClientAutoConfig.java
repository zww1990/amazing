package com.amazing.config;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.boot.configuration.CasClientConfigurationProperties;
import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

/**
 * CAS 配置类
 * 
 * @author home
 */
@Configuration
@EnableCasClient
@ConditionalOnProperty(prefix = CasClientConfigurationProperties.CAS_CLIENT_CONFIG_PROPERTIES_PREFIX, name = "enabled", havingValue = "true")
@ConditionalOnWebApplication
public class CasClientAutoConfig {
	@Bean
	public CasClientController casClientController() {
		return new CasClientController();
	}

	@RequestMapping
	public static class CasClientController {
		@Resource
		private CasClientConfigurationProperties properties;

		@GetMapping("/")
		public Object index() {
			if (StringUtils.hasText(properties.getClientWebappUrl())) {
				return new RedirectView(properties.getClientWebappUrl());
			}
			return ResponseEntity.ok(Arrays.asList("你好世界！！！"));
		}

		@GetMapping("/logout")
		@ResponseBody
		public Object logout(HttpSession session) {
			session.invalidate();
			return Arrays.asList(properties.getServerLogoutUrl());
		}
	}
}

package com.amazing.config;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置类
 * 
 * @author home
 */
@Configuration
@EnableWebMvc
public class DispatcherConfig implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof AbstractHttpMessageConverter) {
				AbstractHttpMessageConverter<?> hmc = (AbstractHttpMessageConverter<?>) converter;
				hmc.setDefaultCharset(StandardCharsets.UTF_8);
			}
		}
	}

	@Bean
	@ConditionalOnMissingBean(CasClientAutoConfig.class)
	public IndexController indexController() {
		return new IndexController();
	}

	@RequestMapping
	public static class IndexController {
		@GetMapping("/")
		@ResponseBody
		public Object index() {
			return Arrays.asList("你好世界！！！");
		}
	}
}

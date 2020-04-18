package com.amazing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = { TaskExecutionAutoConfiguration.class, TaskSchedulingAutoConfiguration.class,
		ProjectInfoAutoConfiguration.class, ValidationAutoConfiguration.class, AopAutoConfiguration.class,
		OAuth2ResourceServerAutoConfiguration.class, MultipartAutoConfiguration.class,
		PersistenceExceptionTranslationAutoConfiguration.class, RestTemplateAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class, WebSocketServletAutoConfiguration.class })
public class SpringWebApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringWebApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringWebApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
}

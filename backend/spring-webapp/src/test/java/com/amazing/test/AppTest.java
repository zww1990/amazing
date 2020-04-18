package com.amazing.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.amazing.SpringWebApplication;

@SpringJUnitWebConfig(classes = SpringWebApplication.class)
public class AppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		String[] names = this.context.getBeanDefinitionNames();
		for (int i = 0; i < names.length; i++) {
			System.err.println((i + 1) + "\t" + names[i]);
		}
	}
}

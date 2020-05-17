package com.amazing.controller;

import java.security.Principal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 根控制器
 * 
 * @author home
 */
@RestController
@RequestMapping
@ApiIgnore("忽略根访问路径")
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping({ "/", "/csrf" })
	public Object index(Principal principal) {
		log.info("{}", principal);
		return Arrays.asList("你好，", "世界！");
	}
}

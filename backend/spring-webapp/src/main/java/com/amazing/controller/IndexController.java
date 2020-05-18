package com.amazing.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 根控制器
 * 
 * @author home
 */
@Controller
@ApiIgnore("忽略根访问路径")
public class IndexController {
	@Value("${cas.client-webapp-url}")
	private String clientWebappUrl;

	@GetMapping("/csrf")
	@ResponseBody
	public Object csrf() {
		return Arrays.asList("你好，", "世界！");
	}

	@GetMapping("/")
	public Object index() {
		return new RedirectView(this.clientWebappUrl);
	}
}

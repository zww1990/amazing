package com.amazing.controller;

import java.util.Arrays;

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
	@GetMapping({ "/", "/csrf" })
	public Object index() {
		return Arrays.asList("你好，", "世界！");
	}
}
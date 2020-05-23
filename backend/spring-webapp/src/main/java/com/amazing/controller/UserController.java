package com.amazing.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazing.support.UserModel;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Value("${cas.enabled}")
	private boolean casEnabled;

	@GetMapping("/query")
	public Object query(Principal principal) {
		UserModel model = new UserModel();
		if (casEnabled) {
			log.info("UserId = {}", principal.getName());
			model.setUserId(principal.getName());
			model.setUserName("攻城狮");
		} else {
			model.setUserId("666666");
			model.setUserName("超级管理员");
		}
		return model;
	}
}

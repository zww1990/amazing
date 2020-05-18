package com.amazing.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazing.support.UserModel;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/query")
	public Object query(Principal principal) {
		log.info("{}", principal.getName());
		UserModel model = new UserModel();
		model.setUserId(Integer.parseInt(principal.getName()));
		model.setUserName("攻城狮");
		return model;
	}
}

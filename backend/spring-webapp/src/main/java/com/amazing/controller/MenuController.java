package com.amazing.controller;

import java.security.Principal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazing.support.MenuModel;

@RestController
@RequestMapping("/menu")
public class MenuController {
	private static final Logger log = LoggerFactory.getLogger(MenuController.class);

	@GetMapping("/query")
	public Object query(Principal principal) {
		log.info("查询菜单");
		MenuModel m1 = new MenuModel();
		m1.setMenuId(1);
		m1.setMenuName("示例1");
		m1.setMenuIcon("appstore");
		MenuModel m2 = new MenuModel();
		m2.setMenuId(2);
		m2.setMenuName("示例2");
		m2.setMenuIcon("appstore");
		MenuModel m3 = new MenuModel();
		m3.setMenuId(3);
		m3.setMenuName("示例1.1");
		m3.setMenuUrl("/demo/page1");
		m3.setParentMenuId(1);
		MenuModel m4 = new MenuModel();
		m4.setMenuId(4);
		m4.setMenuName("示例2.1");
		m4.setMenuUrl("/demo/page2");
		m4.setParentMenuId(2);
		MenuModel m5 = new MenuModel();
		m5.setMenuId(5);
		m5.setMenuName("示例2.2");
		m5.setMenuUrl("/demo/page3");
		m5.setParentMenuId(2);
		MenuModel m6 = new MenuModel();
		m6.setMenuId(6);
		m6.setMenuName("示例2.3");
		m6.setMenuUrl("/demo/page4");
		m6.setParentMenuId(2);
		return Arrays.asList(m1, m2, m3, m4, m5, m6);
	}
}

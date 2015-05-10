package com.ccc.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class JspPageController {

	@RequestMapping("/main")
	public String toMainPage(){
		System.out.println("toMainPage");
		return "main";
	}
	@RequestMapping("/toAllUserPage")
	public String toAddUser(){
		System.out.println("toAddUser");
			return "addUser";
	}
	
}

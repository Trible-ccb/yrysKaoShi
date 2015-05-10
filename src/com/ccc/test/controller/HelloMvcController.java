package com.ccc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloMvcController {

	@RequestMapping(value = "/hello.do")
	public ModelAndView hello(){
		System.out.println("hello");
		return new ModelAndView("hello");
	}
	
	@RequestMapping("/test.do")
	public String test(
			HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("password") String psw,
			HttpServletResponse response,
			Model model
			){
		
		return "";
	}
}

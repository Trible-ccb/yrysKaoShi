package com.ccc.test.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ccc.test.pojo.UserInfo;
import com.ccc.test.service.interfaces.IBaseService;
import com.ccc.test.utils.GlobalValues;

//´ú±í¿ØÖÆ²ã
@Controller
@RequestMapping("/user")
@SessionAttributes(GlobalValues.SESSION_USER)
public class UserController {

	@Autowired
	IBaseService<UserInfo> userService;
	
	@RequestMapping("/addUser.do")
	public String addUser(
			String username,
			String password,
			Model model){
		
		UserInfo user = new UserInfo();
		user.setPassword(password);
		user.setUsername(username);
		Serializable id = userService.add(user);
		System.out.println("username = "+ username +" password = " + password + " id = " + id);
		if ( id != null ){
			model.addAttribute("uid", id);
			return "redirect:/user/getAllUser.do";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/login.do",method = RequestMethod.POST)
	public String login(
			String username,
			String password,
			ModelMap model){
		
		UserInfo user = new UserInfo();
		user.setPassword(password);
		user.setUsername(username);
		model.addAttribute(GlobalValues.SESSION_USER,user);
		
		return "userinfo";
	}
	
	@RequestMapping(value = "/loginOut.do",method = RequestMethod.POST)
	public String logOut(
			@ModelAttribute(GlobalValues.SESSION_USER)UserInfo user,
			HttpSession httpSession){
		return "main";
	}
	
	@RequestMapping("/getAllUser.do")
	public String getAllUser(Model model){
		List<UserInfo> userlist = userService.getList(null);
		model.addAttribute("userlist", userlist);
		return "allUser";
	}
}

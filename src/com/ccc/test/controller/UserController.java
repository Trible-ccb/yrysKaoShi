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
import com.ccc.test.service.interfaces.IUserService;
import com.ccc.test.utils.GlobalValues;
import com.ccc.test.utils.ListUtil;
import com.ccc.test.utils.SecurityMethod;
import com.ccc.test.utils.StringUtil;

//代表控制层
@Controller
@RequestMapping("/user")
@SessionAttributes(GlobalValues.SESSION_USER)
public class UserController {

	@Autowired
	IUserService userService;
	
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
	
	/**用户注册时调用
	 * @param username
	 * @param password
	 * @param con_password
	 * @param usertype
	 * @param model
	 * @return 返回页面main.jsp
	 */
	@RequestMapping(value = "/register.do",method = RequestMethod.POST)
	public String register(
			String username,//参数名字与jsp传递的参数名字一致
			String password,
			String con_password,
			String usertype,
			ModelMap model){
		
		if ( ListUtil.isEmpty(username)
				|| ListUtil.isEmpty(password)
				|| ListUtil.isEmpty(con_password)
				|| ListUtil.isEmpty(usertype) 
				|| !password.equals(con_password) ){
			model.addAttribute("result","注册信息输入有误");
			return "main";
		}
		UserInfo t = new UserInfo();
		String md5psw = SecurityMethod.encryptMD5(SecurityMethod.encryptMD5(password));
		t.setPassword(md5psw);
		t.setUsername(username);
		t.setType(usertype);
		Serializable id = userService.add(t);
		if ( id instanceof Integer && (Integer)id < 0 ){
			model.addAttribute("result","注册失败，请重新输入。");
		} else {
			model.addAttribute("result","恭喜你，注册成功，请登录。");
		}
		return "main";
	}
	@RequestMapping(value = "/login.do",method = RequestMethod.POST)
	public String login(
			String username,
			String password,
			String usertype,
			ModelMap model){
		String tokenid = userService.login(username, password,usertype);
		UserInfo user = userService.fetchUserInfo(tokenid);
		model.addAttribute(GlobalValues.SESSION_USER,user);
		if ( user == null ){
			model.addAttribute("result", "用户名或密码错误");
			return "main";
		}
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

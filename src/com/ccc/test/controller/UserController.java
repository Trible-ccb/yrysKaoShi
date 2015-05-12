package com.ccc.test.controller;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ccc.test.pojo.UserInfo;
import com.ccc.test.service.interfaces.IUserService;
import com.ccc.test.utils.GlobalValues;
import com.ccc.test.utils.ListUtil;

//代表控制层
@Controller
@RequestMapping("/user")
@SessionAttributes(GlobalValues.SESSION_USER)
public class UserController {

	@Autowired
	IUserService userService;
	SimpleHandleException she = new SimpleHandleException();
	
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
				){
			model.addAttribute("result","注册信息不能为空！");
		} else if( !password.equals(con_password) ){
			model.addAttribute("result","两次密码不匹配！");
		} else {
			
			try {
				Serializable id = userService.register(username, password, con_password, usertype);
				if ( id instanceof Integer ){
					Integer uid = (Integer) id;
					if( uid == -1 ){
						model.addAttribute("result","注册失败，用户名已经被注册！");
					} else if ( uid > 0 ){
						model.addAttribute("result","恭喜你，注册成功，请登录！");
					}
				} else {
					model.addAttribute("result","未知错误，请重新注册！");
				}
			} catch (Exception e) {
				she.handle(e, model);
			}

		}
		return "main";
	}
	
	
	/**用户登录时调用
	 * @param username
	 * @param password
	 * @param usertype
	 * @param model 用于保存返回的数据，这里是保存用户对象
	 * @return 返回页面名字字符串，如main.jsp页面对应的字符串是main
	 */
	@RequestMapping(value = "/login.do",method = RequestMethod.POST)
	public String login(
			String username,
			String password,
			String usertype,
			ModelMap model){
		
		if ( ListUtil.isEmpty(username)
				|| ListUtil.isEmpty(password)
				|| ListUtil.isEmpty(usertype) 
				){
			model.addAttribute("result","输入不能为空！");
			return "main";
		}
		try {
			String tokenid = userService.login(username, password,usertype);
			UserInfo user = userService.fetchUserInfo(tokenid);
			if ( user == null ){
				model.addAttribute("result", "用户名与密码不匹配！");
				return "main";
			}
			//addAttribute 值不能为空
			model.addAttribute(GlobalValues.SESSION_USER,user);
			return "userinfo";
		} catch (Exception e) {
			she.handle(e, model);
			return "main";
		}

	}
	
	/**用户登出调用
	 * @param user 登出前，保存在Session中的user
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/loginOut.do",method = RequestMethod.POST)
	public String logOut(
			@ModelAttribute(GlobalValues.SESSION_USER)UserInfo user,
			HttpSession httpSession){
		return "main";
	}
}

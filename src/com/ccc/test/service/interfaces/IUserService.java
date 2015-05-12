package com.ccc.test.service.interfaces;

import java.io.Serializable;

import com.ccc.test.pojo.UserInfo;

public interface IUserService {

	/**登录接口
	 * @return 返回token字符串，用于该用户的其他操作 可以简单返回id
	 */
	String login(String username,String password,String type)  throws Exception ;

	/**登出接口
	 * @param token 用户唯一表示token
	 * @return 成功返回0
	 * @throws Exception
	 */
	Serializable loginOut(String token)  throws Exception ;
	
	/**注册接口
	 * @return 注册成功返回用户的uid，否则<0代表有误
	 */
	Serializable register(String username,String password,String conPassword, String type)  throws Exception ;
	
	/**注册接口
	 * @return 注册成功返回用户id，否则<0代表有误
	 */
	Serializable updateUserInfo(UserInfo info)  throws Exception ;
	
	/**根据需要获取用户部分信息，不包括密码等敏感信息
	 * @param token 用户的token字符串
	 * @return 返回用户数据对象
	 */
	UserInfo fetchUserInfo(String token)  throws Exception;
	
}

package com.ccc.test.service.interfaces;

import com.ccc.test.pojo.UserInfo;

public interface IUserService extends IBaseService<UserInfo> {

	
	/**登录接口
	 * @return 返回token字符串，用于该用户的其他操作
	 */
	String login(String username,String password);
	
	/**根据需要获取用户部分信息，不包括密码等敏感信息
	 * @param token 用户的token字符串
	 * @return 返回用户数据对象
	 */
	UserInfo fetchUserInfo(String token);
}

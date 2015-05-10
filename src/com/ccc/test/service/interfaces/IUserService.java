package com.ccc.test.service.interfaces;

import com.ccc.test.pojo.UserInfo;

public interface IUserService extends IBaseService<UserInfo> {

	
	/**��¼�ӿ�
	 * @return ����token�ַ��������ڸ��û�����������
	 */
	String login(String username,String password);
	
	/**������Ҫ��ȡ�û�������Ϣ�������������������Ϣ
	 * @param token �û���token�ַ���
	 * @return �����û����ݶ���
	 */
	UserInfo fetchUserInfo(String token);
}

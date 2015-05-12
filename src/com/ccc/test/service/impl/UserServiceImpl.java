package com.ccc.test.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccc.test.hibernate.AbSessionHelper;
import com.ccc.test.hibernate.dao.interfaces.IBaseHibernateDao;
import com.ccc.test.pojo.UserInfo;
import com.ccc.test.service.interfaces.IUserService;
import com.ccc.test.utils.ListUtil;
import com.ccc.test.utils.SecurityMethod;

//代表服务层
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IBaseHibernateDao<UserInfo> userDao ;
	
	@Override
	public String login(String username, String password,String type) throws Exception {
		String md5psw = SecurityMethod.encryptMD5(SecurityMethod.encryptMD5(password));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(UserInfo.USERINFO_COLUMN_USER_NAME, username);
		map.put(UserInfo.USERINFO_COLUMN_PASSWORD, md5psw);
		map.put(UserInfo.USERINFO_COLUMN_TYPE, type);
		List<UserInfo> users = userDao.getList(map);
		if ( ListUtil.isEmpty(users) ){
			return "-1";
		} else {
			return users.get(0).getId()+"";
		}
	}

	@Override
	public UserInfo fetchUserInfo(String token) throws Exception {
		Integer id = Integer.valueOf(token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(UserInfo.USERINFO_COLUMN_ID, id);
		List<UserInfo> users = userDao.getList(map);
		if ( ListUtil.isEmpty(users) ){
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public Serializable loginOut(String token) throws Exception {
		return false;
	}

	@Override
	public Serializable register(String username, String password,
			String conPassword, String type) throws Exception {
		String md5psw = SecurityMethod.encryptMD5(SecurityMethod.encryptMD5(password));
		UserInfo t = new UserInfo();
		t.setPassword(md5psw);
		t.setUsername(username);
		t.setType(type);
		Serializable id = userDao.add(t);
		return id;
	}

	@Override
	public Serializable updateUserInfo(UserInfo info) throws Exception {
		return false;
	}

}

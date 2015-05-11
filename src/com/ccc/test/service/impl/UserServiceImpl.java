package com.ccc.test.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccc.test.hibernate.dao.AbSessionHelper;
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
	public UserInfo getById(Serializable id) {
		Integer uid = null;
		if ( id instanceof Integer ){
			uid = (Integer) id;
			return userDao.getById(uid);
		}
		return null;
	}

	@Override
	public List<UserInfo> getList(Map<String, Object> arg) {
		return userDao.getList(arg);
	}

	@Override
	public boolean delete(UserInfo t) {
		return userDao.delete(t);
	}

	@Override
	public boolean deleteAll(List<UserInfo> list) {
		return userDao.deleteAll(list);
	}

	@Override
	public boolean update(UserInfo t) {
		return userDao.update(t);
	}

	@Override
	public Serializable add(UserInfo t) {
		return userDao.add(t);
	}

	@Override
	public String login(String username, String password,String type) {
		String md5psw = SecurityMethod.encryptMD5(SecurityMethod.encryptMD5(password));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(UserInfo.USERINFO_COLUMN_USER_NAME, username);
		map.put(UserInfo.USERINFO_COLUMN_PASSWORD, md5psw);
		map.put(UserInfo.USERINFO_COLUMN_TYPE, type);
		List<UserInfo> users = getList(map);
		if ( ListUtil.isEmpty(users) ){
			return "-1";
		} else {
			return users.get(0).getId()+"";
		}
	}

	@Override
	public UserInfo fetchUserInfo(String token) {
		try{
			Integer id = Integer.valueOf(token);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(UserInfo.USERINFO_COLUMN_ID, id);
			List<UserInfo> users = getList(map);
			if ( ListUtil.isEmpty(users) ){
				return null;
			} else {
				return users.get(0);
			}
		}catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

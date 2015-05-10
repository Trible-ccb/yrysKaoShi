package com.ccc.test.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccc.test.hibernate.dao.AbSessionHelper;
import com.ccc.test.hibernate.dao.interfaces.IBaseHibernateDao;
import com.ccc.test.pojo.UserInfo;
import com.ccc.test.service.interfaces.IUserService;

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
	public List<UserInfo> getList(Object arg) {
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
	public String login(String username, String password) {
		return new AbSessionHelper<String>() {
			@Override
			public String handleSession(Session s) {
				return null;
			}
		}.getResult();
	}

	@Override
	public UserInfo fetchUserInfo(String token) {
		return null;
	}
}

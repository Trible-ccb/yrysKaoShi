package com.ccc.test.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ccc.test.hibernate.dao.AbSessionHelper;
import com.ccc.test.hibernate.dao.interfaces.IBaseHibernateDao;
import com.ccc.test.pojo.UserInfo;

//代表数据持久层
@Repository
public class UserDaoImpl implements IBaseHibernateDao<UserInfo>{

	@Override
	public UserInfo getById(final Serializable id) {
		return new AbSessionHelper<UserInfo>() {

			@Override
			public UserInfo handleSession(Session s) {
				return (UserInfo) s.get(UserInfo.class, id);
			}
		}.getResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getList(Object arg) {
		return null;
	}

	@Override
	public boolean delete(UserInfo t) {
//		hibernateTemplate.delete(t);
		return true;
	}

	@Override
	public boolean deleteAll(List<UserInfo> list) {
		if ( list == null )return false;
		for ( UserInfo info : list ){
			delete(info);
		}
		return true;
	}

	@Override
	public boolean update(UserInfo t) {
//		hibernateTemplate.update(t);
		return true;
	}

	@Override
	public Serializable add(final UserInfo t) {

		return new AbSessionHelper<Serializable>() {

			@Override
			public Serializable handleSession(Session s) {
				return s.save(t);
			}
		}.getResult();
	}

	
}

package com.ccc.test.hibernate.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ccc.test.hibernate.AbSessionHelper;
import com.ccc.test.hibernate.QueryParamsHelper;
import com.ccc.test.hibernate.dao.interfaces.IBaseHibernateDao;
import com.ccc.test.pojo.UserInfo;
import com.ccc.test.utils.ListUtil;

//代表数据持久层
@Repository
public class UserDaoImpl implements IBaseHibernateDao<UserInfo>{

	@Override
	public UserInfo getById(final Serializable id) throws Exception {
		return new AbSessionHelper<UserInfo>() {
			@Override
			public UserInfo handleSession(Session s) {
				return (UserInfo) s.get(UserInfo.class, id);
			}
		}.getResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getList(final Map<String, Object> args)  throws Exception {
		if ( ListUtil.isEmpty(args))return null;
		return new AbSessionHelper<List<UserInfo>>() {
			@Override
			public List<UserInfo> handleSession(Session s) {
				QueryParamsHelper qph = new QueryParamsHelper();
				for ( Entry<String, Object> entry : args.entrySet() ){
					qph.add("=", entry.getKey(), entry.getValue());
				}
				String hql = "FROM UserInfo WHERE " ;    
				
		         Query query = qph.buildQuery(s, hql);
		         List<UserInfo> results = query.list();
				return results;
			}
		}.getResult();
	}

	@Override
	public boolean delete(UserInfo t)  throws Exception {
//		hibernateTemplate.delete(t);
		return true;
	}

	@Override
	public boolean deleteAll(List<UserInfo> list)  throws Exception{
		if ( list == null )return false;
		for ( UserInfo info : list ){
			delete(info);
		}
		return true;
	}

	@Override
	public boolean update(UserInfo t)  throws Exception{
//		hibernateTemplate.update(t);
		return true;
	}

	@Override
	public Serializable add(final UserInfo t)  throws Exception{
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(UserInfo.USERINFO_COLUMN_USER_NAME, t.getUsername());
		List<UserInfo> checkExist = getList(args);
		
//		用户名已存在，不能添加
		if ( !ListUtil.isEmpty(checkExist) ){
			return -1;
		}
		return new AbSessionHelper<Serializable>() {
			@Override
			public Serializable handleSession(Session s) {
				return s.save(t);
			}
		}.getResult();
	}
	
	
	
}

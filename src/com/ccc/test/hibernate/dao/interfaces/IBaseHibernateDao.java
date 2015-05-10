package com.ccc.test.hibernate.dao.interfaces;

import java.io.Serializable;
import java.util.List;


public interface IBaseHibernateDao <T>{


	public abstract T getById(Serializable id);
	
	public abstract List<T> getList(Object arg);
	
	public abstract boolean delete(T t);
	public abstract boolean deleteAll(List<T> list);
	public abstract boolean update(T t);
	public abstract Serializable add(T t);
}

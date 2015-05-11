package com.ccc.test.hibernate.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface IBaseHibernateDao <T>{


	/**
	 * @param id 根据id获取对象
	 * @return
	 */
	public abstract T getById(Serializable id);
	
	/**根据属性值获取对象列表
	 * @param args 参数key为数据库列表名，参数value为属性值
	 * @return 返回对象列表
	 */
	public abstract List<T> getList(Map<String, Object> args);
	
	/**
	 * @param t 删除对象
	 * @return 成功返回true 否则返回false
	 */
	public abstract boolean delete(T t);
	
	/**
	 * @param list 批量删除对象
	 * @return
	 */
	public abstract boolean deleteAll(List<T> list);
	
	/**
	 * @param t 更新对象
	 * @return
	 */
	public abstract boolean update(T t);
	
	/**
	 * @param t 添加对象
	 * @return
	 */
	public abstract Serializable add(T t);
}

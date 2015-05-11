package com.ccc.test.service.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseService <T>{

	/**获取类型T的对象
	 * @param id 对象的id
	 * @return T的实例
	 */
	T getById(Serializable id);
	
	/**获取一组T的对象
	 * @param arg 获取参数
	 * @return 返回一组T对象
	 */
	List<T> getList(Map<String, Object> args);
	
	/**删除对象T
	 * @param t 要删除的对象
	 * @return 删除成功返回true，否则false；
	 */
	boolean delete(T t);
	
	/**删除的对象列表
	 * @param list 要删除的对象列表
	 * @return 删除成功返回true，否则false；
	 */
	boolean deleteAll(List<T> list);
	
	/**更新对象
	 * @param t 需要更新的对象
	 * @return 成功返回true，否则false；
	 */
	boolean update(T t);
	
	/**添加对象
	 * @param t 需要添加的对象
	 * @return 成功返回对象id，否则null；
	 */
	Serializable add(T t);
}

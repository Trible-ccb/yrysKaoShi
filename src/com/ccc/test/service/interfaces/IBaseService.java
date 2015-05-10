package com.ccc.test.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IBaseService <T>{

	/**��ȡ����T�Ķ���
	 * @param id �����id
	 * @return T��ʵ��
	 */
	T getById(Serializable id);
	
	/**��ȡһ��T�Ķ���
	 * @param arg ��ȡ����
	 * @return ����һ��T����
	 */
	List<T> getList(Object arg);
	
	/**ɾ������T
	 * @param t Ҫɾ���Ķ���
	 * @return ɾ���ɹ�����true������false��
	 */
	boolean delete(T t);
	
	/**ɾ���Ķ����б�
	 * @param list Ҫɾ���Ķ����б�
	 * @return ɾ���ɹ�����true������false��
	 */
	boolean deleteAll(List<T> list);
	
	/**���¶���
	 * @param t ��Ҫ���µĶ���
	 * @return �ɹ�����true������false��
	 */
	boolean update(T t);
	
	/**��Ӷ���
	 * @param t ��Ҫ��ӵĶ���
	 * @return �ɹ����ض���id������null��
	 */
	Serializable add(T t);
}

package com.ccc.test.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ccc.test.hibernate.HibernateSessionFactory;
import com.ccc.test.utils.Bog;

/**
 * @author Trible Chen
 *Session 操作辅助类
 */
	
public abstract class AbSessionHelper <T> {

	/**
	 * @author Trible Chen
	 *操作数据库 Session回调函数
	 * @param <T> 
	 */
	public abstract T handleSession(Session s);
	
	/**获取操作Session后的结果
	 * @return 返回T类型的对象
	 */
	public T getResult(){
		Session s = null;  
        Transaction t = null;
        T result = null;
        try{  
	         s = HibernateSessionFactory.getSession();
	         t = s.beginTransaction();
	         result = handleSession(s);   
	         t.commit();  
        }catch(Exception err){
        	if ( t != null ){
        		t.rollback();  
        	}
	        err.printStackTrace(); 
	        Bog.fetal("<handlerTransaction>"+t+ "<Exception>"+err.getMessage());
        }finally{
        	if ( s != null) 	
        	s.close();  
        }
        return result;
	}
}


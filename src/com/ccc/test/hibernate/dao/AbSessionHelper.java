package com.ccc.test.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ccc.test.hibernate.HibernateSessionFactory;
import com.ccc.test.utils.Bog;

/**
 * @author Trible Chen
 *数据库Session使用辅助类
 */
	
public abstract class AbSessionHelper <T> {

	/**
	 * @author Trible Chen
	 *处理Session的回调接口
	 * @param <T> 接口函数返回类型
	 */
	public abstract T handleSession(Session s);
	
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
        	s.close();  
        }
        return result;
	}
}


package com.ccc.test.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ccc.test.hibernate.HibernateSessionFactory;
import com.ccc.test.utils.Bog;

/**
 * @author Trible Chen
 *���ݿ�Sessionʹ�ø�����
 */
	
public abstract class AbSessionHelper <T> {

	/**
	 * @author Trible Chen
	 *����Session�Ļص��ӿ�
	 * @param <T> �ӿں�����������
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


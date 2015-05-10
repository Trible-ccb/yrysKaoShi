package com.ccc.test.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class QueryParamsHelper {


	List<QueryHolder> params;
	
	public QueryParamsHelper(){
		params = new ArrayList<QueryHolder>();
	}
	
	public void add(String type,String name,String value){
		QueryHolder p = new QueryHolder();
		p.queryType = type;
		p.queryValue = value;
		p.queryColumnName = name;
		params.add(p);
	}
	String toWhereClause(){
		StringBuffer sb = new StringBuffer();
		String andstr = " AND ";
		for ( QueryHolder h : params ){
			sb.append(" " + h.queryColumnName)
			.append(" " + h.queryType)
			.append(":" + h.queryColumnName)
			.append(andstr);
		}
		int idx = sb.lastIndexOf(andstr);
		if ( idx != -1 ){
			sb.replace(idx, idx + andstr.length(), "");
		}
		return sb.toString();
	}
	public Query buildQuery(Session s,String hql){
		Query q = s.createQuery(hql+toWhereClause());
		for ( QueryHolder h : params ){
			q.setParameter(h.queryColumnName, h.queryValue);
		}
		return q;
	}
	class QueryHolder{
		String queryType;
		String queryColumnName;
		String queryValue;
	}
}

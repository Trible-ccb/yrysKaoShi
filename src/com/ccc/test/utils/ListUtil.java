package com.ccc.test.utils;

import java.util.List;
import java.util.Map;

public class ListUtil {

	public static boolean isEmpty(@SuppressWarnings("rawtypes") List list) {

		boolean ret= (list == null || list.isEmpty());
		return ret;
	}
	
	public static boolean isNotEmpty(@SuppressWarnings("rawtypes") List list){
		
		return !isEmpty(list);
	}

	public static boolean isEmpty( Object o) {
		
		if ( o instanceof Map ){
			Map map = (Map) o;
			return map.isEmpty();
		} else if ( o instanceof List ){
			List l = (List)o;
			return l.isEmpty();
		} else if ( o instanceof String ){
			String s = (String)o;
			if ( s == null || s.trim().equals(""))return true;
			return false;
		} else {
			return false;
		}
		
	}
}

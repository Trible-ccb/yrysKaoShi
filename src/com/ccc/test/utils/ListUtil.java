package com.ccc.test.utils;

import java.util.List;

public class ListUtil {

	public static boolean isEmpty(@SuppressWarnings("rawtypes") List list) {

		boolean ret= (list == null || list.isEmpty());
		return ret;
	}
	
	public static boolean isNotEmpty(@SuppressWarnings("rawtypes") List list){
		
		return !isEmpty(list);
	}

}

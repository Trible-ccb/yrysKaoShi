package com.ccc.test.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(String s){
		if ( s == null || s.trim().equals(""))return true;
		return false;
	}
	public static boolean isValidName(String name){
		return name == null ? false : name.matches("[^\"'%*]+");
	}
	
	public static boolean isValidPwd(String pwd){
		return pwd == null ? false : pwd.matches("[^\"']+");
	}	
	
	public static boolean isValidPhone(String phone){
		return phone == null ? false : phone.matches("[^\"'%*]+");
	}
	
	public static boolean isValidEmail(String email) {

		String pat_string = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
		Pattern patten = Pattern.compile(pat_string);
		if (email != null && patten.matcher(email).matches()) {
			return true;
		}

		return false;
	}
	public static boolean isValidURL(String url) {
		if (url == null || url.contains(" "))
			return false;
		String tmp = url.toLowerCase();
		return (tmp.startsWith("http://") || tmp.startsWith("https://"))
				&& tmp.length() > 7;
	}

	public static boolean isValidPhoneNumber(String phone) {
		if ( phone == null )return false;
		String ps = "^\\d{6,15}$";
		Pattern p = Pattern.compile(ps);
		Matcher m = p.matcher(phone.replaceAll("[\\+\\-\\(\\)\\s]+", ""));
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}
	public static String getEncodeURLParams(String v){
		Bog.v("before encode v = " + v);
		String ev = null;
		try {
			ev = URLEncoder.encode(v, "UTF-8");
			Bog.v("after encode v = " + ev);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
	public static String getDecodeURLParams(String v){
		Bog.v("before Decode v = " + v);
		String ev = null;
		try {
			ev = URLDecoder.decode(v, "UTF-8");
			Bog.v("after Decode v = " + ev);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
	public static void main(String[] args){
		String testName ="+861232";
		Bog.v(testName + " isValidName "+ isValidName(testName));
		Bog.v(testName + " isValidPhoneNumber "+ isValidPhoneNumber(testName));
	}
}

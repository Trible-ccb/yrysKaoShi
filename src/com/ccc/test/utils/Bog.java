package com.ccc.test.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Bog {

	public static boolean DEBUG = true;
	static String logger_property = Bog.class.getClassLoader().getResource("/").getPath().replace("%20", " ")+"config/log4j.properties";
	static Logger mLogger = Logger.getLogger(Bog.class);
	static boolean hadLoadProperty = false;
	
	public static void v(String s){
		if ( DEBUG )
		System.out.println(s);
	}
	static void loadLogConfig(){
		if ( !hadLoadProperty ){
			PropertyConfigurator.configure(logger_property);
			hadLoadProperty = true;
		}
	}
	public static void info(String s){
		loadLogConfig();
		mLogger.info(s);
	}
	public static void warn(String s){
		loadLogConfig();
		mLogger.warn(s);
	}
	public static void fetal(String s){
		loadLogConfig();
		mLogger.fatal(s);
	}
	public static void error(String s){
		loadLogConfig();
		mLogger.error(s);
	}
}

package com.ccc.test.utils;

import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.crypto.provider.SunJCE;

public class SecurityMethod {
	private static final String UNICODE_FORMAT  = "UTF-8";
	private static final byte[] keyValue = 
	        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
	public static String getHexStringOfByte(byte[] bytes){
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = (bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	public static String encryptSHA(String info){  
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
	        byte[] srcBytes = info.getBytes();  
	        //使用srcBytes更新摘要  
	        md.update(srcBytes);  
	        //完成哈希计算，得到result  
	        return getHexStringOfByte(md.digest());  
		} catch (Exception e) {
			e.printStackTrace();
			return null;  
		}  
        
    }
	
	public static String encryptMD5(String info){  
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
	        byte[] srcBytes = info.getBytes();  
	        //使用srcBytes更新摘要  
	        md.update(srcBytes);  
	        //完成哈希计算，得到result  
	        return getHexStringOfByte(md.digest());  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
 
    }
	
	static EncrypAES mEncrypAES;
	public static EncrypAES getAESInstance(){
		if ( mEncrypAES == null )mEncrypAES = new EncrypAES();
		return mEncrypAES;
	}
      
	public static class EncrypAES {  
	      
	    //KeyGenerator 提供对称密钥生成器的功能，支持各种算�? 
	    private KeyGenerator keygen;  
	    //SecretKey 负责保存对称密钥  
	    private SecretKey deskey;  
	    //Cipher负责完成加密或解密工�? 
	    private Cipher c;  
	    
	    
	    public EncrypAES(){  
	        Security.addProvider(new SunJCE());  
	        //实例化支持DES算法的密钥生成器(算法名称命名�?��规定，否则抛出异�?  
	        try {
	        	
				keygen = KeyGenerator.getInstance("AES");
		        //生成密钥  
//		        deskey = keygen.generateKey();  
		        deskey = new SecretKeySpec(keyValue, "AES");
		        //生成Cipher对象,指定其支持的DES算法  
		        c = Cipher.getInstance("AES");
			} catch (Exception e) {
				e.printStackTrace();
			}  
  
	    }  
	      
	    /** 
	     * 对字符串加密 
	     *  
	     * @param str 
	     * @return 
	     */  
	    public String Encrytor(String str){  
	        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
	        try {
				c.init(Cipher.ENCRYPT_MODE, deskey);
		        byte[] src = str.getBytes(UNICODE_FORMAT);
		        src = c.doFinal(src);
		        String encryptedValue = new BASE64Encoder().encode(src);
		        return encryptedValue;  
			} catch (Exception e) {
				e.printStackTrace();
				return null;  
			}  
	        
	    }  
	  
	    /** 
	     * 对字符串解密 
	     *  
	     * @param buff 
	     * @return 
	     */  
	    public String Decryptor(String buff){  
	        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式  
	        try {
				c.init(Cipher.DECRYPT_MODE, deskey);
				byte[] bts = new BASE64Decoder().decodeBuffer(buff);
				return new String(c.doFinal(bts),UNICODE_FORMAT);  
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}  
	        
	    }
	}
	public static void main(String[] args) throws Exception {  
        EncrypAES de1 = new EncrypAES();  
        String msg ="郭XX-搞笑相声全集";  
        String encontent = de1.Encrytor(msg);  
        String decontent = de1.Decryptor(encontent);  
        System.out.println("明文�?" + msg);  
        System.out.println("加密�?" + encontent);
        System.out.println("md5加密�?" + encryptMD5(msg));  
        System.out.println("sha加密�?" + encryptSHA(msg));  
        System.out.println("解密�?" + decontent);  
    }
}

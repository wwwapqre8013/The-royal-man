package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public String makeMD5(String password) { 
		 String psw = null;
		MessageDigest md; 
		password +="*%$15^";
		 try {
			md = MessageDigest.getInstance("MD5");
			//生成一个md5加密计算摘要
			 md.update(password.getBytes());   
			 // 计算md5函数   
			 psw = new BigInteger(1, md.digest()).toString(16).substring(10, 26);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return psw;
	}
//	public static void main(String[] args) {
//		String password = "a555asd85a51sdasd";
//		makeMD5(password);
//	}
}

package com.yxh.conversion.tools;

import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 采用MD5加密解密
 * 
 * @author
 * @datetime
 */
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
/**
 * 
 * 加密用户密码
 * @param userid
 * @param pwd
 * @return
 */
	public static String encryUserPwd(String userid, String pwd) {
		return string2MD5(userid + pwd);
	}

	// 测试主函数
	public static void main(String args[]) {
		String s = new String("111111aa");

		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[4];
		random.nextBytes(bytes);
		System.out.println(string2MD5("lklfwdslkf"));

	}
}
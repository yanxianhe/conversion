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
	/**
     * <p>MD5加码 生成32位md5码</p>
     * @param String
     * @return String 
    */
    public static String getMD5(String str) {
        String get_md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            String part = null;
            for (int i = 0; i < md5.length; i++) {
                part = Integer.toHexString(md5[i] & 0xFF);
                if (part.length() == 1) {
                    part = "" + part;
                }
                sb.append(part);
            }
            get_md5 = (sb).toString();
            } catch (Exception ex) {
                System.out.println("ERROR " + ex);
				ex.printStackTrace();
            }
            return get_md5;
    }

	/**
	 * 
	 * 加密用户密码
	 * @param userid
	 * @param pwd
	 * @return
	 */
	public static String encryUserPwd(String userid, String pwd) {
		return getMD5(userid + pwd);
	}

	// 测试主函数
	public static void main(String args[]) {
		String s = new String("111111aa");

		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[4];
		random.nextBytes(bytes);
		System.out.println(getMD5("lklfwdslkf"));

	}
}
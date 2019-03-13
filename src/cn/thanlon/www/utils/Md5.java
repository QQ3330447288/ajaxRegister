package cn.thanlon.www.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {
	public static String md5(String text, String key) {
		String encodeStr = DigestUtils.md5Hex(text + key);
//		System.out.println(encodeStr);
		return encodeStr;
	}
}

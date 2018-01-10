package com.mybatis.util;

import java.util.Random;

public class ToolUtils {

	/**
	 * 根据入参长度返回字符
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(int length) {
		String str = "abcdefghijklmnopqqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int nextInt = random.nextInt(str.length());
			stringBuffer.append(str.charAt(nextInt));
		}
		return stringBuffer.toString();
	}
}

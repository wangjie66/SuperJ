/**	
 * <br>
 * Copyright 2017 IFlyTek.All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.sc.pl.core.api.utils <br>
 * FileName: Base64Utils.java <br>
 * <br>
 * @version v0.6
 * @author llchen
 * @created 2017年12月21日
 * @last Modified 
 * @history
 */
package utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * {该处说明该构造函数的含义及作用}
 * 
 * @author llchen
 * @created 2017年12月21日 下午9:30:37
 * @lastModified
 * @history
 */
public class Base64Utils {

	/**
	 * 
	 * 加密
	 * 
	 * @return
	 * @author llchen
	 * @throws UnsupportedEncodingException 
	 * @created 2017年12月21日 下午9:31:41
	 * @lastModified
	 * @history
	 */
	public static String encrypt(String data) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		
		String encryptResult = null;

		byte[] result = Base64.encodeBase64(data.getBytes());
		
		encryptResult = new String(result, "utf-8");
		
		return encryptResult;
	}

	/**
	 * 
	 * 解密
	 * 
	 * @param data
	 * @return
	 * @author llchen
	 * @throws UnsupportedEncodingException 
	 * @created 2017年12月21日 下午9:33:46
	 * @lastModified
	 * @history
	 */
	public static String decrypt(String data) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		String decryptResult = null;
		byte[] result = Base64.decodeBase64(data);
		decryptResult = new String(result, "utf-8");
		return decryptResult;
	}
	
	public static void main(String[] args) {
		try {
			String a = encrypt("WLSBH2017122100001");
			System.out.println(a);
			System.out.println(decrypt(a));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

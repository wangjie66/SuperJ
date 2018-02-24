/**	
 * <br>
 * Copyright 2014 IFlyTek.All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.sc.pl.core.utils <br>
 * FileName: CommonUtil.java <br>
 * <br>
 * @version
 * @author xhcheng3
 * @created 2017年10月8日
 * @last Modified 
 * @history
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common Util
 * 
 * @author xhcheng3
 * @created 2017年10月8日 下午6:21:35
 * @lastModified
 * @history
 */
public class CommonUtil {

	/**
	 * 去除多余的html标签
	 * 
	 * @param inputString
	 *            String 待去除的字符串
	 * @return String 去除后的字符串
	 * @author huiwang8
	 * @created 2014-11-10 下午3:07:57
	 */
	public static String removeTag(String inputString) {
		if (inputString == null) {
			return "";
		}

		String htmlStr = inputString;
		String textStr = "";
		Pattern p_html = null;
		Matcher m_html = null;
		Pattern p_special1 = null;
		Matcher m_special1 = null;
		try {
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";

			String regEx_special1 = "(\r\n|\r|\n|\n\r)";

			String regEx_special2 = "(\t)";

			// 过滤html标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");

			p_special1 = Pattern.compile(regEx_special1, Pattern.CASE_INSENSITIVE);
			m_special1 = p_special1.matcher(htmlStr);
			htmlStr = m_special1.replaceAll("");

			p_special1 = Pattern.compile(regEx_special2, Pattern.CASE_INSENSITIVE);
			m_special1 = p_special1.matcher(htmlStr);
			htmlStr = m_special1.replaceAll("");

			textStr = htmlStr;

			textStr = textStr.replaceAll("&nbsp;", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return textStr;
	}


	/**
	 * 将blob转化为byte[],可转化二进制流
	 * @param blob
	 * @return
	 */
	public static byte[] blobToBytes(Blob blob) {
		if (blob == null) {
			return null;
		}
		InputStream is = null;
		byte[] b = null;
		try {
			is = blob.getBinaryStream();
			b = new byte[(int) blob.length()];
			is.read(b);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null){
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

}

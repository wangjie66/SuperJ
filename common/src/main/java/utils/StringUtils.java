/**	
 * <br>
 * Copyright 2014 IFlyTek.All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.sc.pl.core.utils <br>
 * FileName: StringUtils.java <br>
 * <br>
 * @version
 * @author xhcheng3
 * @created 2017年10月25日
 * @last Modified 
 * @history
 */
package utils;

import org.apache.commons.collections.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtils extends org.apache.commons.lang.StringUtils  {

	public StringUtils() {
	}

	public static String getLimitLengthString(String str, int len, String symbol) {
		if (str != null && !str.equals("")) {
			try {
				int counterOfDoubleByte = 0;
				byte[] b = str.getBytes("GBK");
				if (b.length <= len) {
					return str;
				} else {
					for(int i = 0; i < len; ++i) {
						if (b[i] < 0) {
							++counterOfDoubleByte;
						}
					}

					if (counterOfDoubleByte % 2 == 0) {
						return new String(b, 0, len, "GBK") + symbol;
					} else {
						return new String(b, 0, len - 1, "GBK") + symbol;
					}
				}
			} catch (UnsupportedEncodingException var6) {
				return "error";
			}
		} else {
			return "";
		}
	}

	public static String[] String2StrArray(String str, String separator) {
		if (isNotBlank(str)) {
			if (str.endsWith(separator)) {
				str = str.substring(0, str.length() - 1);
			}

			String[] array = str.split(separator);
			return array;
		} else {
			return null;
		}
	}

	public static String StrArray2String(String[] array, String separator) {
		if (array == null) {
			return "";
		} else {
			String str = "";
			String[] var6 = array;
			int var5 = array.length;

			for(int var4 = 0; var4 < var5; ++var4) {
				String s = var6[var4];
				str = str + s + separator;
			}

			if (str.endsWith(separator)) {
				str = str.substring(0, str.length() - 1);
			}

			return str;
		}
	}

	public static String removeStringItem(String str, String item, String separator) {
		String[] array = String2StrArray(str, separator);
		String newStr = "";
		String[] var8 = array;
		int var7 = array.length;

		for(int var6 = 0; var6 < var7; ++var6) {
			String s = var8[var6];
			if (!s.equals(item)) {
				newStr = newStr + s + separator;
			}
		}

		if (newStr.endsWith(separator)) {
			newStr = newStr.substring(0, newStr.length() - 1);
		}

		return newStr;
	}

	public static List<Long> stringToListForLong(String ids) {
		List<Long> id = new ArrayList();
		String[] sItem = ids.split(",");
		String[] var6 = sItem;
		int var5 = sItem.length;

		for(int var4 = 0; var4 < var5; ++var4) {
			String s = var6[var4];
			id.add(Long.valueOf(s));
		}

		return id;
	}

	public static List<String> stringToListForString(String ids) {
		List<String> bh = new ArrayList();
		String[] sItem = ids.split(",");
		String[] var6 = sItem;
		int var5 = sItem.length;

		for(int var4 = 0; var4 < var5; ++var4) {
			String s = var6[var4];
			bh.add(s);
		}

		return bh;
	}

	public static String toNewsOrgCode(String orgcode) {
		if (isNotEmpty(orgcode)) {
			boolean temp = false;
			orgcode = orgcode.replace("\r", "");
			orgcode = orgcode.replace("\n", "");

			do {
				if (orgcode.length() > 2) {
					String newstr = orgcode.substring(orgcode.length() - 2);
					if ("00".equals(newstr)) {
						orgcode = orgcode.substring(0, orgcode.length() - 2);
					} else {
						temp = true;
					}
				} else {
					temp = true;
				}
			} while(!temp);
		}

		return orgcode;
	}
	/**
	 *  字符串拼接 -> '1','2','3'
	 *  @param array
	 *  @return
	 *  @author xhcheng3
	 *  @created 2017年10月25日 下午6:26:38
	 *  @lastModified
	 *  @history           
	 */
	public static String join(Object[] array) {
		StringBuilder result = new StringBuilder();
		if (null != array && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				result.append("'").append(array[i]).append("'");
				if (i != array.length - 1) {
					result.append(",");
				}
			}
		}
		return result.toString();
	}
	
	/**
	 *  字符串拼接 -> '1','2','3'
	 *  @param list
	 *  @return
	 *  @author xhcheng3
	 *  @created 2017年11月10日 上午10:44:14
	 *  @lastModified
	 *  @history           
	 */
	public static String join(List<String> list) {
		StringBuilder result = new StringBuilder();
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				result.append("'").append(list.get(i)).append("'");
				if (i != list.size() - 1) {
					result.append(",");
				}
			}
		}
		return result.toString();
	}
	
}

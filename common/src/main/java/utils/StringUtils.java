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

import java.util.List;

/**
 * String Utils
 * 
 * @author xhcheng3
 * @created 2017年10月25日 下午5:28:16
 * @lastModified
 * @history
 */
public class StringUtils extends com.iflytek.iframework.utils.StringUtils {

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

/**	
 * <br>
 * Copyright 2011 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.sgy.irms.base <br>
 * FileName: JSONUtil.java <br>
 * <br>
 * @version
 * @author zhuxianming
 * @created 2015年11月18日
 * @last Modified 
 * @history
 */

package utils;

import com.iflytek.iframework.orm.Page;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理返回JSON格式数据
 * 
 * @author zhuxianming
 * @lastModified
 * @history
 */

public class JSONUtil {

	/**
	 * 
	 * 返回结果JSON传
	 * 
	 * @param b
	 *            操作是否成功
	 * @param page
	 *            查询的分页数据
	 * @param msg
	 *            操作结果描述
	 * @return JSON格式数据， {@link #result(boolean, Object, String)}
	 * @author zhuxianming
	 * @created 2015年11月18日 上午9:17:42
	 * @lastModified
	 * @history
	 */
	public static String resultPage(boolean b, Page page, String msg) {
		if (page == null) {
			return error();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", page.getResult());
		result.put("total", page.getTotalCount());
		return result(b, result, msg);
	}

	/**
	 * 处理返回值
	 * 
	 * @param b
	 *            操作是否成功
	 * @param data
	 *            操作返回的 主体数据 data
	 * @param msg
	 *            操作返回的信息描述
	 * @return JSON { "status":true, //状态 true成功，false 失败 "data":OBJECT //主体数据,
	 *         如果没有返回Null "msg":"操作成功" // 返回操作描述，可以是前后台标准接口编码 }
	 * @author zhuxianming
	 * @created 2015年11月18日 上午9:17:46
	 * @lastModified
	 * @history
	 */
	public static String result(boolean b, Object data, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", b);
		map.put("data", data);
		map.put("msg", msg);
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 便捷方法，快速返回成功分页信息
	 * 
	 * @param page
	 * @return
	 * @author zhuxianming
	 * @created 2015年11月18日 上午9:54:53
	 * @lastModified
	 * @history
	 */
	public static String success(Page page) {
		return resultPage(true, page, "");
	}

	/**
	 * 便捷方法，快速返回成功信息
	 * 
	 * @param data
	 * @return
	 * @author zhuxianming
	 * @created 2015年11月18日 上午9:56:30
	 * @lastModified
	 * @history
	 */
	public static String success(Object data) {
		return result(true, data, "");
	}

	/**
	 * 
	 * 无返回值的成功操作
	 * 
	 * @return
	 * @author zhuxianming
	 * @created 2015年11月18日 上午10:25:41
	 * @lastModified
	 * @history
	 */
	public static String success() {
		return success("");
	}

	/**
	 * 错误返回接口
	 * 
	 * @author zhuxianming
	 * @created 2015年11月18日 下午3:57:20
	 * @lastModified
	 * @history
	 */
	public static String error() {
		return result(false, null, "");
	}

	/**
	 * 错误返回接口
	 * 
	 * @author zhuxianming
	 * @created 2015年11月18日 下午3:57:20
	 * @lastModified
	 * @history
	 */
	public static String error(String msg) {
		return result(false, null, msg);
	}

	/**
	 * toBean
	 * @param json
	 * @return
	 */
	public Object jsonAndMapToObject(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map<String, Class> classMap = new HashMap<String, Class>();
		// 信访人
		classMap.put("xfrAddList", ArrayList.class);
		classMap.put("xfrDeleteList", String.class);
		Object xfjxxDto = (Object) JSONObject.toBean(jsonObject,
				Object.class, classMap);
		return xfjxxDto ;
	}

}

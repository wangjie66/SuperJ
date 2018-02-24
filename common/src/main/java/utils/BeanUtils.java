/**	
 * <br>
 * Copyright 2014 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.utils <br>
 * FileName: BeanUtil.java <br>
 * <br>
 * @version
 * @author yfcheng2@iflytek.com
 * @created 2014年12月15日
 * @last Modified 
 * @history
 */

package utils;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  
 *  实体对象赋值类
 * @author yfcheng2@iflytek.com
 * @created 2014年12月15日 上午8:48:59
 * @lastModified
 * @history
 */

public class BeanUtils {
	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 
	 * 复制Bean的属性到Map里面
	 * 
	 * @param bean
	 * @param map
	 * @author yfcheng2@iflytek.com
	 * @created 2014年12月30日 上午8:52:50
	 * @lastModified
	 * @history
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyProps(Object bean, Map map) {
		try {
			BeanInfo binfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
			PropertyDescriptor[] props = binfo.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				Object val = props[i].getReadMethod().invoke(bean);
				map.put(props[i].getName(), val);
			}
		} catch (Exception e) {
			logger.error("复制map失败",e);
		}
	}

	/**
	 * 
	 * 复制Bean的属性到model上去
	 * 
	 * @param bean
	 * @param md
	 * @author yfcheng2@iflytek.com
	 * @created 2014年12月30日 上午8:49:42
	 * @lastModified
	 * @history
	 */
	public static void copyProps(Object bean, Model md) {
		try {
			BeanInfo binfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
			PropertyDescriptor[] props = binfo.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				Object val = props[i].getReadMethod().invoke(bean);
				md.addAttribute(props[i].getName(), val);
			}

		} catch (Exception e) {
			logger.error("复制bean失败",e);
		}

	}

	/**
	 * 
	 * 初始化属性值，实例化一个类
	 * 
	 * @param prop
	 * @param val
	 * @param clzz
	 * @return
	 * @author yfcheng2@iflytek.com
	 * @created 2014年12月17日 下午2:23:04
	 * @lastModified
	 * @history
	 */
	public static <T> T initializeBean(String prop, String val, Class<T> clzz) {
		T obj = null;
		try {
			obj = clzz.newInstance();
			if (StringUtils.isBlank(prop)) {
				return obj;
			}
			PropertyDescriptor pDesc = new PropertyDescriptor(prop, clzz);
			pDesc.getWriteMethod().invoke(obj, val);
		} catch (Exception e) {
			logger.error("初始化bean失败",e);
		}
		return obj;
	}

	/**
	 * 
	 * 将源的属性复制到目标属性上去
	 * 
	 * @param src
	 * @param dest
	 * @author yfcheng2@iflytek.com
	 * @created 2014年12月19日 下午1:10:04
	 * @lastModified
	 * @history
	 */
	public static void copyProperties(Object dest, Object src) {
		if (src == null || dest == null) {
			return;
		}
		// 获取所有的get/set 方法对应的属性
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(src);

		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor propItem = descriptors[i];
			// 过滤setclass/getclass属性
			if ("class".equals(propItem.getName())) {
				continue;
			}

			try {
				Method method = propItem.getReadMethod();
				// 通过get方法获取对应的值
				Object val = method.invoke(src);
				// 如果是空，不做处理
				if (null == val) {
					continue;
				}
				// 值复制
				PropertyDescriptor prop = propertyUtilsBean.getPropertyDescriptor(dest, propItem.getName());
				// 调用写方法，设置值
				if (null != prop && prop.getWriteMethod() != null) {
					prop.getWriteMethod().invoke(dest, val);
				}
			} catch (Exception e) {
				logger.error("复制出错 ,src prop : " + propItem.getDisplayName() + " src class: " + src.getClass()
						+ ";dest type :" + dest.getClass(), e);
			}

		}

	}

	/**
	 * 将list源的属性复制到目标属性上去
	 *
	 * @param srcList
	 * @param yClass
	 * @author dsliu@iflytek.com
	 * @created 2015年7月21日 下午1:10:04
	 * @lastModified
	 * @history
	 */
	public static <T, Y> List<Y> copyListCommonProperties(List<T> srcList, Class<Y> yClass) {
		List<Y> destList = new ArrayList<Y>();
		if (srcList == null || srcList.isEmpty()) {
			return destList;
		}

		try {
			for (T t : srcList) {
				Y y = yClass.newInstance();
				copyProperties(y,t);
				destList.add(y);
			}
		} catch (Exception e) {
			logger.error("复制属性失败",e);
		}

		return destList;
	}

}

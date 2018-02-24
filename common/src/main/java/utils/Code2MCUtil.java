/**	
 * <br>
 * Copyright 2011 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.utils <br>
 * FileName: Code2MCUtil.java <br>
 * <br>
 * @version
 * @author Administrator
 * @created 2012-5-12
 * @last Modified 
 * @history
 */

package utils;

import com.iflytek.iframework.business.cache.impl.OrgCacheLoadImpl;
import com.iflytek.iframework.business.cache.impl.PublicZdCacheLoadImpl;
import com.iflytek.iframework.business.cache.vo.PublicDict;
import com.iflytek.iframework.orm.Page;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {根据系统字典缓存替换编码显示}
 * 
 * @author jbyu
 * @lastModified
 * @history
 */
public class Code2MCUtil {

    /**
     * 
     * ｛转换单个实体编码到名称｝
     * 
     * @param <T> 返回实体
     * @param t 实体类
     * @return 实体类
     * @author jbyu
     * @created 2012-5-24 下午12:34:08
     * @lastModified
     * @history
     */
    public static <T> T code2MC(T t) {
    	if(t == null){
    		return t;
    	}
        
            try {
				Class<?> objclass = t.getClass();
				Field[]  fields = objclass.getDeclaredFields();
				for (Field fd : fields) {
				    if (fd.isAnnotationPresent(Code2MC.class)) {
				        Code2MC d = fd.getAnnotation(Code2MC.class);
				        fd.setAccessible(true);
						Field codeField = null;
						try {
							codeField = objclass.getDeclaredField(d.dmFieldMc());
						}catch (Exception e){
							//从父类获取编码字段
							Class<?> superClass = objclass.getSuperclass();
							codeField = superClass.getDeclaredField(d.dmFieldMc());
						}
				        String code = "";
				        if (codeField != null) {
				            codeField.setAccessible(true);
				            Object temp = codeField.get(t);
				            if (temp != null) {
				                code = temp.toString();
				            }
				        }
                    	//空值处理
				        if(StringUtils.isNotBlank(d.emptyText()) && StringUtils.isBlank(code)){
				        	fd.set(t, d.emptyText());
				        	continue;
				        }
				        if (d.isOrg()) {
				            String org = OrgCacheLoadImpl.getOrgMc(code, true);
				            if (org != null) {
				                fd.set(t, org);
				            } else {
				                fd.set(t, StringUtils.EMPTY);
				            }
				        } else {
				            String mc = getZdmc(d.lxjp(), code);
				            if (mc != null) {
				                fd.set(t, mc);
				            } else {
				                fd.set(t, StringUtils.EMPTY);
				            }

				        }

				    }
				}

				//遍历父类
				Class<?> supClass = objclass.getSuperclass();
				Field[] supField = supClass.getDeclaredFields();
				for (Field fd : supField) {
					if (fd.isAnnotationPresent(Code2MC.class)) {
						Code2MC d = fd.getAnnotation(Code2MC.class);
						fd.setAccessible(true);
						Field codeField = supClass.getDeclaredField(d.dmFieldMc());
						String code = "";
						if (codeField != null) {
							codeField.setAccessible(true);
							Object temp = codeField.get(t);
							if (temp != null) {
								code = temp.toString();
							}
						}
                    	//空值处理
						if(StringUtils.isNotBlank(d.emptyText()) && StringUtils.isBlank(code)){
							fd.set(t, d.emptyText());
							continue;
						}
						if (d.isOrg()) {
							String org = OrgCacheLoadImpl.getOrgMc(code, true);
							if (org != null) {
								fd.set(t, org);
							} else {
								fd.set(t, StringUtils.EMPTY);
							}
						} else {
							String mc = getZdmc(d.lxjp(), code);
							if (mc != null) {
								fd.set(t, mc);
							} else {
								fd.set(t, StringUtils.EMPTY);
							}

						}

					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        return t;
    }

    /**
     * 
     * 代码转换成名称
     * 
     * @param <T> 实体类
     * @param page	翻页对象
     * @author sbwang@iflytek.com
     * @created 2013-6-3 下午10:54:15
     * @lastModified
     * @history
     */
    public static <T> Page<T> code2MC(Page<T> page) {
    	page.setResult(code2MC( page.getResult()));
    	return page;
    }

    /**
     * 
     * ｛大数据量需要转换编码到名称｝
     * 
     * @param <T> 实体类
     * @param list 需要转换的实体list
     * @return 实体List
     * @author jbyu
     * @created 2012-5-24 下午12:36:56
     * @lastModified
     * @history
     */
    public static <T> List<T> code2MC(List<T> list) {
        for (T t : list) {
            code2MC(t);
        }
        return list;
    }

    /**
     * 
     * ｛获取名称支持多个编码｝
     * 
     * @param lxjp 类型简拼
     * @param code 代码
     * @return 名称
     * @author jbyu
     * @created 2012-5-21 上午09:54:51
     * @lastModified
     * @history
     */
    public static String getZdmc(String lxjp, String code) {
        if (StringUtils.isEmpty(lxjp) || StringUtils.isEmpty(code)
                || StringUtils.split(code, ",").length == 0) {
            return StringUtils.EMPTY;
        }
        if (code.indexOf(",") != -1) {
            String[] codes = code.split(",");
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < codes.length; i++) {
                String string = getDictMc(lxjp, codes[i]);
                if (i != codes.length - 1) {
                    str.append(string).append(",");
                } else {
                    str.append(string);
                }
            }
            return str.toString();
        } else {
            return getDictMc(lxjp, code);
        }
    }
    
    /**
     *  得到字典名称
     *  @param lxjp 类型简拼
     *  @param code 代码
     *  @return 名称
     *  @author xmzhu2
     *  @created 2013-10-22 下午3:51:35
     *  @lastModified       
     *  @history
     */
    private static String getDictMc(String lxjp, String code){
    	PublicDict dict = PublicZdCacheLoadImpl.getDict(lxjp, code);
         if(dict == null){
        	 return StringUtils.EMPTY;
         }
         return dict.getMc();
    }
    
    /**
     *  字典翻译
     *  @param t 转换后的dto对象
     *  @param <V> 待转换的vo对象
     *  @param page 待翻译结果
     *  @return Page<T> 翻译后的结果
     *  @author rhzhao
     *  @created 2015年11月24日 下午12:07:00
     *  @lastModified       
     *  @history           
     */
    @SuppressWarnings("all")
    public static <T, V> Page<T> code2MC(Class<T> t, Page<V> page) {
    	Page<T> resultPage = new Page<T>();
    	resultPage.setTotalCount(page.getTotalCount());
    	resultPage.setCurrentPageNo(page.getCurrentPageNo());
    	resultPage.setPageSize(page.getPageSize());
    	ArrayList<T> list =  new ArrayList<T>();
    	for(V v : page.getResult()){
    		T t2 = null ;
    		try {
    			 t2 = t.newInstance();  			
				BeanUtils.copyProperties(t2, v);
				code2MC(t2);
			} catch (Exception e) {
				e.printStackTrace();
			} 
    		list.add(t2);
    	}
    	resultPage.setResult(list);
    	return resultPage;
    }
    /**
     *  字典翻译
     *  @param t 转换后的dto对象
     *  @param v 待翻译结果
     *  @return t2 翻译后的结果
     *  @author rhzhao
     *  @created 2015年11月24日 下午12:07:00
     *  @lastModified       
     *  @history           
     */
	@SuppressWarnings("all")
	public static <T, V> T code2MC(Class<T> t, V v) {
		T t2 = null ;
		try {
			 t2 = t.newInstance();  			
			BeanUtils.copyProperties(t2, v);
			code2MC(t2);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return t2;
 	}
}

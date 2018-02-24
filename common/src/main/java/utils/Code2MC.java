/**	
 * <br>
 * Copyright 2011 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.xzzdry.base <br>
 * FileName: Code2MC.java <br>
 * <br>
 * @version
 * @author Administrator
 * @created 2012-5-12
 * @last Modified 
 * @history
 */

package utils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 解释编码到名称
 * 
 * @author jbyu
 * @lastModified
 * @history
 */
@Target( { FIELD })
@Retention(RUNTIME)
public @interface Code2MC {

    /**
     * 
     * 类型简拼
     * @author scrum
     * @created 2013-6-18 下午10:44:15
     * @lastModified
     * @history
     */
    String lxjp() default "";

    /**
     * 
     * 名称对应字段
     * @author scrum
     * @created 2013-6-18 下午10:44:19
     * @lastModified
     * @history
     */
    String dmFieldMc() default "";

    /**
     * 
     * 
     * 是否组织机构
     * @author scrum
     * @created 2013-6-18 下午10:44:22
     * @lastModified
     * @history
     */
    boolean isOrg() default false;
    
    /**
     * 
     *  空值默认显示
     *  @return
     *  @author xkfeng@iflytek.com
     *  @created 2013-8-22 下午03:32:53
     *  @lastModified       
     *  @history
     */
    String emptyText() default "";
}

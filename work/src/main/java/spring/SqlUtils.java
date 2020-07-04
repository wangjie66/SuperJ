package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Author : JieWang
 * @Date : Created in 2017年12月28日9:38
 * @Email : wjahstu@163.com
 *
 * 初始化加载这个类
 * 调用setApplicationContext 方法
 * 通过上下文获取配置bean
 * 将配置bean的数据保存到properties中
 *
 */
@Component
@Lazy(false)
public class SqlUtils implements ApplicationContextAware, InitializingBean {

    private static Properties props  ;


    public static String getSql(String key) {
        return props.getProperty(key).replaceAll("\n"," ").replace("\t","");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.print("afterPropertiesSet");
    }

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        try {
            String[] postProcessorNames = ctx
                    .getBeanNamesForType(BeanFactoryPostProcessor.class,true,true);
            for (String ppName : postProcessorNames) {
                BeanFactoryPostProcessor beanProcessor=
                        ctx.getBean(ppName, BeanFactoryPostProcessor.class);
                if(beanProcessor instanceof PropertyResourceConfigurer){
                    PropertyResourceConfigurer propertyResourceConfigurer=
                            (PropertyResourceConfigurer) beanProcessor;
                    Method mergeProperties=PropertiesLoaderSupport.class.
                            getDeclaredMethod("mergeProperties");
                    // get the props
                    mergeProperties.setAccessible(true);
                     props=(Properties) mergeProperties.
                            invoke(propertyResourceConfigurer);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

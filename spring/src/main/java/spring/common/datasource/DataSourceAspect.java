package spring.common.datasource;

import com.sun.istack.internal.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

@Aspect
public class DataSourceAspect {


    @Pointcut("execution(* spring.*.service.impl..*.*(..))")
    public void pointCut(){};


    @Pointcut("execution(* spring.*.service.impl..*.*(..))")
    public void pointLoginCut(){};

    @Before(value = "pointCut() || pointLoginCut()")
    public void beforeInvoke(JoinPoint joinpoint) {
        try {
            MultiDataSourceTypeManager.set(MultiDataSourceTypeEnum.BUSINESS);
            String methodName = joinpoint.getSignature().getName();
            Method[] methods = getMethodsByJoinPoint(joinpoint);
            for(Method method : methods) {
                if(method.getName().equals(methodName)) {
                    // 首先查看方法是否使用注解
                    // 如果使用注解，则获取注解定义的值，并根据注解的值设置访问数据库的key
                    if(method.isAnnotationPresent(DataSource.class)) {
                        DataSource dataSource = method.getAnnotation(DataSource.class);
                        MultiDataSourceTypeManager.set(MultiDataSourceTypeEnum.valueOf(dataSource.value()));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @NotNull
    private Method[] getMethodsByJoinPoint(JoinPoint joinpoint) throws ClassNotFoundException {
        String clazzName = joinpoint.getTarget().getClass().getName();
        Class targetClazz = Class.forName(clazzName);
        return targetClazz.getMethods();
    }

    @After(value = "pointCut()")
    public void afterInvoke(JoinPoint joinpoint){
        try {
            String methodName = joinpoint.getSignature().getName();
            Method[] methods = getMethodsByJoinPoint(joinpoint);
            for(Method method : methods) {
                if(method.getName().equals(methodName)) {
                    // 首先查看方法是否使用注解
                    // 如果使用注解，则获取注解定义的值，并根据注解的值设置访问数据库的key
                    if(method.isAnnotationPresent(SwitchDataSource.class)) {
                        SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);
                        MultiDataSourceTypeManager.set(MultiDataSourceTypeEnum.valueOf(annotation.value()));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
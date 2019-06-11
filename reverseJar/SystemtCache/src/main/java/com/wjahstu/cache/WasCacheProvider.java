package com.iflytek.iframework.business.cache;

import com.ibm.websphere.cache.DistributedMap;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WasCacheProvider
{
    protected static final Log logger = LogFactory.getLog(WasCacheProvider.class);
    private static DistributedMap distMap;
    private String cacheJndi = "services/cache/iflytek_cache";

    public void buildCache()
    {
        if (distMap != null) return;
        try {
            distMap = (DistributedMap)new InitialContext().lookup(this.cacheJndi);
            if (distMap != null) {
                distMap.put("test", "iflytek.com");
                distMap.remove("test");
                logger.info("WAS高速缓存测试通过！");
            }
        } catch (NamingException e) {
            logger.error("WAS高速缓存测试无法使用！");
        }
    }

    public static boolean validatorWasCache()
    {
        return distMap != null;
    }

    public static void putCache(String cacheName, Object obj)
    {
        distMap.put(cacheName, obj);
        logger.debug("was缓存添加【" + cacheName + "】成功！");
    }

    public static void removeCache(String cacheName)
    {
        distMap.remove(cacheName);
        logger.debug("was缓存删除【" + cacheName + "】成功！");
    }

    public static void updateCache(String cacheName, Object obj)
    {
        distMap.remove(cacheName);
        distMap.put(cacheName, obj);
        logger.debug("was缓存更新【" + cacheName + "】成功！");
    }

    public static Object getCacheObject(String cacheName)
    {
        if (distMap != null) {
            return distMap.get(cacheName);
        }
        return null;
    }

    public String getCacheJndi()
    {
        return this.cacheJndi;
    }

    public void setCacheJndi(String cacheJndi)
    {
        this.cacheJndi = cacheJndi;
    }
}
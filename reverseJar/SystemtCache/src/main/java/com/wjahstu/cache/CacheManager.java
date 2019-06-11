package com.iflytek.iframework.business.cache;

import java.util.HashMap;

public class CacheManager
{
    private static HashMap<String, Object> CacheMap = new HashMap();

    private static Object getValue(String key)
    {
        return CacheMap.get(key);
    }

    private static boolean hasCache(String key)
    {
        return CacheMap.containsKey(key);
    }

    public static synchronized void remove(String key)
    {
        if (WasCacheProvider.validatorWasCache())
            WasCacheProvider.removeCache(key);
        else
            CacheMap.remove(key);
    }

    public static synchronized void putCache(String key, Object object)
    {
        remove(key);
        if (WasCacheProvider.validatorWasCache())
            WasCacheProvider.putCache(key, object);
        else
            CacheMap.put(key, object);
    }

    public static Object getContent(String key)
    {
        if (WasCacheProvider.validatorWasCache()) {
            return WasCacheProvider.getCacheObject(key);
        }
        if (hasCache(key)) {
            Object Object = getValue(key);
            return Object;
        }
        return null;
    }
}
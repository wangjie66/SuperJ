package com.wjahstu.cache;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemCacheFactoryBean
{
    private static List<SystemCacheLoad> loaders;
    protected final Log logger = LogFactory.getLog(super.getClass());

    @Autowired
    private WasCacheProvider wasCacheProvider;

    public SystemCacheFactoryBean(List<SystemCacheLoad> systemCacheLoads)
    {
        loaders = systemCacheLoads;
    }

    public synchronized void init()
    {
        this.wasCacheProvider.buildCache();

        if (loaders == null) {
            return;
        }
        this.logger.info("初始化缓存start..........");
        for (SystemCacheLoad loader : loaders) {
            loader.load();
        }
        this.logger.info("初始化缓存end..........");
    }
}
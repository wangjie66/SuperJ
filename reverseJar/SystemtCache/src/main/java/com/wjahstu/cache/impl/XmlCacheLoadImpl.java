package com.iflytek.iframework.business.cache.impl;

import com.iflytek.iframework.business.cache.CacheManager;
import com.iflytek.iframework.business.cache.SystemCacheLoad;
import com.iflytek.iframework.utils.XmlParse;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XmlCacheLoadImpl
        implements SystemCacheLoad
{
    private static final String XML_CACHE = "XML_CACHE";
    protected final Log logger = LogFactory.getLog(super.getClass());

    public void load()
    {
        update();
    }

    public void update()
    {
        HashMap xmlCache =
                XmlParse.readReportSql("core_resources/reportTemplate.xml", "report/sqltemplate");
        CacheManager.putCache("XML_CACHE", xmlCache);

        this.logger.info("报表缓存加载成功,共计" + xmlCache.size() + "条数据。");
    }

    public static String getValue(String key)
    {
        HashMap xmlCache = (HashMap)CacheManager.getContent("XML_CACHE");
        if ((xmlCache != null) &&
                (xmlCache.containsKey(key))) {
            return (String)xmlCache.get(key);
        }

        return "";
    }
}
package com.iflytek.iframework.business.cache.conf;

import com.iflytek.iframework.utils.XmlParse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Node;

public class SystemDictConfigrationLoader
{
    private static final String CACHE_FILE = "cache-config.xml";
    private static final String XPATH = "//cache/cache-entry/entry";
    private static List<CacheEntry> cacheEntryList = new ArrayList(10);

    public static List<CacheEntry> getCacheEntry()
    {
        Document document = XmlParse.getXml("cache-config.xml");
        if (document == null) return null;
        List list = document.selectNodes("//cache/cache-entry/entry");
        cacheEntryList.clear();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            loadCacheEntry((Node)iter.next());
        }
        return cacheEntryList;
    }

    private static void loadCacheEntry(Node node)
    {
        CacheEntry entry = new CacheEntry();

        entry.setIdentify(node.valueOf("@identify"));
        entry.setDescription(node.valueOf("@description"));
        Node dataSql = node.selectSingleNode("dataSql");
        entry.setDataSql(dataSql.getText());

        cacheEntryList.add(entry);
    }
}
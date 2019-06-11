package com.iflytek.iframework.business.cache.impl;

import com.iflytek.iframework.business.cache.CacheManager;
import com.iflytek.iframework.business.cache.SystemCacheLoad;
import com.iflytek.iframework.business.cache.conf.CacheEntry;
import com.iflytek.iframework.business.cache.conf.SystemDictConfigrationLoader;
import com.iflytek.iframework.business.cache.vo.CombxJsonBean;
import com.iflytek.iframework.business.cache.vo.PublicDict;
import com.iflytek.iframework.orm.hibernate.HibernateEntityDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;

public class PublicZdCacheLoadImpl extends HibernateEntityDao
        implements SystemCacheLoad
{
    private static final String DICTS_CACHE = "DICTS_CACHE";
    protected final Log logger = LogFactory.getLog(super.getClass());

    public void load()
    {
        update();
    }

    public void update()
    {
        List dictsCache = new ArrayList();

        List cacheEntryList =
                SystemDictConfigrationLoader.getCacheEntry();
        label281: for (CacheEntry entry : cacheEntryList) {
            if (!entry.isValid()) {
                continue;
            }

            List data = null;
            try {
                data = super.createSqlQuery(
                        entry.getDataSql(), new Object[0]).addScalar("dm", StringType.INSTANCE)
                        .addScalar("mc", StringType.INSTANCE).addScalar("jc",
                                StringType.INSTANCE).addScalar("sm",
                                StringType.INSTANCE).addScalar("lxjp",
                                StringType.INSTANCE).addScalar("sourceId",
                                StringType.INSTANCE).addScalar("pyjp",
                                StringType.INSTANCE).setResultTransformer(
                                Transformers.aliasToBean(PublicDict.class))
                        .list();
            } catch (Exception e) {
                this.logger.error("加载缓存 " + entry.getDescription() + " 出现异常！取数据语句为：" +
                        entry.getDataSql());
                this.logger.error(e.getMessage());
                break label281:
            }
            dictsCache.addAll(data);
            if (dictsCache != null) {
                this.logger.info(entry.getDescription() + "存加载成功，共计 " + data.size() +
                        "， 缓存共计 " + dictsCache.size() + " 条数据。");
            }
        }
        CacheManager.putCache("DICTS_CACHE", dictsCache);
    }

    public static PublicDict getDict(String lxjp, String code)
    {
        if ((StringUtils.isBlank(lxjp)) || (StringUtils.isBlank(code))) {
            return null;
        }
        List dictsCache = (List)
                CacheManager.getContent("DICTS_CACHE");
        if (dictsCache != null) {
            for (PublicDict dict : dictsCache) {
                if ((dict.getLxjp().equals(lxjp)) && (dict.getDm().equals(code))) {
                    return dict;
                }
            }
        }
        return null;
    }

    public static List<PublicDict> getListByLxjp(String lxjp)
    {
        if (StringUtils.isBlank(lxjp)) {
            return null;
        }
        List data = new ArrayList();
        List dictsCache = (List)
                CacheManager.getContent("DICTS_CACHE");
        if (dictsCache != null) {
            for (PublicDict dict : dictsCache) {
                if (dict.getLxjp().equals(lxjp)) {
                    data.add(dict);
                }
            }
        }
        return data;
    }

    public static JSONArray getJsonListByLxjp(String lxjp)
    {
        if (StringUtils.isBlank(lxjp)) {
            return null;
        }
        List data = new ArrayList();
        List dictsCache = (List)
                CacheManager.getContent("DICTS_CACHE");
        if (dictsCache != null) {
            for (PublicDict dict : dictsCache) {
                if (dict.getLxjp().equals(lxjp)) {
                    CombxJsonBean bean = new CombxJsonBean();
                    bean.setValue(dict.getDm());
                    bean.setText(dict.getMc());
                    data.add(bean);
                }
            }
        }
        return JSONArray.fromObject(data);
    }

    public static JSONArray getJsonListByLxjp(String lxjp, String val)
    {
        if (StringUtils.isBlank(lxjp)) {
            return null;
        }
        List data = new ArrayList();
        List dictsCache = (List)
                CacheManager.getContent("DICTS_CACHE");
        if (dictsCache != null) {
            List valList = new ArrayList();
            if (StringUtils.isNotBlank(val)) {
                valList.addAll(Arrays.asList(val.split(",")));
            }
            for (PublicDict dict : dictsCache) {
                if (dict.getLxjp().equals(lxjp)) {
                    CombxJsonBean bean = new CombxJsonBean();
                    bean.setValue(dict.getDm());
                    bean.setText(dict.getMc());
                    if (valList.contains(dict.getDm())) {
                        bean.setSelected("true");
                    }
                    data.add(bean);
                }
            }
        }
        return JSONArray.fromObject(data);
    }

    public static String getDictMc(String lxbm, String key, boolean isJc)
    {
        PublicDict dict = getDict(lxbm, key);
        if (dict == null) {
            return key;
        }

        if (isJc) {
            return dict.getJc();
        }
        return dict.getMc();
    }
}
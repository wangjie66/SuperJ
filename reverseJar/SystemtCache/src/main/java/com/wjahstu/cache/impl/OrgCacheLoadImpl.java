package com.iflytek.iframework.business.cache.impl;

import com.iflytek.iframework.business.cache.CacheManager;
import com.iflytek.iframework.business.cache.SystemCacheLoad;
import com.iflytek.iframework.business.cache.vo.JsecOrg;
import com.iflytek.iframework.exception.GaCacheSystemException;
import com.iflytek.iframework.orm.hibernate.HibernateEntityDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;

public class OrgCacheLoadImpl extends HibernateEntityDao
        implements SystemCacheLoad
{
    private static final String CACHE_NAME = "ORGS";
    protected final Log logger = LogFactory.getLog(super.getClass());

    public void load()
    {
        update();
    }

    public void update()
    {
        String sql = "select bh as code,org_level as orglevel,mc,jc,up_org_guid as sjdm,mc_pinyin as pyjp from T_JSEC_ORGANIZATION order by ORG_LEVEL asc,UP_ORG_GUID asc";

        List orgs = null;
        try {
            orgs = super.createSqlQuery(sql, new Object[0])
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("orgLevel", StringType.INSTANCE)
                    .addScalar("mc", StringType.INSTANCE)
                    .addScalar("jc", StringType.INSTANCE)
                    .addScalar("sjdm", StringType.INSTANCE)
                    .addScalar("pyjp", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(JsecOrg.class)).list();
        } catch (Exception e) {
            this.logger.error("加载机构缓存 出现异常！取数据语句为：" + sql);
            this.logger.error(e.getMessage());
            throw new GaCacheSystemException("加载机构缓存 出现异常！", e);
        }
        CacheManager.putCache("ORGS", orgs);
        this.logger.info("机构缓存加载成功,共计" + orgs.size() + "条数据。");
    }

    public static JsecOrg getOrg(String code)
    {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        List orgs = (List)CacheManager.getContent("ORGS");
        if (orgs != null) {
            for (JsecOrg org : orgs) {
                if (code.equalsIgnoreCase(org.getCode())) {
                    return org;
                }
            }
        }
        return null;
    }

    public static String getOrgMc(String code, boolean isJc)
    {
        JsecOrg org = getOrg(code);
        if (org == null) {
            return code;
        }
        if (isJc) {
            return org.getJc();
        }
        return org.getMc();
    }

    public static List<JsecOrg> getSubOrgs(String parCode)
    {
        if (StringUtils.isBlank(parCode)) {
            return null;
        }
        List orgs = new ArrayList();
        List orgCaches = (List)CacheManager.getContent("ORGS");
        if (orgCaches != null) {
            for (JsecOrg org : orgCaches) {
                if (org.getSjdm().equals(parCode)) {
                    orgs.add(org);
                }
            }
        }
        return orgs;
    }
}
package com.wjahstu.cache.conf;

import org.apache.commons.lang.StringUtils;

public class CacheEntry
{
    private String identify;
    private String dataSql;
    private String description;

    public void setIdentify(String identify)
    {
        this.identify = identify;
    }

    public String getIdentify()
    {
        return this.identify;
    }

    public void setDataSql(String dataSql)
    {
        this.dataSql = dataSql;
    }

    public String getDataSql()
    {
        return this.dataSql;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isValid()
    {
        return StringUtils.isNotEmpty(this.dataSql);
    }
}
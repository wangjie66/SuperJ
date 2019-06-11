package com.iflytek.iframework.business.cache.vo;

import java.io.Serializable;

public class PublicDict
        implements Serializable
{
    private static final long serialVersionUID = -7646818946943978749L;
    private String dm;
    private String mc;
    private String jc;
    private String sm;
    private String lxjp;
    private String sourceId;
    private String pyjp;

    public String getDm()
    {
        return this.dm;
    }

    public void setDm(String dm)
    {
        this.dm = dm;
    }

    public String getMc()
    {
        return this.mc;
    }

    public void setMc(String mc)
    {
        this.mc = mc;
    }

    public String getJc()
    {
        return this.jc;
    }

    public void setJc(String jc)
    {
        this.jc = jc;
    }

    public String getSm()
    {
        return this.sm;
    }

    public void setSm(String sm)
    {
        this.sm = sm;
    }

    public String getLxjp()
    {
        return this.lxjp;
    }

    public void setLxjp(String lxjp)
    {
        this.lxjp = lxjp;
    }

    public String getSourceId()
    {
        return this.sourceId;
    }

    public void setSourceId(String sourceId)
    {
        this.sourceId = sourceId;
    }

    public String getPyjp()
    {
        return this.pyjp;
    }

    public void setPyjp(String pyjp)
    {
        this.pyjp = pyjp;
    }
}
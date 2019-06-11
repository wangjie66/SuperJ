package com.iflytek.iframework.business.cache.vo;

import java.io.Serializable;

public class JsecOrg
        implements Serializable
{
    private static final long serialVersionUID = 3657521999352207937L;
    private String code;
    private String orgLevel;
    private String mc;
    private String jc;
    private String sjdm;
    private String pyjp;
    private String regionCode;

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getOrgLevel()
    {
        return this.orgLevel;
    }

    public void setOrgLevel(String orgLevel)
    {
        this.orgLevel = orgLevel;
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

    public String getSjdm()
    {
        return this.sjdm;
    }

    public void setSjdm(String sjdm)
    {
        this.sjdm = sjdm;
    }

    public String getPyjp()
    {
        return this.pyjp;
    }

    public void setPyjp(String pyjp)
    {
        this.pyjp = pyjp;
    }

    public String getRegionCode()
    {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode)
    {
        this.regionCode = regionCode;
    }
}
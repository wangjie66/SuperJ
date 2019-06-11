package com.iflytek.iframework.business.tags;

import com.iflytek.iframework.business.cache.impl.PublicZdCacheLoadImpl;
import com.iflytek.iframework.business.cache.vo.PublicDict;
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;

public class CheckTag extends TagSupport
{
    private static final long serialVersionUID = 1L;
    private String lxbm;
    private String isRadio = "false";
    private String name;
    private String checkVal;
    private int maxSize = 4;
    private String cssClass;
    private String cssStyle;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLxbm() {
        return this.lxbm;
    }

    public void setLxbm(String lxbm) {
        this.lxbm = lxbm;
    }

    public String getIsRadio() {
        return this.isRadio;
    }

    public void setIsRadio(String isRadio) {
        this.isRadio = isRadio;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getCheckVal() {
        return this.checkVal;
    }

    public void setCheckVal(String checkVal) {
        this.checkVal = checkVal;
    }

    public String getCssClass() {
        return this.cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssStyle() {
        return this.cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public int doStartTag() throws JspException
    {
        JspWriter out = this.pageContext.getOut();
        StringBuffer sb = new StringBuffer();
        String type = "checkbox";
        if ("true".equalsIgnoreCase(this.isRadio)) {
            type = "radio";
        }
        String[] checkVals = (String[])null;
        if (StringUtils.isNotBlank(this.checkVal))
            checkVals = this.checkVal.split(",");
        try
        {
            List list = PublicZdCacheLoadImpl.getListByLxjp(this.lxbm);
            int index = 0;
            if (list != null) {
                for (PublicDict zd : list) {
                    sb.append("<input type='" + type + "' id='" + zd.getLxjp() +
                            zd.getDm() + "' name='" + this.name + "' value='" +
                            zd.getDm() + "' title='" + zd.getMc() + "' ");

                    if ((checkVals != null) && (checkVals.length > 0)) {
                        for (String val : checkVals) {
                            if (val.equals(zd.getDm())) {
                                sb.append(" checked='checked' ");
                                break;
                            }
                        }
                    }

                    if (StringUtils.isNotBlank(this.cssStyle)) {
                        sb.append("style=\"" + this.cssStyle + "\" ");
                    }

                    if (StringUtils.isNotBlank(this.cssClass)) {
                        sb.append("class=\"" + this.cssClass + "\" ");
                    }
                    sb.append("/>");
                    sb.append(zd.getMc());
                    ++index;
                    if (index % this.maxSize == 0) {
                        sb.append("<br/>");
                    }
                }
                out.print(sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
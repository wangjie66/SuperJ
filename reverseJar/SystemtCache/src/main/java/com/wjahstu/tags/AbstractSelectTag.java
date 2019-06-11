package com.iflytek.iframework.business.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

public abstract class AbstractSelectTag extends TagSupport
{
    private static final long serialVersionUID = 1923482429229642440L;
    protected Logger logger = Logger.getLogger(super.getClass());
    protected String id;
    protected String name;
    protected String multiple;
    protected String cssClass;
    protected String cssStyle;
    protected String selected;
    protected String disabled;
    protected String onChange;
    protected String ondblclick;
    protected String empty;
    protected String emptyDy;
    protected String emptyText;

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCssClass()
    {
        return this.cssClass;
    }

    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getSelected()
    {
        return this.selected;
    }

    public void setSelected(String selected)
    {
        this.selected = selected;
    }

    public String getDisabled()
    {
        return this.disabled;
    }

    public void setDisabled(String disabled)
    {
        this.disabled = disabled;
    }

    public String getOnChange()
    {
        return this.onChange;
    }

    public void setOnChange(String onChange)
    {
        this.onChange = onChange;
    }

    public String getEmpty()
    {
        return this.empty;
    }

    public void setEmpty(String empty)
    {
        this.empty = empty;
    }

    public int doStartTag()
            throws JspException
    {
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("<select  name=\"" + this.name + "\" ");
        if ((this.id != null) && (!"".equals(this.id))) {
            selectBuffer.append("id=\"" + this.id + "\" ");
        }
        if ((this.disabled != null) && (!"".equals(this.disabled)) && ("true".equals(this.disabled))) {
            selectBuffer.append("disabled=\"true\" ");
        }
        if ((this.cssClass != null) && (!"".equals(this.cssClass))) {
            selectBuffer.append("class=\"" + this.cssClass + "\" ");
        }
        if ((this.cssStyle != null) && (!"".equals(this.cssStyle))) {
            selectBuffer.append("style=\"" + this.cssStyle + "\" ");
        }
        if (("multiple".equals(this.multiple)) ||
                ("true".equalsIgnoreCase(this.multiple))) {
            selectBuffer.append("multiple=\"multiple\" ");
        }

        if ((this.onChange != null) && (!"".equals(this.onChange))) {
            fillChange(selectBuffer);
        }
        if ((this.ondblclick != null) && (!"".equals(this.ondblclick))) {
            onDblClick(selectBuffer);
        }
        selectBuffer.append("/>");
        if ("true".equals(this.empty)) {
            selectBuffer.append("<option value=\"\">");
            if ("true".equalsIgnoreCase(this.emptyDy))
                selectBuffer.append("全部");
            else {
                selectBuffer
                        .append(((this.emptyText == null) || ("".equals(this.emptyText))) ? "请选择" :
                                this.emptyText);
            }
            selectBuffer.append("</option>");
        }

        fillOptions(selectBuffer);
        selectBuffer.append("</select>");
        JspWriter out = this.pageContext.getOut();
        try {
            out.print(selectBuffer);
        }
        catch (IOException e) {
            e.getMessage();
        }
        return 0;
    }

    protected abstract void fillOptions(StringBuffer paramStringBuffer);

    protected void fillChange(StringBuffer selectBuffer)
    {
        selectBuffer.append("onchange=\"" + this.onChange + "\" ");
    }

    protected void onDblClick(StringBuffer selectBuffer)
    {
        selectBuffer.append("ondblclick=\"" + this.ondblclick + "\" ");
    }

    public String getMultiple()
    {
        return this.multiple;
    }

    public void setMultiple(String multiple)
    {
        this.multiple = multiple;
    }

    public String getCssStyle()
    {
        return this.cssStyle;
    }

    public void setCssStyle(String cssStyle)
    {
        this.cssStyle = cssStyle;
    }

    public String getEmptyText()
    {
        return this.emptyText;
    }

    public void setEmptyText(String emptyText)
    {
        this.emptyText = emptyText;
    }

    public String getEmptyDy()
    {
        return this.emptyDy;
    }

    public void setEmptyDy(String emptyDy)
    {
        this.emptyDy = emptyDy;
    }

    public String getOndblclick()
    {
        return this.ondblclick;
    }

    public void setOndblclick(String ondblclick)
    {
        this.ondblclick = ondblclick;
    }
}
package com.iflytek.iframework.business.common.controller;

import com.iflytek.iframework.business.common.service.SuggestService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuggestController
{

    @Autowired
    private SuggestService suggestService;

    @RequestMapping({"/common/suggest.do"})
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getParameter("key") != null) {
            String key = request.getParameter("key");
            String tableName = (request.getParameter("tableName") == null) ?
                    "TB_110_DW_INCTYPE_MAP" : request.getParameter("tableName");
            String code = (request.getParameter("code") == null) ?
                    "TO_DW_INCTYPE_DM" : request.getParameter("code");
            String cn = (request.getParameter("cn") == null) ?
                    "INCTYPE_DM_DESCRIPTION" : request.getParameter("cn");
            String py = (request.getParameter("py") == null) ?
                    "FROM_110_INCTYPE_DM" : request.getParameter("py");
            int currPage = Integer.parseInt((request.getParameter("currPage") == null) ?
                    "1" : request.getParameter("currPage").trim());
            int pageNum = 10;
            try
            {
                int count = this.suggestService.getCount(tableName, code, cn, py, key);
                List lib = this.suggestService.getValue(tableName, code, cn, py, key, currPage, pageNum);
                setReturnXML(response, count, lib);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setReturnXML(HttpServletResponse response, int count, List<String> lib)
            throws IOException
    {
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        StringBuilder output = new StringBuilder();
        output.append("<response>");
        output.append("<totalNum>" + count + "</totalNum>");
        if (lib.size() > 0) {
            output.append("<item id=\"null\" value=\"null\">null</item>");
        }
        for (int i = 0; i < lib.size(); ++i) {
            String match = ((String)lib.get(i)).toString();
            output.append("<item id=\"" + match.split(" ")[0].trim() + "\" value=\"" +
                    match.split(" ")[2].trim() + "\">" + match + "</item>");
        }
        output.append("</response>");
        out.print(output);
        out.close();
        out = null;
    }
}
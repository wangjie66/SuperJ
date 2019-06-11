package com.iflytek.iframework.business.common.controller;

import com.iflytek.iframework.business.cache.impl.PublicZdCacheLoadImpl;
import com.iflytek.iframework.business.cache.vo.PublicDict;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JartController
{
    private static String JS_MAP = null;

    @RequestMapping({"/common/getZd.do"})
    public void getJartZdCache(HttpServletResponse response, String lxjp)
    {
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Control", "no-store");
        response.addDateHeader("Expires", 0L);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String s;
            if (StringUtils.isBlank(JS_MAP)) {
                StringBuffer sbs = new StringBuffer();
                InputStream is = null;
                BufferedReader br = null;
                s = "";
                try {
                    is = super.getClass().getResourceAsStream(
                            "/com/iflytek/iframework/business/common/controller/jsmap.js");
                    br = new BufferedReader(
                            new InputStreamReader(is,
                                    Charset.forName("utf-8")));
                    while ((s = br.readLine()) != null)
                        sbs.append(s).append("\r\n");
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Exception localException) {
                }
                finally {
                    if (is != null)
                        try {
                            is.close();
                        }
                        catch (IOException localIOException5)
                        {
                        }
                    if (br != null)
                        try {
                            br.close();
                        }
                        catch (IOException localIOException6)
                        {
                        }
                }
                JS_MAP = sbs.toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(JS_MAP);
            if (StringUtils.isNotBlank(lxjp)) {
                String[] jp = lxjp.split(",");
                if ((jp != null) && (jp.length > 0))
                {
                    String[] arrayOfString1;
                    localException = (arrayOfString1 = jp).length; for (s = 0; s < localException; ++s) { String p = arrayOfString1[s];
                    List dict =
                            PublicZdCacheLoadImpl.getListByLxjp(p.toUpperCase());
                    if ((dict != null) && (!dict.isEmpty())) {
                        for (PublicDict pd : dict) {
                            sb.append("MapClass.setAt(\"").append(
                                    pd.getLxjp()).append("-").append(
                                    pd.getDm()).append("\",\"");
                            sb.append(pd.getMc()).append("\");\r\n");
                        }
                    } }

                }
            }

            out.print("\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
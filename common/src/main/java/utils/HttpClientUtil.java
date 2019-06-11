package utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {

    /**
     *请求超时时间
     *
     */
    private int connectTimeout;

    /**
     * Token令牌
     */
    private String token;


    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




    /**
     * Http Post请求
     * @param url
     * @param map
     * @return
     */
    public String doPost(String url, Map<String, String> map) {
        //生成签名
        String sign = SignTools.sign(map, token);

        System.out.println("请求代理Rest:"+url);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        try {
            RequestConfig requestConfig =RequestConfig.custom().setConnectionRequestTimeout(connectTimeout).setConnectTimeout(connectTimeout).setSocketTimeout(connectTimeout).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.addHeader("Authorization", "Bearer " + sign);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return EntityUtils.toString(resEntity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                httpPost.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取响应对象编码格式
     *
     * @param response
     * @return
     */
    public String getCharset(HttpResponse response) {
        Header header = response.getFirstHeader("Content-Type");
        if (header != null) {
            String[] pro = header.getValue().split(";");
            for (String p : pro) {
                if (p.contains("charset=")) {
                    return p.replaceAll("charset=", "");
                }
            }
            return "utf-8";
        } else {
            return "utf-8";
        }
    }
}
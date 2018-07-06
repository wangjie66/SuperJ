package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author : JieWang
 * @Date : Created in 2017/11/16 16:03
 * @Email : wjahstu@163.com
 * 连接工具类
 */
public class ConnectionUtils {

    protected static int cach_size = 3 * 1024 * 1024;
    protected static int con_timeout = 36000;
    protected static int read_timeout = 30000;
    protected static String charset = "UTF-8";

    public static String post(String urlStr, String params) {
        String response = null;
        try {
            URL url = null;
            HttpURLConnection con = null;
            BufferedReader ins = null;
            OutputStream ous = null;
            url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(con_timeout);
            con.setReadTimeout(read_timeout);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.connect();
            ous = con.getOutputStream();
            ous.write(params.getBytes(charset));
            ous.flush();
            ous.close();
            ins = new BufferedReader(
                    new InputStreamReader(con.getInputStream()), cach_size);
            if (con.getResponseCode() == 200) {
                String line = null;
                StringBuilder rspStr = new StringBuilder();
                while ((line = ins.readLine()) != null) {
                    rspStr.append(line);
                }
                response = rspStr.toString();
            } else {
                throw new Exception();
            }
            ins.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}

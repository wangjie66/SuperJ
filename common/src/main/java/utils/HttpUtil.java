package utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        InputStream is=null;
        InputStreamReader isr=null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            is=connection.getInputStream();
            isr=new InputStreamReader(is);
            in=new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(is !=null){
                    is.close();
                }
                if(isr != null){
                    isr.close();
                }if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String sendPost(String urlString, String param, String png) {
    	 PrintWriter out = null;
         BufferedReader in = null;
         String result = "";
         try {
             URL realUrl = new URL(urlString);
             URLConnection conn = realUrl.openConnection();
             conn.setRequestProperty("accept", "*/*");
             conn.setRequestProperty("connection", "Keep-Alive");
             conn.setRequestProperty("user-agent",
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             conn.setRequestProperty("test.png",png);
             conn.setDoOutput(true);
             conn.setDoInput(true);
             out = new PrintWriter(conn.getOutputStream());
             out.print(param);
             out.flush();
             in = new BufferedReader(
                     new InputStreamReader(conn.getInputStream()));
             String line;
             while ((line = in.readLine()) != null) {
                 result += line;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         finally{
             try{
                 if(out!=null){
                     out.close();
                 }
                 if(in!=null){
                     in.close();
                 }
             }
             catch(IOException ex){
                 ex.printStackTrace();
             }
         }
         return result;
     }    

}
    
    

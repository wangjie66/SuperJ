package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * <code>HTTP工具</code>
 * 
 * @author zhuwei
 * @date 2016-12-31 0:49
 */
public class HttpUtils {

	public static String httpPost(String urlPaths, Object object,
			Integer connectionTimeOut, Integer readTimeOut) throws IOException {
		StringBuffer sb = null;
		String urlPath = new String(urlPaths);
		// 建立连接
		URL url = new URL(urlPath);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 设置参数
		httpConn.setDoOutput(true); // 需要输出
		httpConn.setDoInput(true); // 需要输入
		httpConn.setUseCaches(false); // 不允许缓存
		httpConn.setRequestMethod("POST"); // 设置POST方式连接
		// 设置请求属性 application/x-www-form-urlencoded
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		httpConn.setRequestProperty("Charset", "UTF-8");
		// 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		httpConn.connect();
		// 建立输入流，向指向的URL传入参数
		OutputStream outputStream = httpConn.getOutputStream();
		outputStream.write(JSONObject.toJSONString(object).getBytes("utf-8"));
		outputStream.flush();
		outputStream.close();
		// 获得响应状态
		int resultCode = httpConn.getResponseCode();
		System.out.println("请求响应状态：" + httpConn.getResponseCode());
		if (HttpURLConnection.HTTP_OK == resultCode) {
			sb = new StringBuffer();
			String readLine = new String();
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine).append("\n");
			}
			responseReader.close();
		}
		return null == sb ? "" : sb.toString();
	}

	public static String httpGet(String urlPaths, Object object,
			Integer connectionTimeOut, Integer readTimeOut) throws IOException {
		StringBuffer sb = null;
		String urlPath = new String(urlPaths);
		// 建立连接
		URL url = new URL(urlPath);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 设置参数
		httpConn.setDoOutput(true); // 需要输出
		httpConn.setDoInput(true); // 需要输入
		httpConn.setUseCaches(false); // 不允许缓存
		httpConn.setRequestMethod("GET"); // 设置POST方式连接
		// 设置请求属性 application/x-www-form-urlencoded
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		httpConn.setRequestProperty("Charset", "UTF-8");
		// 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		httpConn.connect();
		// 建立输入流，向指向的URL传入参数
		OutputStream outputStream = httpConn.getOutputStream();
		outputStream.write(JSONObject.toJSONString(object).getBytes("utf-8"));
		outputStream.flush();
		outputStream.close();
		// 获得响应状态
		int resultCode = httpConn.getResponseCode();
		if (HttpURLConnection.HTTP_OK == resultCode) {
			sb = new StringBuffer();
			String readLine = new String();
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine).append("\n");
			}
			responseReader.close();
		}
		return null == sb ? "" : sb.toString();
	}

	public static String httpGet(String urlPaths, Integer connectionTimeOut,
			Integer readTimeOut) throws IOException {
		StringBuffer sb = null;
		String urlPath = new String(urlPaths);
		// 建立连接
		URL url = new URL(urlPath);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 设置参数
		httpConn.setDoOutput(true); // 需要输出
		httpConn.setDoInput(true); // 需要输入
		httpConn.setUseCaches(false); // 不允许缓存
		httpConn.setRequestMethod("GET"); // 设置POST方式连接
		// 设置请求属性 application/x-www-form-urlencoded
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		httpConn.setRequestProperty("Charset", "UTF-8");
		// 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		httpConn.connect();
		// 获得响应状态
		int resultCode = httpConn.getResponseCode();
		if (HttpURLConnection.HTTP_OK == resultCode) {
			sb = new StringBuffer();
			String readLine = new String();
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine).append("\n");
			}
			responseReader.close();
		}
		return null == sb ? "" : sb.toString();
	}

	public static String httpRequest(String url, String param)
			throws IOException {
		String result = "";
		BufferedReader in = null;
		String urlNameString = "";
		try {
			if (param != null) {
				urlNameString = url + "?" + param;
			} else {
				urlNameString = url;
			}

			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "GBK"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}

package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServiceClient {

	public static void main(String[] args) throws Exception {
		
		/*ServiceIterface service = ProxyUtils.getProxy(ServiceIterface.class,"methodA",hostname,port);
		Result = service.methodA(parameters);*/
		
		// 向服务器发出请求建立连接
		Socket socket = new Socket("localhost", 8899);
		// 从socket中获取输入输出流
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();

		PrintWriter pw = new PrintWriter(outputStream);
		StringBuilder s = new StringBuilder();
		s.append("socket.GetDataServiceImpl@");
		s.append("getData@") ;
		String ss ="";
		BufferedReader xx = new BufferedReader(new InputStreamReader(System.in));
		String ll="";
		while( (ll = xx.readLine())!=null){
			//s.append(ll);
			ss ="socket.GetDataServiceImpl@getData@"+ll;
			//pw.println(s.toString());
			pw.println(ss);
			pw.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String result = br.readLine();
			System.out.println(result);

		}

		//s.append("hello");

		
		inputStream.close();
		outputStream.close();
		socket.close();
		
		
	}
}

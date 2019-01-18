package com.zicoo.kelddispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class DispatchEleServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String parameter = req.getParameter("userName");
//		
//		System.out.println("json"+parameter);
		
		BufferedReader reader = req.getReader();
		String len=null;
		StringBuffer sbf = new StringBuffer();
		while((len=reader.readLine())!=null){
			sbf.append(len);
		} 
		String json = sbf.toString();
		System.out.println("json"+json);
		String url="http://localhost:8080/Rain/testAccept";
		 HttpPost httpPost = new HttpPost(url);
		 Header header = new BasicHeader("content-type", "application/json");
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 httpPost.addHeader(header);
		 StringEntity stringEntity = new StringEntity(json); 
		 httpPost.setEntity(stringEntity);
	
		 CloseableHttpResponse response2 =httpclient.execute(httpPost);
		 HttpEntity entity2=null;
              System.out.println(response2.getStatusLine());
               entity2= response2.getEntity();
//              EntityUtils.consume(entity2);
		  BufferedReader read2=null;
		  try {
			  InputStream content = entity2.getContent();
				 read2= new BufferedReader(new InputStreamReader(content, "utf-8"));
				 String str=null;
				 StringBuilder sbu = new StringBuilder();
				 while((str=read2.readLine())!=null) {
					 sbu.append(str);
				 }
				System.out.println("返回值"+sbu.toString());
				resp.setContentType("text/json;charset=utf-8");
				PrintWriter writer = resp.getWriter();
				writer.write(sbu.toString());
		  }finally {
//				if(read2!=null) {
//					read2.close();
//				}
//				 response2.close();
		  }
		 

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		this.doGet(req, resp);
		
	}
	

}

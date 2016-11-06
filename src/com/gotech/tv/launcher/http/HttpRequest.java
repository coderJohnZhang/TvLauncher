package com.gotech.tv.launcher.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * 
 * @author john
 * 
 */
public class HttpRequest
{

	private static final String TAG = "HttpRequest";
	/**
	 * HTTP请求地址
	 */
	private String reqUrl;
	/**
	 * 请求的字符编码
	 */
	private String charset = "utf-8";
	/**
	 * POST请求参数
	 */
	private Map<String, String> paramsMap;
	/**
	 * 请求返回的结果
	 */
	private String respResult;
	/**
	 * 文件的总长度
	 */
	private long fileTotalLength;

	public HttpRequest(String reqUrl)
	{
		this.reqUrl = reqUrl;
	}

	/**
	 * 设置字符编码
	 * 
	 * @param charset
	 * @return
	 */
	public HttpRequest setCharset(String charset)
	{
		if (null != charset && !"".equals(charset.trim()))
		{
			this.charset = charset;
		}
		return this;
	}

	/**
	 * 设置POST请求参数
	 * 
	 * @param paramsMap
	 * @return
	 */
	public HttpRequest setPostParams(Map<String, String> paramsMap)
	{
		this.paramsMap = paramsMap;
		return this;
	}

	/**
	 * 获取请求结果
	 * 
	 * usage: HttpRequest httpRequest = new HttpRequest("http://www.baidu.com");
	 * httpRequest.request();
	 * 
	 * @param isPost
	 * @return
	 */
	public String request(boolean... isPost)
	{
		boolean post = false;
		if (isPost.length > 0)
		{
			post = isPost[0];
		}
		try
		{
			if (post)
			{
				// 发送POST请求
				post();
			}
			else
			{
				// 发送GET请求
				get();
			}
		}
		catch (Exception e)
		{
			Log.e(TAG, "DNS解析失败,url[" + reqUrl + "]", e);
		}
		return this.respResult;
	}

	/**
	 * 发送一个GET请求
	 * 
	 * @return
	 * @throws Exception
	 */
	private HttpRequest get() throws Exception
	{
		HttpGet httpget = null;
		String result = null;
		int respCode = -1;
		try
		{
			httpget = new HttpGet(reqUrl);
			httpget.setHeader("Connection", "close");
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 1000 * 60);
			httpget.setParams(params);
			HttpResponse response = new DefaultHttpClient().execute(httpget);
			result = EntityUtils.toString(response.getEntity(), charset).trim();
			respCode = response.getStatusLine().getStatusCode();
			if (respCode == HttpStatus.SC_OK)
			{
				this.respResult = result;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (null != httpget)
			{
				httpget = null;
			}
		}
		return this;
	}

	/**
	 * 发送一个POST请求
	 * 
	 * @return
	 */
	private HttpRequest post() throws Exception
	{
		HttpPost httppost = null;
		int respCode = -1;
		try
		{
			httppost = new HttpPost(reqUrl);
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 1000 * 60);
			httppost.setParams(params);
			if (null != paramsMap && !paramsMap.isEmpty())
			{
				// 添加所需要的post内容
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<String> keys = paramsMap.keySet();
				for (String key : keys)
				{
					nvps.add(new BasicNameValuePair(key, paramsMap.get(key)));
				}
				httppost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			}
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httppost);
			respCode = response.getStatusLine().getStatusCode();
			if (respCode == HttpStatus.SC_OK)
			{
				this.respResult = EntityUtils.toString(response.getEntity(), charset).trim();
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (null != httppost)
			{
				httppost = null;
			}
		}
		return this;
	}

	/**
	 * 获取文件流
	 * 
	 * @return
	 */
	public InputStream getInputStream()
	{
		InputStream in = null;
		HttpGet httpget = null;
		int respCode = -1;
		try
		{
			httpget = new HttpGet(reqUrl);
			httpget.setHeader("Connection", "close");
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 1000 * 60);
			httpget.setParams(params);
			HttpResponse response = new DefaultHttpClient().execute(httpget);
			respCode = response.getStatusLine().getStatusCode();
			if (respCode == HttpStatus.SC_OK)
			{
				HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					in = entity.getContent();
				}
				fileTotalLength = response.getEntity().getContentLength();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != httpget)
			{
				httpget = null;
			}
		}
		return in;
	}

	/**
	 * 获取请求文件的总长度
	 * 
	 * @return
	 */
	public long getFileTotalLength()
	{
		return fileTotalLength;
	}

	/**
	 * 关闭文件下载流
	 * 
	 * @param in
	 * @param out
	 */
	public void close(InputStream in, OutputStream out)
	{
		if (in != null)
		{
			try
			{
				in.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (out != null)
		{
			try
			{
				out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}

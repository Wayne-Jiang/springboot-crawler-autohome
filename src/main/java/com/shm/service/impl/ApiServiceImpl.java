package com.shm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shm.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

//	注入httpClient连接器	
	@Autowired
	private PoolingHttpClientConnectionManager cm;

	@Override
	public String getHtml(String url) {
//		获取httpClient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
//		声明httpGet请求
		HttpGet httpGet = new HttpGet(url);
//		设置用户代理信息
		httpGet.setHeader("User-Agent", "");

//		设置请求参数RequestConfig
//		httpGet.setConfig(this.getConfig());

//		使用httpClient发起请求，返回response
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
//			解析response返回数据
			if (response.getStatusLine().getStatusCode() == 200) {
//				如果response.getEntity获取的结果为空，在执行EntityUtils.toString会报错
//				需要对Entity进行非空判断
				// 判断是否有响应体
				if (response.getEntity() != null) {
					// 如果有响应体，则进行解析
					String html = EntityUtils.toString(response.getEntity(), "UTF-8");

					// 返回
					return html;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					// 关闭连接
					response.close();
				}
				// 不能关闭，现在使用的是连接管理器
//				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public String getImage(String url) {
//		获取httpClient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
//		声明httpGet请求
		HttpGet httpGet = new HttpGet(url);
//		设置用户代理信息
		httpGet.setHeader("User-Agent", "");

//		设置请求参数RequestConfig
//		httpGet.setConfig(this.getConfig());

//		使用httpClient发起请求，返回response
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
//			解析response下载图片
			if (response.getStatusLine().getStatusCode() == 200) {

//				image/png
				String contentType = response.getEntity().getContentType().getValue();
//				获取文件类型
				String extName = "." + contentType.split("/")[1];

//				使用UUID生成图片名
				String imageName = UUID.randomUUID().toString() + extName;

//				声明输出的文件
				OutputStream outputStream = new FileOutputStream(
						new File("D:/Program Files/eclipse/workspace1/images/" + imageName));

//				使用响应体输出文件
				response.getEntity().writeTo(outputStream);
//				返回生成的图片名
				return imageName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					// 关闭连接
					response.close();
				}
				// 不能关闭，现在使用的是连接管理器
//				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取请求参数对象
	 * 
	 * @return
	 */
	private RequestConfig getConfig() {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(5000) // 设置创建连接的超时时间
				.setConnectionRequestTimeout(2500) // 设置获取连接的超时时间
				.build();

		return config;
	}
}

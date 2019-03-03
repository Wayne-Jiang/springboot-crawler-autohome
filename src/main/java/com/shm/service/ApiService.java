package com.shm.service;

public interface ApiService {

	/**
	 * get请求获取页面数据
	 * @param url
	 * @return
	 */
	public String getHtml(String url);
	
	/**
	 * get 请求下载图片
	 * @param url
	 * @return 返回图片名称
	 */
	public String getImage(String url);
}

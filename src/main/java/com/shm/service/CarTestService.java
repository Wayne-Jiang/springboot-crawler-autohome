package com.shm.service;

import java.util.List;

import com.shm.pojo.CartTest;

public interface CarTestService {

	/**
	 * 分页查询评测数据的汽车标题
	 * @param page
	 * @param rows
	 * @return
	 */
	List<String> queryTitleByPage(int page, int rows);

	/**
	 * 保存数据到数据库
	 * @param cartTest
	 */
	void saveCarTest(CartTest cartTest);

}

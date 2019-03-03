package com.shm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.TestService;

@RestController
public class TestController {

	@Autowired
	public TestService testService;
	
	/**
	 * 查询数据库时间
	 * @return
	 */
	@RequestMapping("date")
	public String queryDate() {
		String date = this.testService.queryDate();
		return date;
	}
}

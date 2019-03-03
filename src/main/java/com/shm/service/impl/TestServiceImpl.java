package com.shm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shm.mapper.TestMapper;
import com.shm.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	public TestMapper testMapper;
	@Override
	public String queryDate() {
		String  date = this.testMapper.queryDate();
		return date;
	}

}

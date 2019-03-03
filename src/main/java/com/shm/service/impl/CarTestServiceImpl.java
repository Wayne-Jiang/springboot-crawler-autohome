package com.shm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shm.mapper.CarTestMapper;
import com.shm.pojo.CartTest;
import com.shm.service.CarTestService;

@Service
public class CarTestServiceImpl implements CarTestService {

	@Autowired
	private CarTestMapper  cartTestMapper;
	@Override
	public List<String> queryTitleByPage(int page, int rows) {
//		计算从那条数据开始查
		int start = (page-1)*rows;
		
//		封装参数map
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("rows", rows);
		
		//使用mapper从数据库中查询数据
		List<String> list = this.cartTestMapper.queryTitleByPage(map);
		return list;
	}
	@Override
	public void saveCarTest(CartTest cartTest) {
		this.cartTestMapper.saveCarTest(cartTest);
	}

}

package com.shm.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shm.service.CarTestService;
import com.shm.util.TitleFilter;

@Configuration
public class TitleFilterCfg {

	@Autowired
	private CarTestService carTestService;
	@Bean
	public TitleFilter titleFilter() {
//		创建汽车标题的去重过滤器
		TitleFilter titleFilter = new TitleFilter();
	
//		声明页码数	
		int page = 1,pageSize = 0;
		do {
//			查询数据库中的title数据，因为数据量过大，最好分页查询
			List<String> titles = this.carTestService.queryTitleByPage(page,500);
			for(String title : titles) {
			//		初始化数据，把数据库中已有汽车标题的数据添加到查重过滤器中
					titleFilter.add(title);								
			}
//			执行完成后页码加一
			page++;
			pageSize = titles.size();
		}while(pageSize == 500);
		
		return titleFilter;
	}
}

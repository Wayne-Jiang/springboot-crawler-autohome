package com.shm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {

	/**
	 * 查询数据库时间
	 */
	@Select("select now()")
	public String queryDate();
}

package com.shm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.shm.pojo.CartTest;

@Mapper
public interface CarTestMapper {

	/**
	 * 分页查询评测数据的汽车标题
	 * @param map 有两个key，一个是分页查询的开始的条数start,另一个是rows每页显示的记录条数
	 * @return
	 */
	@Select("select title from car_test limit #{start},#{rows}")
	List<String> queryTitleByPage(Map<String, Object> map);

	/**
	 * 保存
	 * @param cartTest
	 */
	@Insert("INSERT INTO `car_test` (" + 
			"`title`, " + 
			"`test_speed`, " + 
			"`test_brake`, " + 
			"`test_oil`, " + 
			"`editor_name1`, " + 
			"`editor_remark1`, " + 
			"`editor_name2`, " + 
			"`editor_remark2`," + 
			"`editor_name3`, " + 
			"`editor_remark3`, " + 
			"`image`, " + 
			"`created`, " + 
			"`updated` " + 
			" )" + 
			" VALUES " +
			" ( " + 
			"#{title}, " + 
			"#{test_speed}, " + 
			"#{test_brake}, " + 
			"#{test_oil}, " + 
			"#{editor_name1}, " + 
			"#{editor_remark1}, " + 
			"#{editor_name2}, " + 
			"#{editor_remark2}, " + 
			"#{editor_name3}, " + 
			"#{editor_remark3}, " + 
			"#{image}," + 
			"#{created}, " + 
			"#{updated}" +")" )
	
	void saveCarTest(CartTest cartTest);

}

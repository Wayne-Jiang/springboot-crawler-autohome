package com.shm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shm.pojo.CartTest;
import com.shm.util.TitleFilter;

@RunWith(value=SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=Application.class)
public class AutohomeCrawlerTest {

	@Autowired
	private ApiService apiService;
	
	@Autowired
	private CarTestService carTestService;
	
	@Autowired
	private TitleFilter titleFilter;
	
	
	@Test
	public void TestCrawler() {
//		声明爬取的页面url
		String url = "https://www.autohome.com.cn/bestauto/1";
		
//		使用apiService爬取数据
		
//		使用jsoup解析页面数据
		Document dom = Jsoup.parse("","UTF-8");
		
//		获取所有的评测div
		Elements divs = dom.select("");
//		遍历当前页所有的评测数据
		for(Element div: divs) {
//			去重过滤，重复的数据不再抓取
			String title = div.select("div.uibox-title").first().text();
			if(titleFilter.contains(title)) {
//				如果数据存在，表示重复，进行下一个遍历
				continue;
			}
//			解析页面获取评测数据
			CartTest cartTest = this.getCarTest(div);
//			解析页面，下载汽车评测图片
			String image = this.getCarImage(div);
			
			this.carTestService.saveCarTest(cartTest);
		}
	}

	/**
	 * 解析传递进来的数据，下载评测图片
	 * @param div
	 * @return
	 */
	private String getCarImage(Element div) {
//		声明图片保存的集合
		List<String> images = new ArrayList<String>();
		
//		获取图片的url地址
		Elements page = div.select("ul.piclist02 li");
//		遍历评测图片的元素
		for (Element element : page) {
//			获取评测图片的展示地址
			String imagePage = "http:"+element.select("a").attr("href");
//			使用apiService发起请求获取图片展示页面
			String html = this.apiService.getHtml(imagePage);
//			解析图片展示页面
			Document dom = Jsoup.parse(html);
//			获取图片url地址
			String imageUrl = "http:"+dom.getElementById("img").attr("src");
//			根据图片url地址下载图片，返回图片名称
			String imageName = this.apiService.getImage(imageUrl);
			
//			把图片放到集合中
			images.add(imageName);
		}
//		把图片名称返回，把集合转换为字符串，多个元素中间用逗号分隔
		return StringUtils.join(images,",");
	}

	/**
	 * 解析传递进来的数据，封装成汽车评测对象
	 * @param div
	 * @return
	 */
	private CartTest getCarTest(Element div) {
//		创建评测对象
		CartTest carTest = new CartTest();
		
//		设置评测数据
//		汽车评测标题
		String title = div.select("div.uibox-title").first().text();
		carTest.setTitle(title);
		
		// 评测项目-加速(0-100公里/小时),单位毫秒
		String speed = div.select(".tabbox1 dd:nth-child(2) div.dd-div2").first().text();
		carTest.setTest_speed(this.changeStr2Num(speed));
		
		// 评测项目-刹车(100-0公里/小时),单位毫米
		String brake = div.select(".tabbox1 dd:nth-child(3) div.dd-div2").first().text();
		carTest.setTest_brake(this.changeStr2Num(brake));
		
		// 评测项目-实测油耗(升/100公里),单位毫升
		String oil = div.select(".tabbox1 dd:nth-child(4) div.dd-div2").first().text();
		carTest.setTest_oil(this.changeStr2Num(oil));
		
		// 评测编辑1
		carTest.setEditor_name1(div.select(".tabbox2 dd:nth-child(2) > div.dd-div1").first().text());
		carTest.setEditor_remark1(div.select(".tabbox2 dd:nth-child(2) > div.dd-div3").first().text());
		
		// 评测编辑2
		carTest.setEditor_name2(div.select(".tabbox2 dd:nth-child(3) > div.dd-div1").first().text());
		carTest.setEditor_remark2(div.select(".tabbox2 dd:nth-child(3) > div.dd-div3").first().text());

		// 评测编辑3
		carTest.setEditor_name3(div.select(".tabbox2 dd:nth-child(4) > div.dd-div1").first().text());
		carTest.setEditor_remark3(div.select(".tabbox2 dd:nth-child(4) > div.dd-div3").first().text());

		
		carTest.setCreated(new Date());
		carTest.setUpdated(carTest.getCreated());
		
		
//		返回评测数据
		
		return null;
	}

	/**
	 * 把字符串最后一个字符去掉，转换成数字再乘以1000
	 * @param str
	 */
	private int changeStr2Num(String str) {
		//把字符串最后一位去掉
		str = str.substring(0, str.length() - 1);
		
//		字符转换为数字
		Number num = Float.parseFloat(str) * 1000;
		return num.intValue();
	}
}

package com.shm.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shm.Application;

@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ApiServiceTest {

	@Autowired
	private ApiService apiService;
	@Test
	public void testGetHtml() throws Exception{
		String html = this.apiService.getHtml("https://www.autohome.com.cn/bestauto/1");
		Document dom = Jsoup.parse(html);
		System.out.println(dom.select("title").first().text());
	}

	@Test
	public void testGetImage() throws Exception{
		String imageName = this.apiService.getImage("https://car3.autoimg.cn/cardfs/product/g3/M03/E0/35/800x0_1_q87_autohomecar__ChcCRVxm2taAenWHAAmfFP848Go764.jpg");
		System.out.println(imageName);
	}

}

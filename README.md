#  网络爬虫抓取汽车评测数据
springboot-crawler-autohome
使用聚焦网络爬虫，抓取汽车之家上的汽车评测数据。https://www.autohome.com.cn/bestauto/

### 使用技术
jdk8+SpringBoot1.5.8+SpringMVC4+MyBatis3.4+HttpClient4.5.3+Jsoup2.10+Quartz2.2

### 流程分析
抓取评测数据：
1.	根据url抓取html页面
2.	对html页面进行解析，获取该页面所有的评测数据
3.	遍历所有的评测数据
4.	判断遍历的评测数据是否已保存，
如果已保存再次遍历下一条评测数据
如果未保存执行下一步
5.	保存评测数据到数据库中

### 数据库表设计
CREATE TABLE `car_test` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(100) NOT NULL COMMENT '评测车辆的名字',
  `test_speed` int(150) DEFAULT NULL COMMENT '评测项目-加速(0-100公里/小时),单位毫秒',
  `test_brake` int(150) DEFAULT NULL COMMENT '评测项目-刹车(100-0公里/小时),单位毫米',
  `test_oil` int(150) DEFAULT NULL COMMENT '评测项目-实测油耗(升/100公里)，单位毫升',
  `editor_name1` varchar(10) DEFAULT NULL COMMENT '评测编辑1',
  `editor_remark1` varchar(1000) DEFAULT NULL COMMENT '点评内容1',
  `editor_name2` varchar(10) DEFAULT NULL COMMENT '评测编辑2',
  `editor_remark2` varchar(1000) DEFAULT NULL COMMENT '点评内容2',
  `editor_name3` varchar(10) DEFAULT NULL COMMENT '评测编辑3',
  `editor_remark3` varchar(1000) DEFAULT NULL COMMENT '点评内容3',
  `image` varchar(1000) DEFAULT NULL COMMENT '评测图片，5张图片名，中间用,分隔',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='汽车之家评测表';



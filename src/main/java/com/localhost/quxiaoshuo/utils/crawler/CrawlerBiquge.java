package com.localhost.quxiaoshuo.utils.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 实现页面分析的逻辑,爬取笔趣阁.tv
 */
public class CrawlerBiquge implements PageProcessor {

	/**
	 * 分析笔趣阁.tv的小说页面(先爬取默认数据库内容)
	 *
	 * @param page 获取下载的结果
	 */
	@Override
	public void process(Page page) {
		Html html = page.getHtml();
		String htmlStr = html.toString();
		//默认输出到控制台的方法
		ResultItems pageResultItems = page.getResultItems();
		pageResultItems.put("html", htmlStr);
		//等价于page.putField("html",htmlStr);

	}

	/**
	 * site是对象
	 * Site是站点的配置
	 * 可以返回默认配置
	 */
	private Site site = Site.me()
			.setCharset("gbk")//编码格式
			.setRetryTimes(3)//重试次数
			.addHeader("Referer", "http://www.biquge.tv/")//设置跳转前页面
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36")
			.setTimeOut(3000);//超时时间3s

	@Override
	public Site getSite() {
		return site;
	}

	private static Spider spider = Spider.create(new CrawlerBiquge())
			//设置起始url
			.addUrl("http://www.biquge.tv/0_63352/");

	public static void main(String[] args) {
		spider.run();
		//启动爬虫(同步方法,当前线程中执行,完成此次进行下一次);start同时进行(新建线程)
		// .run();
		// .start();
	}
}

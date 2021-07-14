package com.localhost.quxiaoshuo.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class CrawlerChapterProc implements PageProcessor {
	@Autowired
	private CrawlerChapterPipeline crawlerChapterPipeline;

	@Override
	public void process(Page page) {

	}

	/**
	 * site是对象
	 * Site是站点的配置
	 * 可以返回默认配置
	 */
	private Site site = Site.me()
			.setCharset("gbk")//编码格式
			.setRetryTimes(2)//重试次数
			.addHeader("Referer", "http://www.biquge.tv/")//设置跳转前页面
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36")
			.setTimeOut(30*1000)//超时时间3s
			.setRetryTimes(2000)//重试间隔
			.setSleepTime(300);//两次间隔

	@Override
	public Site getSite() {
		return site;
	}

	private static Spider spider = Spider.create(new CrawlerChapterProc())
			.thread(10);//线程10个


	public void processStart(Integer novelKey,String url) {
		System.out.println("执行了小说爬取:"+novelKey);
		spider.addUrl(url);
		spider.addPipeline(this.crawlerChapterPipeline);
		spider.start();
	}
}

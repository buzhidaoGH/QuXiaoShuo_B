package com.localhost.quxiaoshuo.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
@Scope("prototype")
public class CrawlerChapterProcessor implements PageProcessor {
	@Autowired
	private CrawlerChapterPipeline crawlerChapterPipeline;

	@Override
	public void process(Page page) {
		Html pageHtml = page.getHtml();
		//一共多少章
		List<Selectable> chapters = pageHtml.css("div#list dl dd").nodes();
		//章节所属小说
		String novelKey = page.getUrl().toString().split("_")[1].split("/")[0];
		//存放保存
		page.putField("novelKey", novelKey);
		page.putField("chapters", chapters);
	}

	/**
	 * site是对象
	 * Site是站点的配置
	 * 可以返回默认配置
	 */
	private Site site = Site.me()
			.setCharset("gbk")//编码格式
			.setRetryTimes(2)//重试次数
			.addHeader("Referer", "https://www.qbiqu.com/")//设置跳转前页面
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36")
			.setTimeOut(30 * 1000)//超时时间30s
			.setRetryTimes(2000)//重试间隔
			.setSleepTime(300);//两次间隔

	@Override
	public Site getSite() {
		return site;
	}

	private static Spider spider = Spider.create(new CrawlerChapterProcessor());
	// .thread(3);


	public void processStart(Integer novelKey, String url) {
		spider.addUrl(url);
		spider.addPipeline(this.crawlerChapterPipeline);
		spider.start();
	}
}

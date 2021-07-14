package com.localhost.quxiaoshuo.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class CompletionChapterProcessor implements PageProcessor {

	@Autowired
	private CompletionChapterPipeline completionChapterPipeline;

	@Override
	public void process(Page page) {
		String html = page.getHtml().toString();
		String content = Jsoup.parse(html).select("div#content").text();
		String url = page.getUrl().toString();
		String[] split = url.split("/");
		Integer novel = Integer.parseInt(split[split.length - 2].split("_")[1]);
		Integer weight = Integer.parseInt(split[split.length - 1].split("\\.")[0]);
		page.putField("content",content);
		page.putField("words",content.length());
		page.putField("novel",novel);
		page.putField("weight",weight);
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
			.setTimeOut(30*1000)//超时时间30s
			.setRetryTimes(2000)//重试间隔
			.setSleepTime(300);//两次间隔

	@Override
	public Site getSite() {
		return site;
	}

	private static Spider spider = Spider.create(new CompletionChapterProcessor())
			.thread(4);//线程5个


	public void processStart(String title,String chapterTitle,String url) {
		System.out.println("开始爬取 "+title+" : "+chapterTitle);
		spider.addUrl(url);
		spider.addPipeline(this.completionChapterPipeline);
		spider.run();
	}
}

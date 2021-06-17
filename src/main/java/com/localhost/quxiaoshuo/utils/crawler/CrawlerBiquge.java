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
			.setTimeOut(3000);//超时时间3s

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new CrawlerBiquge())
				//设置起始url
				.addUrl("http://www.biquge.tv/0_63352/")
				//启动爬虫
				.run();
	}
}

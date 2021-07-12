package com.localhost.quxiaoshuo.crawler;

import com.localhost.quxiaoshuo.dao.NovelInfoDao;
import com.localhost.quxiaoshuo.domain.NovelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 实现页面分析的逻辑,爬取笔趣阁.tv小说基本信息,并且存入数据库
 */
@Component
public class CrawlerBiquge implements PageProcessor {

	@Autowired
	private NovelInfoDao novelInfoDao;

	/**
	 * 分析笔趣阁.tv的小说页面(先爬取默认数据库内容)
	 *
	 * @param page 获取下载的结果
	 */
	@Override
	public void process(Page page) {
		try {
			Html pageHtml = page.getHtml();
			//标题
			String title = pageHtml.css("meta[property=og:novel:book_name]").css("meta", "content").toString();
			//小说id
			String id = page.getUrl().toString().split("_")[1];
			//image
			String image = pageHtml.css("meta[property=og:image]").css("meta", "content").toString();
			//作者
			String author = pageHtml.css("meta[property=og:novel:author]").css("meta", "content").toString();
			//URL
			String url = pageHtml.css("meta[property=og:url]").css("meta", "content").toString();
			//更新时间
			// DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pageHtml.css("meta[property=og:url]").css("meta", "content").toString());
			Date updateDate = null;
			try {
				updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pageHtml.css("meta[property=og:novel:update_time]").css("meta", "content").toString());
			} catch (ParseException e) {
				page.setSkip(true);
				e.printStackTrace();
			}
			//状态
			String statusStr = pageHtml.css("meta[property=og:novel:status]").css("meta", "content").toString();
			int status = 1;
			if ("连载".equals(statusStr)) {
				status = 0;
			}
			//简介
			String description = pageHtml.css("meta[property=og:description]").css("meta", "content").toString();
			//一共多少章
			List<String> chapters = pageHtml.css("div#list dl dd").all();
			int size = chapters.size();
			if (size <= 18) {
				size /= 2;
			} else {
				size -= 9;
			}

			System.out.println(title + " : " + image + " : " + id + " : " + size + " : " + status + " : " + updateDate + " : " + url + " : " + author);
			NovelInfo novelInfo = new NovelInfo();
			novelInfo.setTitle(title);
			novelInfo.setNovelkey(Long.valueOf(id));
			novelInfo.setAuthor(author);
			novelInfo.setChapters(Long.valueOf(size));
			novelInfo.setUrl(url);
			novelInfo.setDescription(description);
			novelInfo.setUpdate(updateDate);
			novelInfo.setStatus(status);

			novelInfoDao.createNovelInfo(novelInfo);
		} catch (Exception e) {
			page.setSkip(true);
			e.printStackTrace();
		}
		// Html html = page.getHtml();
		//标题
		// String title = Jsoup.parse(html.css("#info h1").toString()).text();
		//小说id
		// html.css("meta")

		//默认输出到控制台的方法
		// ResultItems pageResultItems = page.getResultItems();
		// pageResultItems.put("html", htmlStr);
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
			.setTimeOut(2000)//超时时间2s
			.setRetryTimes(1000)//重试间隔
			.setSleepTime(200);//两次间隔

	@Override
	public Site getSite() {
		return site;
	}

	private static Spider spider = Spider.create(new CrawlerBiquge())
			//设置起始url
			.thread(10);
	//.addUrl("http://www.biquge.tv/0_63352/","http://www.biquge.tv/0_63351/");

	// public static void main(String[] args) {
	// 	String url = "http://www.biquge.tv/0_";
	// 	// http://www.biquge.tv/0_66056/
	// 	for (int i = 1; i <= 2; i++) {
	// 		String realUrl = url + String.valueOf(i);
	// 		spider.addUrl(realUrl);
	// 	}
	// 	spider.start();
	// 	//启动爬虫(同步方法,当前线程中执行,完成此次进行下一次);start同时进行(新建线程)
	// 	// .run();
	// 	// .start();
	// }
}

package com.localhost.quxiaoshuo.controller;

import com.localhost.quxiaoshuo.crawler.CrawlerChapterProcessor;
import com.localhost.quxiaoshuo.domain.ChapterInfo;
import com.localhost.quxiaoshuo.domain.NovelInfo;
import com.localhost.quxiaoshuo.service.ChapterInfoService;
import com.localhost.quxiaoshuo.service.NovelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//小说章节详情处理(uri="/book/")
@Controller
public class ChapterController {

	@Autowired
	private NovelInfoService novelInfoService;
	@Autowired
	private ChapterInfoService chapterInfoService;
	//爬取工具
	@Autowired
	private CrawlerChapterProcessor crawlerChapterProcessor;

	@RequestMapping("/book/{novelkey}")
	@ResponseBody
	public Map<String,Object> bookChapter(@PathVariable("novelkey")Integer novelKey){
		Map<String,Object> map = new HashMap<>();
		//先判断该小说key是否存在,并且获取小说信息
		NovelInfo novelInfo = novelInfoService.isExistByNovelkey(novelKey);
		if (novelInfo == null) {
			map.put("msg","小说不存在");
			return map;
		}
		//如果小说存在,则判断chapter章节是否存在
		// System.out.println(novelInfo);
		if (0==novelInfo.getIsexist()){//章节不存在,需要爬虫(创建一个新线程来爬取),此线程先沉睡5s
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("爬取小说Key:"+novelInfo.getNovelkey());
					crawlerChapterProcessor.processStart(novelKey,novelInfo.getUrl());
				}
			}).start();
			try {//线程休眠3s
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//如果章节存在(则直接搜索章节,按照权重升序排序),按照NovelKey查询所有章节;
		List<ChapterInfo> chapterInfoList = chapterInfoService.searchChapterByNovelKey(novelKey);
		map.put("novelInfo",novelInfo);
		map.put("chapterInfo",chapterInfoList);
		return map;
	}
}

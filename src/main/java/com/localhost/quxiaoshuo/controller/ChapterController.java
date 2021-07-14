package com.localhost.quxiaoshuo.controller;

import com.localhost.quxiaoshuo.crawler.CompletionChapterProcessor;
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
	//爬取工具(章节详情)
	@Autowired
	private CrawlerChapterProcessor crawlerChapterProcessor;
	//爬取工具(章节内容)
	@Autowired
	private CompletionChapterProcessor completionChapterProcessor;

	//章节的详情(不包括内容)
	@RequestMapping("/book/{novelkey}")
	@ResponseBody
	public Map<String, Object> bookChapter(@PathVariable("novelkey") Integer novelKey) {
		Map<String, Object> map = new HashMap<>();
		//先判断该小说key是否存在,并且获取小说信息
		NovelInfo novelInfo = novelInfoService.isExistByNovelkey(novelKey);
		if (novelInfo == null) {
			map.put("msg", "小说不存在");
			return map;
		}
		//如果小说存在,则判断chapter章节是否存在
		// System.out.println(novelInfo);
		if (0 == novelInfo.getIsexist()) {//章节不存在,需要爬虫(创建一个新线程来爬取),此线程先沉睡5s
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("爬取小说Key:" + novelInfo.getNovelkey());
					crawlerChapterProcessor.processStart(novelKey, novelInfo.getUrl());
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
		map.put("novelInfo", novelInfo);
		map.put("chapterInfo", chapterInfoList);
		return map;
	}

	//章节详情的完善和返回(包括章节内容)
	@RequestMapping("/book/{novelkey}/{chapterweight}")
	@ResponseBody
	public Map<String, Object> bookChapterContent(@PathVariable("novelkey") Integer novelKey,
												  @PathVariable("chapterweight") Integer chapterWeight) {
		Map<String, Object> map = new HashMap<>();
		//先判断该小说key存在,并且章节简介存在
		NovelInfo novelInfo = novelInfoService.isExistByNovelkey(novelKey);
		ChapterInfo chapterInfo = chapterInfoService.selectChapterInfoByNovelAndChapter(novelKey, chapterWeight);
		if (novelInfo == null || 0 == novelInfo.getIsexist() || chapterInfo == null) {
			map.put("msg", "小说或章节不存在");
			return map;
		}
		//先把小说基本信息存入
		map.put("novelInfo", novelInfo);
		//如果章节存在为1,则通过novelkey确定数据库表,chapterweight来搜索章节具体信息
		//判断具体的章节内容是否存在,chapter的isexist,是否为1
		if (0 == chapterInfo.getIsexist()) {
			//如果不存在则来爬取信息,通过章节中的地址filepath(修改为存在,通过novelkey确定数据库表,chapterweight)
			completionChapterProcessor.processStart(novelInfo.getTitle(),chapterInfo.getTitle(),chapterInfo.getFilepath());
			//更新chapterInfo
			chapterInfo = chapterInfoService.selectChapterInfoByNovelAndChapter(novelKey, chapterWeight);
		}
		//如果存在则直接返回内容数据
		map.put("chapterInfo", chapterInfo);
		return map;
	}

}

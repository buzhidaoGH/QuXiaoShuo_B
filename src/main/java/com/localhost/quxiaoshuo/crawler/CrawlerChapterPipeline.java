package com.localhost.quxiaoshuo.crawler;

import com.localhost.quxiaoshuo.domain.ChapterInfo;
import com.localhost.quxiaoshuo.service.ChapterInfoService;
import com.localhost.quxiaoshuo.service.NovelInfoService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
public class CrawlerChapterPipeline implements Pipeline {

	@Autowired
	private ChapterInfoService chapterInfoService;
	@Autowired
	private NovelInfoService novelInfoService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		// 获取封装好的章节详情
		ChapterInfo chapterInfo = new ChapterInfo();
		System.out.println("章节基本信息爬取进来了");
		Integer novelKey = Integer.parseInt(resultItems.get("novelKey"));
		List<Selectable> chapters = resultItems.get("chapters");
		int size = chapters.size();
		if (chapters == null) {
			size = 0;
		}
		int ibg;
		if (size <= 18) {
			size /= 2;
			ibg = size;
		} else {
			size -= 9;
			ibg = 9;
		}
		//数据库是否存在,不存在则创建;
		Boolean isExitTable = chapterInfoService.createChapterTable(novelKey);
		//将小说详解改为存在(爬取之前就要将小说设置为存在/因为如果连续查询,会导致下载多遍该小说的章节)
		novelInfoService.isExitChapterByNovelKey(novelKey);
		//System.out.println(chapters);
		for (int i = ibg; i < chapters.size(); i++) {
			String chapterLink = chapters.get(i).links().toString();
			String chapterTitle = Jsoup.parse(chapters.get(i).toString()).select("a").text();
			chapterInfo.setTitle(chapterTitle);
			chapterInfo.setNovelkey(novelKey);
			chapterInfo.setFilepath(chapterLink);
			String[] split1 = chapterLink.split("/");
			Integer weight = Integer.parseInt(split1[split1.length - 1].split("\\.")[0]);
			// Integer weight = Integer.parseInt(split[split.length - 1].split(".")[0]);
			chapterInfo.setWeight(weight);
			// System.out.println(chapterInfo);
			//保存入数据库内
			if (isExitTable) chapterInfoService.saveChapterInfo(chapterInfo);
		}
	}
}

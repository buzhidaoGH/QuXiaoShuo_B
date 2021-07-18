package com.localhost.quxiaoshuo.crawler;

import com.localhost.quxiaoshuo.service.ChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
@Scope("prototype")
public class CompletionChapterPipeline implements Pipeline {

	@Autowired
	private ChapterInfoService chapterInfoService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		// 获取封装好的章节内容
		String content = resultItems.get("content");
		Integer words = resultItems.get("words");
		Integer novel = resultItems.get("novel");
		Integer weight = resultItems.get("weight");
		// 通过novel和weight 更新完善补全该章的内容和字数,并且更新isexist为1
		chapterInfoService.completionChapterInfo(novel,weight,content,words);
	}
}

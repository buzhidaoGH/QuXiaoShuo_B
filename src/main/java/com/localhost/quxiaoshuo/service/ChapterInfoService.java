package com.localhost.quxiaoshuo.service;

import com.localhost.quxiaoshuo.domain.ChapterInfo;

import java.util.List;

public interface ChapterInfoService {

	//按照小说novelKey查询小说的章节
	List<ChapterInfo> searchChapterByNovelKey(Integer novelKey);

	//数据库是否存在,不存在则创建
	Boolean createChapterTable(Integer novelKey);

	//将章节存入数据库中
	void saveChapterInfo(ChapterInfo chapterInfo);
}

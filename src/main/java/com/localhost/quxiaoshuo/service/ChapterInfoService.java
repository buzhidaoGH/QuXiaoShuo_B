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

	//小说码确认数据库,章节码确认文章码
	ChapterInfo selectChapterInfoByNovelAndChapter(Integer novelKey, Integer chapterWeight);

	// 通过novel和weight 更新完善补全该章的内容和字数,并且更新isexist为1
	void completionChapterInfo(Integer novel, Integer weight, String content, Integer words);
}

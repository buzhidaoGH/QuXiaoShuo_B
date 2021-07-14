package com.localhost.quxiaoshuo.service.impl;

import com.localhost.quxiaoshuo.dao.ChapterInfoDao;
import com.localhost.quxiaoshuo.domain.ChapterInfo;
import com.localhost.quxiaoshuo.service.ChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterInfoServiceImpl implements ChapterInfoService {

	@Autowired
	private ChapterInfoDao chapterInfoDao;

	//按照NovelKey查询该小说的所有章节
	@Override
	public List<ChapterInfo> searchChapterByNovelKey(Integer novelKey) {
		//按照小说的NovelKey确定数据库,和NovelKey小说
		String chapterNumber = "chapter" + novelKey / 1000;
		return chapterInfoDao.searchChapterByNovelKey(chapterNumber,novelKey);
	}

	//数据库是否存在,不存在则创建
	@Override
	public Boolean createChapterTable(Integer novelKey) {
		String chapterNumber = "chapter" + novelKey / 1000;
		try {
			chapterInfoDao.createChapterTable(chapterNumber);
		}catch (Exception e){
			System.out.println("创建数据库出现问题");
			return false;
		}
		return true;
	}

	//将章节存入数据库中
	@Override
	public void saveChapterInfo(ChapterInfo chapterInfo) {
		String chapterNumber = "chapter" + chapterInfo.getNovelkey() / 1000;
		chapterInfoDao.saveChapterInfo(chapterNumber,chapterInfo);
	}
}

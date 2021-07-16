package com.localhost.quxiaoshuo.service;

import com.localhost.quxiaoshuo.domain.NovelInfo;

import java.util.List;

public interface NovelInfoService {
	//新的小说基本信息
	public void createNovelInfo(NovelInfo novelInfo);

	//收藏排行
	List<NovelInfo> collectionRanking();

	//更新排行
	List<NovelInfo> latestUpdatesRanking();

	//点击排行
	List<NovelInfo> hitsRanking();

	//玄幻小说
	List<NovelInfo> xuanhuanXiaoShuo(Integer page);

	List<NovelInfo> xiuzhenXiaoShuo(Integer page);

	List<NovelInfo> dushiXiaoShuo(Integer page);

	List<NovelInfo> chuanyueXiaoShuo(Integer page);

	List<NovelInfo> wangyouXiaoShuo(Integer page);

	List<NovelInfo> kehuanXiaoShuo(Integer page);

	List<NovelInfo> qitaXiaoShuo(Integer page);

	List<NovelInfo> searchNovelByTitle(String title);

	List<String> searchTipsByTitle(String title);

	NovelInfo isExistByNovelkey(Integer novelKey);

	//修改小说章节存在状态,为存在
	void isExitChapterByNovelKey(Integer novelKey);

	//按照日期来随机推荐小说
	List<NovelInfo> randomRankings(Integer day, Integer page);
}

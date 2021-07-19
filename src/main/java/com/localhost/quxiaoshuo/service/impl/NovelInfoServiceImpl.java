package com.localhost.quxiaoshuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.localhost.quxiaoshuo.dao.NovelInfoDao;
import com.localhost.quxiaoshuo.domain.NovelInfo;
import com.localhost.quxiaoshuo.service.NovelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NovelInfoServiceImpl implements NovelInfoService {
	@Autowired
	private NovelInfoDao novelInfoDao;
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	//插入新的NovelInfo,小说基本信息
	@Override
	public void createNovelInfo(NovelInfo novelInfo) {
		novelInfoDao.createNovelInfo(novelInfo);
	}

	//查找小说收藏排行
	@Override
	public List<NovelInfo> collectionRanking() {
		return novelInfoDao.collectionRanking();
	}

	@Override
	public List<NovelInfo> latestUpdatesRanking() {
		return novelInfoDao.latestUpdatesRanking();
	}

	@Override
	public List<NovelInfo> hitsRanking() {
		return novelInfoDao.hitsRanking();
	}

	@Override
	public List<NovelInfo> xuanhuanXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.xuanhuanXiaoShuo();
	}

	@Override
	public List<NovelInfo> xiuzhenXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.xiuzhenXiaoShuo();
	}

	@Override
	public List<NovelInfo> dushiXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.dushiXiaoShuo();
	}

	@Override
	public List<NovelInfo> chuanyueXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.chuanyueXiaoShuo();
	}

	@Override
	public List<NovelInfo> wangyouXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.wangyouXiaoShuo();
	}

	@Override
	public List<NovelInfo> kehuanXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.kehuanXiaoShuo();
	}

	@Override
	public List<NovelInfo> qitaXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page, 20);
		return novelInfoDao.qitaXiaoShuo();
	}

	//小说名称搜索结果
	@Override
	public List<NovelInfo> searchNovelByTitle(String title) {
		return novelInfoDao.searchNovelByTitle(title);
	}

	//搜索栏小说标题
	@Override
	public List<String> searchTipsByTitle(String title) {
		try {
			Set<String> titles = redisTemplate.keys('*' + title + '*');
			if (6 >= titles.size()) {
				System.out.println("开启新线程来往redis中存标题");
				new Thread(new Runnable() {
					@Override
					public void run() {
						List<String> listTitle = novelInfoDao.searchTipsByTitle(title);
						for (String listItem : listTitle) {
							redisTemplate.opsForValue().set(listItem, '0');
						}
						redisTemplate.slaveOfNoOne();
					}
				}).start();
			}
			if (0 == titles.size()) {
				System.out.println("redis搜索为空-返回数据库的数据");
				return novelInfoDao.searchTipsByTitle(title);
			}
			System.out.println("返回的redis数据");
			return new ArrayList<>(titles);
		}catch (Exception e){
			System.out.println("redis未开启");
		}
		return novelInfoDao.searchTipsByTitle(title);
	}

	//通过NovelKey查询小说,并且判断小说信息是否存在,获取小说基本信息
	@Override
	public NovelInfo isExistByNovelkey(Integer novelKey) {
		return novelInfoDao.isExistByNovelkey(novelKey);
	}

	//修改小说章节的存在状态为存在
	@Override
	public void isExitChapterByNovelKey(Integer novelKey) {
		novelInfoDao.isExitChapterByNovelKey(novelKey);
	}

	@Override
	public List<NovelInfo> randomRankings(Integer day, Integer page) {
		//分页
		PageHelper.startPage(page, 10);
		List<NovelInfo> novelInfoList = novelInfoDao.randomRankings(day);
		return novelInfoList;
	}

	@Override
	public List<Integer> xiaoshuoCategory() {
		List<Integer> category = new ArrayList<>();
		category.add(novelInfoDao.totalXuanhuan("玄幻"));
		category.add(novelInfoDao.totalXuanhuan("修真"));
		category.add(novelInfoDao.totalXuanhuan("都市"));
		category.add(novelInfoDao.totalXuanhuan("穿越"));
		category.add(novelInfoDao.totalXuanhuan("网游"));
		category.add(novelInfoDao.totalXuanhuan("科幻"));
		category.add(novelInfoDao.totalXuanhuan("其他"));
		return category;
	}

}

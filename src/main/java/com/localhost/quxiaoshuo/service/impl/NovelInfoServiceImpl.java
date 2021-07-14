package com.localhost.quxiaoshuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.localhost.quxiaoshuo.dao.NovelInfoDao;
import com.localhost.quxiaoshuo.domain.NovelInfo;
import com.localhost.quxiaoshuo.service.NovelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelInfoServiceImpl implements NovelInfoService {
	@Autowired
	private NovelInfoDao novelInfoDao;

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
		PageHelper.startPage(page,20);
		return novelInfoDao.xuanhuanXiaoShuo();
	}

	@Override
	public List<NovelInfo> xiuzhenXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page,20);
		return novelInfoDao.xiuzhenXiaoShuo();
	}

	@Override
	public List<NovelInfo> dushiXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page,20);
		return novelInfoDao.dushiXiaoShuo();
	}

	@Override
	public List<NovelInfo> chuanyueXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page,20);
		return novelInfoDao.chuanyueXiaoShuo();
	}

	@Override
	public List<NovelInfo> wangyouXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page,20);
		return novelInfoDao.wangyouXiaoShuo();
	}

	@Override
	public List<NovelInfo> kehuanXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page,20);
		return novelInfoDao.kehuanXiaoShuo();
	}

	@Override
	public List<NovelInfo> qitaXiaoShuo(Integer page) {
		//分页
		PageHelper.startPage(page,20);
		return novelInfoDao.qitaXiaoShuo();
	}

	@Override
	public List<NovelInfo> searchNovelByTitle(String title) {
		return novelInfoDao.searchNovelByTitle(title);
	}

	@Override
	public List<String> searchTipsByTitle(String title) {
		return novelInfoDao.searchTipsByTitle(title);
	}

	@Override
	public NovelInfo isExistByNovelkey(Integer novelKey) {
		return novelInfoDao.isExistByNovelkey(novelKey);
	}

}

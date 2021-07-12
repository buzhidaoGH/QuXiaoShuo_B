package com.localhost.quxiaoshuo.service.impl;

import com.localhost.quxiaoshuo.dao.NovelInfoDao;
import com.localhost.quxiaoshuo.domain.NovelInfo;
import com.localhost.quxiaoshuo.service.NovelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovelInfoServiceImpl implements NovelInfoService {
	@Autowired
	private NovelInfoDao novelInfoDao;

	//插入新的NovelInfo,小说基本信息
	@Override
	public void createNovelInfo(NovelInfo novelInfo) {
		novelInfoDao.createNovelInfo(novelInfo);
	}

}

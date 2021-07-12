package com.localhost.quxiaoshuo.dao;

import com.localhost.quxiaoshuo.domain.NovelInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//NovelInfo的Dao层
@Repository
public interface NovelInfoDao {
	//插入新的NovelInfo
	@Insert("INSERT INTO `quxiaoshuo`.`novelinfo` (`title`,`novelkey`,`author`,`image`,`chapters`,`url`,`description`,`update`,`status`) VALUES (#{novelInfo.title},#{novelInfo.novelkey},#{novelInfo.author},#{novelInfo.image},#{novelInfo.chapters},#{novelInfo.url},#{novelInfo.description},#{novelInfo.update},#{novelInfo.status})")
	public void createNovelInfo(@Param("novelInfo")NovelInfo novelInfo);

}

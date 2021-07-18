package com.localhost.quxiaoshuo.dao;

import com.localhost.quxiaoshuo.domain.ChapterInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//ChapterInfo的Dao层
@Repository
public interface ChapterInfoDao {
	//按照NovelKey查询该小说的所有章节
	@Select("SELECT title,novelkey,weight FROM ${chapterNumber} WHERE novelkey = #{novelKey} ORDER BY weight")
	List<ChapterInfo> searchChapterByNovelKey(@Param("chapterNumber") String chapterNumber, @Param("novelKey") Integer novelKey);

	@Update("CREATE TABLE IF NOT EXISTS `${chapterNumber}` (" +
			"  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id'," +
			"  `title` VARCHAR(256) DEFAULT NULL COMMENT '小说章节名'," +
			"  `novelkey` INT(11) DEFAULT NULL COMMENT '所属小说id/key'," +
			"  `weight` INT(11) DEFAULT NULL COMMENT '章节权重(小说章节排序)'," +
			"  `filepath` VARCHAR(1024) DEFAULT NULL COMMENT '章节路径'," +
			"  `words` INT(11) DEFAULT NULL COMMENT '章节字数'," +
			"  `content` TEXT COMMENT '章节内容'," +
			"  `isexist` INT(11) DEFAULT '0' COMMENT '该章节的内容是否存在'," +
			"  PRIMARY KEY (`id`)," +
			"  UNIQUE KEY `weight` (`weight`)" +
			") ENGINE=INNODB DEFAULT CHARSET=utf8")
	Boolean createChapterTable(@Param("chapterNumber") String chapterNumber);

	@Insert("INSERT INTO `${chapterNumber}` (title,novelkey,weight,filepath) VALUES (#{chapterInfo.title},#{chapterInfo.novelkey},#{chapterInfo.weight},#{chapterInfo.filepath})")
	void saveChapterInfo(@Param("chapterNumber") String chapterNumber, @Param("chapterInfo") ChapterInfo chapterInfo);

	@Select("SELECT title,filepath,isexist,weight,novelkey,words,content FROM ${chapterNumber} WHERE weight = #{chapterWeight}")
	ChapterInfo selectChapterInfoByNovelAndChapter(@Param("chapterNumber") String chapterNumber, @Param("chapterWeight") Integer chapterWeight);

	// 通过novel和weight 更新完善补全该章的内容和字数,并且更新isexist为1
	@Update("UPDATE `${chapterNumber}` SET content = #{content} ,words = #{words} ,isexist = 1 WHERE weight = #{weight};")
	void completionChapterInfo(@Param("chapterNumber") String chapterNumber,
							   @Param("weight") Integer weight,
							   @Param("content") String content,
							   @Param("words") Integer words);
}

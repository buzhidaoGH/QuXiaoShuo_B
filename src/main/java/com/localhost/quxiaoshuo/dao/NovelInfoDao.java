package com.localhost.quxiaoshuo.dao;

import com.localhost.quxiaoshuo.domain.NovelInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//NovelInfo的Dao层
@Repository
public interface NovelInfoDao {
	//插入新的NovelInfo
	@Insert("INSERT INTO `quxiaoshuo`.`novelinfo` (`title`,`novelkey`,`author`,`image`,`chapters`,`url`,`description`,`update`,`status`) VALUES (#{novelInfo.title},#{novelInfo.novelkey},#{novelInfo.author},#{novelInfo.image},#{novelInfo.chapters},#{novelInfo.url},#{novelInfo.description},#{novelInfo.update},#{novelInfo.status})")
	public void createNovelInfo(@Param("novelInfo")NovelInfo novelInfo);

	//查找小说收藏排行前6
	@Select("SELECT title,novelkey,author,image,url,category,`update` FROM novelinfo ORDER BY weight DESC LIMIT 0,6;")
	List<NovelInfo> collectionRanking();

	//查找小说最新更新前6
	@Select("SELECT title,novelkey,author,image,url,category,`update` FROM novelinfo ORDER BY `update` DESC LIMIT 0,6;")
	List<NovelInfo> latestUpdatesRanking();

	//查找小说最热点击前六
	@Select("SELECT title,novelkey,author,image,url,category,`update` FROM novelinfo ORDER BY hits DESC LIMIT 0,6;")
	List<NovelInfo> hitsRanking();

	//玄幻小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%玄幻%'")
	List<NovelInfo> xuanhuanXiaoShuo();
	//修真小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%修真%'")
	List<NovelInfo> xiuzhenXiaoShuo();
	//都市小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%都市%'")
	List<NovelInfo> dushiXiaoShuo();
	//穿越小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%穿越%'")
	List<NovelInfo> chuanyueXiaoShuo();
	//网游小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%网游%'")
	List<NovelInfo> wangyouXiaoShuo();
	//科幻小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%科幻%'")
	List<NovelInfo> kehuanXiaoShuo();
	//其他小说
	@Select("SELECT title,novelkey,author,image,url,description,category FROM novelinfo WHERE category LIKE '%其他%'")
	List<NovelInfo> qitaXiaoShuo();

	//按照书名模糊查找小说信息
	@Select("SELECT title,novelkey,author,image,description,category FROM novelinfo WHERE title LIKE '%${title}%'")
	List<NovelInfo> searchNovelByTitle(@Param("title") String title);

	//按照标题书名模糊查找(热度最高的前十)
	@Select("SELECT title FROM novelinfo WHERE title LIKE '%${title}%' ORDER BY hits DESC LIMIT 0,10")
	List<String> searchTipsByTitle(@Param("title")String title);

	//按照novelKey来获取小说基本信息,和小说章节是否存在信息
	@Select("SELECT title,novelkey,`update`,chapters,author,image,url,description,category,isexist FROM novelinfo WHERE novelkey = #{novelKey}")
	NovelInfo isExistByNovelkey(@Param("novelKey") Integer novelKey);

	//修改小说的章节存在状态为存在
	@Update("UPDATE `novelinfo` SET isexist = '1' WHERE novelkey = #{novelKey}")
	void isExitChapterByNovelKey(@Param("novelKey") Integer novelKey);

	//按照日期随机推荐小说
	@Select("SELECT title,novelkey,author,image,url,category,`update` FROM novelinfo ORDER BY RAND(${day}) ")
	List<NovelInfo> randomRankings(@Param("day") Integer day);

	//小说数量
	@Select("SELECT COUNT(*) FROM novelinfo WHERE category LIKE '%${novel}%'")
	Integer totalXuanhuan(@Param("novel") String novel);
}

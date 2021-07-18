package com.localhost.quxiaoshuo.controller;

import com.github.pagehelper.PageInfo;
import com.localhost.quxiaoshuo.domain.NovelInfo;
import com.localhost.quxiaoshuo.service.NovelInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = {"http://127.0.0.1/","http://localhost/"})
public class NovelController {

	@Autowired
	NovelInfoService novelInfoService;

	@RequestMapping("/")
	public String indexMethod() {
		return "forward:/index";
	}

	@RequestMapping("/index")
	@ResponseBody
	public Map<String, List<NovelInfo>> quxaioshuoIndex() {
		//System.out.println("首页内容");
		Map<String, List<NovelInfo>> indexContent = new HashMap<>();
		//首页推荐(权重/收藏排行)
		List<NovelInfo> collects = novelInfoService.collectionRanking();
		indexContent.put("collects", collects);
		//热门点击(点击量排行)
		List<NovelInfo> hits = novelInfoService.hitsRanking();
		indexContent.put("hits", hits);
		//最近更新推荐(时间)
		List<NovelInfo> latestUpdates = novelInfoService.latestUpdatesRanking();
		indexContent.put("latestUpdates", latestUpdates);
		return indexContent;
	}

	@RequestMapping("/randomnovel")//支持分页查询
	@ResponseBody
	public Map<String,PageInfo> randomNovel(@RequestParam(value = "page", required = false) Integer page){
		if (page == null || page == 0) {
			page = 1;
		}
		Map<String, PageInfo> randomRanking = new HashMap<>();
		Integer day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		//随机推荐
		List<NovelInfo> novelInfoList = novelInfoService.randomRankings(day, page);
		PageInfo pageInfo = new PageInfo(novelInfoList);
		randomRanking.put("random", pageInfo);
		return randomRanking;
	}

	//小说分类本数
	@RequestMapping("/xiaoshuocategory")
	@ResponseBody
	public List<Integer> xiaoshuoCategory(){
		return novelInfoService.xiaoshuoCategory();
	}

	//小说类型分类(支持分页)
	@RequestMapping({"/xiaoshuo/{category}/{page}", "/xiaoshuo/{category}"})
	@ResponseBody
	public PageInfo categoryXiaoShuo(@PathVariable(value = "page", required = false) Integer page,
									 @PathVariable(value = "category") String category) {
		//System.out.println(page);
		if (page == null || page == 0) {
			page = 1;
		}
		//玄幻
		if (category.equals("xuanhuan")) return new PageInfo(novelInfoService.xuanhuanXiaoShuo(page));
		//修真
		if (category.equals("xiuzhen")) return new PageInfo(novelInfoService.xiuzhenXiaoShuo(page));
		//都市
		if (category.equals("dushi")) return new PageInfo(novelInfoService.dushiXiaoShuo(page));
		//穿越
		if (category.equals("chuanyue")) return new PageInfo(novelInfoService.chuanyueXiaoShuo(page));
		//网游
		if (category.equals("wangyou")) return new PageInfo(novelInfoService.wangyouXiaoShuo(page));
		//科幻
		if (category.equals("kehuan")) return new PageInfo(novelInfoService.kehuanXiaoShuo(page));
		//其他
		if (category.equals("qita")) return new PageInfo(novelInfoService.qitaXiaoShuo(page));
		//玄幻
		return new PageInfo(novelInfoService.xuanhuanXiaoShuo(page));
	}

	//小说搜索标题(提示)
	@RequestMapping("/searchTips")
	@ResponseBody
	public Map<String, Object> searchTips(@RequestParam("title") String title) {
		Map<String, Object> titles = new HashMap();
		List<String> titleList = novelInfoService.searchTipsByTitle(title);
		titles.put("titleList",titleList);
		titles.put("size",titleList.size());
		return titles;
	}

	//小说书名搜索(模糊分页)
	@RequestMapping("/search")
	@ResponseBody
	public PageInfo searchNovel(@RequestParam("title") String title) {
		return new PageInfo(novelInfoService.searchNovelByTitle(title));
	}
}

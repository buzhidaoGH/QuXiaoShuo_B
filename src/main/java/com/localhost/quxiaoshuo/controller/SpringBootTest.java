package com.localhost.quxiaoshuo.controller;

import com.localhost.quxiaoshuo.domain.NovelInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = {"http://127.0.0.1/","http://localhost/"})
public class SpringBootTest {

	//测试整合springMVC springboot
	@RequestMapping("/test")
	@ResponseBody //返回json
	public String backTest(){
		System.out.println("进来了1");
		return "返回test测试springboot成功";
	}

	//测试整合springMVC springboot
	@RequestMapping("/test2")
	@ResponseBody //返回json
	public List<String> backTest2(){
		System.out.println("进来了2");
		List<String> strings = new ArrayList<String>();
		strings.add("21321");
		strings.add("你好啊");
		return strings;
	}

	//测试整合springMVC springboot
	@RequestMapping("/test3")
	@ResponseBody //返回json
	public Map<String,String> backTest3(){
		System.out.println("进来了3");
		Map<String,String> maps = new HashMap<>();
		maps.put("21321","321321");
		maps.put("你好啊","是么");
		return maps;
	}

	//测试整合springMVC springboot
	@RequestMapping("/test4")
	@ResponseBody //返回json
	public List<NovelInfo> backTest4(){
		System.out.println("进来了4");
		NovelInfo novelInfo = new NovelInfo();
		novelInfo.setId(1);
		novelInfo.setImage("321321321321");
		List<NovelInfo> novelInfoList = new ArrayList<>();
		novelInfoList.add(novelInfo);
		novelInfo.setId(2);
		novelInfo.setImage("wawwqewqewq");
		novelInfoList.add(novelInfo);
		return novelInfoList;
	}

	//测试整合springMVC springboot
	@RequestMapping("/test5")
	@ResponseBody //返回json
	public NovelInfo backTest5(){
		System.out.println("进来了5");
		NovelInfo novelInfo = new NovelInfo();
		novelInfo.setId(1);
		novelInfo.setImage("321321321321");
		return novelInfo;
	}
}

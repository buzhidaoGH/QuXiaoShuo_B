package com.localhost.quxiaoshuo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringBootTest {

	//测试整合springMVC springboot
	@RequestMapping("/test")
	@ResponseBody //返回json
	public String backTest(){
		System.out.println("进来了");
		return "返回test测试springboot成功";
	}
}

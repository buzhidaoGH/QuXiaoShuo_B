package com.localhost.quxiaoshuo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class QuxiaoshuoApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		//redisTemplate的用法 opsForValue 操作字符串,类似String
		//redisTemplate的用法 opsForList 操作List,类似List
		
	}

}

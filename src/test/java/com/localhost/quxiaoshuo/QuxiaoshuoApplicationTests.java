package com.localhost.quxiaoshuo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class QuxiaoshuoApplicationTests {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		//redisTemplate的用法 opsForValue 操作字符串,类似String
		//redisTemplate的用法 opsForList 操作List,类似List
		// redisTemplate.opsForZSet().add()
		// RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		// connection.close();
		redisTemplate.opsForValue().set("mykey","liwang1");
		System.out.println(redisTemplate.opsForValue().get("mykey"));
	}

	@Test
	void test2() throws JsonProcessingException{
		List<String> listString = new ArrayList<>();
		listString.add("21321");
		listString.add("1211");
		listString.add("我的");
		String string = new ObjectMapper().writeValueAsString(listString);
		redisTemplate.opsForValue().set("list",string);
		System.out.println(redisTemplate.opsForValue().get("list"));
	}
	@Test
	void test3(){
		redisTemplate.opsForValue().set("我的",'0');
		redisTemplate.opsForValue().set("哈哈我的",'0');
		Set<String> keys = redisTemplate.keys("*我*");
		System.out.println(keys);
	}

	public Set<String> keys(String keyPrefix) {
		return null;
	}

}

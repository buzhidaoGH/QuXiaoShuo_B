package com.localhost.quxiaoshuo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	//编写自己的RedisTemplate
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		//配置具体的序列化方式
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//String的序列化StringRedisSerializer
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		//key采用StringRedisSerializer
		template.setKeySerializer(stringRedisSerializer);
		//hash的key
		template.setHashKeySerializer(stringRedisSerializer);
		//value采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		//hash的value采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		//list的value采用jackson
		// template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}
}

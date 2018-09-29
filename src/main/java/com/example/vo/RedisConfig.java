package com.example.vo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.model.UserDetails;

@Configuration
public class RedisConfig {

	@Bean
	public LettuceConnectionFactory  redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("localhost");
		configuration.setPort(6379);
		return new LettuceConnectionFactory(configuration);
	}

	@Bean
	public RedisTemplate<String, UserDetails> redisTemplate() {
		RedisTemplate<String, UserDetails> redisTemplate = new RedisTemplate<String, UserDetails>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager rcm = RedisCacheManager.create(redisConnectionFactory());
		rcm.setTransactionAware(true);
		return rcm;
	}
}

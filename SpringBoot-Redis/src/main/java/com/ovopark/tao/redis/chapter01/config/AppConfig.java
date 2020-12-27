package com.ovopark.tao.redis.chapter01.config;

import com.ovopark.tao.redis.chapter01.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
public class AppConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(@Autowired LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean
    public RedisTemplate<String, Users> redisUserTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Users> stringUserRedisTemplate = new RedisTemplate<>();
        stringUserRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringUserRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        stringUserRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringUserRedisTemplate;
    }
}

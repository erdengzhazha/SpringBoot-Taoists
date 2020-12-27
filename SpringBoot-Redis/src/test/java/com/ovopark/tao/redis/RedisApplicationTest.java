package com.ovopark.tao.redis;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

@Slf4j
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTest {

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Test
    void t(){
        RedisConnection connection = lettuceConnectionFactory.getConnection();
        System.out.println(connection);
        connection.set("name".getBytes(), "cc".getBytes());
    }

    @Test
    void redisTemplate(){
        redisTemplate.opsForValue().set("name",111);
        redisTemplate.opsForValue().set("namecc",new StringBuffer("a"));
        Serializable name = redisTemplate.opsForValue().get("name");
        log.info("name : {}" , name);
        redisTemplate.opsForValue().set("namebb","ddd",5);
    }
}

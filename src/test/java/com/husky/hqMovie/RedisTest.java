package com.husky.hqMovie;

import com.husky.hqMovie.service.impl.RedisServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {
    @Autowired
    RedisServiceImpl redis;
    @Test
    void testRedisSet(){
        redis.setValue("name","段琪");
    }
    @Test
    void testRedisGet(){
        System.out.println(redis.getValue("name"));
    }
}

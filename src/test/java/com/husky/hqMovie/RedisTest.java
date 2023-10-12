package com.husky.hqMovie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void testRedisSet(){
        Map map=redisTemplate.opsForHash().entries("Husky_hasTicket");

        System.out.println(map);
    }
    @Test
    void testClearAll(){
        redisTemplate.delete("段琪_hasTicket");
    }
    @Test
    void testGetNullHash(){
        Map 霖霖_hasTicket = redisTemplate.opsForHash().entries("Husky_hasTicket");
        System.out.println(霖霖_hasTicket);
    }
    @Test
    void testSetNullHash(){
        Map map=new HashMap<>();
        redisTemplate.opsForHash().put("Husky_hasTicket","Husky_hasTicket",1);
    }
    @Test
    void testDeleteKey()
    {
        redisTemplate.delete("霖霖_hasTicket");
    }
    @Test
    void testModifyNull(){
        redisTemplate.rename("霖霖_hasT","霖霖_has");
    }

}

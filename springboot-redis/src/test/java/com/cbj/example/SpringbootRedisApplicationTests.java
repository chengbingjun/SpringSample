package com.cbj.example;

import com.cbj.entity.User;
import com.cbj.util.RedisUtil;
import com.cbj.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("key", "你好redis");
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

    @Test
    public void test01() {
        User user = new User("张三", "15");
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    public void test02() {
       redisUtil.addToListRight
               ("listKey", Status.ExpireEnum.UNREAD_MSG,"A","B","C");
    }

}

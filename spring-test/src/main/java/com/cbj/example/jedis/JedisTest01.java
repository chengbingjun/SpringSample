package com.cbj.example.jedis;

import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @Author cbjun
 * @create 2020/8/11 14:43
 */
public class JedisTest01 {
    public static void main(String[] args) {
        //创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //测试连接是否成功
        System.out.println(jedis.ping());
        //jedis部分命令操作
        jedis.select(0);
        jedis.flushDB();
        //string
        jedis.set("username","hanhong");
        System.out.println(jedis.get("username"));
        //list
        jedis.lpush("user","user1","user2");
        System.out.println(jedis.lpop("user"));
        //set
        jedis.sadd("userSet","user1","user2");
        System.out.println(jedis.spop("userSet"));
        //Zset
        jedis.zadd("userZset",2,"user2");
        jedis.zadd("userZset",1,"user1");
        System.out.println(jedis.zrange("userZset",0,1000));
        System.out.println(jedis.zrevrange("userZset",0,10000));
        //hash
        Map<String,String> map = Maps.newHashMap();
        map.put("name","zhangsan");
        map.put("age","13");
        jedis.hmset("userHash",map);
        System.out.println(jedis.hget("userHash","name"));
        System.out.println(jedis.hgetAll("userHash"));
        System.out.println(jedis.hmget("userHash","name","age"));

        jedis.flushDB();

        //关闭连接
        jedis.close();
    }
}

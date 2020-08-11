package com.example.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @Author cbjun
 * @create 2020/8/11 15:15
 */
public class JedisTest02 {
    public static void main(String[] args) {
        //创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //测试连接是否成功
        System.out.println(jedis.ping());
        jedis.flushDB();
        //开启redis事务
        Transaction multi = jedis.multi();
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username","lisi");
            jsonObject.put("age","15");
            String s = jsonObject.toJSONString();
            multi.set("user1",s);
            multi.set("user2",s);
            int i = 1/0;
            multi.exec();
        }catch (Exception e){
            //如果出现异常则取消事务
            multi.discard();
            System.out.println(e.getMessage());
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
    }
}

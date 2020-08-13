package com.cbj.example.rabbitMQ.topic;

import com.cbj.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/24 15:39
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("topics","topic");
        //设置路由key
        String key = "user.save.right";
        //发布消息
        channel.basicPublish("topics",key,null,"topic类型的消息".getBytes());
        //关闭资源
        RabbitMQUtils.close(connection,channel);
    }
}

package com.example.rabbitMQ.fanout;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/22 15:09
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明并绑定交换机 参数一：交换机名称  参数二：交换机类型
        channel.exchangeDeclare("logs","fanout");
        //发送消息
        channel.basicPublish("logs","",null,"hello fanout message".getBytes());
        //关闭连接
        RabbitMQUtils.close(connection,channel);


    }

}

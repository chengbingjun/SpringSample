package com.example.rabbitMQ.workQueue;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/20 10:23
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare("work",false,false,false,null);
        for(int i=0;i<10;i++){
            //发布消息
            channel.basicPublish("","work",null,("work"+i).getBytes());
        }
        //关闭连接
        RabbitMQUtils.close(connection,channel);
    }
}

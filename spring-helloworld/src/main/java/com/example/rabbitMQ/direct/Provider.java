package com.example.rabbitMQ.direct;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/23 15:25
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs-direct","direct");
        //声明路由key
        String key = "warning";
        //发布消息，并制定路由
        channel.basicPublish("logs-direct",key,null,("这是一个"+key+"级别的日志").getBytes());
        //关闭资源
        RabbitMQUtils.close(connection,channel);
    }

}

package com.cbj.example.rabbitMQ.helloWord;

import com.cbj.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cbjun
 * @create 2020/3/31 15:55
 */
public class Provider {
    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //获取mq连接对象
        Connection connection = RabbitMQUtils.createConnection();
        //获取连接通道
        Channel channel = connection.createChannel();
        //连接通道绑定队列
        //参数1：队列名称，若虚拟主机中无该队列则会自动创建
        //参数2：队列是否要持久化
        //参数3：队列是否要独占
        //参数4：队列是否要在消费后自动删除
        //参数5：额外的参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1：交换机名称；参数2：队列名称；参数3：传递消息的设置；参数4：消息内容（byte数组类型）
        channel.basicPublish("","hello",null,"hello rabbitMQ".getBytes());
        RabbitMQUtils.close(connection,channel);
    }
}

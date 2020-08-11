package com.example.rabbitMQ.workQueue;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/20 10:30
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare("work",false,false,false,null);
        //保证通道里面同时只能有一条消息存在
        channel.basicQos(1);
        //消费消息 参数二：开启消息自动确认机制可能会导致消息的丢失
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1"+new String(body));
                //手动确认消息，参数一：消息的标志，表示具体确认哪一条消息
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });

    }
}

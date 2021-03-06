package com.cbj.example.rabbitMQ.direct;

import com.cbj.example.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/23 15:33
 */
public class Customer1 {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs-direct","direct");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //声明路由key
        String key = "error";
        //交换机绑定队列
        channel.queueBind(queue,"logs-direct",key);
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1："+new String(body));
            }
        });


    }

}

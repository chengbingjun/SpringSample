package com.example.rabbitMQ.direct;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/23 15:40
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs-direct","direct");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //声明路由key
        String key1 = "info";
        String key2 = "warning";
        String key3 = "error";
        //交换机绑定队列
        channel.queueBind(queue,"logs-direct",key1);
        channel.queueBind(queue,"logs-direct",key2);
        channel.queueBind(queue,"logs-direct",key3);
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2："+new String(body));
            }
        });

    }

}

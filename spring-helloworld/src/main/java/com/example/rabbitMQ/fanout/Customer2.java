package com.example.rabbitMQ.fanout;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/22 15:31
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.createConnection();

        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //交换机绑定队列
        channel.queueBind(queue,"logs","");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2："+new String(body));
            }
        });


    }
}

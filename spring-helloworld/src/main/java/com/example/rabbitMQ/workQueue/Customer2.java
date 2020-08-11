package com.example.rabbitMQ.workQueue;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author cbjun
 * @create 2020/4/20 10:36
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.createConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare("work",false,false,false,null);
        //通道内消息可以存在的数量
        channel.basicQos(1);
        //消费消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2" + new String(body));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //手动确认消息
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });
    }
}

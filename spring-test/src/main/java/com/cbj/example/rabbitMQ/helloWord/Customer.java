package com.cbj.example.rabbitMQ.helloWord;

import com.cbj.example.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cbjun
 * @create 2020/4/1 15:33
 */
public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //通过工具类获取mq连接对象
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
        //消费消息
        //参数1：队列名称
        //参数2：开始消息的自动确认机制
        //参数3：消费时回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息内容" + new String(body));
            }
        });

    }
}

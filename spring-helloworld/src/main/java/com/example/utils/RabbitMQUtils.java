package com.example.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cbjun
 * @create 2020/4/1 16:02
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        //创建mq连接工厂（只需要创建一次）
        connectionFactory = new ConnectionFactory();
    }
    /**
     * 获取rabbitMQ连接对象
     */
    public static Connection createConnection(){

        //获取连接
        Connection connection = null;
        try {
            //设置连接mq的主机
            connectionFactory.setHost("127.0.0.1");
            //设置端口号
            connectionFactory.setPort(5672);
            //设置连接虚拟主机
            connectionFactory.setVirtualHost("/ems");
            //设置访问虚拟主机的用户名和密码
            connectionFactory.setUsername("ems");
            connectionFactory.setPassword("ems");

            connection = connectionFactory.newConnection();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭连接和通道
    public static void close(Connection connection, Channel channel){
        try {
            if(channel != null){
                channel.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}

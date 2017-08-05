package com.xxx.config;

import com.xxx.listener.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Wyl on 2017-07-03.
 * 连接redis
 */
@Configuration
public class RedisConfig {

    //信息储存到redis数据库中的名字
    public static final String MY_DATABASE =  "my_redis";

    //数据订阅的话题名字，用来通知分发系统开始存储数据
    public static final String DATA_TOPIC ="data_topic";

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter,new PatternTopic(DATA_TOPIC));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

    @Bean
    Receiver receiver(){
        return new Receiver();
    }

    @Bean
    CountDownLatch latch(){
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}

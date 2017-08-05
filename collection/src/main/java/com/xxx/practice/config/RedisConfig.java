package com.xxx.practice.config;

/**
 * Created by Wyl on 2017-06-29.
 */
public class RedisConfig {

    //信息储存到redis数据库中的名字
    public static final String MY_DATABASE =  "my_redis";

    //数据订阅的话题名字，用来通知分发系统开始存储数据
    public static final String DATA_TOPIC ="data_topic";

    //每收集到100条数据就发送一次消息，通知分发系统处理数据
    public static final int DATA_NOTICE_FLAG = 5;

}

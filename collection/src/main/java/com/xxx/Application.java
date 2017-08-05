package com.xxx;

import com.xxx.practice.CustomServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Wyl on 2017-06-13.
 */
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        Integer port = 8080;

        //启动springBoot容器
        SpringApplication.run(Application.class, args);

        //启动netty服务
        CustomServer customServer = new CustomServer();
        if (args.length != 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                logger.error("端口格式错误");
            }
        }
        customServer.start(port);
    }

}

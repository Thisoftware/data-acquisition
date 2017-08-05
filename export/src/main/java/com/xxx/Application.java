package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Wyl on 2017-06-.13
 * 启动分发系统
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

    }

}

package com.xxx.config;

import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wyl on 2017-07-04.
 */
//@Component
public class TimeTask {

    //private static final SimpleDateFormat dataFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    //@Scheduled(cron = "0 07 20 ? * *" ) //使用cron属性可按照指定时间执行，本例指的是每天20点07分执行；
    //@Scheduled(fixedDelay = 5000)
    @Test
    public void reportCurrentTime() {

        //System.out.println("执行时间:" + dataFormat.format(new Date()));

        int code = 10;
        switch (code){
            case 0 :
                System.out.println("case0");
                break;
            case 1 :
                System.out.println("case1");
                break;
             default:
                System.out.println("没有这个数！");
                 break;
        }

    }


}

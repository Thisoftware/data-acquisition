package com.xxx.practice.service;

import com.alibaba.fastjson.JSON;
import com.xxx.practice.model.CarPcGpsData;
import com.xxx.practice.model.CustomMsg;
import com.xxx.practice.enums.MsgEnum;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.Random;

/**
 * Created by Wyl on 2017-06-28.
 * 给服务端发送消息
 */
public class SendMessage implements Runnable{

    private Channel channel;

    public SendMessage(Channel channel){
        this.channel = channel;
    }

    public void run() {

        Random random = new Random();

        while(true){
            try {
                Thread.sleep(1000);
                CustomMsg customMsg = new CustomMsg();
                customMsg.setType(MsgEnum.Type.BusinessData.code);
                CarPcGpsData gpsData = new CarPcGpsData();
                gpsData.setAccuracy("1111");
                gpsData.setBearing("11");
                gpsData.setCollectionTime(new Date());
                gpsData.setLongitude(random.nextDouble());
                gpsData.setLatitude(random.nextDouble());
                gpsData.setAltitude(random.nextDouble());
                gpsData.setProvider("模拟程序");
                gpsData.setSerialNum("1234567890");
                gpsData.setSiteName("鸟不拉屎星球");
                gpsData.setType(1);
                String msg = JSON.toJSONString(gpsData);
                customMsg.setBody(msg);
                System.out.println("发送消息:"+ customMsg);
                channel.writeAndFlush(customMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

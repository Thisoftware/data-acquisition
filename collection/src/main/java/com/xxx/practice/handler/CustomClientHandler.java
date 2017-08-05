package com.xxx.practice.handler;

import com.xxx.practice.service.SendMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Wyl on 2017-06-27.
 */
public class CustomClientHandler extends SimpleChannelInboundHandler<Object> {

    private Logger logger = LoggerFactory.getLogger(CustomClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

//        String msg = "你好，服务端！我是客户端。。";
//        CustomMsg customMsg = new CustomMsg((byte)0xAB, (byte)0xCD, msg.length(), msg);
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    ctx.writeAndFlush(customMsg);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.start();

        try {
            Channel channel = ctx.channel();
            Executor executor = Executors.newFixedThreadPool(10);
            executor.execute(new SendMessage(channel));
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
        String msg = (String) object;
        logger.info("收到服务端消息啦" + msg + ";长度是" + msg.length());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}

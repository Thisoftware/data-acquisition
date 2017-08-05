package com.xxx.practice.handler;

import com.alibaba.fastjson.JSON;
import com.xxx.practice.config.RedisConfig;
import com.xxx.practice.enums.DataEmun;
import com.xxx.practice.enums.MsgEnum;
import com.xxx.practice.model.CarPcGpsData;
import com.xxx.practice.model.CustomMsg;
import com.xxx.practice.util.SpringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

import static com.xxx.practice.constant.AttributeMapConstant.NETTY_CHANNEL_KEY;

/**
 * Created by Wyl on 2017-06-27.
 */
public class CustomServerHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(CustomServerHandler.class);

    private boolean firstData = false;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("有设备加入连接;");
        firstData = true;
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof CustomMsg) {
            CustomMsg customMsg = (CustomMsg)msg;
            //System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+customMsg.getBody());
            logger.info("收到消息：" + customMsg.getBody());
            byte type = customMsg.getType();
            //如果是业务数据则进行处理并回复,如果是心跳数据则回复空串
            if(type == MsgEnum.Type.BusinessData.code){
                businessData(ctx,customMsg.getBody());
            }else {
                heartBeatResponse(ctx);
            }
            //存储最新的行程数据，以便于判断是不是行程结束
            Attribute<String> attr = ctx.channel().attr(NETTY_CHANNEL_KEY);
            attr.set(customMsg.getBody());
        }
    }

    //心跳数据包处理
    private void heartBeatResponse(ChannelHandlerContext ctx){
        CustomMsg customMsg = new CustomMsg(MsgEnum.Type.HeartBeat.code,0,"");
        ctx.writeAndFlush(customMsg);
    }
    //处理业务数据
    private void businessData(ChannelHandlerContext ctx,String data){
        CarPcGpsData carPcGpsData = JSON.parseObject(data,CarPcGpsData.class);
        //如果是第一条数据
        if(firstData){
            carPcGpsData.setType(DataEmun.Type.Begin.code);
            firstData = false;
            data = JSON.toJSONString(carPcGpsData);
        }
        //缓存消息到redis中
        StringRedisTemplate redisTemplate = SpringUtils.getBean("stringRedisTemplate",StringRedisTemplate.class);
        ListOperations lists = redisTemplate.opsForList();

        long size = lists.size(RedisConfig.MY_DATABASE);
        int flag = RedisConfig.DATA_NOTICE_FLAG;

        lists.leftPush(RedisConfig.MY_DATABASE,data);
        CustomMsg reg = new CustomMsg(MsgEnum.Type.BusinessData.code,0,"");
        ctx.writeAndFlush(reg);
        if(size % flag == 0){
            redisTemplate.convertAndSend(RedisConfig.DATA_TOPIC,"");
        }

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        endTheData(channel);
        cause.printStackTrace();
        ctx.close();
    }

    //对最后一条数据进行处理
    private void endTheData(Channel channel){
        Attribute<String> attr = channel.attr(NETTY_CHANNEL_KEY);
        String data = attr.get();
        CarPcGpsData carPcGpsData = JSON.parseObject(data,CarPcGpsData.class);
        if(carPcGpsData != null && carPcGpsData.getType() != DataEmun.Type.End.code){
            StringRedisTemplate redisTemplate = SpringUtils.getBean("stringRedisTemplate",StringRedisTemplate.class);
            ListOperations lists = redisTemplate.opsForList();
            carPcGpsData.setType(DataEmun.Type.End.code);
            long collectionTime = carPcGpsData.getCollectionTime().getTime()+1000;
            carPcGpsData.setCollectionTime(new Date(collectionTime));
            String endData = JSON.toJSONString(carPcGpsData);
            lists.leftPush(RedisConfig.MY_DATABASE,endData);
        }
    }

}

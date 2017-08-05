package com.xxx.practice.handler;

import com.alibaba.fastjson.JSON;
import com.xxx.practice.config.RedisConfig;
import com.xxx.practice.enums.DataEmun;
import com.xxx.practice.model.CarPcGpsData;
import com.xxx.practice.util.SpringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

import static com.xxx.practice.constant.AttributeMapConstant.NETTY_CHANNEL_KEY;

/**
 * Created by Wyl on 2017-07-06.
 * 心跳检测
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter{

    private int lossConnectTime = 0;

    private Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {

        Channel channel = ctx.channel();
        if(obj instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)obj;
            if(event.state() == IdleState.READER_IDLE){
                lossConnectTime++;
                logger.info("5秒没有接收到客户端消息啦!");
                if(lossConnectTime > 2){
                    logger.info("关闭这个不活跃channel");
                    //结束行程
                    endTheData(channel);
                    ctx.channel().close();
                }
            }
        }else {
            super.userEventTriggered(ctx, obj);
        }
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

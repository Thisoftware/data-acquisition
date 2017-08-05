package com.xxx.practice.coder;

import com.xxx.practice.model.CustomMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by Wyl on 2017-06-27.
 * 消息编码器
 */
public class CustomEncoder extends MessageToByteEncoder<CustomMsg>{

    Logger logger = LoggerFactory.getLogger(CustomEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, CustomMsg msg, ByteBuf byteBuf) throws Exception {
        if(msg == null){
            logger.error("消息为空!");
        }

        String body = msg.getBody();
        byte[] bodyBytes = body.getBytes(Charset.forName("utf-8"));
        byteBuf.writeByte(msg.getType());
        byteBuf.writeInt(bodyBytes.length);
        byteBuf.writeBytes(bodyBytes);
    }
}

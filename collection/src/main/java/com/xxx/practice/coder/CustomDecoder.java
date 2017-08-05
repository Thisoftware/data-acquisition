package com.xxx.practice.coder;

import com.xxx.practice.model.CustomMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wyl on 2017-06-27.
 * 消息解码器
 */
public class CustomDecoder extends LengthFieldBasedFrameDecoder {

    private Logger logger = LoggerFactory.getLogger(CustomDecoder.class);

    //判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是 byte + int = 1+4 = 5
    private static final int HEADER_SIZE = 5;

    private byte type;

    private int length;

    private String body;

    /**
     * @param maxFrameLength      解码时，处理每个帧数据的最大长度
     * @param lengthFieldOffset   该帧数据中，存放该帧数据的长度的数据的起始位置
     * @param lengthFieldLength   记录该帧数据长度的字段本身的长度
     * @param lengthAdjustment    修改帧数据长度字段中定义的值，可以为负数
     * @param initialBytesToStrip 解析的时候需要跳过的字节数
     * @param failFast            为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常
     */

    public CustomDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                         int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if (in == null) {
            return null;
        }
        if (in.readableBytes() < HEADER_SIZE) {
            logger.error("可读信息段比头部信息小!");
        }

        //注意在读的过程中，readIndex的指针也在移动
        type = in.readByte();

        length = in.readInt();

        int readable = in.readableBytes();
        if (readable < length) {
            logger.error("信息数据异常，可读信息长度比告知的信息少,可读信息长度" + readable + "告知的信息长度：" + length);
        }
        ByteBuf buf = in.readBytes(length);
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        body = new String(req, "UTF-8");

        CustomMsg customMsg = new CustomMsg(type,length,body);
        return customMsg;
    }

}

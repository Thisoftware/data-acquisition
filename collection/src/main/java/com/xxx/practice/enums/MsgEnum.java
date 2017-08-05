package com.xxx.practice.enums;

/**
 * Created by Wyl on 2017-06-28.
 * 接受消息标记
 */
public class MsgEnum {

    public enum Type {
        HeartBeat((byte) 0xAB, "心跳消息"),
        BusinessData((byte) 0xCD, "业务数据");

        public byte code;
        public String desc;

        Type(byte code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}

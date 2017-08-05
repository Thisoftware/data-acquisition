package com.xxx.practice.model;

/**
 * Created by Wyl on 2017-06-27.
 */
public class CustomMsg {

    //信息标志  0xAB 表示心跳包  0xCD 业务信息包
    private byte type;

    //主题信息的长度
    private int length;

    //主题信息
    private String body;

    public CustomMsg() {

    }

    public CustomMsg(byte type, int length, String body) {
        this.type = type;
        this.length = length;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CustomMsg{" +
                "type=" + type +
                ", length=" + length +
                ", body='" + body + '\'' +
                '}';
    }
}

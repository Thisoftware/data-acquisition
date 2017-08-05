package com.xxx.common.consts;

/**
 * Created by wyl on 2017/4/25 0025.
 */
public enum ReCode {

    Fail(1,"操作失败"),

    Success(0,"操作成功"),

    SystemError(100001,"系统错误"),

    ReCode(int code, String msg){
        this.code=code;
        this.msg =msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

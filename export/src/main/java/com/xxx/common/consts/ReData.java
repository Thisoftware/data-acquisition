package com.xxx.common.consts;

import java.io.Serializable;

/**
 * Created by Wyl on 2017/4/25 0025.
 */
public class ReData implements Serializable{

    private String msg;

    private int code;

    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.xxx.practice.enums;

/**
 * Created by Wyl on 2017-07-06.
 */
public class DataEmun {

    public enum Type{
        Begin(0,"开始"),
        Loading(1,"行程中"),
        End(6,"结束");

        public int code;
        public String desc;

        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}

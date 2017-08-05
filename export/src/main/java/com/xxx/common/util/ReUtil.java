package com.xxx.common.util;


import com.xxx.common.consts.ReCode;
import com.xxx.common.consts.ReData;

/**
 * Created by wyl on 2017/4/25 0025.
 */
public class ReUtil {

    public static ReData success(Object data){
        ReData reData = new ReData();
        reData.setCode(ReCode.Success.getCode());
        reData.setMsg(ReCode.Success.getMsg());
        reData.setData(data==null?"":data);
        return reData;
    }


    public static ReData fail(Object Data){
        ReData reData = new ReData();
        reData.setCode(ReCode.Fail.getCode());
        reData.setMsg(ReCode.Fail.getMsg());
        reData.setData("");
        return reData;
    }

    public static ReData success(ReCode code,Object data){
        ReData reData = new ReData();
        reData.setCode(code.getCode());
        reData.setMsg(code.getMsg());
        reData.setData(data==null?"":data);
        return reData;
    }

    public static ReData fail(ReCode code){
        ReData reData = new ReData();
        reData.setCode(code.getCode());
        reData.setMsg(code.getMsg());
        reData.setData("");
        return reData;
    }

    public static ReData fail(ReCode code,Object data){
        ReData reData = new ReData();
        reData.setCode(code.getCode());
        reData.setMsg(code.getMsg());
        reData.setData(data==null?"":data);
        return reData;
    }
}

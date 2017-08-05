package com.xxx.worker;

import com.alibaba.fastjson.JSONObject;
import com.xxx.common.util.SpringUtils;
import com.xxx.config.RedisConfig;
import com.xxx.dao.CarPcGpsMapper;
import com.xxx.model.CarPcGps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.locks.Lock;

/**
 * Created by Wyl on 2017-07-03.
 */
public class Worker implements Runnable{

    private Logger logger = LoggerFactory.getLogger(Worker.class);

    private ListOperations<String,String> lists;

    private String name;

    private CarPcGpsMapper carPcGpsMapper = SpringUtils.getBean("carPcGpsMapper",CarPcGpsMapper.class);

    private Lock lock;

    public Worker(ListOperations<String,String> lists, String name, Lock lock){
        this.lists = lists;
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        String data = lists.rightPop(RedisConfig.MY_DATABASE);
        while (!StringUtils.isEmpty(data)){
            logger.info(data);
            CarPcGps carPcGps = JSONObject.parseObject(data,CarPcGps.class);
            carPcGps.setCreateTime(new Date());
            carPcGps.setDeleteFlag(false);
            carPcGpsMapper.insert(carPcGps);
            data  =lists.rightPop(RedisConfig.MY_DATABASE);
        }
    }
}

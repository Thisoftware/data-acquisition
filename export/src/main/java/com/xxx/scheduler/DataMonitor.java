package com.xxx.scheduler;

import com.xxx.config.RedisConfig;
import com.xxx.config.WorkerConfig;
import com.xxx.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Wyl on 2017/6/16 0016.
 * 查看redis中是否有数据需要导出，并通知导出程序进行导出数据
 */

@Component
public class DataMonitor {

    private Logger logger = LoggerFactory.getLogger(DataMonitor.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ExecutorService executor = Executors.newFixedThreadPool(WorkerConfig.WORKER_THREAD_NUM);

    private Lock lock = new ReentrantLock();

    @Scheduled(cron="0/5 * * * * ?")
    public void test(){
        ListOperations<String,String> lists =  redisTemplate.opsForList();
        long size = lists.size(RedisConfig.MY_DATABASE);
        if(size>0){
            logger.info("有数据需要处理："+size);
            int flag=0;
            Worker worker = new Worker(lists,"worker",lock);
            while(flag<WorkerConfig.WORKER_THREAD_NUM){
                flag++;
                executor.execute(worker);
            }
        }
    }

}

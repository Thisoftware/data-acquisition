package com.xxx.listener;

import com.xxx.config.WorkerConfig;
import com.xxx.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Wyl on 2017/5/19 0019.
 */
public class Receiver {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Lock lock = new ReentrantLock();

    private int flag=0;

    private ExecutorService executor = Executors.newFixedThreadPool(WorkerConfig.WORKER_THREAD_NUM);

    private List<Worker> workers = new ArrayList<Worker>();

    public void receiveMessage(String message){

        /*ListOperations<String,String> lists =  redisTemplate.opsForList();

        while(workers.size()<WorkerConfig.WORKER_THREAD_NUM){
            flag++;
            String name = "worker"+flag;
            Worker worker = new Worker(lists,name,lock);
            workers.add(worker);
        }
        for(Worker worker:workers){
            executor.execute(worker);
        }*/
    }
}

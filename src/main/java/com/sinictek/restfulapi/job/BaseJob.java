package com.sinictek.restfulapi.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  多任务执行类
 */
@Component
public class BaseJob {

    @Scheduled(cron = "0/5 * * * * *")
    public void doGc(){
        System.gc();
    }

}

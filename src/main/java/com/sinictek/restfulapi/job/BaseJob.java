package com.sinictek.restfulapi.job;

import com.sinictek.restfulapi.model.SPad;
import com.sinictek.restfulapi.model.SPcb;
import com.sinictek.restfulapi.service.SPadService;
import com.sinictek.restfulapi.service.SPcbService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  多任务执行类
 */
@Component
@Log4j2
public class BaseJob {

    @Autowired
    SPcbService sPcbService;
    @Autowired
    SPadService sPadService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void doGc(){
        //System.out.println("-----------------------GC---------------------");
        System.gc();

    }

}

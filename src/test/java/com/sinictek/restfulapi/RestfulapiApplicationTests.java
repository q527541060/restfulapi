package com.sinictek.restfulapi;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSON;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.SPad;
import com.sinictek.restfulapi.model.SPcb;
import com.sinictek.restfulapi.service.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.sinictek.restfulapi.dao")
class RestfulapiApplicationTests {

    @Autowired
    SLineService sLineService;
    @Autowired
    SPcbService sPcbService;
    @Autowired
    SPadService sPadService;
    @Autowired
    SJobService sJobService;
    @Autowired
    SStatusService sStatusService;
    @Autowired
    AStatusService aStatusService;
    @Autowired
    ALineService aLineService;

    @Test
    void getSPILine(){

        String str = JSON.toJSONString(sLineService.selectById(459));
    }

    @Test
    void getSPIJob(){

        String str = JSON.toJSONString(sJobService.selectById(45));
    }
    @Test
    void getSPIstatus(){
        String str = JSON.toJSONString(sStatusService.selectById(413));
    }
    @Test
    void contextLoads() {

        SLine sLine = sLineService.selectById(459);

        String str = JSON.toJSONString(sLine);

    }

    @Test
    void getSPIPcb(){

        SPcb sPcb = sPcbService.selectById(422);
        String str = JSON.toJSONString(sPcb);
    }
    @Test
    void getSPIPad(){
        SPad sPad = sPadService.selectById(1);
        //String strBase64 = net.iharder.Base64.encodeBytes(sPad.getPad2dImage()) ;
        String str = JSON.toJSONString(sPad);
    }

    @Test
    void testPadTabName(){

        boolean b = false;
       // sPadService.insertSpiPadBatch("20200923",)

    }
}

package com.sinictek.restfulapi;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.SPad;
import com.sinictek.restfulapi.model.SPcb;
import com.sinictek.restfulapi.service.*;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.awt.SunHints;

import java.security.Key;
import java.util.*;

@Log4j2
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


    /*@Test
    void testGetPad(){

        //579337100176916481
        List<SPad> sPad = sPadService.selectList(Condition.create().eq("padIndex",0));
        int i = 9;
    }*/
   /* @Test
    void testInsertBoard(){

        *//*for (int i = 0; i < 2; i++) {
            SPcb sPcb = new SPcb();
            sPcb.setPcbIdLine("1#SMT31");
            sPcb.setLineNo("SMT31");
            sPcb.setJobName("Sample1_510_GWok-0-test");
            sPcb.setLaneNo(0);
            sPcb.setInspectResult("0");
            sPcb.setInspectStarttime(new Date());
            sPcb.setInspectEndtime(new Date());
            sPcb.setBoardWidth(0.11);
            sPcb.setBoardLength(0.22);
            sPcb.setBoardBarcode("SDFWES");
            sPcb.setComponentTableName("as");
            sPcb.setPadTableName("pad_20210317");
            sPcb.setArrayBarcode("sdf,sdgfg,dfg");
            sPcb.setArrayWidth("23,346,543");
            sPcb.setArrayLength("12,4,5");
            sPcb.setArrayinspectResult("NG,Good,Pass");
            sPcb.setTotalpadCount(12);
            sPcb.setPasspadCount(123);
            sPcb.setNgpadCount(1);
            sPcb.setGoodpadCount(11);
            sPcb.setShiftyCount(23);
            sPcb.setBridgeCount(0);
            sPcb.setShapeerrorCount(0);
            //sPcb.setaCpk(0.1);
            sPcb.setVcpk(0.3);
            //sPcb.sethCpk(0.5);
            sPcb.setShithxCpk(0.1);
            sPcb.setShithyCpk(0.2);
            sPcb.setUcl("234");
            sPcb.setLcl("44");
            sPcb.setRemark("ss");
            sPcb.setShiftyCount(0);
            sPcb.setBridgeCount(0);
            sPcb.setShapeerrorCount(0);
            sPcb.setSmearedCount(0);
            sPcb.setCoplanarityCount(0);
            sPcb.setPrebridgeCount(0);
            sPcb.setPadareapercentCount(0);
            sPcb.setShiftxCount(0);
            sPcb.setOtherCount(0);
            sPcb.setLowareaCount(0);
            sPcb.setOverareaCount(0);
            sPcb.setLowheightCount(0);
            sPcb.setOverheightCount(0);
            sPcb.setExcessCount(0);
            sPcb.setInsufficientCount(0);
            sPcb.setMissingCount(0);
            sPcbService.insert(sPcb);
        }
*//*
       *//* List<SPad> padList = new ArrayList<SPad>();
        //pad
        log.warn("for start....");
       for (int i = 0; i < 9999; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("1#SMT30");
            //pad.setPadIndex(i);
            pad.setComponentId("GSDG"+i);
            pad.setArrayId("2");
            pad.setPadInspectResult("1");
            pad.setDefectTypeCode("0");
            pad.setDefectTypeName("1");
            pad.setPadImagePath("s");
            pad.setHeight(0.1);
            pad.setArea(0.22);
            pad.setVolume(0.22);
            pad.setOffsetx(0.22);
            pad.setPerHeight(0.22);
            pad.setHeight(0.44);
            pad.setPerOffsetx(0.33);
            pad.setPerArea(0.44);
            pad.setPerVolume(0.333);
            pad.setPadTableID(Long.valueOf("20210317"));
            pad.setComponentTableID(Long.valueOf("20210317"));
            String str = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z";
           java.sql.Clob c=null;
            try {
                c = new javax.sql.rowset.serial.SerialClob(str
                       .toCharArray());
           } catch (Exception e) {
           }
            pad.setPad2dImageBase64(null);
            pad.setPad3dImageBase64(null);
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.warn("for end....");

        log.error("Batch start....");
        sPadService.insertBatch(padList);
        log.error("Batch end....");
*//*
    }*/

    /*@Test
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



       *//* SLine sLine = new SLine();
        sLine.setLineNo("SMT001");
        sLine.setLineContent("其实是假的");
        sLineService.insert(sLine);*//*

      *//*  long id = sLine.getId();
        String str = JSON.toJSONString(sLine);*//*

    }*/

    /*@Test
    void getSPIPcb(){

        SPcb sPcb = sPcbService.selectById(422);
        String str = JSON.toJSONString(sPcb);
    }*/
   /* @Test
    void getSPIPad(){
        SPad sPad = sPadService.selectById(1);
        //String strBase64 = net.iharder.Base64.encodeBytes(sPad.getPad2dImage()) ;
        String str = JSON.toJSONString(sPad);
    }*/
    /*String t ;
    @Test
    void testPadTabName(){

        boolean b = false;

        System.out.println(t+"O");
       // sPadService.insertSpiPadBatch("20200923",)

    }
    @Test
    void testRemark(){
        Map<Object,String> map = new HashMap<Object,String>();
        map.put(1,"result 1");
        map.put(2,"result 2");
        map.put(3,"result 3");
        map.put(4,"result 4");
        map.put(5,"result 5");
        map.remove(1);
        map.remove(2);
        map.remove(3);
        map.put(4,"result 1");
        map.put(5,"result 2");
        assert map.size() == 2;
        assert map.get(4).equals("result 1");
        assert map.get(5).equals("result 2");
       *//* SPcb sPcb= sPcbService.selectById(422);
        Object obj = JSON.isValid(sPcb.getRemark()) ;*//*

    }*/
}

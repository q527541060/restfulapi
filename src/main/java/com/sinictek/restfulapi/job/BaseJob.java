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


    /*@Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_0(){
        //System.out.println("doGc.....");
       // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("1#S201");
        sPcb.setLineNo("S201");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);
        List<SPad> padList = new ArrayList<SPad>();
        //pad
        for (int i = 0; i < 100000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("1#S201");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_0      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_1(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S202");
        sPcb.setLineNo("S202");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S202");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_1      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_2(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("3#S203");
        sPcb.setLineNo("S203");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S202");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_2      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_3(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("4#S204");
        sPcb.setLineNo("S204");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S202");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_3      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_4(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S205");
        sPcb.setLineNo("S205");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S205");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_4      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_5(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S206");
        sPcb.setLineNo("S206");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S206");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_5      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_6(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S207");
        sPcb.setLineNo("S207");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S207");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_6      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_7(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S208");
        sPcb.setLineNo("S208");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S208");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_7      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_8(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S209");
        sPcb.setLineNo("S209");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S209");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_8      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_9(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S210");
        sPcb.setLineNo("S210");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S210");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_9      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_10(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S211");
        sPcb.setLineNo("S211");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S211");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_10      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_11(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S212");
        sPcb.setLineNo("S212");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S212");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_11      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_12(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S213");
        sPcb.setLineNo("S213");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);
        List<SPad> padList = new ArrayList<SPad>();
        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S213");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_12      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_13(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S214");
        sPcb.setLineNo("S214");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);
        List<SPad> padList = new ArrayList<SPad>();
        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S214");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_13      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_14(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S215");
        sPcb.setLineNo("S215");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S215");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_14      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_15(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S216");
        sPcb.setLineNo("S216");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S216");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_15      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_16(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S217");
        sPcb.setLineNo("S217");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S217");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_16      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_17(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S218");
        sPcb.setLineNo("S218");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S218");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_17      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_18(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S219");
        sPcb.setLineNo("S219");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S219");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_18      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_19(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S220");
        sPcb.setLineNo("S220");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S220");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_19      end");
    }


    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_20(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("1#S201");
        sPcb.setLineNo("S201");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);
        List<SPad> padList = new ArrayList<SPad>();
        //pad
        for (int i = 0; i < 100000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("1#S201");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_20      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_21(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S202");
        sPcb.setLineNo("S202");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S202");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_21      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_22(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("3#S203");
        sPcb.setLineNo("S203");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S202");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_22      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_23(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("4#S204");
        sPcb.setLineNo("S204");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S202");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_23      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_24(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S205");
        sPcb.setLineNo("S205");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S205");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_24      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_25(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S206");
        sPcb.setLineNo("S206");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S206");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_25      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_26(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S207");
        sPcb.setLineNo("S207");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S207");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_26      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_27(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S208");
        sPcb.setLineNo("S208");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S208");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_27      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_28(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S209");
        sPcb.setLineNo("S209");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S209");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_28      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_29(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S210");
        sPcb.setLineNo("S210");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S210");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_29      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_30(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S211");
        sPcb.setLineNo("S211");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 10000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S211");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        sPadService.insertBatch(padList);
        log.info("autoInsertSpiBoard_30      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_31(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S212");
        sPcb.setLineNo("S212");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S212");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_31      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_32(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S213");
        sPcb.setLineNo("S213");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);
        List<SPad> padList = new ArrayList<SPad>();
        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S213");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_32      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_33(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S214");
        sPcb.setLineNo("S214");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);
        List<SPad> padList = new ArrayList<SPad>();
        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S214");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_33      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_34(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S215");
        sPcb.setLineNo("S215");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S215");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_34      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_35(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S216");
        sPcb.setLineNo("S216");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S216");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_35      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_36(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S217");
        sPcb.setLineNo("S217");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S217");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_36      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_37(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S218");
        sPcb.setLineNo("S218");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S218");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_37      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_38(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S219");
        sPcb.setLineNo("S219");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S219");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_38      end");
    }
    @Scheduled(cron = "0/15 * * * * *")
    public void autoInsertSpiBoard_39(){
        //System.out.println("doGc.....");
        // System.gc();
        SPcb sPcb = new SPcb();
        sPcb.setPcbIdLine("2#S220");
        sPcb.setLineNo("S220");
        sPcb.setJobName("Sample1_630_GWok-0");
        sPcb.setLaneNo(0);
        sPcb.setInspectResult("0");
        sPcb.setInspectStarttime(new Date());
        sPcb.setInspectEndtime(new Date());
        sPcb.setBoardWidth(0.11);
        sPcb.setBoardLength(0.22);
        sPcb.setBoardBarcode("SDFWES");
        sPcb.setComponentTableName("");
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
        sPcb.setaCpk(0.1);
        sPcb.setVcpk(0.3);
        sPcb.sethCpk(0.5);
        sPcb.setShithxCpk(0.1);
        sPcb.setShithyCpk(0.2);
        sPcbService.insert(sPcb);List<SPad> padList = new ArrayList<SPad>();

        //pad
        for (int i = 0; i < 9000; i++) {
            SPad pad = new SPad();
            pad.setPadId(i+"");
            pad.setPcbidLine("2#S220");
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
            pad.setPad2dImageBase64("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoACcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDz1AjIWYHpyenNMLx5ACcetOc7YgmOvNRW1u93KcltoJAAbAAHHOPpTpUVNOUnZL+rJf1sfOJdWPJXOAmR60gKE4K4981Sv2MEzKHZfVQ7dPbJqGzuXeQJ8zA56np/nFaLDU5p+zlqk3qrba92b+wfLzI0HUrgGin8vEDjJBoriZkh02Q689qk06Xyopz/ABfNjnGPmqPHmIMEZFVkfyXKSYHzMfm4BGex6d67qEJVKUowV3dOy7Wev4iirqxS1HzZJmlIJBPD5zkfWoLXiVWbLHPIPFdHNqlv9jijVoi3ofnxj+VZOFlmHlqMA7iR0z6D866KVCrTk5Si0kpa2/uv+vU7IVbws1YuRzOIskksevNFMyQm04/OivKb1OVpXFzSYz1OM96KKBERQHHfHTNODbeMD/CiigokyDGAF5HU0UUUCP/Z");
            pad.setPad3dImageBase64("");
            padList.add(pad);
            //sPadService.insert(pad);
        }
        log.info("autoInsertSpiBoard_39      end");
    }*/
}

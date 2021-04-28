package com.sinictek.restfulapi.api;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.Base64;
import com.alibaba.druid.util.StringUtils;
//import com.baomidou.mybatisplus.mapper.Condition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SPad;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.model.queryBean.SpiComponentAndPadBean;
import com.sinictek.restfulapi.model.queryBean.SpiPads;
import com.sinictek.restfulapi.service.SPadService;
import com.sinictek.restfulapi.service.SPcbService;
import com.sinictek.restfulapi.util.Base64Helper;
import com.sinictek.restfulapi.util.StringTimeUtils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 焊盘 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/Api")
@Log4j2
public class SPadController {


    @Resource
    SPcbService sPcbService;
    @Resource
    SPadService sPadService;

    //private static final Logger logger = LoggerFactory.getLogger(SPadController.class);
    //插入spi-pad-com
    @PostMapping("/insertSpiComponentAndPad")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiComponentAndPadTable(@RequestBody List<SpiComponentAndPadBean> lstSpiComponentAndPadBean) throws Exception{

        //padTableID    s_pad_padTableID
        boolean bIsPadInsertOK = false;
        String message = "";
        if(lstSpiComponentAndPadBean==null || lstSpiComponentAndPadBean.size()==0){
            message = "SpiComponentAndPadBean_IS_NULL";
            return  new ApiResponse(bIsPadInsertOK,message,"FAIL");
        }else if(lstSpiComponentAndPadBean.get(0).getPcbidLine() ==null || lstSpiComponentAndPadBean.get(0).getPcbidLine()==""){
            message = "SpiComponentAndPadBean_PcbidLine_IS_NULL";
            return  new ApiResponse(bIsPadInsertOK,message,"FAIL");
        } else{
            //查索引+padtableid是否存在
            String padTableName = "s_pad_"+ StringTimeUtils.getDateToYearMonthDayString(StringTimeUtils.getTimeStringToDate(lstSpiComponentAndPadBean.get(0).getInspectStarttime()))  ;
            int iPcbCount = sPcbService.selectCount(Condition.create()
                            .eq("pcbIdLine",lstSpiComponentAndPadBean.get(0).getPcbidLine())
                            .and()
                            .eq("padTableName",padTableName));
            //logger.info("coming..");
            if(iPcbCount>0){
                //logger.info("start");
                //分表padtable
                String strInsertSpiPadTableSqlSec = "";
                String tmp = null;
                sPadService.createSpiPadTable(padTableName);
                for (int i = 0; i < lstSpiComponentAndPadBean.size(); i++)
                {
                    for (int j = 0; j < lstSpiComponentAndPadBean.get(i).getPads().size(); j++) {
                        SpiPads spiPads = lstSpiComponentAndPadBean.get(i).getPads().get(j);
                        String strInsertSpiPadTableSql = "(";
                        //padId,pcbidLine,padIndex,componentId,arrayId,padInspectResult,defectTypeCode,defectTypeName,padImagePath,pad2dImage,pad3dImage,height,area,
                        tmp = spiPads.getPadIndex()==null?"'',":"'"+spiPads.getPadIndex() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPcbidLine()==null?"'',":"'"+spiPads.getPcbidLine() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPadIndex()==null?"'0',":"'"+spiPads.getPadIndex() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getComponentId()==null?"'',":"'"+spiPads.getComponentId() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getArrayId()==null?"'',":"'"+spiPads.getArrayId() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPadInspectResult()==null?"'',":"'"+spiPads.getPadInspectResult() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getDefectTypeCode()==null?"'',":"'"+spiPads.getDefectTypeCode() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getDefectTypeName()==null?"'',":"'"+spiPads.getDefectTypeName() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPadImagePath()==null?"'',":"'"+spiPads.getPadImagePath() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = "'',";//spiPads.getPad2dImage()==null?"'',":"'"+ spiPads.getPad2dImage() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp ="'',";// spiPads.getPad3dImage()==null?"'',":"'"+spiPads.getPad3dImage() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getHeight()==null?"'0',":"'"+spiPads.getHeight() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getArea()==null?"'0',":"'"+spiPads.getArea() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        // volume,offsetx,offsety,perHeight,perArea,perVolume,perOffsetx,perOffsety,shape,bridgeType,uHeight,lHeight,uArea,lArea,uVolume,lVolume,
                        tmp = spiPads.getVolume()==null?"'0',":"'"+spiPads.getVolume() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getOffsetx()==null?"'0',":"'"+spiPads.getOffsetx() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getOffsety()==null?"'0',":"'"+spiPads.getOffsety() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPerHeight()==null?"'0',":"'"+spiPads.getPerHeight() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPerArea()==null?"'0',":"'"+spiPads.getPerArea() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPerVolume()==null?"'0',":"'"+spiPads.getPerVolume() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPerOffsetx()==null?"'0',":"'"+spiPads.getPerOffsetx() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPerOffsety()==null?"'0',":"'"+spiPads.getPerOffsety() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getShape()==null?"'0',":"'"+spiPads.getShape() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getBridgeType()==null?"'0',":"'"+spiPads.getBridgeType() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUHeight()==null?"'0',":"'"+spiPads.getUHeight() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getLHeight()==null?"'0',":"'"+spiPads.getLHeight() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUArea()==null?"'0',":"'"+spiPads.getUArea() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getLArea()==null?"'0',":"'"+spiPads.getLArea() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUVolume()==null?"'0',":"'"+spiPads.getUVolume() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getLVolume()==null?"'0',":"'"+spiPads.getLVolume() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        // uOffsetx,uOffsety,uPerHeight,lPerHeight,uPerArea,lPerArea,uPerVolume,lPerVolume,uPerOffsetx,uPerOffsety,padTableID,componentTableID,remark
                        tmp = spiPads.getUOffsetx()==null?"'0',":"'"+spiPads.getUOffsetx() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUOffsety()==null?"'0',":"'"+spiPads.getUOffsety() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUPerHeight()==null?"'0',":"'"+spiPads.getUPerHeight() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getLPerHeight()==null?"'0',":"'"+spiPads.getLPerHeight() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUPerArea()==null?"'0',":"'"+spiPads.getUPerArea() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getLPerArea()==null?"'0',":"'"+spiPads.getLPerArea() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUPerVolume()==null?"'0',":"'"+spiPads.getUPerVolume() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getLPerVolume()==null?"'0',":"'"+spiPads.getLPerVolume() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUPerOffsetx()==null?"'0',":"'"+spiPads.getUPerOffsetx() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getUPerOffsety()==null?"'0',":"'"+spiPads.getUPerOffsety() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPadTableID()==null?"'0',":"'"+spiPads.getPadTableID() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getComponentId()==null?"'0',":"'"+spiPads.getComponentId() +"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getRemark()==null?"'',":"'"+spiPads.getRemark()+"'," ;
                        strInsertSpiPadTableSql += tmp ;
                        try {
                            tmp = spiPads.getPad2dImageBase64()==null?"'',":"'"+ spiPads.getPad2dImageBase64()+"'," ;//Base64Helper.compressData(
                        }catch (Exception ex){
                        }
                        strInsertSpiPadTableSql += tmp ;
                        tmp = spiPads.getPad3dImageBase64()==null?"''":"'"+spiPads.getPad3dImageBase64()+"'" ;
                        strInsertSpiPadTableSql += tmp ;
                        strInsertSpiPadTableSql += ")";
                   /* if(i == (lstsPad.size()-1)){
                        strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql;
                    }else{
                        strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql + ",";
                    }*/
                        //批量处理优化
                        if(j% 25 ==0  &&  j!=0)
                        {
                            strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql;
                            try {
                                //logger.info("do Batch start.." + i );
                                sPadService.addSpiPadBatch(padTableName+"",strInsertSpiPadTableSqlSec);
                                //logger.info("do Batch end.." + i);
                            }catch (Exception ex){
                                return  new ApiResponse(bIsPadInsertOK,ex.getMessage(),"FAIL");
                            }
                            strInsertSpiPadTableSqlSec = "";
                        }
                        else{
                            if(i == (lstSpiComponentAndPadBean.get(i).getPads().size()-1)){
                                strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql;
                            }else{
                                strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql + ",";
                            }
                        }
                        strInsertSpiPadTableSql = null;
                        spiPads = null;
                    }
                }
                try
                {
                    //logger.info(strInsertSpiPadTableSqlSec);
                    if(!StringUtils.isEmpty(strInsertSpiPadTableSqlSec)) {
                        //logger.info("do last Batch start.." );
                        sPadService.addSpiPadBatch(padTableName + "", strInsertSpiPadTableSqlSec);
                        //logger.info("do last Batch end.." );
                    }
                    //logger.info("end");
                    bIsPadInsertOK = true;
                    message=null;
                }catch (Exception ex){
                    return  new ApiResponse(bIsPadInsertOK,ex.getMessage(),"FAIL");
                }
                strInsertSpiPadTableSqlSec = null;
            }else{
                message = "PCB_TABLE_PADTABLENAME_IS_NOT_EXITS";
            }
        }
        return  new ApiResponse(bIsPadInsertOK,message,"OK");
    }

    @PostMapping("/insertSpiPad")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiPadTable(@RequestBody List<SPad> lstsPad){
        //padTableID    s_pad_padTableID
        //log.warn("start..");
        boolean bIsPadInsertOK = false;
        String message = "";
        if(lstsPad==null || lstsPad.size()==0){
            message = "PAD_IS_NULL";
            return  new ApiResponse(bIsPadInsertOK,message,"FAIL");
        }else{
            if(StringUtils.isEmpty(lstsPad.get(0).getCreate_time()) ){
                for (int i = 0; i < lstsPad.size(); i++) {
                    lstsPad.get(i).setCreate_time(lstsPad.get(i).getPadTableID()+"");
                }
            }else{
            }
            bIsPadInsertOK = sPadService.insertBatch(lstsPad,25);
            lstsPad = null;
        }
        //log.warn("end..");
        System.gc(); //doGc
        return  new ApiResponse(bIsPadInsertOK,message,"OK");
    }


    @PostMapping("/insertSpiPadNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiPadTableNew(@RequestBody List<SPad> lstsPad){

        log.warn(" pad个数: "+ lstsPad.size());
        log.warn("pad start...");
        sPadService.insertBatch(lstsPad);
        log.warn("pad end...");

        return  new ApiResponse(true,"","OK");
    }



    @GetMapping("/getSpiPadList/{padID}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getSpiPadList(@PathVariable("padID") Integer padID){

        return  new ApiResponse(true,"",sPadService.selectById(padID));//new ApiResponse(true,"",sPadService.selectById(padID));
    }
}


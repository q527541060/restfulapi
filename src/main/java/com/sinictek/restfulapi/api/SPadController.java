package com.sinictek.restfulapi.api;


import com.alibaba.druid.util.Base64;
import com.alibaba.druid.util.StringUtils;
//import com.baomidou.mybatisplus.mapper.Condition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SPad;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SPadService;
import com.sinictek.restfulapi.service.SPcbService;
import com.sinictek.restfulapi.util.Base64Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
public class SPadController {


    @Autowired
    SPcbService sPcbService;
    @Autowired
    SPadService sPadService;

    private static final Logger logger = LoggerFactory.getLogger(SPadController.class);
    @PostMapping("/insertSpiPad")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiPadTable(@RequestBody List<SPad> lstsPad){
        //padTableID    s_pad_padTableID
        boolean bIsPadInsertOK = false;
        String message = "";
        if(lstsPad==null || lstsPad.size()==0){
            message = "PAD_IS_NULL";
            return  new ApiResponse(bIsPadInsertOK,message,"FAIL");
        }else if(lstsPad.get(0).getPadTableID()==null ||
                lstsPad.get(0).getPadTableID()==0 || !StringUtils.isNumber(lstsPad.get(0).getPadTableID()+""))
        {
            message ="PAD_TABLEID_IS_|NULL_NOTNUM|";
            return  new ApiResponse(bIsPadInsertOK,message,"FAIL");
        }else{
            //查索引+padtableid是否存在
            String padTableName = "s_pad_"+ lstsPad.get(0).getPadTableID();
            int iPcbCount = sPcbService.selectCount(Condition.create()
                            .eq("pcbIdLine",lstsPad.get(0).getPcbidLine())
                            .and()
                            .eq("padTableName",padTableName));
            //logger.info("coming..");
            if(iPcbCount>0){
                logger.info("start");
                //分表padtable
                String strInsertSpiPadTableSqlSec = "";
                String tmp = null;
                sPadService.createSpiPadTable(padTableName);
                for (int i = 0; i < lstsPad.size(); i++)
                {
                    String strInsertSpiPadTableSql = "(";
                    //padId,pcbidLine,padIndex,componentId,arrayId,padInspectResult,defectTypeCode,defectTypeName,padImagePath,pad2dImage,pad3dImage,height,area,
                    tmp = lstsPad.get(i).getPadId()==null?"'',":"'"+lstsPad.get(i).getPadId() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPcbidLine()==null?"'',":"'"+lstsPad.get(i).getPcbidLine() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPadIndex()==null?"'0',":"'"+lstsPad.get(i).getPadIndex() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getComponentId()==null?"'',":"'"+lstsPad.get(i).getComponentId() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getArrayId()==null?"'',":"'"+lstsPad.get(i).getArrayId() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPadInspectResult()==null?"'',":"'"+lstsPad.get(i).getPadInspectResult() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getDefectTypeCode()==null?"'',":"'"+lstsPad.get(i).getDefectTypeCode() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getDefectTypeName()==null?"'',":"'"+lstsPad.get(i).getDefectTypeName() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPadImagePath()==null?"'',":"'"+lstsPad.get(i).getPadImagePath() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = "'',";//lstsPad.get(i).getPad2dImage()==null?"'',":"'"+ lstsPad.get(i).getPad2dImage() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp ="'',";// lstsPad.get(i).getPad3dImage()==null?"'',":"'"+lstsPad.get(i).getPad3dImage() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getHeight()==null?"'0',":"'"+lstsPad.get(i).getHeight() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getArea()==null?"'0',":"'"+lstsPad.get(i).getArea() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    // volume,offsetx,offsety,perHeight,perArea,perVolume,perOffsetx,perOffsety,shape,bridgeType,uHeight,lHeight,uArea,lArea,uVolume,lVolume,
                    tmp = lstsPad.get(i).getVolume()==null?"'0',":"'"+lstsPad.get(i).getVolume() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getOffsetx()==null?"'0',":"'"+lstsPad.get(i).getOffsetx() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getOffsety()==null?"'0',":"'"+lstsPad.get(i).getOffsety() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPerHeight()==null?"'0',":"'"+lstsPad.get(i).getPerHeight() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPerArea()==null?"'0',":"'"+lstsPad.get(i).getPerArea() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPerVolume()==null?"'0',":"'"+lstsPad.get(i).getPerVolume() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPerOffsetx()==null?"'0',":"'"+lstsPad.get(i).getPerOffsetx() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPerOffsety()==null?"'0',":"'"+lstsPad.get(i).getPerOffsety() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getShape()==null?"'0',":"'"+lstsPad.get(i).getShape() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getBridgeType()==null?"'0',":"'"+lstsPad.get(i).getBridgeType() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuHeight()==null?"'0',":"'"+lstsPad.get(i).getuHeight() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getlHeight()==null?"'0',":"'"+lstsPad.get(i).getlHeight() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuArea()==null?"'0',":"'"+lstsPad.get(i).getuArea() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getlArea()==null?"'0',":"'"+lstsPad.get(i).getlArea() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuVolume()==null?"'0',":"'"+lstsPad.get(i).getuVolume() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getlVolume()==null?"'0',":"'"+lstsPad.get(i).getlVolume() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    // uOffsetx,uOffsety,uPerHeight,lPerHeight,uPerArea,lPerArea,uPerVolume,lPerVolume,uPerOffsetx,uPerOffsety,padTableID,componentTableID,remark
                    tmp = lstsPad.get(i).getuOffsetx()==null?"'0',":"'"+lstsPad.get(i).getuOffsetx() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuOffsety()==null?"'0',":"'"+lstsPad.get(i).getuOffsety() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuPerHeight()==null?"'0',":"'"+lstsPad.get(i).getuPerHeight() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getlPerHeight()==null?"'0',":"'"+lstsPad.get(i).getlPerHeight() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuPerArea()==null?"'0',":"'"+lstsPad.get(i).getuPerArea() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getlPerArea()==null?"'0',":"'"+lstsPad.get(i).getlPerArea() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuPerVolume()==null?"'0',":"'"+lstsPad.get(i).getuPerVolume() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getlPerVolume()==null?"'0',":"'"+lstsPad.get(i).getlPerVolume() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuPerOffsetx()==null?"'0',":"'"+lstsPad.get(i).getuPerOffsetx() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getuPerOffsety()==null?"'0',":"'"+lstsPad.get(i).getuPerOffsety() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPadTableID()==null?"'0',":"'"+lstsPad.get(i).getPadTableID() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getComponentTableID()==null?"'0',":"'"+lstsPad.get(i).getComponentTableID() +"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getRemark()==null?"'',":"'"+lstsPad.get(i).getRemark()+"'," ;
                    strInsertSpiPadTableSql += tmp ;
                    try {
                        tmp = lstsPad.get(i).getPad2dImageBase64()==null?"'',":"'"+ lstsPad.get(i).getPad2dImageBase64()+"'," ;//Base64Helper.compressData(
                    }catch (Exception ex){
                    }
                    strInsertSpiPadTableSql += tmp ;
                    tmp = lstsPad.get(i).getPad3dImageBase64()==null?"''":"'"+lstsPad.get(i).getPad3dImageBase64()+"'" ;
                    strInsertSpiPadTableSql += tmp ;

                    strInsertSpiPadTableSql += ")";
                   /* if(i == (lstsPad.size()-1)){
                        strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql;
                    }else{
                        strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql + ",";
                    }*/
                    //批量处理优化
                    if(i% 25 ==0  &&  i!=0)
                     {
                        strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql;
                        try {
                            //logger.info("do Batch start.." + i );
                            sPadService.addSpiPadBatch(lstsPad.get(0).getPadTableID()+"",strInsertSpiPadTableSqlSec);
                            //logger.info("do Batch end.." + i);
                        }catch (Exception ex){
                            return  new ApiResponse(bIsPadInsertOK,ex.getMessage(),"FAIL");
                        }
                        strInsertSpiPadTableSqlSec = "";
                    }
                    else{
                        if(i == (lstsPad.size()-1)){
                            strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql;
                        }else{
                            strInsertSpiPadTableSqlSec += strInsertSpiPadTableSql + ",";
                        }
                    }
                }
                try {
                    //logger.info(strInsertSpiPadTableSqlSec);
                    if(!StringUtils.isEmpty(strInsertSpiPadTableSqlSec)) {
                        //logger.info("do last Batch start.." );
                        sPadService.addSpiPadBatch(lstsPad.get(0).getPadTableID() + "", strInsertSpiPadTableSqlSec);
                        //logger.info("do last Batch end.." );
                    }
                    logger.info("end");
                    bIsPadInsertOK = true;
                    message=null;
                }catch (Exception ex){
                    return  new ApiResponse(bIsPadInsertOK,ex.getMessage(),"FAIL");
                }
            }else{
                message = "PCB_TABLE_PADTABLENAME_IS_NOT_EXITS";
            }
        }
        return  new ApiResponse(bIsPadInsertOK,message,"OK");
    }

   /* private  String  padListObjectToStr(List<SPad> lstPad){



    }*/

    @GetMapping("/getSpiPadList/{padID}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getSpiPadList(@PathVariable("padID") Integer padID){

        return  new ApiResponse(true,"",sPadService.selectById(padID));//new ApiResponse(true,"",sPadService.selectById(padID));
    }
}


package com.sinictek.restfulapi.api;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.AComponent;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.AComponentService;
import com.sinictek.restfulapi.service.APcbService;
import com.sinictek.restfulapi.service.AWindowService;
import com.sinictek.restfulapi.util.StringTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/Api")
public class AComponentController {

    @Resource
    APcbService aPcbService;

    @Resource
    AComponentService aComponentService;

    @Resource
    AWindowService aWindowService;

    //private static final Logger logger = LoggerFactory.getLogger(AComponentController.class);
    @PostMapping("insertAoiComponentAndWindows")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertAoiComponentTable(@RequestBody List<AComponent> lstComponent){
        ApiResponse apiResponse = ApiResponse.success("","OK");
        if(lstComponent!=null && lstComponent.size()>0 ){
            //logger.info("[insertAoiComponentAndWindows->lstComponent]"+ JSONArray.toJSONString(lstComponent));
            //判断PcbIdLine是否存在
            if( StringUtils.isEmpty( lstComponent.get(0).getPcbIdLine())){
                return   new ApiResponse(false,"insertAoiComponentAndWindows->pcbIdLine_IS_NULL",null);
            }
            //do batch windows
            for (int i = 0; i < lstComponent.size(); i++) {

                lstComponent.get(i).setCreate_time(StringTimeUtils.getDateToYearMonthDayString(lstComponent.get(i).getInspectStarttime()));

                if(lstComponent.get(i).getWindows()!=null && lstComponent.get(i).getWindows().size()>0){

                    for (int j = 0; j < lstComponent.get(i).getWindows().size(); j++) {
                        lstComponent.get(i).getWindows().get(j).setCreate_time(
                                StringTimeUtils.getDateToYearMonthDayString(lstComponent.get(i).getInspectStarttime()
                                )
                        );
                    }
                    aWindowService.insertBatch(lstComponent.get(i).getWindows(),25);
                }
            }
            //do batch component
            aComponentService.insertBatch(lstComponent,25);

            //判断componentTable 是否存在
            /*String componentTableName =  "a_component_" + StringTimeUtils.getDateToYearMonthString( lstComponent.get(0).getInspectStarttime());
            String windowTableName = "a_window_"+ StringTimeUtils.getDateToYearMonthDayString( lstComponent.get(0).getInspectStarttime());
            int iPcbCount = aPcbService.selectCount(Condition.create().eq("pcbIdLine",lstComponent.get(0).getPcbIdLine())
                .and()
                    .eq("componentTableName",componentTableName));
            if(iPcbCount>0){
                //logger.info("doInsertAoiComponentWindow start.");
                apiResponse = doInsertAoiComponentWindow(lstComponent,componentTableName,windowTableName);
                //logger.info("doInsertAoiComponentWindow end.");
            }else{
                return new ApiResponse(false,"iPcbCount_IS_ZERO",null);
            }*/

        }else{
            return new ApiResponse(false,"lstComponent_IS_NULL",null);
        }
        lstComponent = null;
        System.gc();
        return  apiResponse;
    }
    private ApiResponse  doInsertAoiComponentWindow(List<AComponent> lstComponent ,String componentTableName, String windowTableName){
        //创建component表
        aComponentService.creatAoiComponentTableService(componentTableName);
        //创建window表
        aWindowService.creatAoiWindowTableService(windowTableName);
        String insertComponentSqlSec ="";
        String insertWindowSqlSec = "";
        String tmp = null;
        //insert acomponent
        int iCountTmp = 0;
        for (int i = 0; i < lstComponent.size(); i++) {
//pcbIdLine,aoiMode,arrayIndex,fovIndex,partdesignate,partno,packagetype,componentposition,componentType,result,defectType,imageInfo,
//pcbIdLine,aoiMode,arrayIndex,fovIndex,partdesignate,partno,packagetype,componentposition,componentType,result,defectType,imageInfo,
            String insertComponentSql = "(";
            tmp = lstComponent.get(i).getPcbIdLine()==null?"'',":"'"+lstComponent.get(i).getPcbIdLine() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getAoiMode()==null?"'0',":"'"+lstComponent.get(i).getAoiMode() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getArrayIndex()==null?"'0',":"'"+lstComponent.get(i).getArrayIndex() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getFovIndex()==null?"'0',":"'"+lstComponent.get(i).getFovIndex() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPartdesignate()==null?"'',":"'"+lstComponent.get(i).getPartdesignate() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPartno()==null?"'',":"'"+lstComponent.get(i).getPartno() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPackagetype()==null?"'',":"'"+lstComponent.get(i).getPackagetype() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getComponentposition()==null?"'',":"'"+lstComponent.get(i).getComponentposition() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getComponentType()==null?"'',":"'"+lstComponent.get(i).getComponentType() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getResult()==null?"'',":"'"+lstComponent.get(i).getResult() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getDefectType()==null?"'',":"'"+lstComponent.get(i).getDefectType() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getImageInfo()==null?"'',":"'"+lstComponent.get(i).getImageInfo() +"'," ;
            insertComponentSql += tmp ;
//valueInfo,height,perheight,xshift,perxshift,yshift,peryshift,angle,perangle,volume,bigvolume,planeness,uplanenesswindowid,lplanenesswindowid,
//valueInfo,height,perheight,xshift,perxshift,yshift,peryshift,angle,perangle,volume,bigvolume,planeness,uplanenesswindowid,lplanenesswindowid,
            tmp = lstComponent.get(i).getValueInfo()==null?"'',":"'"+lstComponent.get(i).getValueInfo() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getHeight()==null?"'0',":"'"+lstComponent.get(i).getHeight() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPerheight()==null?"'0',":"'"+lstComponent.get(i).getPerheight() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getXshift()==null?"'0',":"'"+lstComponent.get(i).getXshift() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPerxshift()==null?"'0',":"'"+lstComponent.get(i).getPerxshift() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getYshift()==null?"'0',":"'"+lstComponent.get(i).getYshift() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPeryshift()==null?"'0',":"'"+lstComponent.get(i).getPeryshift() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getAngle()==null?"'0',":"'"+lstComponent.get(i).getAngle() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPerangle()==null?"'0',":"'"+lstComponent.get(i).getPerangle() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getVolume()==null?"'0',":"'"+lstComponent.get(i).getVolume() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getBigvolume()==null?"'0',":"'"+lstComponent.get(i).getBigvolume() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPlaneness()==null?"'0',":"'"+lstComponent.get(i).getPlaneness() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getUplanenesswindowid()==null?"'0',":"'"+lstComponent.get(i).getUplanenesswindowid() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getLplanenesswindowid()==null?"'0',":"'"+lstComponent.get(i).getLplanenesswindowid() +"'," ;
            insertComponentSql += tmp ;
//linearity,ulinearitywindowid,llinearitywindowid,similarity,polarity,area,bigarea,perarea,inspectStarttime,inspectEndtime,remark
//linearity,ulinearitywindowid,llinearitywindowid,similarity,polarity,area,bigarea,perarea,inspectStarttime,inspectEndtime,historyDefectRecord,remark
            tmp = lstComponent.get(i).getLinearity()==null?"'0',":"'"+lstComponent.get(i).getLinearity() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getUlinearitywindowid()==null?"'0',":"'"+lstComponent.get(i).getUlinearitywindowid() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getLlinearitywindowid()==null?"'0',":"'"+lstComponent.get(i).getLlinearitywindowid() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getSimilarity()==null?"'0',":"'"+lstComponent.get(i).getSimilarity() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPolarity()==null?"'0',":"'"+lstComponent.get(i).getPolarity() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getArea()==null?"'0',":"'"+lstComponent.get(i).getArea() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getBigarea()==null?"'0',":"'"+lstComponent.get(i).getBigarea() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getPerarea()==null?"'0',":"'"+lstComponent.get(i).getPerarea() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getInspectStarttime()==null?"'',":"'"+StringTimeUtils.getDateToYearMonthDayString(lstComponent.get(i).getInspectStarttime()) +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getInspectEndtime()==null?"'',":"'"+StringTimeUtils.getDateToYearMonthDayString(lstComponent.get(i).getInspectEndtime()) +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getComImageBase64()==null?"'',":"'"+lstComponent.get(i).getComImageBase64() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getCom3dImageBase64()==null?"'',":"'"+lstComponent.get(i).getCom3dImageBase64() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getHistoryDefectRecord()==null?"'',":"'"+lstComponent.get(i).getHistoryDefectRecord() +"'," ;
            insertComponentSql += tmp ;
            tmp = lstComponent.get(i).getRemark()==null?"''":"'"+lstComponent.get(i).getRemark() +"'" ;
            insertComponentSql += tmp ;
            insertComponentSql += ")" ;

            //批量处理优化
            if(i% 25 ==0  &&  i!=0)
            {
                insertComponentSqlSec += insertComponentSql;
                try {
                    //logger.info("do Batch start.." + i );
                    aComponentService.insertBatchAoiComponentTableService(componentTableName+"",insertComponentSqlSec);
                    //logger.info("do Batch end.." + i);
                }catch (Exception ex){
                    return  new ApiResponse(false,"[insertBatchAoiComponentTableService]"+ex.getMessage(),"FAIL");
                }
                insertComponentSqlSec = "";
            }
            else{
                if(i == (lstComponent.size()-1)){
                    insertComponentSqlSec += insertComponentSql;
                }else{
                    insertComponentSqlSec += insertComponentSql + ",";
                }
            }

            //insert window
            for (int j = 0; j < lstComponent.get(i).getWindows().size(); j++) {
                iCountTmp++;
//comid,pcbIdLine,aoiMode,arrayIndex,fovIndex,partdesignate,windowIndex,result,windowposition,valueInfo,height,perheight,xshift,
                String insertWindowSql = "(";
                tmp = lstComponent.get(i).getPcbIdLine()==null?"'',":"'"+lstComponent.get(i).getPcbIdLine() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getAoiMode()==null?"'0',":"'"+lstComponent.get(i).getAoiMode() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getArrayIndex()==null?"'0',":"'"+lstComponent.get(i).getArrayIndex() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getFovIndex()==null?"'0',":"'"+lstComponent.get(i).getFovIndex() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getPartdesignate()==null?"'',":"'"+lstComponent.get(i).getPartdesignate() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getWindowIndex()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getWindowIndex() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getResult()==null?"'',":"'"+lstComponent.get(i).getWindows().get(j).getResult() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getWindowposition()==null?"'',":"'"+lstComponent.get(i).getWindows().get(j).getWindowposition() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getValueInfo()==null?"'',":"'"+lstComponent.get(i).getWindows().get(j).getValueInfo() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getHeight()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getHeight() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getPerheight()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPerheight() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getXshift()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getXshift() +"'," ;
                insertWindowSql += tmp ;
//perxshift,yshift,peryshift,angle,perangle,volume,bigvolume,pervolume,planeness,uplanenesswindowid,lplanenesswindowid,linearity,ulinearitywindowid,llinearitywindowid,similarity,
                tmp = lstComponent.get(i).getWindows().get(j).getPerxshift()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPerxshift() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getYshift()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getYshift() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getPeryshift()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPeryshift() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getAngle()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getAngle() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getPerangle()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPerangle() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getVolume()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getVolume() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getBigvolume()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getBigvolume() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getPervolume()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPervolume() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getPlaneness()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPlaneness() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getUplanenesswindowid()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getUplanenesswindowid() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getLplanenesswindowid()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getLplanenesswindowid() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getLinearity()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getLinearity() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getUlinearitywindowid()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getUlinearitywindowid() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getLlinearitywindowid()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getLlinearitywindowid() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getSimilarity()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getSimilarity() +"'," ;
                insertWindowSql += tmp ;
//polarity,area,bigarea,perarea,algorithmparam,winImageBase64,historyDefectRecord,win3dImageBase64
                tmp = lstComponent.get(i).getWindows().get(j).getPolarity()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPolarity() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getArea()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getArea() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getBigarea()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getBigarea() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getPerarea()==null?"'0',":"'"+lstComponent.get(i).getWindows().get(j).getPerarea() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getAlgorithmparam()==null?"'',":"'"+lstComponent.get(i).getWindows().get(j).getAlgorithmparam() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getWinImageBase64()==null?"'',":"'"+lstComponent.get(i).getWindows().get(j).getWinImageBase64() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getHistoryDefectRecord()==null?"'',":"'"+lstComponent.get(i).getWindows().get(j).getHistoryDefectRecord() +"'," ;
                insertWindowSql += tmp ;
                tmp = lstComponent.get(i).getWindows().get(j).getWin3dImageBase64()==null?"''":"'"+lstComponent.get(i).getWindows().get(j).getWin3dImageBase64() +"'" ;
                insertWindowSql += tmp ;
                insertWindowSql +=")";

                //批量处理优化
                if(iCountTmp% 25 ==0  &&  iCountTmp!=0)
                {
                    insertWindowSqlSec += insertWindowSql;
                    try {
                        //logger.info("do Batch start.." + i );
                        aWindowService.insertAoiWindowBatchService(windowTableName,insertWindowSqlSec);
                        //logger.info("do Batch end.." + i);
                    }catch (Exception ex){
                        return  new ApiResponse(false,"[insertBatchAoiComponentTableService]"+ex.getMessage(),"FAIL");
                    }
                    insertWindowSqlSec = "";
                }
                else{
                    insertWindowSqlSec += insertWindowSql + ",";
                }
                insertWindowSql = null;
            }
            insertComponentSql = null;
        }
        if(StringUtils.isEmpty(insertComponentSqlSec) == false){
            try {
                aComponentService.insertBatchAoiComponentTableService(componentTableName+"",insertComponentSqlSec);
            }catch (Exception ex){
                return  new ApiResponse(false,"[insertBatchAoiComponentTableService]"+ex.getMessage(),"FAIL");
            }
        }
        if(StringUtils.isEmpty(insertWindowSqlSec) == false){
            insertWindowSqlSec = insertWindowSqlSec.substring(0,insertWindowSqlSec.length()-1);
            try {
                aWindowService.insertAoiWindowBatchService(windowTableName,insertWindowSqlSec);
            }catch (Exception ex){
                insertWindowSqlSec = null;
                return  new ApiResponse(false,"[insertBatchAoiComponentTableService]"+ex.getMessage(),"FAIL");
            }

        }
        insertWindowSqlSec = null;
        insertWindowSqlSec = null;
        lstComponent = null;
        System.gc();
        return new ApiResponse(true,null,"OK");
    }

}


package com.sinictek.restfulapi.api;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.AFov;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.model.queryBean.AoiFovQueryBean;
import com.sinictek.restfulapi.service.AFovService;
import com.sinictek.restfulapi.service.APcbService;
import com.sinictek.restfulapi.util.StringTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
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
public class AFovController {

    @Resource
    APcbService aPcbService;

    @Resource
    AFovService aFovService;

    //private static Logger logger = LoggerFactory.getLogger(AFovController.class);

    @PostMapping("insertAoiPcbFovImageInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertAFovTable(@RequestBody AoiFovQueryBean aoiFovQueryBean){
        boolean bIsInertFovSuccess = false;
        //ApiResponse apiResponse = ApiResponse.success(bIsInertFovSuccess,"","OK");
        if(aoiFovQueryBean!=null){
            if(StringUtils.isEmpty(aoiFovQueryBean.getPcbIdLine())){
                return new ApiResponse(false,"PcbIdLine_IS_NULL",null);
            }
            else {
                //logger.info("[insertAoiPcbFovImageInfo->AoiFovQueryBean:]"+ JSON.toJSON(aoiFovQueryBean));
                String fovTableName = "a_fov_";
                fovTableName+= StringTimeUtils.getDateToYearMonthString(aoiFovQueryBean.getInspectStarttime()) ;
                //判断fov-tableName是否存在
                /*int iPcbCount = aPcbService.selectCount(Condition.create().eq("pcbIdLine",aoiFovQueryBean.getPcbIdLine())
                    .and()
                        .eq("fovTableName",fovTableName));
                if(iPcbCount>0){
                    apiResponse = doInsertAoiFovTable(aoiFovQueryBean,fovTableName);
                }else{
                    return new ApiResponse(false,"fovTableName_IS_NOT_EXISTS",null);
                }*/
                for (int i = 0; i < aoiFovQueryBean.getFovs().size(); i++) {
                    aoiFovQueryBean.getFovs().get(i).setCreate_time( StringTimeUtils.getDateToYearMonthDayString(aoiFovQueryBean.getFovs().get(i).getInspectStarttime()) );
                }
                bIsInertFovSuccess = aFovService.insertBatch(aoiFovQueryBean.getFovs(),25);
                //return new ApiResponse(bIsInertFovSuccess,"","");
            }
        }else{
            return new ApiResponse(false,"REQUEST_JSON_IS_NULL",null);
        }
        aoiFovQueryBean = null;
        return new ApiResponse(bIsInertFovSuccess,"",null);
    }
    private ApiResponse doInsertAoiFovTable(AoiFovQueryBean aoiFovQueryBean,String fovTableName){

        //创建fov表
        aFovService.createAoiFovTableService(fovTableName);
        //插入fov表
        String tmp = "";
        String strInsertAoiFovTableSqlSec="";
        for (int i = 0; i < aoiFovQueryBean.getFovs().size(); i++)
        {
//pcbIdLine,aoiMode,pcbImagePath,boardposition,pcbImageBase64,
            String strInsertAoiFovTableSql = "(";
            tmp = aoiFovQueryBean.getPcbIdLine()==null?"'',":"'"+aoiFovQueryBean.getPcbIdLine() +"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getAoiMode()==null?"'1',":"'"+aoiFovQueryBean.getAoiMode() +"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getPcbImagePath() == null?"'',":"'"+aoiFovQueryBean.getPcbImagePath()+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getBoardposition() == null?"'',":"'"+aoiFovQueryBean.getBoardposition()+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getPcbImageBase64() == null?"'',":"'"+aoiFovQueryBean.getPcbImageBase64()+"'," ;
            strInsertAoiFovTableSql += tmp ;
//fovIndex,fovposition,fovimageInfo,fovimageBase64,fov3dImageBase64,inspectStarttime,inspectEndtime,remark
            tmp = aoiFovQueryBean.getFovs().get(i).getFovIndex() == null?"'0',":"'"+aoiFovQueryBean.getFovs().get(i).getFovIndex()+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getFovs().get(i).getFovposition() == null?"'',":"'"+aoiFovQueryBean.getFovs().get(i).getFovposition()+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getFovs().get(i).getFovimageInfo() == null?"'',":"'"+aoiFovQueryBean.getFovs().get(i).getFovimageInfo() +"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getFovs().get(i).getFovimageBase64() == null?"'',":"'"+aoiFovQueryBean.getFovs().get(i).getFovimageBase64()+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getFovs().get(i).getFov3dImageBase64() == null?"'',":"'"+aoiFovQueryBean.getFovs().get(i).getFov3dImageBase64()+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getInspectStarttime()  == null?"'',":"'"+ StringTimeUtils.getDateToYearMonthDayString(aoiFovQueryBean.getInspectStarttime())+"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getInspectEndtime()  == null?"'',":"'"+StringTimeUtils.getDateToYearMonthDayString(aoiFovQueryBean.getInspectEndtime()) +"'," ;
            strInsertAoiFovTableSql += tmp ;
            tmp = aoiFovQueryBean.getFovs().get(i).getRemark()  == null?"''":"'"+aoiFovQueryBean.getFovs().get(i).getRemark() +"'" ;
            strInsertAoiFovTableSql += tmp ;
            strInsertAoiFovTableSql +=")";

            //批量处理优化
            if(i% 25 ==0  &&  i!=0)
            {
                strInsertAoiFovTableSqlSec += strInsertAoiFovTableSql;
                try {
                    //logger.info("do Batch start.." + i );
                    aFovService.insertBatchAoiFovTableService(fovTableName+"",strInsertAoiFovTableSqlSec);
                    //logger.info("do Batch end.." + i);
                }catch (Exception ex){
                    return  new ApiResponse(false,"[insertBatchAoiFovTable error]"+ex.getMessage(),"FAIL");
                }
                strInsertAoiFovTableSqlSec = "";
            }
            else{
                if(i == (aoiFovQueryBean.getFovs().size()-1)){
                    strInsertAoiFovTableSqlSec += strInsertAoiFovTableSql;
                }else{
                    strInsertAoiFovTableSqlSec += strInsertAoiFovTableSql + ",";
                }
            }
        }
        //如果批处理还未结束执行最后sql
        if(StringUtils.isEmpty(strInsertAoiFovTableSqlSec) == false){
            try {
                aFovService.insertBatchAoiFovTableService(fovTableName,strInsertAoiFovTableSqlSec);
            }catch (Exception ex){
                return  new ApiResponse(false,"[insertBatchAoiFovTable error]"+ex.getMessage(),"FAIL");
            }
        }

        return new ApiResponse(true,"","OK");
    }

}


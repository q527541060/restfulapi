package com.sinictek.restfulapi.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.*;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.AJobService;
import com.sinictek.restfulapi.service.ALineService;
import com.sinictek.restfulapi.service.APcbService;
import com.sinictek.restfulapi.util.StringTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;

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
public class APcbController {

    @Resource
    ALineService aLineService;

    @Resource
    AJobService aJobService;

    @Resource
    APcbService aPcbService;


    @PostMapping("insertAoiPcb")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertAPcb(@RequestBody APcb aPcb){

        boolean bIsInsertPcb = true;
        if(aPcb==null){
            return  new ApiResponse(false,"PCB_IS_NULL","FAIL");
        }else if(aPcb.getLineNo()==null || aPcb.getLineNo()==""){
            return new ApiResponse(false,"LineNo_IS_NULL","FAIL");
        }else if(aPcb.getJobName()==null || aPcb.getJobName()=="") {
            return new ApiResponse(false, "JobName_IS_NULL", "FAIL");
        }else if(aPcb.getPcbIdLine()==null || aPcb.getPcbIdLine()==""){
            return new ApiResponse(false, "PcbIdLine_IS_NULL", "FAIL");
        }
        else{
            try {
                //Insert Line
                if(aLineService.selectCount(Condition.create().eq("LineNo",aPcb.getLineNo()))>0){
                }else{
                    ALine aline = new ALine();
                    aline.setLineNo(aPcb.getLineNo());
                    aline.setCreateDate(aPcb.getInspectStarttime());
                    aline.setUpdateDate(aPcb.getInspectStarttime());
                    aline.setAoiMode(aPcb.getAoiMode());
                    aline.setCreate_time("21000101");
                    aLineService.insertOrUpdate(aline);
                    aline=null;
                }
                //Insert Job
                if(aJobService.selectCount(Condition.create().eq("jobName",aPcb.getJobName()))>0){
                }else{
                    AJob aJob = new AJob();
                    aJob.setJobName(aPcb.getJobName());
                    aJob.setCreatDate(aPcb.getInspectStarttime());
                    aJob.setUpdateDate(aPcb.getInspectStarttime());
                    aJob.setLineNo(aPcb.getLineNo());
                    aJob.setAoiMode(aPcb.getAoiMode());
                    aJob.setCreate_time("21000101");
                    aJobService.insertOrUpdate(aJob);
                    aJob=null;
                }
                String yyyyMMdd = StringTimeUtils.getDateToYearMonthDayString(aPcb.getInspectStarttime());
                String yyyyMM = StringTimeUtils.getDateToYearMonthString(aPcb.getInspectStarttime());
                aPcb.setComponentTableName("a_component_"+yyyyMM);
                aPcb.setWindowTableName("a_window_"+yyyyMMdd);
                aPcb.setFovTableName("a_fov_"+yyyyMM);
                aPcb.setCreate_time(yyyyMMdd);
                bIsInsertPcb =  aPcbService.insertOrUpdate(aPcb);
            }catch (Exception ex){
                aPcb=null;
                return  new ApiResponse(false,ex.getMessage(),"FAIL");
            }
        }
        aPcb=null;
        System.gc();
        return  new ApiResponse(bIsInsertPcb,"","OK");
    }


}


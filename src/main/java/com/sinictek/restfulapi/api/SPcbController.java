package com.sinictek.restfulapi.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SJob;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.SPcb;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SJobService;
import com.sinictek.restfulapi.service.SLineService;
import com.sinictek.restfulapi.service.SPcbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * spi-pcb表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/Api")
public class SPcbController {

    @Autowired
    SPcbService sPcbService;
    @Resource
    SLineService sLineService;
    @Autowired
    SJobService sJobService;

    @PostMapping("/insertSpiPcb")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiPcbTable(@RequestBody SPcb sPcb){
        boolean bIsInsertPcb = true;
        if(sPcb==null){
            return  new ApiResponse(false,"PCB_IS_NULL","FAIL");
        }else if(sPcb.getPadTableName()==null || sPcb.getPadTableName()==""){
            return  new ApiResponse(false,"PadTableName_IS_NULL","FAIL");
        }else if(sPcb.getLineNo()==null || sPcb.getLineNo()==""){
            new ApiResponse(false,"LineNo_IS_NULL","FAIL");
        }else if(sPcb.getJobName()==null || sPcb.getJobName()=="") {
            new ApiResponse(false, "JobName_IS_NULL", "FAIL");
        }
        else{
            try {
                //Insert Line
                if(sLineService.selectCount(Condition.create().eq("LineNo",sPcb.getLineNo()))>0){
                }else{
                    SLine sline = new SLine();
                    sline.setLineNo(sPcb.getLineNo());
                    sline.setCreateDate(sPcb.getInspectStarttime());
                    sline.setUpdateDate(sPcb.getInspectStarttime());
                    sLineService.insertOrUpdate(sline);
                }
                //Insert Job
                if(sJobService.selectCount(Condition.create().eq("jobName",sPcb.getJobName()))>0){
                }else{
                    SJob sJob = new SJob();
                    sJob.setJobName(sPcb.getJobName());
                    sJob.setCreatDate(sPcb.getInspectStarttime());
                    sJob.setUpdateDate(sPcb.getInspectStarttime());
                    sJob.setLineNo(sPcb.getLineNo());
                    sJobService.insertOrUpdate(sJob);
                }
                 bIsInsertPcb =  sPcbService.insertOrUpdate(sPcb);
            }catch (Exception ex){
                return  new ApiResponse(false,ex.getMessage(),"FAIL");
            }
        }
        return  new ApiResponse(bIsInsertPcb,"","OK");
    }

}


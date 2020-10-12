package com.sinictek.restfulapi.api;


//import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SJob;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SJobService;
import com.sinictek.restfulapi.service.SLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * job总表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/Api")
public class SJobController {

    @Autowired
    SJobService sJobService;

   /* @GetMapping("/job")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getJob(){
        List<SLine> lst = sLineService.selectList(null);
        return new ApiResponse(true,"",lst);
    }*/
    /***
     * insertjob
     * @param sJob
     * @return
     */
    @PostMapping("/insertSpiJob")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiJobTable(@RequestBody SJob sJob){

        if(sJob==null){
            return new ApiResponse(false,"job_is_null","FAIL");

        }
        boolean bIsSuccess  =  true;
        try {


            if(sJobService.selectCount(Condition.create().eq("jobName",sJob.getJobName()))>0){

            }else{
                bIsSuccess = sJobService.insertOrUpdate(sJob);
            }
        }catch (Exception ex){
            return new ApiResponse(false,ex.getMessage(),"FAIL");
        }
        return new ApiResponse(bIsSuccess,"","OK");

    }

}


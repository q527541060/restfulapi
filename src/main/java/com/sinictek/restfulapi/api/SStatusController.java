package com.sinictek.restfulapi.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SJob;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.SStatus;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SLineService;
import com.sinictek.restfulapi.service.SStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

/**
 * <p>
 * spi-设备状态 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Controller
@RequestMapping("/Api")
public class SStatusController {

    @Autowired
    SStatusService sStatusService;
    @Resource
    SLineService sLineService;
    @PostMapping("/insertSpiStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiStatus(@RequestBody SStatus sStatus){

        boolean bIsInsertsStatus = true;
        if(sStatus==null){
            return  new ApiResponse(false,"PCB_IS_NULL","FAIL");
        }
        else{
            try {
                //Insert Line
                if(sLineService.selectCount(Condition.create().eq("LineNo",sStatus.getLineNo()))>0){
                }else{
                    SLine sline = new SLine();
                    sline.setLineNo(sStatus.getLineNo());
                    sline.setCreateDate(sStatus.getUpdateTime());
                    sline.setUpdateDate(sStatus.getUpdateTime());
                    sLineService.insertOrUpdate(sline);
                }

                bIsInsertsStatus =  sStatusService.insertOrUpdate(sStatus);
            }catch (Exception ex){
                return  new ApiResponse(false,ex.getMessage(),"FAIL");
            }
        }
        return  new ApiResponse(bIsInsertsStatus,"","OK");

    }

}


package com.sinictek.restfulapi.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.ALine;
import com.sinictek.restfulapi.model.AStatus;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.ALineService;
import com.sinictek.restfulapi.service.AStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * aoi-设备状态 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Controller
@RequestMapping("/Api")
public class AStatusController {

    @Autowired
    AStatusService statusService;
    @Autowired
    ALineService aLineService;

    @PostMapping("/insertAoiStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiStatus(@RequestBody AStatus aStatus){

        boolean bIsInsertsStatus = true;
        if(aStatus==null){
            return  new ApiResponse(false,"AStatus_IS_NULL","FAIL");
        }else if(aStatus.getLineNo()==null || aStatus.getLineNo()==""){
            return  new ApiResponse(false,"ALineNo_IS_NULL","FAIL");
        }
        else{
            try {
                //Insert Line
                if(aLineService.selectCount(Condition.create().eq("LineNo",aStatus.getLineNo()))>0){
                }else{
                    ALine aline = new ALine();
                    aline.setLineNo(aStatus.getLineNo());
                    aline.setCreateDate(aStatus.getUpdateTime());
                    aline.setUpdateDate(aStatus.getUpdateTime());
                    aLineService.insertOrUpdate(aline);
                }
                bIsInsertsStatus =  statusService.insertOrUpdate(aStatus);
            }catch (Exception ex){
                return  new ApiResponse(false,ex.getMessage(),"FAIL");
            }
        }
        return  new ApiResponse(bIsInsertsStatus,"","OK");

    }


}


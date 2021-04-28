package com.sinictek.restfulapi.api;

import com.sinictek.restfulapi.model.SRealtimeconfig;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SRealtimeconfigService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/Api")
public class SRealtimeconfigController {

    @Resource
    SRealtimeconfigService sRealtimeconfigService;


    @PostMapping("/insertSpiRealtimeconfig")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiRealtimeconfig( @RequestBody SRealtimeconfig sRealtimeconfig){

        boolean bIsSuccess = false;
        String message = null;
        if(sRealtimeconfig!=null){
            try {
                bIsSuccess = sRealtimeconfigService.insert(sRealtimeconfig);
            }catch (Exception e){
                message = e.getMessage();
            }finally {
                sRealtimeconfig = null;
            }
        }else{
            message = "SRealtimeconfig_IS_NULL";
            bIsSuccess = false;
        }

        return  new ApiResponse(bIsSuccess,message, null);
    }


}


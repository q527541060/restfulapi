package com.sinictek.restfulapi.api;


import com.sinictek.restfulapi.model.SRealtimeautorefresh;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SRealtimeautorefreshService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
public class SRealtimeautorefreshController {

    @Resource
    SRealtimeautorefreshService sRealtimeautorefreshService;

    @PostMapping("insertSpiRealTimeAutoRefresh")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiRealTimeAutoRefresh(@RequestBody SRealtimeautorefresh sRealtimeautorefresh){
        String message=null;
        boolean bIsSuccess = false;
        if(sRealtimeautorefresh!=null){

            try{
                bIsSuccess = sRealtimeautorefreshService.insert(sRealtimeautorefresh);
            }catch (Exception e){
                message = e.getMessage();
            }finally {
                sRealtimeautorefresh = null;
            }
        }else{
            message = "SRealtimeautorefresh_IS_NULL";
        }
        return  new ApiResponse(bIsSuccess,message,"");
    }

}


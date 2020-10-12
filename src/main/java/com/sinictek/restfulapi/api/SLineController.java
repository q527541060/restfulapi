package com.sinictek.restfulapi.api;

//import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SLineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 线体总表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/Api")
public class SLineController {

    @Resource
    SLineService sLineService;
    /***
     * 插入spi线体
     * @param sline
     * @return
     */
    @PostMapping("/insertSpiLine")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiLineTable(@RequestBody SLine sline){

        //DateUtil.getTimeZone(sline.getCreateDate());
        if(sline==null){
            return new ApiResponse(false,"line_is_null","FAIL");
        }
        boolean bIsSuccess  =  true;
        try {
            if(sLineService.selectCount(Condition.create().eq("LineNo",sline.getLineNo()))>0){

            }else{
                bIsSuccess = sLineService.insertOrUpdate(sline);
            }
        }catch (Exception ex){
            return new ApiResponse(false,ex.getMessage(),"FAIL");
        }
        return new ApiResponse(bIsSuccess,"","OK");
    }
}


package com.sinictek.restfulapi.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.ALine;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.ALineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * 线体总表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Controller
@RequestMapping("/Api")
public class ALineController {

    @Autowired
    ALineService aLineService;
    @PostMapping("/insertAoiLine")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiLineTable(@RequestBody ALine sline){

        //DateUtil.getTimeZone(sline.getCreateDate());
        if(sline==null){
            return new ApiResponse(false,"line_is_null","FAIL");
        }
        boolean bIsSuccess  =  true;
        try {
            if(aLineService.selectCount(Condition.create().eq("LineNo",sline.getLineNo()))>0){

            }else{
                bIsSuccess = aLineService.insertOrUpdate(sline);
            }
        }catch (Exception ex){
            return new ApiResponse(false,ex.getMessage(),"FAIL");
        }
        return new ApiResponse(bIsSuccess,"","OK");
    }
}


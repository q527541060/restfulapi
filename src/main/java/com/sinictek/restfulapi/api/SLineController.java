package com.sinictek.restfulapi.api;

//import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SLineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @PostMapping(value="/insertSpiLine",produces = "application/json;charset=utf-8")
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
                sline.setCreate_time("21000101");
                bIsSuccess = sLineService.insertOrUpdate(sline);
            }
        }catch (Exception ex){
            return new ApiResponse(false,ex.getMessage(),"FAIL");
        }
        return new ApiResponse(bIsSuccess,"","OK");
    }


    @GetMapping(value="/getSpiLine",produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getSpiLineTable(){

        return new ApiResponse(true,"",sLineService.selectList(null));
    }

    @GetMapping("/updateSpiLine")
    //@ResponseStatus(HttpStatus.CREATED)
    public ApiResponse updateSpiLineTable(){
        List<SLine> lineList = sLineService.selectList(null) ;
        for (int i = 0; i < lineList.size(); i++) {
            SLine sLine = new SLine();
            sLine.setLineContent("你走开 我不认识你。。。");
            //sLine.setCreate_time("21000101");
            sLine.setId(lineList.get(i).getId());
            sLine.setCreateDate(new Date());
            sLine.setUpdateDate(new Date());
            sLine.setLineNo("SMT3");
            sLine.setRemark("sadf");
            sLineService.update(sLine,Condition.create().eq("lineNo",sLine.getLineNo()));
        }

        return new ApiResponse(true,"",lineList);
    }
}


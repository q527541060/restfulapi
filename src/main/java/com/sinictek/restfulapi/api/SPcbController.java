package com.sinictek.restfulapi.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.restfulapi.model.SJob;
import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.model.SPcb;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SJobService;
import com.sinictek.restfulapi.service.SLineService;
import com.sinictek.restfulapi.service.SPadService;
import com.sinictek.restfulapi.service.SPcbService;
import com.sinictek.restfulapi.util.StringTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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

    @Resource
    SPcbService sPcbService;
    @Resource
    SLineService sLineService;
    @Resource
    SJobService sJobService;

    @Autowired
    SPadService sPadService;

    @PostMapping("/insertSpiPcb")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiPcbTable(@RequestBody SPcb sPcb){

        boolean bIsInsertPcb = true;
        if(sPcb==null){
            return  new ApiResponse(false,"PCB_IS_NULL","FAIL");
        }else if(sPcb.getPadTableName()==null || sPcb.getPadTableName()==""){
            return  new ApiResponse(false,"PadTableName_IS_NULL","FAIL");
        }else if(sPcb.getLineNo()==null || sPcb.getLineNo()==""){
            return new ApiResponse(false,"LineNo_IS_NULL","FAIL");
        }else if(sPcb.getJobName()==null || sPcb.getJobName()=="") {
            return new ApiResponse(false, "JobName_IS_NULL", "FAIL");
        }else if(sPcb.getPcbIdLine()==null || sPcb.getPcbIdLine()==""){
            return new ApiResponse(false, "PcbIdLine_IS_NULL", "FAIL");
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
                    sline.setCreate_time("21000101");
                    sLineService.insertOrUpdate(sline);

                    sline = null;
                }
                //Insert Job
                if(sJobService.selectCount(Condition.create().eq("jobName",sPcb.getJobName()))>0){
                }else{
                    SJob sJob = new SJob();
                    sJob.setJobName(sPcb.getJobName());
                    sJob.setCreatDate(sPcb.getInspectStarttime());
                    sJob.setUpdateDate(sPcb.getInspectStarttime());
                    sJob.setLineNo(sPcb.getLineNo());
                    sJob.setCreate_time("21000101");
                   // sJob.setCreate_time(StringTimeUtils.getDateToYearMonthDayString(sPcb.getInspectStarttime()));
                    sJobService.insertOrUpdate(sJob);

                    sJob =null;
                }
                sPcb.setCreate_time(StringTimeUtils.getDateToYearMonthDayString(sPcb.getInspectStarttime()));
                 bIsInsertPcb =  sPcbService.insertOrUpdate(sPcb);
            }catch (Exception ex){
                sPcb = null;
                return  new ApiResponse(false,ex.getMessage(),"FAIL");
            }
        }
        sPcb = null;
        System.gc(); //doGc
        return  new ApiResponse(bIsInsertPcb,"","OK");
    }



    @GetMapping("/pcbList")
    @ResponseBody
    public ApiResponse getPcbList(){

        return new ApiResponse(true,"",sPcbService.selectList(null));
    }
    @GetMapping("/padList")
    @ResponseBody
    public ApiResponse getPadist(){

        //List<SPad> sPadList = sPadService.selectList(null);
        return new ApiResponse(true,"", sPadService.selectList(null));
    }


}


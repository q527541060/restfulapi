package com.sinictek.restfulapi.model.queryBean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/12/1 17:51
 * @Version 1.0
 */
@Getter
@Setter
public class SpiComponentAndPadBean {

    private String pcbidLine;
    private String jobName;
    private String lineNo;
    private String componentID;
    private String arrayID;            //增加arrayID
    private String pinNumber;       //拼脚
    private String packageType; //封装类型
    private String result;           //检测结果
    private String shapeType;     //形状
    private String MaskPattern; //PE元器件内每个点的位置 可查询spidb-Tbshape表获取  也可留空,
    private String comImagePath;//192.168.1.3/1.JPG",      //如果图片保存模式,共享形式 需要填入 否则可留空
    private String comImageBase64;
    private String inspectStarttime;			   //板检测开始时间
    private String inspectEndtime;     		   //板检测结束时间
    private String remark;
    private List<SpiPads> pads;


}

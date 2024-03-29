package com.sinictek.restfulapi.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.mysql.jdbc.Clob;
import lombok.Data;
import org.apache.ibatis.mapping.FetchType;

import java.awt.*;
import java.sql.Blob;


import java.io.Serializable;

/**
 * <p>
 * 焊盘
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Data
@TableName(value = "s_pad")//,keepGlobalPrefix=true
public class SPad implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String padId;
    private String pcbidLine;
    private Long padIndex;
    private String componentId;
    private String arrayId;
    private String padInspectResult;
    private String defectTypeCode;
    private String defectTypeName;
    private String padImagePath;
    private byte[] pad2dImage;
    private byte[] pad3dImage;
    private Double height;
    private Double area;
    private Double volume;
    private Double offsetx;
    private Double offsety;
    private Double perHeight;
    private Double perArea;
    private Double perVolume;
    private Double perOffsetx;
    private Double perOffsety;
    private Double shape;
    private Integer bridgeType;
    private Double uHeight;
    private Double lHeight;
    private Double uArea;
    private Double lArea;
    private Double uVolume;
    private Double lVolume;
    private Double uOffsetx;
    private Double uOffsety;
    private Double uPerHeight;
    private Double lPerHeight;
    private Double uPerArea;
    private Double lPerArea;
    private Double uPerVolume;
    private Double lPerVolume;
    private Double uPerOffsetx;
    private Double uPerOffsety;
    private Long padTableID;
    private Long componentTableID;
    private String remark;
    private String pad2dImageBase64;
    private String  pad3dImageBase64;
    private String create_time;


}

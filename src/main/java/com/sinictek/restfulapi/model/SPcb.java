package com.sinictek.restfulapi.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * spi-pcb表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Data
@TableName("s_pcb")
public class SPcb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pcbIdLine;
    private String lineNo;
    private String jobName;
    private Integer laneNo;
    private String inspectResult;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private Double boardWidth;
    private Double boardLength;
    private String boardBarcode;
    private String componentTableName;
    private String padTableName;
    private String arrayBarcode;
    private String arrayWidth;
    private String arrayLength;
    private String arrayinspectResult;
    private Integer totalpadCount;
    private Integer passpadCount;
    private Integer ngpadCount;
    private Integer goodpadCount;
    private Integer shiftyCount;
    private Integer bridgeCount;
    private Integer shapeerrorCount;
    private Integer smearedCount;
    private Integer coplanarityCount;
    private Integer prebridgeCount;
    private Integer padareapercentCount;
    private Integer shiftxCount;
    private Integer otherCount;
    private Integer lowareaCount;
    private Integer overareaCount;
    private Integer lowheightCount;
    private Integer overheightCount;
    private Integer excessCount;
    private Integer insufficientCount;
    private Integer missingCount;
    private Double hCpk;
    private Double aCpk;
    private Double vcpk;
    private Double shithxCpk;
    private Double shithyCpk;
    private String ucl;
    private String lcl;
    private String remark;

    private String create_time;



}

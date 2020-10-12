package com.sinictek.restfulapi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sinictek.restfulapi.model.SPad;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 焊盘 Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
public interface SPadMapper extends BaseMapper<SPad> {


    @Select("<script>" +
            "CREATE TABLE IF NOT EXISTS `db_spm`.${padTableName}" +
            "(  `id` bigint(255)  NOT NULL AUTO_INCREMENT," +
                " `padId` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                " `pcbidLine` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL," +
                " `padIndex` bigint(20) NULL DEFAULT NULL," +
                "`componentId` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "`arrayId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "`padInspectResult` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "`defectTypeCode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "`defectTypeName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "`padImagePath` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "`pad2dImage` blob NULL," +
                "`pad3dImage` blob NULL," +
                "`height` double NULL DEFAULT NULL," +
                "`area` double NULL DEFAULT NULL," +
                "`volume` double NULL DEFAULT NULL," +
                "`offsetx` double NULL DEFAULT NULL," +
                "`offsety` double NULL DEFAULT NULL," +
                "`perHeight` double NULL DEFAULT NULL," +
                "`perArea` double NULL DEFAULT NULL," +
                "`perVolume` double NULL DEFAULT NULL," +
                "`perOffsetx` double NULL DEFAULT NULL," +
                "`perOffsety` double NULL DEFAULT NULL," +
                "`shape` double NULL DEFAULT NULL," +
                "`bridgeType` int(11) NULL DEFAULT NULL," +
                "`uHeight` double NULL DEFAULT NULL," +
                "`lHeight` double NULL DEFAULT NULL," +
                "`uArea` double NULL DEFAULT NULL," +
                " `lArea` double NULL DEFAULT NULL," +
                " `uVolume` double NULL DEFAULT NULL," +
                "  `lVolume` double NULL DEFAULT NULL," +
                " `uOffsetx` double NULL DEFAULT NULL," +
                " `uOffsety` double NULL DEFAULT NULL," +
                " `uPerHeight` double NULL DEFAULT NULL," +
                " `lPerHeight` double NULL DEFAULT NULL," +
                " `uPerArea` double NULL DEFAULT NULL," +
                " `lPerArea` double NULL DEFAULT NULL," +
                " `uPerVolume` double NULL DEFAULT NULL," +
                " `lPerVolume` double NULL DEFAULT NULL," +
                " `uPerOffsetx` double NULL DEFAULT NULL," +
                " `uPerOffsety` double NULL DEFAULT NULL," +
                " `padTableID` bigint(20) NULL DEFAULT NULL," +
                " `componentTableID` bigint(20) NULL DEFAULT NULL," +
                " `pad2dImageBase64` longtext Null DEFAULT NULL," +
                " `pad3dImageBase64` longtext Null DEFAULT NULL,"+
                " `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                "  PRIMARY KEY (`id`) USING BTREE," +
                " INDEX `fore_${padTableName}_pcb_pcbidLine`(`pcbidLine`) USING BTREE," +
                "  CONSTRAINT `fore_${padTableName}_pcb_pcbidLine` FOREIGN KEY (`pcbidLine`) REFERENCES `db_spm`.`s_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE" +
            ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '焊盘' ROW_FORMAT = Compact;" +

            "</script>")
    public void createSpiPadTable(@Param("padTableName")String padTableName);


    @Select("<script>INSERT INTO s_pad_${s_pad_yyyyMMdd}(" +
            "padId,pcbidLine,padIndex,componentId,arrayId,padInspectResult,defectTypeCode,defectTypeName,padImagePath,pad2dImage," +
            "pad3dImage,height,area,volume,offsetx,offsety,perHeight,perArea,perVolume,perOffsetx," +
            "perOffsety,shape,bridgeType,uHeight,lHeight,uArea,lArea,uVolume,lVolume,uOffsetx," +
            "uOffsety,uPerHeight,lPerHeight,uPerArea,lPerArea,uPerVolume,lPerVolume,uPerOffsetx,uPerOffsety,padTableID," +
            "componentTableID,remark,pad2dImageBase64,pad3dImageBase64) " +
            " values${sql_pad}" +
            "</script>")
    public void insertSpiPadBatch(@Param("s_pad_yyyyMMdd") String s_pad_yyyy, @Param("sql_pad") String sql_pad);

}

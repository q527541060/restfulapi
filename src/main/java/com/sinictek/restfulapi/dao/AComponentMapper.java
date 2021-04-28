package com.sinictek.restfulapi.dao;

import com.sinictek.restfulapi.model.AComponent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
public interface AComponentMapper extends BaseMapper<AComponent> {

    @Select("<script>" +
            "CREATE TABLE IF NOT EXISTS `db_spm`.${componentTable}  ( " +
            "`id` bigint(20) NOT NULL AUTO_INCREMENT, " +
            "`pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`aoiMode` int(1) NULL DEFAULT NULL, " +
            "`arrayIndex` int(5) NULL DEFAULT NULL, " +
            "`fovIndex` int(5) NULL DEFAULT NULL, " +
            "`partdesignate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`partno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`packagetype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`componentposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
            "`componentType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`result` int(1) NULL DEFAULT NULL, " +
            "`defectType` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`imageInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
            "`valueInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
            "`height` double NULL DEFAULT NULL, " +
            "`perheight` double NULL DEFAULT NULL, " +
            "`xshift` double NULL DEFAULT NULL, " +
            "`perxshift` double NULL DEFAULT NULL, " +
            "`yshift` double NULL DEFAULT NULL, " +
            "`peryshift` double NULL DEFAULT NULL, " +
            "`angle` double NULL DEFAULT NULL, " +
            "`perangle` double NULL DEFAULT NULL, " +
            "`volume` double NULL DEFAULT NULL, " +
            "`bigvolume` double NULL DEFAULT NULL, " +
            "`planeness` double NULL DEFAULT NULL, " +
            "`uplanenesswindowid` double NULL DEFAULT NULL, " +
            "`lplanenesswindowid` double NULL DEFAULT NULL, " +
            "`linearity` double NULL DEFAULT NULL, " +
            "`ulinearitywindowid` double NULL DEFAULT NULL, " +
            "`llinearitywindowid` double NULL DEFAULT NULL, " +
            "`similarity` double NULL DEFAULT NULL, " +
            "`polarity` double NULL DEFAULT NULL, " +
            "`area` double NULL DEFAULT NULL, " +
            "`bigarea` double NULL DEFAULT NULL, " +
            "`perarea` double NULL DEFAULT NULL, " +
            "`inspectStarttime` datetime NULL DEFAULT NULL, " +
            "`inspectEndtime` datetime NULL DEFAULT NULL, " +
            "`comImageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`com3dImageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,"+
            "`historyDefectRecord` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,"+
            "`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "PRIMARY KEY (`id`) USING BTREE, " +
            "INDEX `FK_Reference_${componentTable}_pcbIdLine_apcb`(`pcbIdLine`) USING BTREE, " +
        "CONSTRAINT `FK_Reference_${componentTable}_pcbIdLine_apcb` FOREIGN KEY (`pcbIdLine`) REFERENCES `db_spm`.`a_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE " +
        ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact; " +
            "</script>")
    public void creatAoiComponentTable(@Param("componentTable") String componentTable);

    @Select("<script>" +
                " INSERT INTO `db_spm`.${componentTable}(pcbIdLine,aoiMode,arrayIndex,fovIndex,partdesignate,partno,packagetype,componentposition,componentType,result,defectType,imageInfo,valueInfo,height,perheight,xshift,perxshift,yshift,peryshift,angle,perangle,volume,bigvolume,planeness,uplanenesswindowid,lplanenesswindowid,linearity,ulinearitywindowid,llinearitywindowid,similarity,polarity,area,bigarea,perarea,inspectStarttime,inspectEndtime,comImageBase64,com3dImageBase64,historyDefectRecord,remark) values${componentSql} " +
            "</script>")
    public void insertAoiComponentBatch(@Param("componentTable") String componentTable,@Param("componentSql")String componentSql);

}

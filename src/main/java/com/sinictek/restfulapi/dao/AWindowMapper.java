package com.sinictek.restfulapi.dao;

import com.sinictek.restfulapi.model.AWindow;
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
public interface AWindowMapper extends BaseMapper<AWindow> {

    @Select("<script>" +
            "CREATE TABLE IF NOT EXISTS `db_spm`.${windowTable}  ( " +
            "`id` bigint(20) NOT NULL AUTO_INCREMENT, " +
            "`comid` bigint(20) NULL DEFAULT NULL, " +
            "`pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`aoiMode` int(1) NULL DEFAULT NULL, " +
            "`arrayIndex` int(5) NULL DEFAULT NULL, " +
            "`fovIndex` int(5) NULL DEFAULT NULL, " +
            "`partdesignate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, " +
            "`windowIndex` int(20) NULL DEFAULT NULL, " +
            "`result` int(1) NULL DEFAULT NULL, " +
            "`windowposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
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
            "`pervolume` double NULL DEFAULT NULL, " +
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
            "`algorithmparam` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
            "`winImageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
            "`win3dImageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL, " +
            "`historyDefectRecord` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,"+
            "PRIMARY KEY (`id`) USING BTREE, " +
            "INDEX `FK_Reference_${windowTable}_pcbIdLine_apcb`(`pcbIdLine`) USING BTREE, " +
            "CONSTRAINT `FK_Reference_${windowTable}_pcbIdLine_apcb` FOREIGN KEY (`pcbIdLine`) REFERENCES `db_spm`.`a_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE " +
") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact; " +
            "</script>")
    public void creatAoiWindowTable(@Param("windowTable") String windowTable);

    @Select("<script>" +
            "INSERT INTO `db_spm`.${windowTable}(pcbIdLine,aoiMode,arrayIndex,fovIndex,partdesignate,windowIndex,result,windowposition,valueInfo,height,perheight,xshift,perxshift,yshift,peryshift,angle,perangle,volume,bigvolume,pervolume,planeness,uplanenesswindowid,lplanenesswindowid,linearity,ulinearitywindowid,llinearitywindowid,similarity,polarity,area,bigarea,perarea,algorithmparam,winImageBase64,historyDefectRecord,win3dImageBase64) VALUES${windowSql}"+
            "</script>")
    public void insertAoiWindowBatch(@Param("windowTable") String windowTable,@Param("windowSql")String windowSql);
}

package com.sinictek.restfulapi.dao;

import com.sinictek.restfulapi.model.AFov;
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
public interface AFovMapper extends BaseMapper<AFov> {


    @Select("<script>" +
            "CREATE TABLE IF NOT EXISTS `db_spm`.${fovTableName}  (" +
            "`id` bigint(20) NOT NULL AUTO_INCREMENT," +
            "`pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
            "`aoiMode` int(1) NULL DEFAULT NULL," +
            "`pcbImagePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
            "`boardposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`pcbImageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`fovIndex` int(11) NULL DEFAULT NULL," +
            "`fovposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`fovimageInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`fovimageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`fov3dImageBase64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
            "`inspectStarttime` datetime NULL DEFAULT NULL," +
            "`inspectEndtime` datetime NULL DEFAULT NULL," +
            "`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
            "PRIMARY KEY (`id`) USING BTREE," +
            "INDEX `FK_Reference_${fovTableName}_pcbIdLine_apcb`(`pcbIdLine`) USING BTREE," +
            " CONSTRAINT `FK_Reference_${fovTableName}_pcbIdLine_apcb` FOREIGN KEY (`pcbIdLine`) REFERENCES `db_spm`.`a_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE " +
") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;" +

            "</script>"
    )
    public void createAoiFovTable(@Param("fovTableName")String fovTableName);


    @Select("<script>"+
            "INSERT INTO ${fovTableName}(pcbIdLine,aoiMode,pcbImagePath,boardposition,pcbImageBase64,fovIndex,fovposition,fovimageInfo,fovimageBase64,fov3dImageBase64,inspectStarttime,inspectEndtime,remark) VALUES ${sqlFov}"+
            "</script>"
    )
    public void insertAoiFovTable(@Param("fovTableName")String fovTableName,@Param("sqlFov") String sqlFov);

}

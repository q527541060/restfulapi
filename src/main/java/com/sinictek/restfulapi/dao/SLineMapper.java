package com.sinictek.restfulapi.dao;

import com.sinictek.restfulapi.model.SLine;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 线体总表 Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
public interface SLineMapper extends BaseMapper<SLine> {

    @Select("<script> INSERT IGNORE INTO db_spm.s_line(LineNo,lineContent,createDate,updateDate,remark) " +
            "VALUES(${sline.LineNo},${sline.lineContent},${sline.createDate}," +
            "${sline.updateDate},${sline.remark}) " +
            "</script> ")
    public Boolean insertIgoreSpiLine(@Param("sline") SLine sline);
}

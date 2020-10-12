package com.sinictek.restfulapi.service;

import com.baomidou.mybatisplus.service.IService;
import com.sinictek.restfulapi.model.SPad;

import java.util.List;

/**
 * <p>
 * 焊盘 服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
public interface SPadService extends IService<SPad> {

    public void createSpiPadTable(String padTableName);
    public void addSpiPadBatch(String s_pad_yyyyMMdd, String sql_pad);
}

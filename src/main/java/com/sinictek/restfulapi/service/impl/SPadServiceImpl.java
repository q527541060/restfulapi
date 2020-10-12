package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.SPad;
import com.sinictek.restfulapi.dao.SPadMapper;
import com.sinictek.restfulapi.service.SPadService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 焊盘 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Service
public class SPadServiceImpl extends ServiceImpl<SPadMapper, SPad> implements SPadService {

    @Autowired
    SPadMapper sPadMapper;
    @Override
    public void createSpiPadTable(String padTableName) {
        sPadMapper.createSpiPadTable(padTableName);
    }


    @Override
    public void addSpiPadBatch(String s_pad_yyyyMMdd, String sql_pad) {
            sPadMapper.insertSpiPadBatch(s_pad_yyyyMMdd, sql_pad);
    }
}

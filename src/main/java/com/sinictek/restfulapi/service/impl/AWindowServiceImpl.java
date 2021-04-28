package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.AWindow;
import com.sinictek.restfulapi.dao.AWindowMapper;
import com.sinictek.restfulapi.service.AWindowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
@Service
public class AWindowServiceImpl extends ServiceImpl<AWindowMapper, AWindow> implements AWindowService {

    @Autowired
    AWindowMapper aWindowMapper;
    @Override
    public void creatAoiWindowTableService(String windowTable) {
        aWindowMapper.creatAoiWindowTable(windowTable);
    }

    @Override
    public void insertAoiWindowBatchService(String windowTable, String windowSql) {
        aWindowMapper.insertAoiWindowBatch(windowTable,windowSql);
    }
}

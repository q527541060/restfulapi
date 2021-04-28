package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.AComponent;
import com.sinictek.restfulapi.dao.AComponentMapper;
import com.sinictek.restfulapi.service.AComponentService;
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
public class AComponentServiceImpl extends ServiceImpl<AComponentMapper, AComponent> implements AComponentService {

    @Autowired
    AComponentMapper aComponentMapper;

    @Override
    public void creatAoiComponentTableService(String componentTableName) {
        aComponentMapper.creatAoiComponentTable(componentTableName);
    }

    @Override
    public void insertBatchAoiComponentTableService(String componentTable, String componentSql) {
        aComponentMapper.insertAoiComponentBatch(componentTable,componentSql);
    }
}

package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.AFov;
import com.sinictek.restfulapi.dao.AFovMapper;
import com.sinictek.restfulapi.service.AFovService;
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
public class AFovServiceImpl extends ServiceImpl<AFovMapper, AFov> implements AFovService {

    @Autowired
    AFovMapper aFovMapper;
    @Override
    public void createAoiFovTableService(String fovTableName) {
        aFovMapper.createAoiFovTable(fovTableName);
    }

    @Override
    public void insertBatchAoiFovTableService(String fovTableName, String sqlFov) {
        aFovMapper.insertAoiFovTable(fovTableName,sqlFov);
    }
}

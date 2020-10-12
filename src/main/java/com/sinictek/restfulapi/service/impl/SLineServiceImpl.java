package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.SLine;
import com.sinictek.restfulapi.dao.SLineMapper;
import com.sinictek.restfulapi.service.SLineService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 线体总表 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Service
public class SLineServiceImpl extends ServiceImpl<SLineMapper, SLine> implements SLineService {

    @Autowired
    SLineMapper sLineMapper;
    @Override
    public Boolean insertIgoreSpiLine(SLine sLine) {
        return sLineMapper.insertIgoreSpiLine(sLine);
    }
}

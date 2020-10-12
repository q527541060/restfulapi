package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.SJob;
import com.sinictek.restfulapi.dao.SJobMapper;
import com.sinictek.restfulapi.service.SJobService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * job总表 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Service
public class SJobServiceImpl extends ServiceImpl<SJobMapper, SJob> implements SJobService {

}

package com.sinictek.restfulapi.service.impl;

import com.sinictek.restfulapi.model.SStatus;
import com.sinictek.restfulapi.dao.SStatusMapper;
import com.sinictek.restfulapi.service.SStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * spi-设备状态 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@Service
public class SStatusServiceImpl extends ServiceImpl<SStatusMapper, SStatus> implements SStatusService {

}

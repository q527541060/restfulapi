package com.sinictek.restfulapi.service;

import com.sinictek.restfulapi.model.SLine;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 线体总表 服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
public interface SLineService extends IService<SLine> {

    Boolean insertIgoreSpiLine(SLine sLine);
}

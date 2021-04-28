package com.sinictek.restfulapi.service;

import com.sinictek.restfulapi.model.AComponent;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
public interface AComponentService extends IService<AComponent> {

    public void creatAoiComponentTableService(String componentTableName);

    public void insertBatchAoiComponentTableService(String componentTable,String componentSql);
}

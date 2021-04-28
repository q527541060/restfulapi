package com.sinictek.restfulapi.service;

import com.sinictek.restfulapi.model.AFov;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
public interface AFovService extends IService<AFov> {

    public void createAoiFovTableService(String fovTableName);
    public void insertBatchAoiFovTableService(String fovTableName,String sqlFov);

}

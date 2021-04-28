package com.sinictek.restfulapi.service;

import com.sinictek.restfulapi.model.AWindow;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
public interface AWindowService extends IService<AWindow> {

    public void creatAoiWindowTableService(String windowTable);

    public void insertAoiWindowBatchService( String windowTable,String windowSql);
}

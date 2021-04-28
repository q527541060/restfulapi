package com.sinictek.restfulapi.service;

import com.baomidou.mybatisplus.service.IService;
import com.sinictek.restfulapi.model.SUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SUserService extends IService<SUser> {
    public List<SUser> getTestAllUser();
    public int addTestAllUser(SUser sUser);

}

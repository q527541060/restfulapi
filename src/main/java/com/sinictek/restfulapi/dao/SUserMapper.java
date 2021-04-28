package com.sinictek.restfulapi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sinictek.restfulapi.model.SUser;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SUserMapper extends BaseMapper<SUser> {

    public List<SUser> getTestAllUser();
}

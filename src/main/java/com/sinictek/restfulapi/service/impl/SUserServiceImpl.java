package com.sinictek.restfulapi.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sinictek.restfulapi.dao.SUserMapper;
import com.sinictek.restfulapi.model.SUser;
import com.sinictek.restfulapi.service.SUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Service

@CacheConfig(cacheNames = "spmUserCache")
@Slf4j
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements SUserService {

    @Autowired
    SUserMapper sUserMapper;

    @Override
    @Cacheable(key = "'getAllSUser'")
    public List<SUser> getTestAllUser() {
        try {
            //模拟耗时操作
            log.info("queryUserstart---");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<SUser> lstUser =  sUserMapper.selectList(null);
        log.info("queryUserEnd---");
        return lstUser;
    }

    @Override
    @CachePut(key = "'getAllSUser'")
    public int addTestAllUser(SUser sUser) {
        int iTmp = sUserMapper.insert(sUser);
        return iTmp;
    }
}

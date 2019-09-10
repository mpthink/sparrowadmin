package com.think.sparrowadmin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.system.entity.SysSetting;
import com.think.sparrowadmin.system.mapper.SysSettingMapper;
import com.think.sparrowadmin.system.service.ISysSettingService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统设置表 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements ISysSettingService {

    @Cacheable(value = "settingCache")
    @Override
    public List<SysSetting> findAll() {
        return this.list(new QueryWrapper<SysSetting>().orderByAsc("sort"));
    }

}

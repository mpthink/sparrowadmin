package com.think.sparrowadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.system.entity.SysSetting;

import java.util.List;

/**
 * <p>
 * 系统设置表 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public interface ISysSettingService extends IService<SysSetting> {

    List<SysSetting> findAll();

}

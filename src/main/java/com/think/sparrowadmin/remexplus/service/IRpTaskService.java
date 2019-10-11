package com.think.sparrowadmin.remexplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * table for creating regular task 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
public interface IRpTaskService extends IService<RpTask> {

    IPage<RpTask> selectTaskPage(Page<RpTask> page, String search);
}

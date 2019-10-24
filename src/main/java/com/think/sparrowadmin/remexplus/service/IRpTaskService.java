package com.think.sparrowadmin.remexplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    List<Map<String,Object>> getTaskWithRecordTimes();

    List<Map<String,Object>> getTaskLastStatus();
}

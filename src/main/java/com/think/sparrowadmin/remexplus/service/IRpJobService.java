package com.think.sparrowadmin.remexplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.remexplus.entity.RpJob;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * jobs of task 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
public interface IRpJobService extends IService<RpJob> {

    List<Map<String, Object>> selectJobsByTaskRecordId(String id);
}

package com.think.sparrowadmin.remexplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * record task running 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
public interface IRpTaskRecordService extends IService<RpTaskRecord> {

    List<Map<String, Object>> selectTaskRecords();

}

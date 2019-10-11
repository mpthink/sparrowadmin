package com.think.sparrowadmin.remexplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;
import com.think.sparrowadmin.remexplus.mapper.RpTaskRecordMapper;
import com.think.sparrowadmin.remexplus.service.IRpTaskRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * record task running 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Service
public class RpTaskRecordServiceImpl extends ServiceImpl<RpTaskRecordMapper, RpTaskRecord> implements IRpTaskRecordService {

    @Override
    public List<Map<String, Object>> selectTaskRecords() {
        return baseMapper.selectTaskRecords();
    }
}

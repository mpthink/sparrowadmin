package com.think.sparrowadmin.remexplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.remexplus.entity.RpJob;
import com.think.sparrowadmin.remexplus.mapper.RpJobMapper;
import com.think.sparrowadmin.remexplus.service.IRpJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * jobs of task 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Service
public class RpJobServiceImpl extends ServiceImpl<RpJobMapper, RpJob> implements IRpJobService {

    @Autowired
    private RpJobMapper jobMapper;

    @Override
    public List<Map<String, Object>> selectJobsByTaskRecordId(String id) {
        return jobMapper.selectJobsByTaskRecordId(id);
    }

}

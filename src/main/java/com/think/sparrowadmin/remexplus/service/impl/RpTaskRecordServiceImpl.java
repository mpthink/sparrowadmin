package com.think.sparrowadmin.remexplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.remexplus.entity.RpTaskJob;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;
import com.think.sparrowadmin.remexplus.mapper.RpJobMapper;
import com.think.sparrowadmin.remexplus.mapper.RpTaskJobMapper;
import com.think.sparrowadmin.remexplus.mapper.RpTaskRecordMapper;
import com.think.sparrowadmin.remexplus.service.IRpTaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    @Autowired
    private RpTaskRecordMapper rpTaskRecordMapper;

    @Autowired
    private RpJobMapper rpJobMapper;

    @Autowired
    private RpTaskJobMapper rpTaskJobMapper;

    @Override
    public List<Map<String, Object>> selectTaskRecords() {
        return baseMapper.selectTaskRecords();
    }

    @Override
    public void removeRelatedTaskRecordAndJob(Serializable taskId) {

        QueryWrapper<RpTaskRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id", taskId);
        List<RpTaskRecord> rpTaskRecords = rpTaskRecordMapper.selectList(wrapper);
        rpTaskRecords.forEach(rpTaskRecord -> {
            QueryWrapper<RpTaskJob> rpTaskJobQueryWrapper = new QueryWrapper<>();
            rpTaskJobQueryWrapper.eq("task_record_id", rpTaskRecord.getTaskId());
            List<RpTaskJob> rpTaskJobs = rpTaskJobMapper.selectList(rpTaskJobQueryWrapper);
            rpTaskJobs.forEach(rpTaskJob -> rpJobMapper.deleteById(rpTaskJob.getJobId()));
            rpTaskJobMapper.delete(rpTaskJobQueryWrapper);
        });
        rpTaskRecordMapper.delete(wrapper);
    }

}

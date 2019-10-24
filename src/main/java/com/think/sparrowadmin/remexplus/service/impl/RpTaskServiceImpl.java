package com.think.sparrowadmin.remexplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;
import com.think.sparrowadmin.remexplus.mapper.RpJobMapper;
import com.think.sparrowadmin.remexplus.mapper.RpTaskJobMapper;
import com.think.sparrowadmin.remexplus.mapper.RpTaskMapper;
import com.think.sparrowadmin.remexplus.mapper.RpTaskRecordMapper;
import com.think.sparrowadmin.remexplus.service.IRpTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * table for creating regular task 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Service
public class RpTaskServiceImpl extends ServiceImpl<RpTaskMapper, RpTask> implements IRpTaskService {

    @Autowired
    private RpTaskMapper rpTaskMapper;

    @Autowired
    private RpTaskRecordMapper rpTaskRecordMapper;

    @Autowired
    private RpTaskJobMapper rpTaskJobMapper;

    @Autowired
    private RpJobMapper rpJobMapper;

    @Override
    public IPage<RpTask> selectTaskPage(Page<RpTask> page, String search) {
        page.setRecords(baseMapper.selectTaskList(page, search));
        return page;
    }

    @Override
    public List<Map<String, Object>> getTaskWithRecordTimes() {
        return rpTaskMapper.selectTaskWithRecordTimes();
    }

    @Override
    public List<Map<String, Object>> getTaskLastStatus() {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<RpTask> rpTasks = rpTaskMapper.selectList(null);
        for (RpTask rpTask : rpTasks) {
            Map<String, Object> map = new HashMap<>();
            QueryWrapper<RpTaskRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("task_id", rpTask.getId()).orderByDesc("gmt_create").last("limit 1");
            RpTaskRecord rpTaskRecord = rpTaskRecordMapper.selectOne(queryWrapper);
            Map<String, Object> jobResult = rpJobMapper.selectLastJobResult(rpTaskRecord.getId());
            if(jobResult != null){
                map.put("failed",jobResult.get("failed"));
                map.put("passed",jobResult.get("passed"));
                map.put("other",jobResult.get("other"));
            }else {
                map.put("failed", 0);
                map.put("passed", 0);
                map.put("other",0);
            }
            map.put("task", rpTask.getName());
            mapList.add(map);
        }
        return mapList;
    }

}

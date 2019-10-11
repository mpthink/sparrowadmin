package com.think.sparrowadmin.remexplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.mapper.RpTaskMapper;
import com.think.sparrowadmin.remexplus.service.IRpTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<RpTask> selectTaskPage(Page<RpTask> page, String search) {
        page.setRecords(baseMapper.selectTaskList(page, search));
        return page;
    }
}

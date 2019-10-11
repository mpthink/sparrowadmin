package com.think.sparrowadmin.remexplus.service.impl;

import com.think.sparrowadmin.remexplus.entity.RpTaskJob;
import com.think.sparrowadmin.remexplus.mapper.RpTaskJobMapper;
import com.think.sparrowadmin.remexplus.service.IRpTaskJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * relationship table between task and job 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Service
public class RpTaskJobServiceImpl extends ServiceImpl<RpTaskJobMapper, RpTaskJob> implements IRpTaskJobService {

}

package com.think.sparrowadmin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.system.entity.SysLog;
import com.think.sparrowadmin.system.mapper.SysLogMapper;
import com.think.sparrowadmin.system.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    public static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Override
    public void insertLog(String title, String uname, String url, String parms) {
        SysLog sysLog  =new SysLog();
        sysLog.setGmtCreate(LocalDateTime.now());
        sysLog.setTitle(title);
        sysLog.setUserName(uname);
        sysLog.setUrl(url);
        sysLog.setParams(parms);
        logger.debug("记录日志:"+sysLog.toString());
        sysLog.insert();
    }

}

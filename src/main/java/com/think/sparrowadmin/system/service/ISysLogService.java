package com.think.sparrowadmin.system.service;

import com.think.sparrowadmin.system.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * 记录日志
     * @param title
     * @param uname
     * @param url
     * @param parms
     */
    void insertLog(String title,String uname,String url,String parms);


}

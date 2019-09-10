package com.think.sparrowadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.system.entity.SysUserRole;

import java.util.Set;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 获取用户的角色
     * @param uid
     * @return
     */
    Set<String> findRolesByUid(String uid);

}

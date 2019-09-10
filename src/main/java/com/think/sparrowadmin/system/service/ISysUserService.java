package com.think.sparrowadmin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.system.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户
     */
    Page<SysUser> selectUserPage(Page<SysUser> page, String search);

    /**
     * 保存用户
     */
    void insertUser(SysUser user, String[] roleId);
    /**
     * 更新用户
     */
    void updateUser(SysUser sysUser, String[] roleId);
    /**
     * 删除用户
     */
    void delete(String id);

}

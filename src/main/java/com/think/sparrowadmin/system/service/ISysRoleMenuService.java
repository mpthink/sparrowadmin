package com.think.sparrowadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.system.entity.SysRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 角色授权
     */
    void addAuth(String roleId, String[] menuIds);

    /**
     * 获取指定角色的权限
     */
    List<SysRoleMenu> selectByRole(String roleId);

    Set<String> findMenusByUid(String id);

}

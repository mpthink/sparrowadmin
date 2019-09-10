package com.think.sparrowadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.think.sparrowadmin.system.entity.SysMenu;
import com.think.sparrowadmin.system.entity.vo.TreeMenu;
import com.think.sparrowadmin.system.entity.vo.TreeMenuAllowAccess;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取指定用户拥有的菜单
     */
    List<String> selectMenuIdsByuserId(String uid);
    /**
     * 获取指定用户的菜单
     * @param  menuIds 当前用户所在角色拥有的权限ID集合
     * @param  pid 菜单父ID
     */
    List<TreeMenu> selectTreeMenuByMenuIdsAndPid(List<String> menuIds, String pid);
    /**
     * 获取当前用户的菜单
     */
    List<TreeMenu> selectTreeMenuByUserId(String uid);
    /**
     * 获取指定用户拥有权限
     * @param  menuIds 该角色拥有的权限ID集合
     * @param  pid 菜单父ID
     */
    List<TreeMenuAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(List<String> menuIds, String pid);

}

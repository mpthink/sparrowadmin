package com.think.sparrowadmin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.system.entity.SysMenu;
import com.think.sparrowadmin.system.entity.vo.TreeMenu;
import com.think.sparrowadmin.system.entity.vo.TreeMenuAllowAccess;
import com.think.sparrowadmin.system.mapper.SysMenuMapper;
import com.think.sparrowadmin.system.mapper.SysRoleMenuMapper;
import com.think.sparrowadmin.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    /**
     * 菜单服务
     */
    @Autowired
    private SysMenuMapper sysMenuMapper;
    /**
     * 角色菜单关系服务
     */
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Cacheable(value = "permissionCache", key = "#uid")
    @Override
    public List<String> selectMenuIdsByuserId(String uid) {
        return sysMenuMapper.selectMenuIdsByuserId(uid);
    }


    @Cacheable(value = "menuCache", key = "#uid")
    @Override
    public List<TreeMenu> selectTreeMenuByUserId(String uid) {
        /**
         * 当前用户二级菜单权限
         */
        List<String> menuIds = sysRoleMenuMapper.selectRoleMenuIdsByUserId(uid);
        return selectTreeMenuByMenuIdsAndPid(menuIds, "0");
    }

    @Override
    public List<TreeMenu> selectTreeMenuByMenuIdsAndPid(final List<String> menuIds,String pid) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("pid", pid);
        queryWrapper.in("id", menuIds.size() > 0 ? menuIds : Arrays.asList("noMenus"));
        List<SysMenu> sysMenus = this.list(queryWrapper);
        List<TreeMenu> treeMenus = new ArrayList<>();
        for(SysMenu sysMenu : sysMenus){
            TreeMenu treeMenu = new TreeMenu();
            treeMenu.setSysMenu(sysMenu);
            if(sysMenu.getDeep() < 2){
                treeMenu.setChildren(selectTreeMenuByMenuIdsAndPid(menuIds,sysMenu.getId()));
            }
            treeMenus.add(treeMenu);
        }
        return treeMenus;
    }


    @Override
    public List<TreeMenuAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(
            final List<String> menuIds, String pid) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("pid", pid);
        List<SysMenu> sysMenus = this.list(queryWrapper);

        List<TreeMenuAllowAccess> treeMenuAllowAccesss = new ArrayList<>();
        for(SysMenu sysMenu : sysMenus){
            TreeMenuAllowAccess treeMenuAllowAccess = new TreeMenuAllowAccess();
            treeMenuAllowAccess.setSysMenu(sysMenu);
            /**
             * 是否有权限
             */
            if(menuIds.contains(sysMenu.getId())){
                treeMenuAllowAccess.setAllowAccess(true);
            }
            /**
             * 子节点
             */
            if(sysMenu.getDeep() < 3){
                treeMenuAllowAccess.setChildren(selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds,sysMenu.getId()));
            }
            treeMenuAllowAccesss.add(treeMenuAllowAccess);
        }
        return treeMenuAllowAccesss;
    }

}

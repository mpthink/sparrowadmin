/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.think.sparrowadmin.system.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author map6
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class InitSystemData {

    @Test
    public void initUserRole(){
        SysRole role = new SysRole();
        role = role.selectOne(new QueryWrapper<SysRole>().eq("role_name","SuperGroup"));
        SysUser sysUser = new SysUser();
        sysUser = sysUser.selectOne(new QueryWrapper<SysUser>().eq("user_name","admin"));
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(role.getId());
        sysUserRole.setUserId(sysUser.getId());
        sysUserRole.insert();
    }


    public void initRoleMenu(){
        SysRole role = new SysRole();
        role = role.selectOne(new QueryWrapper<SysRole>().eq("role_name","SuperGroup"));
        String roleId = role.getId();

        SysMenu sysMenu = new SysMenu();
        List<SysMenu> sysMenus = sysMenu.selectAll();

        sysMenus.forEach(menu -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menu.getId());
            sysRoleMenu.insert();
        });

    }

    public void initMenu(){
        SysMenu sysMenu1 = new SysMenu("系统管理","0",null,"fa fa-cogs",1,1,"01",null);
        sysMenu1.insert();
        String pid1 = sysMenu1.getId();
        SysMenu sysMenu11 = new SysMenu("用户管理",pid1,"/system/user/list/1","fa-user-circle-o",1,2,"0101","user");
        sysMenu11.insert();
        String userPID = sysMenu11.getId();
        SysMenu sysMenu110 = new SysMenu("查看用户列表",userPID,null,null,0,3,"010100","listUser");
        sysMenu110.insert();
        SysMenu sysMenu111 = new SysMenu("新增用户",userPID,null,null,1,3,"010101","addUser");
        sysMenu111.insert();
        SysMenu sysMenu112 = new SysMenu("编辑用户",userPID,null,null,2,3,"010102","editUser");
        sysMenu112.insert();
        SysMenu sysMenu113 = new SysMenu("删除用户",userPID,null,null,3,3,"010103","deleteUser");
        sysMenu113.insert();

        SysMenu sysMenu12 = new SysMenu("角色管理",pid1,"/system/role/list/1","fa-users",2,2,"0102","role");
        sysMenu12.insert();
        String rolePID = sysMenu12.getId();
        SysMenu sysMenu120 = new SysMenu("查看角色列表",rolePID,null,null,0,3,"010200","listRole");
        sysMenu120.insert();
        SysMenu sysMenu121 = new SysMenu("新增角色",rolePID,null,null,1,3,"010201","addRole");
        sysMenu121.insert();
        SysMenu sysMenu122 = new SysMenu("编辑角色",rolePID,null,null,2,3,"010202","editRole");
        sysMenu122.insert();
        SysMenu sysMenu123 = new SysMenu("删除角色",rolePID,null,null,3,3,"010203","deleteRole");
        sysMenu123.insert();
        SysMenu sysMenu124 = new SysMenu("角色授权",rolePID,null,null,4,3,"010204","authRole");
        sysMenu124.insert();
        SysMenu sysMenu125 = new SysMenu("批量删除角色",rolePID,null,null,5,3,"010205","deleteBatchRole");
        sysMenu125.insert();

        SysMenu sysMenu13 = new SysMenu("菜单管理",pid1,"/system/menu/list/1","fa-list",3,2,"0103","menu");
        sysMenu13.insert();
        String menuPID = sysMenu13.getId();
        SysMenu sysMenu130 = new SysMenu("查看菜单列表",menuPID,null,null,0,3,"010300","listMenu");
        sysMenu130.insert();
        SysMenu sysMenu131 = new SysMenu("创建菜单",menuPID,null,null,1,3,"010301","addMenu");
        sysMenu131.insert();
        SysMenu sysMenu132 = new SysMenu("编辑菜单",menuPID,null,null,2,3,"010302","editMenu");
        sysMenu132.insert();
        SysMenu sysMenu133 = new SysMenu("删除菜单",menuPID,null,null,3,3,"010303","deleteMenu");
        sysMenu133.insert();

        SysMenu sysMenu14 = new SysMenu("业务日志",pid1,"/system/log/list/1","fa-info-circle",5,2,"0105","log");
        sysMenu14.insert();
        String logPID = sysMenu14.getId();
        SysMenu sysMenu140 = new SysMenu("查看日志列表",logPID,null,null,0,3,"010500","listLog");
        sysMenu140.insert();

        SysMenu sysMenu15 = new SysMenu("系统配置",pid1,"/system/setting/page","fa-cog",6,2,"0106","setting");
        sysMenu15.insert();
        String sysPID = sysMenu13.getId();
        SysMenu sysMenu150 = new SysMenu("查询系统配置",sysPID,null,null,0,3,"010600","listSetting");
        sysMenu150.insert();
        SysMenu sysMenu151 = new SysMenu("操作系统设置",sysPID,null,null,1,3,"010601","doSetting");
        sysMenu151.insert();

        SysMenu sysMenu16 = new SysMenu("系统监控",pid1,"/system/monitor/list","fa-eye",7,2,"0107",null);
        sysMenu16.insert();
        String monitorID = sysMenu16.getId();
        SysMenu sysMenu160 = new SysMenu("监控列表",monitorID,null,null,1,3,"010701","monitorList");
        sysMenu160.insert();

    }



    public void initRole(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("SuperGroup");
        sysRole.setRoleDesc("超级管理员");
        sysRole.setRoleStatus(0);
        sysRole.insert();
    }

    public void initSettings(){
        SysSetting sysSetting1 = new SysSetting();
        sysSetting1.setSysKey("systemName");
        sysSetting1.setSysName("系统名称");
        sysSetting1.setSysValue("SparrowAdmin");
        sysSetting1.setSort(0);
        sysSetting1.insert();

        SysSetting sysSetting2 = new SysSetting();
        sysSetting2.setSysKey("systemSubName");
        sysSetting2.setSysName("系统简称");
        sysSetting2.setSysValue("后台管理系统");
        sysSetting2.setSort(1);
        sysSetting2.insert();

        SysSetting sysSetting3 = new SysSetting();
        sysSetting3.setSysKey("bottomCopyright");
        sysSetting3.setSysName("许可说明");
        sysSetting3.setSysValue("Copyright © 2020 think.com. All rights reserved.");
        sysSetting3.setSort(2);
        sysSetting3.insert();
    }
}

package com.think.sparrowadmin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.system.entity.SysRoleMenu;
import com.think.sparrowadmin.system.mapper.SysMenuMapper;
import com.think.sparrowadmin.system.mapper.SysRoleMenuMapper;
import com.think.sparrowadmin.system.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public void addAuth(String roleId, String[] menuIds) {
        /**
         * 删除原有权限
         */
        this.remove(new QueryWrapper<SysRoleMenu>().eq("roleId",roleId));
        /**
         * 重新授权
         */
        if(ArrayUtils.isNotEmpty(menuIds)){
            for(String menuId : menuIds){
                SysRoleMenu sysRoleMenu2 = new SysRoleMenu();
                sysRoleMenu2.setRoleId(roleId);
                sysRoleMenu2.setMenuId(menuId);
                this.save(sysRoleMenu2);
            }
        }
    }

    @Override
    public List<SysRoleMenu> selectByRole(String roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<SysRoleMenu>();
        queryWrapper.eq("role_id", roleId);
        return this.list(queryWrapper);

    }

    @Override
    public Set<String> findMenusByUid(String id) {
        List<String> list =  sysMenuMapper.selectResourceByUid(id);
        return new HashSet<>(list);
    }

}

package com.think.sparrowadmin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.common.util.ShiroUtil;
import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.entity.SysUserRole;
import com.think.sparrowadmin.system.mapper.SysUserMapper;
import com.think.sparrowadmin.system.mapper.SysUserRoleMapper;
import com.think.sparrowadmin.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired private SysUserRoleMapper userRoleMapper;

    @Override
    public void insertUser(SysUser user, String[] roleIds) {
        user.setGmtCreate(LocalDateTime.now());
        user.setPassword(ShiroUtil.md51024Pwd(user.getPassword(), user.getUserName()));
        //保存用户
        userMapper.insert(user);
        //绑定角色
        if(ArrayUtils.isNotEmpty(roleIds)){
            for(String rid : roleIds){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(user.getId());
                sysUserRole.setRoleId(rid);
                userRoleMapper.insert(sysUserRole);
            }
        }

    }

    @Override
    public void updateUser(SysUser sysUser, String[] roleIds) {
        // TODO Auto-generated method stub
        sysUser.setPassword(null);
        //更新用户
        userMapper.updateById(sysUser);
        //删除已有权限
        userRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id",sysUser.getId()));
        //重新绑定角色
        if(ArrayUtils.isNotEmpty(roleIds)){
            for(String rid : roleIds){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(rid);
                userRoleMapper.insert(sysUserRole);
            }
        }
    }

    @Override
    public Page<SysUser> selectUserPage(Page<SysUser> page, String search) {
        page.setRecords(baseMapper.selectUserList(page, search));
        return page;
    }

    @Override
    public void delete(String id) {
        this.removeById(id);
        userRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id", id));
    }

}

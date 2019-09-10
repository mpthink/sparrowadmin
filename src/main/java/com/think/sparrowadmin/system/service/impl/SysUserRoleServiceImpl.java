package com.think.sparrowadmin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.sparrowadmin.system.entity.SysUserRole;
import com.think.sparrowadmin.system.mapper.SysUserRoleMapper;
import com.think.sparrowadmin.system.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public Set<String> findRolesByUid(String uid) {
        List<SysUserRole> list = this.list(new QueryWrapper<SysUserRole>().eq("user_id", uid));

        Set<String> set = new HashSet<String>();
        for (SysUserRole ur : list) {
            set.add(ur.getRoleId());
        }
        return set;
    }

}

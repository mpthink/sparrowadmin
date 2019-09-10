package com.think.sparrowadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.sparrowadmin.system.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * Get role permissions by uid
     * @param uid
     * @return
     */
    public List<String> selectRoleMenuIdsByUserId(String uid);

}

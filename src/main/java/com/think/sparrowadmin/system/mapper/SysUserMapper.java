package com.think.sparrowadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUserList(Page<SysUser> page, @Param("search") String search);

}

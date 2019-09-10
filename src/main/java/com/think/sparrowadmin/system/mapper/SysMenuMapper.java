package com.think.sparrowadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.sparrowadmin.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectMenuIdsByuserId(String uid);

    List<String> selectResourceByUid(@Param("uid") String uid);

}

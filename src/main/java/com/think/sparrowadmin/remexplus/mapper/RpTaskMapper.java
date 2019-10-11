package com.think.sparrowadmin.remexplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * table for creating regular task Mapper 接口
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Repository
public interface RpTaskMapper extends BaseMapper<RpTask> {

    List<RpTask> selectTaskList(Page<RpTask> page, @Param("search") String search);

}

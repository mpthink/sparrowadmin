package com.think.sparrowadmin.remexplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.sparrowadmin.remexplus.entity.RpJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * jobs of task Mapper 接口
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Repository
public interface RpJobMapper extends BaseMapper<RpJob> {

    List<Map<String,Object>> selectJobsByTaskRecordId(@Param("id") String id);

    Map<String,Object> selectLastJobResult(@Param("id") String id);
}

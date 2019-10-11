package com.think.sparrowadmin.remexplus.mapper;

import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * record task running Mapper 接口
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Repository
public interface RpTaskRecordMapper extends BaseMapper<RpTaskRecord> {

    List<Map<String, Object>> selectTaskRecords();
}

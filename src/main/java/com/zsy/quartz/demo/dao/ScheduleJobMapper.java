package com.zsy.quartz.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsy.quartz.demo.entity.ScheduleJob;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 Mapper 接口
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Repository
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

    int updateJobStatusBatchByIds(Map<String,Object> paramMap);
}

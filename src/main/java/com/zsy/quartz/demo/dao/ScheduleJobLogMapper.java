package com.zsy.quartz.demo.dao;

import com.zsy.quartz.demo.entity.ScheduleJobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 定时任务日志 Mapper 接口
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Repository
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLog> {

}

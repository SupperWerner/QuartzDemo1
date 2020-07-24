package com.zsy.quartz.demo.service.impl;

import com.zsy.quartz.demo.entity.ScheduleJobLog;
import com.zsy.quartz.demo.dao.ScheduleJobLogMapper;
import com.zsy.quartz.demo.service.ScheduleJobLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

}

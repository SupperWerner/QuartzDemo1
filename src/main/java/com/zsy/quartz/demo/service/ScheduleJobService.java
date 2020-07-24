package com.zsy.quartz.demo.service;

import com.zsy.quartz.demo.entity.ScheduleJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsy.quartz.demo.utils.ResModel;

import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

    ResModel saveScheduleJob(ScheduleJob scheduleJob);

    ResModel updateScheduleJob(ScheduleJob scheduleJob);

    ResModel updateJobStatusBatch(List<Long> jobIds, Integer status);

    ResModel deleteJobBatch(List<Long> jobIds);

}

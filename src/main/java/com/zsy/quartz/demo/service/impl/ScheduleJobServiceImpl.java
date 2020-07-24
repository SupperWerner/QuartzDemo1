package com.zsy.quartz.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zsy.quartz.demo.entity.ScheduleJob;
import com.zsy.quartz.demo.dao.ScheduleJobMapper;
import com.zsy.quartz.demo.service.ScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsy.quartz.demo.utils.ResModel;
import com.zsy.quartz.demo.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
    @Autowired
    private Scheduler scheduler;

    /**
     * 新增定时任务
     * @param scheduleJob
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResModel saveScheduleJob(ScheduleJob scheduleJob) {
        // 新增到数据库中
        baseMapper.insert(scheduleJob);
        // 添加关联
        ScheduleUtils.createScheduleJob(scheduler,scheduleJob);
        return ResModel.success();
    }

    /**
     * 修改定时任务
     * @param scheduleJob
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResModel updateScheduleJob(ScheduleJob scheduleJob) {
        baseMapper.updateById(scheduleJob);
        ScheduleUtils.updateScheduleJob(scheduler,scheduleJob);
        return ResModel.success();
    }

    /**
     * 更新、批量更新定时任务状态
     * @param jobIds
     * @param status
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResModel updateJobStatusBatch(List<Long> jobIds, Integer status) {
        // 更新数据库信息
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("jobIds",jobIds);
        paramMap.put("status",status);
        baseMapper.updateJobStatusBatchByIds(paramMap);
        return ResModel.success();
    }

    /**
     * 删除、批量删除定时任务
     * @param jobIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResModel deleteJobBatch(List<Long> jobIds) {
        baseMapper.deleteBatchIds(jobIds);
        jobIds.forEach(jobId -> ScheduleUtils.deleteScheduleJob(scheduler,jobId));
        return ResModel.success();
    }

}

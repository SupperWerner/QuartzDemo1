package com.zsy.quartz.demo.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsy.quartz.demo.entity.ScheduleJob;
import com.zsy.quartz.demo.service.ScheduleJobService;
import com.zsy.quartz.demo.utils.ResModel;
import com.zsy.quartz.demo.utils.ScheduleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 定时任务 前端控制器
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Api(description = "定时任务管理")
@RestController
@RequestMapping("/demo/schedule-job")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private Scheduler scheduler;

    @ApiOperation("新增定时任务")
    @PostMapping("saveScheduleJob")
    public ResModel saveScheduleJob(@ApiParam("新增定时任务信息接收对象")@RequestBody ScheduleJob scheduleJob){
        return scheduleJobService.saveScheduleJob(scheduleJob);
    }

    @ApiOperation("修改定时任务")
    @PostMapping("updateScheduleJob")
    public ResModel updateScheduleJob(@ApiParam("修改定时任务的接收对象")@RequestBody ScheduleJob scheduleJob ){
        return scheduleJobService.updateScheduleJob(scheduleJob);
    }

    @ApiOperation("分页查询定时任务")
    @PostMapping("/queryScheduleJobPage/{size}/{curpage}")
    public ResModel queryScheduleJobPage(@ApiParam("显示条数")@PathVariable Integer size
                                        ,@ApiParam("当前页码")@PathVariable Integer curpage
                                        ,@ApiParam("查询条件")@RequestBody ScheduleJob scheduleJob){
        Page<ScheduleJob> page = new Page<>(curpage,size);
        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<>();
        String remark = scheduleJob.getRemark();
        if (StringUtils.isNotEmpty(remark)){
            wrapper.like("remark",remark);
        }
        return ResModel.success().data("data",scheduleJobService.page(page,wrapper));
    }

    @ApiOperation("更新定时任务状态")
    @PostMapping("/updateBatchJobStatus/{status}")
    public ResModel updateJobStatusBatch(@ApiParam("定时任务Id集合")@RequestBody List<Long> jobIds,@ApiParam("定时任务状态")@PathVariable Integer status){
        return scheduleJobService.updateJobStatusBatch(jobIds,status);
    }

    @ApiOperation("删除、批量删除")
    @DeleteMapping("/deleteJobBatch")
    public  ResModel deleteJobBatch(@ApiParam("定时任务Id集合")@RequestBody List<Long> jobIds){
        return scheduleJobService.deleteJobBatch(jobIds);
    }

    @ApiOperation("立即执行任务")
    @PostMapping("/runJob")
    public ResModel run(@RequestBody @ApiParam("定时任务Id集合")@PathVariable Long jobIds){
        return ResModel.success();
    }

    @ApiOperation("暂停任务")
    @PostMapping("/pauseJob")
    public ResModel pauseJob(@RequestBody @ApiParam("定时任务Id集合")List<Long> jobIds){
        jobIds.forEach(jobId -> ScheduleUtils.pauseJob(scheduler,jobId));
        return ResModel.success();
    }

    @ApiOperation("恢复任务")
    @PostMapping("/resumeJob")
    public ResModel resumeJob(@RequestBody @ApiParam("定时任务Id集合") List<Long> jobIds){
        jobIds.forEach(jobId -> ScheduleUtils.resumeJob(scheduler,jobId));
        return ResModel.success();
    }
}


package com.zsy.quartz.demo.utils;

import com.zsy.quartz.demo.dao.ScheduleJobLogMapper;
import com.zsy.quartz.demo.entity.ScheduleJob;
import com.zsy.quartz.demo.entity.ScheduleJobLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName Schedule
 * @Description 定时任务工具
 * @Author mybook
 * @Date 2020/7/19 1:17 AM
 * @Version 1.0
 */

@Slf4j
@Component
public class ScheduleJobUtils extends QuartzJobBean {
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    /**
     * @Author zsy
     * @Description // TODO 执行方法
     * @Date 1:27 AM 2020/7/19
     * @Param [jobExecutionContext]
     * @return void
     **/
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob ) context.getMergedJobDataMap()
                .get(Constant.JOB_PARAM_KEY);

        //获取spring bean
//        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLog jobLog = new ScheduleJobLog();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            /*log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            future.get();*/
            System.out.println("Hello world"+scheduleJob.getParams());

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int)times);
            //任务状态    0：成功    1：失败
            jobLog.setStatus(0);

            log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int)times);

            //任务状态    0：成功    1：失败
            jobLog.setStatus(1);
            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        }finally {
            scheduleJobLogMapper.insert(jobLog);
        }
    }
}
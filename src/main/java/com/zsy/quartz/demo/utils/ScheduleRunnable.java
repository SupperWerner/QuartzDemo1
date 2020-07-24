package com.zsy.quartz.demo.utils;

import com.zsy.quartz.demo.exception.MyQuartzException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;

/**
 * @ClassName ScheduleRunnable
 * @Description TODO
 * @Author mybook
 * @Date 2020/7/19 1:30 AM
 * @Version 1.0
 */

public class ScheduleRunnable implements Runnable {
    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtils.getBean(beanName);
        this.params = params;

        if(StringUtils.isNotBlank(params)){
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        }else{
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if(StringUtils.isNotBlank(params)){
                method.invoke(target, params);
            }else{
                method.invoke(target);
            }
        }catch (java.lang.Exception e) {
            throw new MyQuartzException(ResultCode.ERROR,"执行定时任务失败");
        }
    }
}

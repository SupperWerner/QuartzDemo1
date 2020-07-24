package com.zsy.quartz.demo.exception;


import com.zsy.quartz.demo.utils.ResModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class MyQuartzExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResModel error(Exception e){
        e.printStackTrace();
        return ResModel.error();
    }

    @ExceptionHandler(MyQuartzException.class)
    @ResponseBody
    public ResModel elonError(MyQuartzException e){
        e.printStackTrace();
        return ResModel.error().message(e.getMsg()).code(e.getCode());
    }

}

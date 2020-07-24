package com.zsy.quartz.demo.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyQuartzException extends RuntimeException {
    @ApiModelProperty(value="状态码")
    private Integer code;

    @ApiModelProperty(value = "状态信息")
    private String msg;

}

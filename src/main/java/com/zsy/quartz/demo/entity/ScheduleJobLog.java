package com.zsy.quartz.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ScheduleJobLog对象", description="定时任务日志")
public class ScheduleJobLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务日志id")
    @TableId(value = "log_id", type = IdType.ASSIGN_ID)
    private Long logId;

    @ApiModelProperty(value = "任务id")
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    private String beanName;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "任务状态    0：成功    1：失败")
    private Integer status;

    @ApiModelProperty(value = "失败信息")
    private String error;

    @ApiModelProperty(value = "耗时(单位：毫秒)")
    private Integer times;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}

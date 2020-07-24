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
 * 定时任务
 * </p>
 *
 * @author zsy
 * @since 2020-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ScheduleJob对象", description="定时任务")
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务id")
    @TableId(value = "job_id", type = IdType.ID_WORKER)
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    private String beanName;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "任务状态  0：正常  1：暂停")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}

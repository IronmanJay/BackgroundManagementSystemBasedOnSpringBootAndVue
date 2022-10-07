package com.ironmanjay.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author IronmanJay
 * @since 2022-10-04
 */
@Getter
@Setter
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("课程名称")
    private String name;

    @ApiModelProperty("学分")
    private Integer score;

    @ApiModelProperty("上课时间")
    private String times;

    @ApiModelProperty("授课老师id")
    private Integer teacherId;

    @TableField(exist = false)
    private String teacher;

}

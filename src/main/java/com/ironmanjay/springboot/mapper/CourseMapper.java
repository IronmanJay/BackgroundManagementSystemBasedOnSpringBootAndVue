package com.ironmanjay.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ironmanjay.springboot.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author IronmanJay
 * @since 2022-10-04
 */
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> page, @Param("name") String name);

}

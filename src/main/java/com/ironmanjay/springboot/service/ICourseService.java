package com.ironmanjay.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ironmanjay.springboot.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author IronmanJay
 * @since 2022-10-04
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> page, String name);

}

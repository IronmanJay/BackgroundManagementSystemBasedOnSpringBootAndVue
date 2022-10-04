package com.ironmanjay.springboot.service;

import com.ironmanjay.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author IronmanJay
 * @since 2022-09-14
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);

}

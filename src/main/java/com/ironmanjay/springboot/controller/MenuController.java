package com.ironmanjay.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ironmanjay.springboot.common.Constants;
import com.ironmanjay.springboot.common.Result;
import com.ironmanjay.springboot.entity.Dict;
import com.ironmanjay.springboot.entity.Menu;
import com.ironmanjay.springboot.mapper.DictMapper;
import com.ironmanjay.springboot.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author IronmanJay
 * @since 2022-09-14
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictMapper dictMapper;

    // 新增或者更新
    @PostMapping
    public boolean save(@RequestBody Menu menu) {
        return menuService.saveOrUpdate(menu);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return menuService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return menuService.removeByIds(ids);
    }

    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(Menu::getId));
    }

    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        return Result.success(menuService.findMenus(name));
    }

    @GetMapping("/{id}")
    public Menu findOne(@PathVariable Integer id) {
        return menuService.getById(id);
    }

    @GetMapping("/page")
    public Page<Menu> findPage(@RequestParam String name, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return menuService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(null));
    }

}


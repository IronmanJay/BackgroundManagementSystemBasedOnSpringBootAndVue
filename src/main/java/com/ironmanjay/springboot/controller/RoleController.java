package com.ironmanjay.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ironmanjay.springboot.common.Result;
import com.ironmanjay.springboot.entity.Role;
import com.ironmanjay.springboot.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    // 新增或者更新
    @PostMapping
    public boolean save(@RequestBody Role role) {
        return roleService.saveOrUpdate(role);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return roleService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return roleService.removeByIds(ids);
    }

    @GetMapping
    public List<Role> findAll() {
        return roleService.list();
    }

    @GetMapping("/{id}")
    public Role findOne(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @GetMapping("/page")
    public Page<Role> findPage(@RequestParam String name, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return roleService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    /**
     * 绑定角色和菜单的关系
     *
     * @param roleId  角色id
     * @param menuIds 菜单id数组
     * @return 绑定结果
     */
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        return Result.success(roleService.getRoleMenu(roleId));
    }

}


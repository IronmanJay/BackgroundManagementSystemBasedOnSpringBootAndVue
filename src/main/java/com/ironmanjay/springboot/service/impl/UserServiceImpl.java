package com.ironmanjay.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ironmanjay.springboot.common.Constants;
import com.ironmanjay.springboot.controller.dto.UserDTO;
import com.ironmanjay.springboot.controller.dto.UserPasswordDTO;
import com.ironmanjay.springboot.entity.Menu;
import com.ironmanjay.springboot.entity.User;
import com.ironmanjay.springboot.exception.ServiceException;
import com.ironmanjay.springboot.mapper.RoleMapper;
import com.ironmanjay.springboot.mapper.RoleMenuMapper;
import com.ironmanjay.springboot.mapper.UserMapper;
import com.ironmanjay.springboot.service.IMenuService;
import com.ironmanjay.springboot.service.IUserService;
import com.ironmanjay.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IronmanJay
 * @since 2022-07-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    // 用于打印错误日志
    private static final Log LOG = Log.get();

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param userDTO 用户类
     * @return 返回登录结果
     */
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    /**
     * 获取当前角色的菜单列表
     *
     * @param roleFlag 当前角色唯一标识
     * @return 返回当前角色的菜单列表
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        // 查出系统所有的菜单
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf 移除 children 里面不在 menuIds集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

    /**
     * 用户注册
     *
     * @param userDTO 用户类
     * @return 返回注册结果
     */
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one); // 把copy完的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return null;
    }

    /**
     * 判断当前用户是否存在
     *
     * @param userDTO 待判断用户
     * @return 判断结果
     */
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            // 在控制台打印错误日志
            LOG.error(e);
            throw new ServiceException(Constants.CODE_600, "系统错误");
        }
        return one;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

}

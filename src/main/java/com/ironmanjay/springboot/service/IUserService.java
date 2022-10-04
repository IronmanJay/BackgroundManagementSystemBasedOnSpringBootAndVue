package com.ironmanjay.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ironmanjay.springboot.controller.dto.UserDTO;
import com.ironmanjay.springboot.controller.dto.UserPasswordDTO;
import com.ironmanjay.springboot.entity.User;

/**
 * @author IronmanJay
 * @since 2022-07-25
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

}

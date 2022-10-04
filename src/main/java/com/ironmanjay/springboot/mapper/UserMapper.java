package com.ironmanjay.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ironmanjay.springboot.controller.dto.UserPasswordDTO;
import com.ironmanjay.springboot.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author IronmanJay
 * @since 2022-07-25
 */
public interface UserMapper extends BaseMapper<User> {

    int updatePassword(@Param("userPasswordDTO") UserPasswordDTO userPasswordDTO);

}

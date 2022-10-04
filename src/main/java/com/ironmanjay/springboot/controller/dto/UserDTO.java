package com.ironmanjay.springboot.controller.dto;

import com.ironmanjay.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接收前端登录请求的参数
 */
@Data
public class UserDTO {

    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
    private String role;
    private List<Menu> menus;

}

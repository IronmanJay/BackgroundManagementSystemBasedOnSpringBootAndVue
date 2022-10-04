package com.ironmanjay.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ironmanjay.springboot.entity.User;
import com.ironmanjay.springboot.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static IUserService staticUserService;

    @Resource
    private IUserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    /**
     * 生成Token
     *
     * @param userId 用户的id
     * @param sign   用户的信息
     * @return 返回生产的Token
     */
    public static String genToken(String userId, String sign) {
        // 将 userId 保存到 token 里面,作为载荷
        // 2小时后token过期
        // 以 password 作为 token 的密钥
        return JWT.create().withAudience(userId).withExpiresAt(DateUtil.offsetHour(new Date(), 2)).sign(Algorithm.HMAC256(sign));
    }

    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}

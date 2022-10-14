package com.ironmanjay.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ironmanjay.springboot.common.Result;
import com.ironmanjay.springboot.config.AuthAccess;
import com.ironmanjay.springboot.entity.Files;
import com.ironmanjay.springboot.entity.User;
import com.ironmanjay.springboot.mapper.FileMapper;
import com.ironmanjay.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IUserService userService;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final String FILES_KEY = "FILES_FRONT_ALL";

    /**
     * 生成前端图形数据
     *
     * @return 返回生成的数据
     */
    @GetMapping("/example")
    public Result get() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    /**
     * 按季度获取数据
     *
     * @return 返回季度数据
     */
    @GetMapping("/members")
    public Result members() {
        List<User> list = userService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:
                    q1 += 1;
                    break;
                case Q2:
                    q2 += 1;
                    break;
                case Q3:
                    q3 += 1;
                    break;
                case Q4:
                    q4 += 1;
                    break;
                default:
                    break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }

    /**
     * 查询数据
     *
     * @return 返回查询到的数据
     */
    @AuthAccess
    @GetMapping("/file/front/all")
    public Result frontAll() {
        // 1. 从缓存获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get(FILES_KEY);
        List<Files> files;
        // 2. 取出来的json是空的
        if (StrUtil.isBlank(jsonStr)) {
            files = fileMapper.selectList(null);
            // 3. 从数据库取出数据，再去缓存到redis
            stringRedisTemplate.opsForValue().set(FILES_KEY, JSONUtil.toJsonStr(files));
        }
        // 4. 取出来的json不是空的
        else {
            // 5. 如果有，从redis缓存中获取数据，减轻了数据库的压力
            files = JSONUtil.toBean(jsonStr, new TypeReference<List<Files>>() {
            }, true);
        }
        return Result.success(files);
    }

}

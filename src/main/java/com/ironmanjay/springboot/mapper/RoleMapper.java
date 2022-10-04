package com.ironmanjay.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ironmanjay.springboot.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author IronmanJay
 * @since 2022-09-14
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from sys_role where flag = #{flag}")
    Integer selectByFlag(@Param("flag") String flag);

}

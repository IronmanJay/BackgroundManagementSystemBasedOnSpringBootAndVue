package com.ironmanjay.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ironmanjay.springboot.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author IronmanJay
 * @since 2022-10-21
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select c.*,u.nickname,u.avatar from t_comment c left join sys_user u on c.user_id = u.id where c.article_id = #{articleId} order by id desc")
    List<Comment> findCommentDetail(@Param("articleId") Integer articleId);

}

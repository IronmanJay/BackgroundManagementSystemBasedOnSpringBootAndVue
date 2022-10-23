package com.ironmanjay.springboot.service.impl;

import com.ironmanjay.springboot.entity.Article;
import com.ironmanjay.springboot.mapper.ArticleMapper;
import com.ironmanjay.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author IronmanJay
 * @since 2022-10-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}

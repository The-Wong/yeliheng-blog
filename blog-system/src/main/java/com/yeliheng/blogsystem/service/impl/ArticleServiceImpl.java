package com.yeliheng.blogsystem.service.impl;

import com.yeliheng.blogsystem.entity.Article;
import com.yeliheng.blogsystem.exception.GeneralException;
import com.yeliheng.blogsystem.exception.InternalServerException;
import com.yeliheng.blogsystem.exception.NotFoundException;
import com.yeliheng.blogsystem.exception.UnexpectedException;
import com.yeliheng.blogsystem.mapper.ArticleMapper;
import com.yeliheng.blogsystem.service.IArticleService;
import com.yeliheng.blogsystem.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserUtils userUtils;

    /**
     * 新增文章
     *
     * @param article 文章实体
     */
    @Override
    public void addArticle(Article article) {
        article.setUserId(userUtils.getLoginUserId());
        boolean result = articleMapper.addArticle(article);
        if(!result) throw new InternalServerException("数据库错误");
    }

    /**
     * 删除文章
     *
     * @param articleId 文章Id
     */
    @Override
    public void deleteAritcle(Integer articleId) {

    }

    /**
     * 修改文章
     *
     * @param article   文章实体
     */
    @Override
    public void updateArticle(Article article) {
        int rows = articleMapper.updateArticle(article);
        if(rows <= 0) throw new GeneralException("更新失败，文章可能不存在");
    }

    /**
     * 获取分页的文章
     *
     * @param page     第几页
     * @param pageSize 每页显示多少
     * @return 文章列表
     */
    @Override
    public List<Article> getArticles(Integer page, Integer pageSize) {
        return null;
    }

    /**
     * 通过Id获取文章
     *
     * @param articleId 文章Id
     * @return 文章实体
     */
    @Override
    public Article getArticleById(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article == null)
            throw new NotFoundException("文章不存在");
        return article;
    }

    /**
     * 通过分类获取文章
     *
     * @param categoryId 分类Id
     * @return 文章列表
     */
    @Override
    public List<Article> getArticlesByCategory(Integer categoryId) {
        return null;
    }

    /**
     * 通过标签获取文章
     *
     * @param tagId 标签Id
     * @return 文章列表
     */
    @Override
    public List<Article> getArticlesByTag(Integer tagId) {
        return null;
    }
}

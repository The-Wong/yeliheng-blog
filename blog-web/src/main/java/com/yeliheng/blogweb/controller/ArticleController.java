package com.yeliheng.blogweb.controller;



import com.yeliheng.blogsystem.domain.Article;
import com.yeliheng.blogsystem.service.IArticleService;
import com.yeliheng.blogweb.common.CommonResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "文章模块")
@RestController
public class ArticleController {
    @Autowired
    private IArticleService articleService;


    /**
     *
     * 新增文章
     * @param article 文章实体
     * @return 请求结果
     */
    @PreAuthorize("@perm.hasPerm('admin:articles:add')")
    @PostMapping("/admin/articles")
    public CommonResponse<Object> add(@Validated @RequestBody Article article){
        articleService.addArticle(article);
        return CommonResponse.success();
    }

    /**
     *
     * 更新文章
     * @param article 文章实体
     * @return 请求结果
     */
    @PreAuthorize("@perm.hasPerm('admin:articles:edit')")
    @PutMapping("/admin/articles")
    public CommonResponse<Object> update(@Validated @RequestBody Article article){
        articleService.updateArticle(article);
        return CommonResponse.success();
    }

    /**
     *
     * 根据Id获取具体文章内容
     * @param articleId 文章Id
     * @return 文章实体
     */
/*    @GetMapping("/articles/{articleId}")
    public CommonResponse<Article> getArticleById(@PathVariable("articleId") Long articleId){
        return CommonResponse.success(articleService.getArticleById(articleId));
    }*/

    /**
     *
     * 根据url获取具体文章内容
     * @param url url
     * @return 文章实体
     */
    @GetMapping("/articles/{url}")
    public CommonResponse<Article> getArticleByUrl(@PathVariable("url") String url){
        return CommonResponse.success(articleService.getArticleByUrl(url));
    }

    /**
     *
     * 根据Id获取具体文章内容-后台
     * @param articleId 文章Id
     * @return 文章实体
     */
    @PreAuthorize("@perm.hasPerm('admin:articles:list')")
    @GetMapping("/admin/articles/{articleId}")
    public CommonResponse<Article> getArticleByIdBacked(@PathVariable("articleId") Long articleId){
        return CommonResponse.success(articleService.getArticleByIdBacked(articleId));
    }

    /**
     *
     * 获取所有文章
     * @param page 当前页
     * @param pageSize 一页多少
     * @return 文章列表
     */
    @GetMapping("/articles")
    public CommonResponse<Object> getArticles(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return CommonResponse.success(articleService.getArticles(page,pageSize));
    }

    /**
     *
     * 获取后台所有文章
     * @param page 当前页
     * @param pageSize 一页多少
     * @return 文章列表
     */
    @GetMapping("/admin/articles/list")
    @PreAuthorize("@perm.hasPerm('admin:articles:list')")
    public CommonResponse<Object> getArticlesBacked(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            Article article){
        return CommonResponse.success(articleService.getArticlesBacked(page,pageSize,article));
    }

    /**
     *
     * 根据分类获取文章
     * @param categoryId 分类Id
     * @param page 当前页
     * @param pageSize 一页多少
     * @return
     */
    @GetMapping("/articles/category")
    public CommonResponse<Object> getArticlesByCategoryId(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return CommonResponse.success(articleService.getArticlesByCategory(categoryId,page,pageSize));
    }

    /**
     *
     * 根据分类获取文章
     * @param tagId 标签Id
     * @param page 当前页
     * @param pageSize 一页多少
     * @return
     */
    @GetMapping("/articles/tag")
    public CommonResponse<Object> getArticlesByTagId(
            @RequestParam("tagId") Long tagId,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return CommonResponse.success(articleService.getArticlesByTag(tagId,page,pageSize));
    }

    @PreAuthorize("@perm.hasPerm('admin:articles:delete')")
    @DeleteMapping("/admin/articles")
    public CommonResponse<Object> deleteArticle(@RequestParam("id") Long articleId){
        //TODO: 软删除
        articleService.deleteAritcle(articleId);
        return CommonResponse.success();
    }

}

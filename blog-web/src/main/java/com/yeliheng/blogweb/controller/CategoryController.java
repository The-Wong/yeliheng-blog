package com.yeliheng.blogweb.controller;

import com.yeliheng.blogcommon.utils.StringUtils;
import com.yeliheng.blogsystem.domain.Category;
import com.yeliheng.blogsystem.service.ICategoryService;

import com.yeliheng.blogweb.common.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PreAuthorize("@perm.hasPerm('admin:categories:add')")
    @PostMapping("/admin/categories")
    public CommonResponse<Object> add(@Validated @RequestBody Category category){
        categoryService.addCategory(category);
        return CommonResponse.success();
    }
    @PreAuthorize("@perm.hasPerm('admin:categories:delete')")
    @DeleteMapping("/admin/categories")
    public CommonResponse<Object> delete(@RequestParam Long id){
        categoryService.deleteCategory(id);
        return CommonResponse.success();
    }

    @PreAuthorize("@perm.hasPerm('admin:categories:edit')")
    @PutMapping("/admin/categories")
    public CommonResponse<Object> update(@RequestBody Category category){
        categoryService.updateCategory(category);
        return CommonResponse.success();
    }

    @GetMapping("/categories")
    public CommonResponse<Object> getCategories(@RequestParam(value = "page",required = false) Integer page,
                                                @RequestParam(value = "pageSize",required = false) Integer pageSize,Category category){
        if(StringUtils.isNull(page) || StringUtils.isNull(pageSize))
             return CommonResponse.success(categoryService.getCategories(category));
        else return CommonResponse.success(categoryService.getCategoriesPaged(page,pageSize,category));
    }

}

package com.qzy.controller;

import com.qzy.pojo.Blog;
import com.qzy.service.BlogCollectionService;
import com.qzy.utils.Page;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
public class BlogCollectionController {

    @Autowired
    private BlogCollectionService blogCollectionService;


    @PostMapping("/collection/{blogId}")
    public Result<?> goods(@PathVariable Long blogId) {
        blogCollectionService.collection(blogId);
        return new Result<>("收藏成功");
    }


    @GetMapping("/getCollection/{blogId}")
    public Result<Integer> getGoods(@PathVariable Long blogId) {
        Integer count = blogCollectionService.getCollection(blogId);
        return new Result<>(count);
    }


    @PostMapping("/getByPage")
    public Result<Page<Blog>> getByPage(@RequestBody Page<Blog> page) {
        page = blogCollectionService.getByPage(page);
        return new Result<>(page);
    }

}

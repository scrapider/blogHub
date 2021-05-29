package com.qzy.controller;


import com.qzy.pojo.BlogComment;
import com.qzy.pojo.vo.BlogCommentVo;
import com.qzy.service.BlogCommentService;
import com.qzy.utils.Page;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class BlogCommentController {
    @Autowired
    private BlogCommentService blogCommentService;


    @PostMapping("/save")
    public Result<?> save(@RequestBody BlogComment blogComment) {
        blogCommentService.save(blogComment);
        return new Result<>("评论成功");
    }


    @GetMapping(value = "/getByBlogId/{blogId}")
    public Result<List<BlogComment>> getByBlogId(@PathVariable Long blogId) {
        List<BlogComment> list = blogCommentService.getByBlogId(blogId);
        return new Result<>(list);
    }


    @PostMapping("/getByPage")
    public Result<Page<BlogCommentVo>> getByPage(@RequestBody Page<BlogCommentVo> page) {
        page = blogCommentService.getByPage(page);
        return new Result<>(page);
    }
}

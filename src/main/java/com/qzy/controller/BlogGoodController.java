package com.qzy.controller;

import com.qzy.service.BlogGoodService;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class BlogGoodController {
    @Autowired
    private BlogGoodService blogGoodService;


    @PostMapping("/goods/{blogId}")
    public Result<?> goods(@PathVariable Long blogId) {
        blogGoodService.goods(blogId);
        return new Result<>("点赞成功");
    }

    @GetMapping("/getGoods/{blogId}")
    public Result<Integer> getGoods(@PathVariable Long blogId) {
        return new Result<>(blogGoodService.getGood(blogId));
    }
}

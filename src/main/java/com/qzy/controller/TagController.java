package com.qzy.controller;


import com.qzy.pojo.Tag;
import com.qzy.pojo.vo.HotTagVo;
import com.qzy.service.TagService;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/searchByName/{name}")
    public Result<List<Tag>> getByName(@PathVariable String name) {
        List<Tag> list = tagService.getByName(name);
        return new Result<>(list);
    }

    @GetMapping("/getByBlogId/{blogId}")
    public Result<List<Tag>> getByBlogId(@PathVariable Long blogId) {
        List<Tag> list = tagService.getByBlogId(blogId);
        return new Result<>(list);
    }

    @GetMapping("/getHotTag")
    public Result<List<HotTagVo>> getHotTag() {
        List<HotTagVo> hotTagVo = tagService.getHotTag();
        return new Result<>(hotTagVo);
    }


}

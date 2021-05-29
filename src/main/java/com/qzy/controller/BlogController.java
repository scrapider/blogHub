package com.qzy.controller;


import com.qzy.pojo.Blog;
import com.qzy.pojo.dto.BlogDto;
import com.qzy.pojo.vo.BlogVo;
import com.qzy.service.BlogService;
import com.qzy.utils.Page;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;


    @PostMapping("/save")
    public Result<?> save(@RequestBody BlogDto blogDto) {
        blogService.save(blogDto);
        return new Result<>("添加成功");
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody BlogDto blogDto) {
        blogService.update(blogDto);
        return new Result<>("修改成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        blogService.delete(id);
        return new Result<>("删除成功");
    }

    @PostMapping("/getByPage")
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page) {
        try {
            page.getSortColumn();
        } catch (Exception e) {
            page.setSortColumn("created_time");
        }
        page = blogService.getPageByName(page);
        return new Result<>(page);
    }

    @GetMapping("/getUpdate/{id}")
    public Result<Blog> getUpdate(@PathVariable Long id) {
        Blog blog = blogService.getUpdate(id);
        return new Result<>(blog);
    }

    @GetMapping("/getInfo/{id}")
    public Result<BlogVo> getInfo(@PathVariable Long id) {
        BlogVo blogVo = blogService.getInfo(id);
        return new Result<>(blogVo);
    }

    @GetMapping("/getTimeLine/{index}")
    public Result<List<Blog>> getTime(@PathVariable Integer index) {
        List<Blog> list = blogService.getTime(index);
        return new Result<>(list);
    }
}

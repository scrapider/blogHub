package com.qzy.controller;


import com.qzy.pojo.Link;
import com.qzy.service.LinkService;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/get/{id}")
    public Result<Link> get(@PathVariable("id") Long id) {
        Link link = linkService.get(id);
        return new Result<>(link);
    }

    @GetMapping("/list")
    public Result<List> list() {
        List<Link> list = linkService.getAll();
        return new Result<>(list);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Link link) {
        linkService.update(link);
        return new Result<>("修改成功");
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Link link) {
        linkService.save(link);
        return new Result<>("添加成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        linkService.delete(id);
        return new Result<>("删除成功");
    }


}

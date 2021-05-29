package com.qzy.controller;

import com.qzy.exception.BlogException;
import com.qzy.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping(value = "/test1")
    public Result<?> test() {
        return new Result<>();
    }

    @GetMapping(value = "/test2")
    public Result<?> test2() {
        throw new BlogException("操作失败");
    }

}

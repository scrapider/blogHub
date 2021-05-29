package com.qzy.controller;

import com.qzy.pojo.Type;
import com.qzy.service.TypeService;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Type type) {
        typeService.save(type);
        return new Result<>("保存");
    }

    @GetMapping("/get/{id}")
    public Result<Type> get(@PathVariable("id") Long id) {
        Type type = typeService.get(id);
        return new Result<>(type);
    }

    @GetMapping("/list")
    public Result<List<Type>> getAll() {
        List<Type> list = typeService.getAll();
        return new Result<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        typeService.delete(id);
        return new Result<>("删除成功");

    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Type type) {
        typeService.update(type);
        return new Result<>("修改成功");


    }


}

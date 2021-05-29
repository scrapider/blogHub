package com.qzy.controller;

import com.qzy.pojo.Music;
import com.qzy.service.MusicService;
import com.qzy.utils.Page;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @PostMapping("/getByPage")
    public Result<Page<Music>> getByPage(@RequestBody Page<Music> page) {
        page = musicService.getByPage(page);
        return new Result<>(page);
    }

    @GetMapping("/get/{id}")
    public Result<Music> get(@PathVariable Long id) {
        Music music = musicService.get(id);
        return new Result<>(music);
    }


    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        musicService.delete(id);
        return new Result<>("删除成功");
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Music music) {
        musicService.update(music);
        return new Result<>("修改成功");
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Music music) {
        musicService.save(music);
        return new Result<>("添加成功");
    }

    @PutMapping("/toggleEnable")
    public Result<?> toggleEnable(@RequestBody Music music) {
        musicService.toggle(music);
        return Result.ok();
    }

    @GetMapping("/getAllEnable")
    public Result<List<Music>> getAll() {
        List<Music> list = musicService.getAll();
        return new Result<>(list);
    }

}

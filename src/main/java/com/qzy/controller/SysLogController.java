package com.qzy.controller;


import com.qzy.annotation.IgnoreLog;
import com.qzy.pojo.SysLog;
import com.qzy.service.SysLogService;
import com.qzy.utils.Page;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService logService;

    @IgnoreLog
    @PostMapping("getByPage")
    public Result<Page<SysLog>> getByPage(@RequestBody Page<SysLog> page) {
        Integer totalCount = logService.totalCount(page);
        page.setTotalCount(totalCount);
        page = logService.getByPage(page);
        return new Result<>(page);
    }

    @IgnoreLog
    @GetMapping("/get/{id}")
    public Result<SysLog> get(@PathVariable Long id) {
        SysLog log = logService.get(id);
        return new Result<>(log);
    }

    @IgnoreLog
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        logService.delete(id);
        return new Result<>("删除成功");
    }
}

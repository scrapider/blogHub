package com.qzy.controller;

import com.qzy.dfs.UploadService;
import com.qzy.pojo.vo.UploadVo;
import com.qzy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploadFile")
    public Result<UploadVo> uploadFile(MultipartFile file) {
        String uploadFile = uploadService.uploadFile(file);
        return new Result<>(new UploadVo(uploadFile));
    }
}

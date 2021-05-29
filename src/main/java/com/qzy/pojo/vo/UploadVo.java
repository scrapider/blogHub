package com.qzy.pojo.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class UploadVo implements Serializable {
    /**
     * 文件路径
     */
    private String path;

    public UploadVo(String path) {
        this.path = path;
    }
}

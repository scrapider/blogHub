package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class BlogTag implements Serializable {

    /**
     * ID，雪花算法
     */
    private Long id;

    private Long blogId;

    private String tagName;

    /**
     * 标签ID
     */
    private Long tagId;

}

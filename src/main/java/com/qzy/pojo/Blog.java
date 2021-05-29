package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Blog implements Serializable {

    /**
     * 博客ID，雪花算法
     */
    private Long blogId;

    private String blogTitle;

    private String blogImage;

    private String blogContent;

    private Integer blogGoods;

    private Integer blogRead;

    private Integer blogCollection;

    private Long blogType;

    private String blogRemark;

    private Integer blogComment;

    private String blogSource;

    private String createdTime;

    private String updateTime;

    private Integer deleted;

}

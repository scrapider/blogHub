package com.qzy.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class BlogVo implements Serializable {

    private Long blogId;

    private String blogTitle;

    private String blogImage;

    private Integer blogGoods;

    private Integer blogRead;

    private String blogContent;

    private Integer blogCollection;

    private Long blogType;

    private String blogRemark;

    private Integer blogComment;

    private String blogSource;

    private String createdTime;

    private String updateTime;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 分类名称
     */
    private String typeName;

}

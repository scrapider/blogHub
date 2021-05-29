package com.qzy.pojo.dto;

import com.qzy.pojo.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class BlogDto implements Serializable {

    private Long blogId;

    private String blogTitle;

    private String blogImage;

    private String blogContent;

    private Long blogType;

    private String blogRemark;

    private String blogSource;

    /**
     * 标签列表
     */
    private List<Tag> tagList;
}

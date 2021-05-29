package com.qzy.pojo.vo;

import com.qzy.pojo.Blog;
import lombok.Data;

import java.io.Serializable;


@Data
public class BlogCommentVo implements Serializable {

    private Blog blog;
    private String content;

}

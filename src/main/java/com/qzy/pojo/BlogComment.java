package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class BlogComment implements Serializable {

    private Long id;
    private Long blogId;
    private Long userId;
    private String header;
    private String nickname;
    private String content;
    private String createdTime;

}

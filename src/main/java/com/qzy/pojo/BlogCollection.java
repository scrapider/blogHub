package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class BlogCollection implements Serializable {

    private Long id;

    private Long blogId;

    private Long userId;

}

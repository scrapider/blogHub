package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
    private Long id;
    private String name;
    /**
     * 头像
     */
    private String header;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 介绍
     */
    private String comment;
    private String username;
    private String password;

}

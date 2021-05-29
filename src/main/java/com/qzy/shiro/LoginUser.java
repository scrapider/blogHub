package com.qzy.shiro;

import lombok.Data;

import java.io.Serializable;


@Data
public class LoginUser implements Serializable {

    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String header;

    /**
     * 签名
     */
    private String signature;

    /**
     * 介绍
     */
    private String comment;

    /**
     * 用户名
     */
    private String username;
    private Integer sex;
    private String userEmail;
    private String nickname;

}

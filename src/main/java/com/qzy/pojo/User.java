package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private Long userId;

    private String username;

    private String password;

    private String name;

    private Integer sex;

    private String header;

    private String nickname;

    private String userEmail;

    private String createdTime;

    private String updateTime;

    private Integer deleted;

}

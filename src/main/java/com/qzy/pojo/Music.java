package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Music implements Serializable {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 歌手
     */
    private String artist;

    /**
     * 歌曲链接
     */
    private String url;

    /**
     * 封面
     */
    private String cover;

    /**
     * 歌词
     */
    private String lrc;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 是否删除，1是0否
     */
    private Integer deleted;

}

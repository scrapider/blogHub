package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Link implements Serializable {
    private Long linkId;
    private String linkName;
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    private Integer deleted;

}

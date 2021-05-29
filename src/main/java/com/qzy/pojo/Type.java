package com.qzy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Type implements Serializable {
    private Long typeId;
    private String typeName;
    private Integer typeBlogCount;
    private Integer enable;
    private Integer deleted;
}

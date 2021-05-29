package com.qzy.pojo.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class HotTagVo implements Serializable {
    private Integer tagCount;
    private String tagName;
}

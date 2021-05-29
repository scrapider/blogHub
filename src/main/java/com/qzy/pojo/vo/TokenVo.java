package com.qzy.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenVo implements Serializable {
    private Serializable token;

    public TokenVo(Serializable token) {
        this.token = token;
    }
}

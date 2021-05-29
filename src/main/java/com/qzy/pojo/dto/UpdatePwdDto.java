package com.qzy.pojo.dto;


import lombok.Data;

@Data
public class UpdatePwdDto {
    private Long id;
    private String currentPwd;
    private String newPwd;
}

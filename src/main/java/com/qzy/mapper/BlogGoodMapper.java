package com.qzy.mapper;


import org.springframework.stereotype.Component;

@Component
public interface BlogGoodMapper {
    void save(Long blogId);

    Integer getGoods(Long blogId);
}

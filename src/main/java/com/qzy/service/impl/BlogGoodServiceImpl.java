package com.qzy.service.impl;

import com.qzy.mapper.BlogGoodMapper;
import com.qzy.service.BlogGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogGoodServiceImpl implements BlogGoodService {
    @Autowired
    private BlogGoodMapper blogGoodMapper;

    @Override
    public void goods(Long blogId) {
        blogGoodMapper.save(blogId);
    }

    @Override
    public Integer getGood(Long blogId) {
        return blogGoodMapper.getGoods(blogId);
    }
}

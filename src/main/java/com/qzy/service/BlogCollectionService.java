package com.qzy.service;

import com.qzy.pojo.Blog;
import com.qzy.utils.Page;

public interface BlogCollectionService {

    void collection(Long blogId);


    Integer getCollection(Long blogId);


    Page<Blog> getByPage(Page<Blog> page);
}

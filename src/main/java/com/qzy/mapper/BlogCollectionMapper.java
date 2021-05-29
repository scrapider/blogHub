package com.qzy.mapper;

import com.qzy.pojo.Blog;
import com.qzy.pojo.BlogCollection;
import com.qzy.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface BlogCollectionMapper {

    void save(BlogCollection blogCollection);


    Integer getCountByBlogIdAndUserId(Long blogId, Long userId);

    List<BlogCollection> getByPage(Page<Blog> page, Long userId);

    Integer getCountByPage(Page<Blog> page, Long userId);
}

package com.qzy.service;

import com.qzy.pojo.Blog;
import com.qzy.pojo.dto.BlogDto;
import com.qzy.pojo.vo.BlogVo;
import com.qzy.utils.Page;

import java.util.List;

public interface BlogService {
    void save(BlogDto blogDto);

    void update(BlogDto blogDto);

    void delete(Long id);


    Page<BlogVo> getPageByName(Page<BlogVo> page);

    Blog getUpdate(Long id);

    BlogVo getInfo(Long id);

    List<Blog> getTime(Integer index);

}

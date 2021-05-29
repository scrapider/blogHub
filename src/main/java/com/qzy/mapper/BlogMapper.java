package com.qzy.mapper;


import com.qzy.pojo.Blog;
import com.qzy.pojo.Type;
import com.qzy.pojo.vo.BlogVo;
import com.qzy.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogMapper {
    void save(Blog blog);

    void update(Blog blog);

    void delete(Long id);

    List<Blog> getByName(Page<BlogVo> page);

    Integer countByName(Page<BlogVo> page);

    Blog getUpdate(Long id);

    Blog getInfo(Long id);

    List<Type> getTypeCount();

    List<Blog> getTime(Integer index);

    List<Blog> getByIds(List<Long> blogIds);
}

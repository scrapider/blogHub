package com.qzy.mapper;


import com.qzy.pojo.BlogTag;
import com.qzy.pojo.vo.HotTagVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogTagMapper {

    void saveBatch(List<BlogTag> blogTagList);

    void deleteByBlogId(Long blogId);

    List<BlogTag> getByBlogIds(List<Long> blogListIds);

    List<BlogTag> getByBlogId(Long blogId);

    List<HotTagVo> getHotTag();
}

package com.qzy.mapper;


import com.qzy.pojo.BlogComment;
import com.qzy.pojo.vo.BlogCommentVo;
import com.qzy.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogCommentMapper {
    void save(BlogComment blogComment);

    List<BlogComment> getByBlogId(Long blogId);

    List<BlogComment> getByPage(Page<BlogCommentVo> page, Long userId);

    Integer getCountByPage(Page<BlogCommentVo> page, Long userId);
}

package com.qzy.service;

import com.qzy.pojo.BlogComment;
import com.qzy.pojo.vo.BlogCommentVo;
import com.qzy.utils.Page;

import java.util.List;

public interface BlogCommentService {
    void save(BlogComment blogComment);

    List<BlogComment> getByBlogId(Long blogId);

    Page<BlogCommentVo> getByPage(Page<BlogCommentVo> page);
}

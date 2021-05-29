package com.qzy.service.impl;

import com.qzy.mapper.BlogCommentMapper;
import com.qzy.mapper.BlogMapper;
import com.qzy.pojo.Blog;
import com.qzy.pojo.BlogComment;
import com.qzy.pojo.vo.BlogCommentVo;
import com.qzy.service.BlogCommentService;
import com.qzy.shiro.LoginUser;
import com.qzy.utils.Page;
import com.qzy.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public void save(BlogComment blogComment) {
        LoginUser user = ShiroUtils.getLoginUser();
        Long id = user.getId();
        String nickname = user.getNickname();
        String header = user.getHeader();
        blogComment.setUserId(id);
        blogComment.setNickname(nickname);
        blogComment.setHeader(header);
        blogCommentMapper.save(blogComment);
    }

    @Override
    public List<BlogComment> getByBlogId(Long blogId) {
        return blogCommentMapper.getByBlogId(blogId);
    }

    /**
     * private Blog blog;
     * private String content;
     *
     * @param page
     * @return
     */

    @Override
    public Page<BlogCommentVo> getByPage(Page<BlogCommentVo> page) {
        Long userId = ShiroUtils.getLoginUser().getId();
        List<BlogComment> list = blogCommentMapper.getByPage(page, userId);
        Integer count = blogCommentMapper.getCountByPage(page, userId);
        page.setTotalCount(count);
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> ids = list.stream().map(BlogComment::getBlogId).collect(Collectors.toList());
            ids = new ArrayList<>(new HashSet<>(ids));
            List<Blog> byIds = blogMapper.getByIds(ids);
            List<BlogCommentVo> voList = list.stream().map(x -> {
                BlogCommentVo blogCommentVo = new BlogCommentVo();
                blogCommentVo.setContent(x.getContent());
                byIds.forEach(y -> {
                    if (y.getBlogId().equals(x.getBlogId())) {
                        x.setBlogId(y.getBlogId());
                    }
                    blogCommentVo.setBlog(y);
                });
                return blogCommentVo;
            }).collect(Collectors.toList());
            page.setList(voList);
        }
        return page;
    }
}

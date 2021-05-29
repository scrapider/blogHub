package com.qzy.service.impl;

import com.qzy.mapper.BlogCollectionMapper;
import com.qzy.mapper.BlogMapper;
import com.qzy.pojo.Blog;
import com.qzy.pojo.BlogCollection;
import com.qzy.service.BlogCollectionService;
import com.qzy.utils.Page;
import com.qzy.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BlogCollectionServiceImpl implements BlogCollectionService {

    @Autowired
    private BlogCollectionMapper blogCollectionMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void collection(Long blogId) {
        // 取出当前登录用户的id
        Long id = ShiroUtils.getLoginUser().getId();
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setBlogId(blogId);
        blogCollection.setUserId(id);
        blogCollectionMapper.save(blogCollection);
    }

    @Override
    public Integer getCollection(Long blogId) {
        Long id = ShiroUtils.getLoginUser().getId();
        Integer count = blogCollectionMapper.getCountByBlogIdAndUserId(blogId, id);
        return count;
    }

    @Override
    public Page<Blog> getByPage(Page<Blog> page) {
        Long userId = ShiroUtils.getLoginUser().getId();
        List<BlogCollection> list = blogCollectionMapper.getByPage(page, userId);
        Integer count = blogCollectionMapper.getCountByPage(page, userId);
        page.setTotalCount(count);
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> blogIds = list.stream().map(BlogCollection::getBlogId).collect(Collectors.toList());
            List<Blog> blogList = blogMapper.getByIds(blogIds);
            page.setList(blogList);
        } else {
            page.setList(new ArrayList<>(0));
        }
        return page;
    }
}

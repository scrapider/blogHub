package com.qzy.service.impl;


import com.qzy.mapper.BlogMapper;
import com.qzy.mapper.BlogTagMapper;
import com.qzy.mapper.SysTypeMapper;
import com.qzy.mapper.TagMapper;
import com.qzy.pojo.Blog;
import com.qzy.pojo.BlogTag;
import com.qzy.pojo.Tag;
import com.qzy.pojo.Type;
import com.qzy.pojo.dto.BlogDto;
import com.qzy.pojo.vo.BlogVo;
import com.qzy.service.BlogService;
import com.qzy.utils.IdWorker;
import com.qzy.utils.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private static final String[] SORT_COLUMNS = {"created_time", "blog_goods", "blog_read", "blog_collection", "blog_comment"};
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private SysTypeMapper typeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BlogDto blogDto) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);
        long blogId = idWorker.nextId();
        blog.setBlogId(blogId);
        blogMapper.save(blog);
        List<Tag> tagList = blogDto.getTagList();
        saveTag(blogId, tagList);
    }


    @Override
    public void update(BlogDto blogDto) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);
        blogMapper.update(blog);
        List<Tag> tagList = blogDto.getTagList();
        blogTagMapper.deleteByBlogId(blog.getBlogId());
        saveTag(blog.getBlogId(), tagList);
    }

    @Override
    public void delete(Long id) {
        blogMapper.delete(id);
    }

    @Override
    public Page<BlogVo> getPageByName(Page<BlogVo> page) {
        String sortColumn = page.getSortColumn();
        // 查询列表
        List<Blog> list = blogMapper.getByName(page);
        // 查询总数
        Integer totalCount = blogMapper.countByName(page);
        // 设置总数
        page.setTotalCount(totalCount);
        if (totalCount == null || totalCount == 0) {
            page.setList(new ArrayList<>(0));
            return page;
        }
        // 构造标签和分类
        // 查询分类
        List<Long> typeIds = list.stream().map(Blog::getBlogType).collect(Collectors.toList());
        List<Type> typeList = typeMapper.getByIds(typeIds);
        // 查询标签
        List<Long> blogIds = list.stream().map(Blog::getBlogId).collect(Collectors.toList());
        List<BlogTag> blogTagList = blogTagMapper.getByBlogIds(blogIds);
        // 封装分类和标签
        List<BlogVo> voList = list.stream()
                .map(e -> {
                    BlogVo blogVo = new BlogVo();
                    BeanUtils.copyProperties(e, blogVo);
                    return blogVo;
                })
                .peek(e -> {
                    // 根据分类ID取出分类列表中对应的数据
                    Type type = typeList.stream()
                            .filter(t -> t.getTypeId().equals(e.getBlogType()))
                            .findFirst()
                            .orElse(new Type());
                    e.setTypeName(type.getTypeName());
                })
                .peek(e -> {
                    // 根据博客ID取出标签
                    List<String> tagNameList = blogTagList.stream()
                            .filter(t -> e.getBlogId().equals(t.getBlogId()))
                            .map(BlogTag::getTagName)
                            .collect(Collectors.toList());
                    e.setTagList(tagNameList);
                })
                .collect(Collectors.toList());
        page.setList(voList);
        return page;
//        List<Blog> list = blogMapper.getByName(page);
//        Integer count = blogMapper.countByName(page);
//        page.setTotalCount(count);
//        List<Long> typeListId = list.stream().map(Blog::getBlogType).collect(Collectors.toList());
//        List<Type> typeList =  typeMapper.getByIds(typeListId);
//        List<Long> blogListIds = list.stream().map(Blog::getBlogId).collect(Collectors.toList());
//        List<BlogTag> tagList = blogTagMapper.getByBlogIds(blogListIds);
//
//        List<BlogVo> blogVoList = list.stream().map(x -> {
//            BlogVo blogVo = new BlogVo();
//            typeList.forEach(type -> {
//                if (x.getBlogType().equals(type.getTypeId())){
//                    blogVo.setTypeName(type.getTypeName());
//                }
//            });
//            return blogVo;
//        }).map(y-> {
//                    BlogVo blogVo = new BlogVo();
//                    tagList.forEach(blogTag -> {
//                        ArrayList<String> strings = new ArrayList<>();
//                        if (blogTag.getBlogId()==y.getBlogId()){
//                            strings.add(blogTag.getTagName());
//                        }
//                        blogVo.setTagList(strings);
//                    });
//                    return blogVo;
//                }
//        ).collect(Collectors.toList());
//        page.setList(blogVoList);
//        return page;
    }

    @Override
    public Blog getUpdate(Long id) {
        return blogMapper.getUpdate(id);
    }

    @Override
    public BlogVo getInfo(Long id) {
        Blog blog = blogMapper.getInfo(id);
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        Type type = typeMapper.get(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    @Override
    public List<Blog> getTime(Integer index) {
        return blogMapper.getTime(index);

    }


    private void saveTag(long blogId, List<Tag> tagList) {
        if (!CollectionUtils.isEmpty(tagList)) {
            tagList.stream().filter(e -> e.getId() == null).collect(Collectors.toList());
            tagMapper.saveBatch(tagList);
            List<BlogTag> blogTagList = tagList.stream().map(e -> {
                BlogTag blogTag = new BlogTag();
                blogTag.setId(idWorker.nextId());
                blogTag.setTagId(e.getId());
                blogTag.setBlogId(blogId);
                blogTag.setTagName(e.getName());
                blogTag.setTagId(e.getId());
                return blogTag;
            }).collect(Collectors.toList());
            blogTagMapper.saveBatch(blogTagList);
        }
    }
}

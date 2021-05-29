package com.qzy.service.impl;

import com.qzy.mapper.BlogTagMapper;
import com.qzy.mapper.TagMapper;
import com.qzy.pojo.BlogTag;
import com.qzy.pojo.Tag;
import com.qzy.pojo.vo.HotTagVo;
import com.qzy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public List<Tag> getByName(String name) {
        List<Tag> list = tagMapper.getByName(name);
//        Set<Tag> set = new HashSet<>();
//        list.forEach(
//                tag -> {
//                    if (set.isEmpty())
//                        set.add(tag);
//                    set.forEach(
//                            tagSet -> {
//                                if (!tag.getName().equals(tagSet.getName())) {
//                                    set.add(tagSet);
//                                }
//                            }
//                    );
//                }
//        );
        return list.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Tag::getName))), ArrayList::new)
                );
    }

    @Override
    public List<Tag> getByBlogId(Long blogId) {
        List<BlogTag> list = blogTagMapper.getByBlogId(blogId);
        List<Tag> tagList = list.stream().map(x -> {
            Tag tag = new Tag();
            tag.setId(x.getTagId());
            tag.setName(x.getTagName());
            return tag;
        }).collect(Collectors.toList());
        return tagList;
    }

    @Override
    public List<HotTagVo> getHotTag() {

        return blogTagMapper.getHotTag();
    }
}

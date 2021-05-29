package com.qzy.service;

import com.qzy.pojo.Tag;
import com.qzy.pojo.vo.HotTagVo;

import java.util.List;

public interface TagService {
    List<Tag> getByName(String name);

    List<Tag> getByBlogId(Long blogId);

    List<HotTagVo> getHotTag();

}

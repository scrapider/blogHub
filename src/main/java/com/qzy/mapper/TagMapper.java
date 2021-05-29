package com.qzy.mapper;


import com.qzy.pojo.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TagMapper {
    List<Tag> getByName(String name);

    void saveBatch(List<Tag> list);

}

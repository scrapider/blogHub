package com.qzy.mapper;


import com.qzy.pojo.Music;
import com.qzy.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MusicMapper {
    List<Music> getList(Page<Music> page);

    Integer getTotalCount(Page<Music> page);

    void delete(Long id);

    void update(Music music);

    Music get(Long id);

    void save(Music music);

    void updateEnable(Music music);

    List<Music> getAll();

}

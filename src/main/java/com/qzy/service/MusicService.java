package com.qzy.service;

import com.qzy.pojo.Music;
import com.qzy.utils.Page;

import java.util.List;

public interface MusicService {
    Page<Music> getByPage(Page<Music> page);

    void delete(Long id);

    void update(Music music);

    Music get(Long id);

    void save(Music music);

    void toggle(Music music);

    List<Music> getAll();
}

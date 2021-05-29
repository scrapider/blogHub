package com.qzy.service.impl;

import com.qzy.mapper.MusicMapper;
import com.qzy.pojo.Music;
import com.qzy.service.MusicService;
import com.qzy.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;


    @Override
    public Page<Music> getByPage(Page<Music> page) {
        List<Music> list = musicMapper.getList(page);
        Integer count = musicMapper.getTotalCount(page);
        page.setList(list);
        page.setTotalCount(count);
        return page;

    }

    @Override
    public void delete(Long id) {
        musicMapper.delete(id);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    public Music get(Long id) {
        Music music = musicMapper.get(id);
        return music;
    }

    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void toggle(Music music) {
        musicMapper.updateEnable(music);
    }

    @Override
    public List<Music> getAll() {

        return musicMapper.getAll();
    }
}

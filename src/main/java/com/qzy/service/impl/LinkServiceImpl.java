package com.qzy.service.impl;

import com.qzy.mapper.LinkMapper;
import com.qzy.pojo.Link;
import com.qzy.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public void delete(Long id) {
        linkMapper.delete(id);
    }

    @Override
    public void save(Link link) {
        linkMapper.save(link);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    @Override
    public List<Link> getAll() {
        List<Link> list = linkMapper.getAll();
        return list;
    }

    @Override
    public Link get(Long id) {
        Link link = linkMapper.get(id);
        return link;
    }
}

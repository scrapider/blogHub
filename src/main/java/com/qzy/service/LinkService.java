package com.qzy.service;

import com.qzy.pojo.Link;

import java.util.List;

public interface LinkService {
    void delete(Long id);

    void save(Link link);

    void update(Link link);

    List<Link> getAll();

    Link get(Long id);
}

package com.qzy.mapper;

import com.qzy.pojo.Link;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface LinkMapper {


    Link get(Long id);

    List<Link> getAll();

    void update(Link link);

    void save(Link link);

    void delete(Long id);
}

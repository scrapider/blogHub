package com.qzy.mapper;

import com.qzy.pojo.Type;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SysTypeMapper {
    void save(Type type);

    Type get(Long id);

    List<Type> getAll();

    void delete(Long id);

    void update(Type type);

    List<Type> getByIds(List<Long> typeListId);
}

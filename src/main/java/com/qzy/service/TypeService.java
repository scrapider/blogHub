package com.qzy.service;

import com.qzy.pojo.Type;

import java.util.List;

public interface TypeService {

    void save(Type type);

    Type get(Long id);

    List<Type> getAll();

    void delete(Long id);

    void update(Type type);
}

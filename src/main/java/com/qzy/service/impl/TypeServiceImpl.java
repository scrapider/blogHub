package com.qzy.service.impl;

import com.qzy.mapper.BlogMapper;
import com.qzy.mapper.SysTypeMapper;
import com.qzy.pojo.Type;
import com.qzy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private SysTypeMapper sysTypeMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void save(Type type) {
        sysTypeMapper.save(type);

    }

    @Override
    public Type get(Long id) {
        return sysTypeMapper.get(id);
    }

    @Override
    public List<Type> getAll() {
        List<Type> typeList = sysTypeMapper.getAll();
        List<Type> countList = blogMapper.getTypeCount();
        typeList.stream().forEach(e -> {
            Type type = countList.stream().filter(t -> {
                return t.getTypeId().equals(e.getTypeId());
            }).findFirst().orElse(new Type());
            if (type.getTypeBlogCount() == null) {
                e.setTypeBlogCount(0);
            } else {
                e.setTypeBlogCount(type.getTypeBlogCount());
            }
        });
        return typeList;
    }

    @Override
    public void delete(Long id) {
        sysTypeMapper.delete(id);
    }

    @Override
    public void update(Type type) {
        sysTypeMapper.update(type);
    }
}

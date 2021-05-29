package com.qzy.service.impl;

import com.qzy.mapper.UserMapper;
import com.qzy.pojo.User;
import com.qzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void updatePwd(User user) {
        userMapper.updatePwd(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getByUserName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public List<String> getNames() {
        return userMapper.getNames();
    }


}
